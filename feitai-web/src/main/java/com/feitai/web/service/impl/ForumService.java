package com.feitai.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feitai.common.constant.Constants;
import com.feitai.common.constant.ErrorConstants;
import com.feitai.common.exception.FtException;
import com.feitai.common.id.IdFactory;
import com.feitai.common.id.IdFormat;
import com.feitai.common.model.BoardEObj;
import com.feitai.common.model.MainPostEObj;
import com.feitai.common.model.PostEObj;
import com.feitai.common.model.TopicEObj;
import com.feitai.common.model.UserEObj;
import com.feitai.web.dao.BoardDAO;
import com.feitai.web.dao.PostDAO;
import com.feitai.web.dao.TopicDAO;
import com.feitai.web.dao.UserDAO;

@Transactional(rollbackFor = Exception.class)
@Service
public class ForumService {
	@Autowired
	private TopicDAO topicDAO;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private IdFactory idSeqNoGenerator;
	
	/**
	 * 发表一个主题帖子,用户积分加10,论坛版块主题帖数加1
	 * @param topic
	 */
	public void addTopic(TopicEObj topic){
		topic.setId(idSeqNoGenerator.generate().toString());
		topic.setCreateTime(new Date());
		topic.setLastPostDt(new Date());
		topicDAO.insert(topic);
		
		BoardEObj board = boardDAO.get(topic.getBoardId());
		board.setTopicNum(board.getTopicNum() + 1);
		boardDAO.update(board);
		
		//创建主题帖子
		topic.getMainPost().setTopic(topic);
		MainPostEObj post = topic.getMainPost();
		post.setCreateTime(new Date());
		post.setUser(topic.getUser());
		post.setPostTitle(topic.getTopicTitle());
		post.setBoardId(topic.getBoardId());
		post.setTopic(topic);
		post.setUser(topic.getUser());
		post.setId(idSeqNoGenerator.generate().toString());
		post.setPostType(Constants.POST_TYPE_MAIN);
		//持久化主题帖子
		postDAO.insert(post);
		
		//更新用户积分
		UserEObj user = topic.getUser();
		user.setCredits(user.getCredits() + 10);
		userDAO.update(user);
	}
	
	/**
	 * 删除一个主题贴,用户积分减50,论坛版块主题帖数减1(删除主题帖所有关联得帖子)
	 * @param topicId
	 */
	public void removeTopic(String topicId){
		TopicEObj topic = topicDAO.get(topicId);

		//将论坛版块的主题帖数减1
		BoardEObj board = boardDAO.get(topic.getBoardId());
		board.setTopicNum(board.getTopicNum() - 1);
		boardDAO.update(board);
		
		//发表该主题帖用户扣除50积分
		UserEObj user = userDAO.get(topic.getUserId());
		user.setCredits(user.getCredits() - 50);
		userDAO.update(user);
		
		//删除主题贴及其关联得帖子
		topicDAO.delete(topic);
		postDAO.deleteTopicPosts(topicId);
	}
	
	/**
	 * 添加一个回复帖子,用户积分加5分,主题贴纸回复加1,并更新最后回复时间
	 * @param post
	 */
	public void addPost(PostEObj post){
		post.setId(idSeqNoGenerator.generate().toString());
		post.setCreateTime(new Date());
		post.setPostType(Constants.POST_TYPE_ORDINARY);
		postDAO.insert(post);
		UserEObj user = post.getUser();
		user.setCredits(user.getCredits() + 5);
		userDAO.update(user);
		
		TopicEObj topic = topicDAO.get(post.getTopicId());
		topic.setReplies(topic.getReplies() + 1);
		topic.setLastPostDt(new Date());
		topicDAO.update(topic);
	}
	
	/**
	 * 删除一个回复帖子,发表回复帖子得用户积分减20,主题帖得回复数减1
	 * @param postId
	 */
	public void removePost(String postId){
		PostEObj post = postDAO.get(postId);
		postDAO.delete(post);
		
		TopicEObj topic = topicDAO.get(post.getTopicId());
		topic.setReplies(topic.getReplies() - 1);
		topicDAO.update(topic);
		
		UserEObj user = post.getUser();
		user.setCredits(user.getCredits() - 20);
		userDAO.update(user);
	}
	
	/**
	 * 创建新论坛版块
	 * @param board
	 */
	public void addBoard(BoardEObj board){
		board.setId(idSeqNoGenerator.generate().toString());
		boardDAO.insert(board);
	}
	
	/**
	 * 删除新论坛版块
	 * @param boardId
	 */
	public void removeBoard(String boardId){
		boardDAO.delete(boardDAO.get(boardId));
	}
	
	/**
	 * 将帖子设置为精华主题帖
	 * @param topicId
	 */
	public void makeDigesTopic(String topicId){
		TopicEObj topic = topicDAO.get(topicId);
		topic.setDigest(Constants.TOPIC_DIGEST_1);
		UserEObj user = topic.getUser();
		user.setCredits(user.getCredits() + 100);
	}
	/**
	 * 添加论坛版块管理员
	 * @param boardId
	 * @param userName
	 * @throws FtException
	 */
	public void addBoardManager(String boardId,String userName) throws FtException{
		UserEObj user = userDAO.queryUserByName(userName);
		if(user == null){
			throw new FtException(ErrorConstants.W_USER_NOT_EXISTS);
		}else{
			BoardEObj board = boardDAO.get(boardId);
			user.getManBoards().add(board);
			userDAO.update(user);
		}
				
	}
}
