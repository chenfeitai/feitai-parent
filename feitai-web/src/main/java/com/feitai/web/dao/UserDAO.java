package com.feitai.web.dao;

import com.feitai.common.global.IDAO;
import com.feitai.common.model.UserEObj;



public interface UserDAO extends IDAO<UserEObj>{
    public UserEObj queryUserByName(String userName);
    
    public int queryMatchCount(UserEObj user);
}