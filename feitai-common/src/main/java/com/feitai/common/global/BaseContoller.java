package com.feitai.common.global;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.feitai.common.constant.Constants;
import com.feitai.common.model.UserEObj;

/**
 * <B>Contoller层基类</B>
 * @author chenzg
 * @since 2015-07-23
 */
public class BaseContoller {
	protected static final Log log = LogFactory.getLog(BaseContoller.class);
	
	/**
	 * 获取session中得UserEObj
	 * @param request
	 * @return
	 */
	protected UserEObj getSessionUser(HttpServletRequest request){
		return (UserEObj)request.getSession().getAttribute(Constants.USER_CONTEXT);
	}
	
	/**
	 * 将UserEObj保存到session中
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request,UserEObj user){
		request.getSession().setAttribute(Constants.USER_CONTEXT,user);
	}
	
	/**
	 * 获取基于应用程序的url绝对路径
	 * @param request
	 * @param url
	 * @return
	 */
	public final String getAppBaseUrl(HttpServletRequest request,String url){
		Assert.hasLength(url,"url不能为空");
		Assert.isTrue(url.startsWith("/"),"必须以/打头");
		return request.getContextPath() + url;
	}
}
