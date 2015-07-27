package com.feitai.common.model;

import java.util.Date;

import com.feitai.common.global.EObject;

/**
 * <b>登录日志类</b>
 * @since 2015-07-02
 * @author chenzhigang
 */
public class LoginLogEObj extends EObject {
	private static final long serialVersionUID = -6263684864611562089L;
	/** 用户号*/
	private String userId;
	/** 登录IP地址*/
	private String ip;
	/** 登录日期*/
	private Date loginDt;
	/** 用户*/
	private UserEObj user;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginDt() {
		return loginDt;
	}
	public void setLoginDt(Date loginDt) {
		this.loginDt = loginDt;
	}
	public UserEObj getUser() {
		return user;
	}
	public void setUser(UserEObj user) {
		this.user = user;
	}
}
