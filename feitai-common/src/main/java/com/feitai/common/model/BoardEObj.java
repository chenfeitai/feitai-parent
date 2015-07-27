package com.feitai.common.model;

import com.feitai.common.global.EObject;

/**
 * <b>论坛版块类</b>
 * @since 2015-07-17
 * @author chenzhigang
 */
public class BoardEObj extends EObject{
	private static final long serialVersionUID = 5807033474625775517L;
	/** 版块名称*/
	private String name;
	/** 版块描述*/
	private String desc;
	/** 主题数*/
	private int topicNum;
	/** 帖子*/
	private MainPostEObj mainPost;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public MainPostEObj getMainPost() {
		return mainPost;
	}
	public void setMainPost(MainPostEObj mainPost) {
		this.mainPost = mainPost;
	}
}
