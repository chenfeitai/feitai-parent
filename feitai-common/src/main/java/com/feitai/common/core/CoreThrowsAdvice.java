package com.feitai.common.core;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * <b>1.基于SpringAOP(非web项目) ThrowsAdvice接口的异常处理转换类</b><br>
 * <b>2.基于SpringAOP(非web项目) Aspect标记的异常处理转换类</b>
 * 
 * @author chenzhigang
 * @since 2015-07-07
 */
@Aspect
public class CoreThrowsAdvice{
	private static final Log log = LogFactory.getLog(CoreThrowsAdvice.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@AfterThrowing(value="execution(* com.feitai.**.job..*Job.*(..))",argNames="exception",throwing="exception")
	public void afterThrowing(Exception exception){
		ExceptionResolver.resolveException(exception, Locale.getDefault(), messageSource);
	}
}
