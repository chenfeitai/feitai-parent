package com.feitai.common.constant;

/**
 * <b>公用常量</b>
 * @since 2015-06-05
 * @author chenzhigang
 *
 */
public class Constants {
	/** 默认日期格式:yyyyMMdd*/
	private static final String DEFAULT_DATE_PATTERN = "yyyyMMdd"; 
	/** 默认日期时间格式:yyyyMMddHHmmss*/
	private static final String DEFAULT_DT_PATTERN = "yyyyMMddHHmmss";
	
	/** ajax请求状态成功*/
	public static final String AJAX_SUCCESS="SUCCESS";
	/** ajax请求状态失败*/
	public static final String AJAX_FAILED="FAILED";
	
	/** 用户注册默认积分*/
	public static final int USER_REGISTER_CREDITS_DEF=100;
	
	/** 用户锁定状态 未锁定-0*/
	public static final String USER_UNlOCK="0";
	/** 用户锁定状态 锁定-1*/
	public static final String USER_lOCKED="1";
	
	/** 用户类型 管理员-0*/
	public static final String USER_TYPE_0="0";
	/** 用户类型 普通用户-1*/
	public static final String USER_TYPE_1="1";
	
	/** 放入上下文中的User对象KEY*/
	public static final String USER_CONTEXT="_user";
	
	/** 帖子精华级别  普通帖-0*/
	public static final String TOPIC_DIGEST_0="0";
	/** 帖子精华级别  精华帖-1*/
	public static final String TOPIC_DIGEST_1="1";
	/** 帖子精华级别  置顶加精帖-2*/
	public static final String TOPIC_DIGEST_2="2";
	
	/** 帖子类型 楼主帖-0*/
	public static final String POST_TYPE_MAIN="0";
	/** 帖子类型 普通回帖-1*/
	public static final String POST_TYPE_ORDINARY="1";
}
