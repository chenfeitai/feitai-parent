package com.feitai.common.model;

import java.util.Date;
import java.util.Set;

import com.feitai.common.global.EObject;


/**
 * <b>用户类</b>
 * @since 2015-06-05
 * @author chenzhigang
 */
public class UserEObj extends EObject{
	private static final long serialVersionUID = 6335118664735316714L;
	/** 用户名*/
	private String name;
	/** 密码*/
	private String password;
	/** 用户类型*/
	private String type;
	/** 积分*/
	private int credits;
	/** 最后登录IP地址*/
	private String lastIp;
	/** 最后登录时间*/
	private Date lastVisitDt;
	/** 是否被锁定*/
	private String locked;
	/** 论坛版块组*/
	private Set manBoards;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastVisitDt() {
		return lastVisitDt;
	}
	public void setLastVisitDt(Date lastVisitDt) {
		this.lastVisitDt = lastVisitDt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Set getManBoards() {
		return manBoards;
	}
	public void setManBoards(Set manBoards) {
		this.manBoards = manBoards;
	}
}
