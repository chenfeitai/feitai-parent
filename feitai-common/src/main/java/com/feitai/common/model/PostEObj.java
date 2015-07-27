package com.feitai.common.model;

import com.feitai.common.global.EObject;

/**
 * <b>回帖类</b>
 * @since 2015-07-17
 * @author chenzhigang
 */
public class PostEObj extends EObject{
	private static final long serialVersionUID = 8675036365816000198L;
	/** 回复类型*/
	private String postType;
	/** 回复标题*/
	private String postTitle;
	/** 回复内容*/
	private String postText;
	
	/** 所属版块*/
	private String boardId;
	private BoardEObj board;
	
	/** 所属主题*/
	private String topicId;
	private TopicEObj topic;
	
	/** 所属用户*/
	private String userId;
	private UserEObj user;
	
	//创建时间
	
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	public TopicEObj getTopic() {
		return topic;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopic(TopicEObj topic) {
		this.topicId = topic.getId();
		this.topic = topic;
	}
	public UserEObj getUser() {
		return user;
	}
	public String getUserId(){
		return userId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUser(UserEObj user) {
		this.userId = user.getId();
		this.user = user;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public BoardEObj getBoard() {
		return board;
	}
	public void setBoard(BoardEObj board) {
		this.boardId = board.getId();
		this.board = board;
	}
}
