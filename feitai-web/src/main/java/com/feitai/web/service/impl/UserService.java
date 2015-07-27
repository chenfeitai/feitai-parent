package com.feitai.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feitai.common.constant.Constants;
import com.feitai.common.constant.ErrorConstants;
import com.feitai.common.exception.FtException;
import com.feitai.common.id.IdFactory;
import com.feitai.common.model.LoginLogEObj;
import com.feitai.common.model.UserEObj;
import com.feitai.web.dao.LoginLogDAO;
import com.feitai.web.dao.UserDAO;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserService{
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LoginLogDAO loginLogDAO;
	@Autowired
	private IdFactory idSeqNoGenerator;
	
	/**
	 * 查询匹配用户
	 * @param eobj
	 * @return
	 */
	public int matchCount(UserEObj eobj) {
		return userDAO.queryMatchCount(eobj);
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws FtException
	 */
	public void register(UserEObj user)throws FtException{
		UserEObj eobj = userDAO.queryUserByName(user.getName());
		if(eobj!=null){
			throw new FtException(ErrorConstants.W_USER_IS_EXISTS);
		}else{
			user.setCredits(Constants.USER_REGISTER_CREDITS_DEF);
			user.setType(Constants.USER_TYPE_1);
			user.setLocked(Constants.USER_UNlOCK);
			user.setId(idSeqNoGenerator.generate().toString());
			userDAO.insert(user);
		}
	}

	/**
	 * 锁定用户
	 * @param userName
	 */
	public void lockUser(String userName){
		UserEObj user = userDAO.queryUserByName(userName);
		user.setLocked(Constants.USER_lOCKED);
		userDAO.update(user);
	}
	
	/**
	 * 解锁用户
	 * @param userName
	 */
	public void unlockUser(String userName){
		UserEObj user = userDAO.queryUserByName(userName);
		user.setLocked(Constants.USER_UNlOCK);
		userDAO.update(user);
	}
	
	/**
	 * 登录成功记录日志
	 * @param user
	 */
	public void loginSuccess(UserEObj user) {
		user.setCredits(5 + user.getCredits());
		LoginLogEObj loginLog = new LoginLogEObj();
		loginLog.setUserId(user.getId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDt(user.getLastVisitDt());
		userDAO.update(user);
		loginLogDAO.insert(loginLog);
	}
	
	@Transactional(readOnly = true)
	public UserEObj getUserByName(String userName){
		return userDAO.queryUserByName(userName);
	}

	@Transactional(readOnly = true)
	public UserEObj getUserById(String userId){
		return userDAO.get(userId);
	}
}