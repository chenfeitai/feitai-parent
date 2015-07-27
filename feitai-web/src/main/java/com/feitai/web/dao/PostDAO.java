package com.feitai.web.dao;

import com.feitai.common.global.IDAO;
import com.feitai.common.model.PostEObj;



public interface PostDAO extends IDAO<PostEObj>{
	public int deleteTopicPosts(String topicId);
}