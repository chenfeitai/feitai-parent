package com.feitai.web.test;
 
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feitai.common.model.BoardEObj;
import com.feitai.common.model.MainPostEObj;
import com.feitai.common.model.PostEObj;
import com.feitai.common.model.TopicEObj;
import com.feitai.web.service.impl.ForumService;
import com.feitai.web.service.impl.UserService;

public class ForumTest {
 
	private ForumService service;
	
	private UserService userService;
	
    @Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
        		new String[]{"classpath:conf/spring-common.xml"});
        service = (ForumService) context.getBean("forumService");
        userService = (UserService) context.getBean("userService");
    }
    
    @Test
    public void addBoradTest(){
    	BoardEObj board = new BoardEObj();
    	board.setName("宝宝淘");
    	board.setTopicNum(0);
    	service.addBoard(board);
    }
     
    @Test
    public void addTopicTest(){
    	TopicEObj topic = new TopicEObj();
    	topic.setBoardId("201507240000001");
    	topic.setUser(userService.getUserByName("admin"));
    	topic.setTopicTitle("宝宝淘");
    	MainPostEObj mainPost = new MainPostEObj();
    	topic.setMainPost(mainPost);
    	mainPost.setPostText("一楼度娘!!!");
    	service.addTopic(topic);
    }
    
    @Test
    public void addPostTest(){
    	for (int i = 1; i < 11; i++) {
	    	PostEObj post = new PostEObj();
	    	post.setBoardId("201507240000001");
	    	post.setPostText(i+"L沙发");
	    	post.setPostTitle(i+1+"L板凳");
	    	post.setUser(userService.getUserByName("admin"));
	    	post.setTopicId("201507240000526");
	    	service.addPost(post);
    	}
    }
    
    @Test
    public void removeTopicTest(){
    	service.removeTopic("201507240000526");
    }
}