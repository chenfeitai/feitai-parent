package com.feitai.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>常用日期和字符串处理工具类</b>
 * @since 2015-07-27
 * @author chenzhigang
 *
 */
public class MiscUtil {
	private static ThreadLocal<Map<String, DateFormat>> dfCache = new ThreadLocal<Map<String, DateFormat>>(){
		protected Map<String, DateFormat> initialValue(){
			return new HashMap<String, DateFormat>();
		}
	};
	
	/**
	 * 根据日期-时间格式获取对应格式化和解析日期的具体类
	 * @param pattern 日期-时间格式
	 * @return
	 */
	public static DateFormat getDateFormat(String pattern) {
		Map<String, DateFormat> cache = dfCache.get();
		DateFormat df = cache.get(pattern);
		if(df == null){
			df = new SimpleDateFormat(pattern);
			cache.put(pattern, df);
		}
		return df;
	}
	
	public static String getLocalHostName(){
		try {
			InetAddress inetaddress = InetAddress.getLocalHost();
			return inetaddress.getHostName();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取当前时间
	 * @return {@link java.util.Date}
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * 获取当前时间
	 * @return {@link java.sql.Date}
	 */
	public static java.sql.Date getCurrentSqlDate(){
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前时间
	 * @return {@link java.sql.Timestamp}
	 */
	public static Timestamp getCurrentDT(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取对象String并去除空格
	 * @param obj
	 * @return
	 */
	public static String toStringAndTrim(Object obj){
		if(obj == null){
			return "";
		}else{
			return obj.toString().trim();
		}
	}
}
