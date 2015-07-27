package com.feitai.common.model;

import java.util.Date;

import com.feitai.common.global.EObject;

/**
 * <b>论坛主题类</b>
 * @since 2015-07-17
 * @author chenzhigang
 */
public class TopicEObj extends EObject {
	private static final long serialVersionUID = -6162463262573273108L;
	/** 主题标题*/
	private String topicTitle;
	/** 最后回帖时间*/
	private Date lastPostDt;
	/** 浏览数*/
	private int views;
	/** 回复数*/
	private int replies;
	/** 是否加精*/
	private String digest;
	
	/** 主题对应版块*/
	private String boardId;
	private BoardEObj board;
	
	/** 主题创建用户*/
	private String userId;
	private UserEObj user;
	
	/** 楼主贴 */
	private MainPostEObj mainPost;
	
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public Date getLastPostDt() {
		return lastPostDt;
	}
	public void setLastPostDt(Date lastPostDt) {
		this.lastPostDt = lastPostDt;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getReplies() {
		return replies;
	}
	public void setReplies(int replies) {
		this.replies = replies;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BoardEObj getBoard() {
		return board;
	}
	public void setBoard(BoardEObj board) {
		this.boardId = board.getId();
		this.board = board;
	}
	public String getUserId() {
		return userId;
	}
	public UserEObj getUser() {
		return user;
	}
	public void setUser(UserEObj user) {
		this.userId = user.getId();
		this.user = user;
	}
	public MainPostEObj getMainPost() {
		return mainPost;
	}
	public void setMainPost(MainPostEObj mainPost) {
		this.mainPost = mainPost;
	}
}
