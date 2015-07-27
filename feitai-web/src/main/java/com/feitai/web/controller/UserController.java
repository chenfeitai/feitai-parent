package com.feitai.web.controller;
 
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.feitai.common.constant.ErrorConstants;
import com.feitai.common.exception.ValidationException;
import com.feitai.common.global.BaseContoller;
import com.feitai.common.model.UserEObj;
import com.feitai.web.service.impl.UserService;


@Controller
public class UserController extends BaseContoller{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){
		return "login";
	}
	
	@RequestMapping("/loginCheck")
    public ModelAndView index(HttpServletRequest request,UserEObj param) throws Exception{
    	boolean isExistSUser = userService.matchCount(param)>0;
    	if(isExistSUser){
    		UserEObj user = userService.getUserById(param.getId());
    		user.setLastIp(request.getRemoteAddr());
    		user.setLastVisitDt(new Date());
    		userService.loginSuccess(user);
    		request.getSession().setAttribute("user", user);
    		return new ModelAndView("main");
		}else{
			throw new ValidationException(ErrorConstants.W_USER_NOT_EXISTS);
		}
    }
     
}
