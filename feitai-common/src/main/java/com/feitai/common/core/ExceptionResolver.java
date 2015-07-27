package com.feitai.common.core;

import java.util.Locale;

import jodd.util.StringUtil;

import org.springframework.context.MessageSource;

import com.feitai.common.exception.FtException;

/**
 * <b>异常信息国际化转换类</b>
 * 
 * @author chenzhigang
 * @since 2015-06-26
 */
public class ExceptionResolver {
	public static void resolveException(Exception exception , Locale locale , MessageSource messageSource) {
		if(exception instanceof FtException){
			FtException e = (FtException)exception;
			String message = messageSource.getMessage(e.getMessageKey(), e.getArgs(), null ,locale);
			if(!StringUtil.isEmpty(message)){
				StringBuffer sb = new StringBuffer();
				sb.append(message);
				e.setArgs(new Object[]{sb.toString()});
			}
		}
	}
}
