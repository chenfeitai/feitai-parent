package com.feitai.web.test;
 
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.feitai.common.exception.FtException;
import com.feitai.common.model.UserEObj;
import com.feitai.web.service.impl.UserService;

public class UserTest {
 
	private UserService service;
     
    @Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
        		new String[]{"classpath:conf/spring-common.xml"});
        service = (UserService) context.getBean("userService");
    }
     
    @Test
    public void register(){
		UserEObj user = new UserEObj();
        user.setName("admin");
        user.setPassword("123456");
        try {
			service.register(user);
		} catch (FtException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void getUserByName(){
        System.out.println(service.getUserByName("admin"));
    }
    
    @Test
    public void loginSuccess(){
        UserEObj eobj = (UserEObj) service.getUserByName("admin");
        UserService us = (UserService) service;
        us.loginSuccess(eobj);
    }
}