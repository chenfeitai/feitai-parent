package com.feitai.web.dao;

import com.feitai.common.global.IDAO;
import com.feitai.common.model.BoardEObj;
import com.feitai.common.model.UserEObj;



public interface BoardDAO extends IDAO<BoardEObj>{
    /** 获取论坛版块数目*/
	public Long queryBoardCount();
}