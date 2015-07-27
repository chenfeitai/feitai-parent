package com.feitai.common.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feitai.common.page.Page;

/**
 * <b>基于SpringMVC HandlerInterceptorAdapter拦截器的处理类</b>
 * 
 * @author chenzhigang
 * @since 2015-07-17
 */
public class CoreHandlerInterceptorAdapter extends HandlerInterceptorAdapter{
	/**
	 * 分页参数封装
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Map parameterMap= request.getParameterMap();
		if(parameterMap.containsKey("pageSize")){
			ThreadLocal<Page> _page=new ThreadLocal<Page>();
			//pageSize每页行数
			_page.set(Page.newBuilder2(Integer.valueOf((String) parameterMap.get("pageSize")), request));
			request.setAttribute("_page", _page);
		}
		return true;
	}
}
