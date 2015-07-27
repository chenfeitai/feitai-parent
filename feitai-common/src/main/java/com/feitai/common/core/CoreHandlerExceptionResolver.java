package com.feitai.common.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.feitai.common.exception.FtException;

/**
 * <b>基于SpringMVC HandlerExceptionResolver接口的异常处理类</b>
 * 
 * @author chenzhigang
 * @since 2015-06-09
 */
public class CoreHandlerExceptionResolver implements HandlerExceptionResolver {
	private static final Log log = LogFactory.getLog(HandlerExceptionResolver.class);

	@Autowired
	private MessageSource messageSource;
	
	@Autowired(required=false) 
	private String defaultErrorView = "error/err";
	
	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
	

	public ModelAndView resolveException(
			HttpServletRequest request,
			HttpServletResponse response, Object object,
			Exception exception) {
		ExceptionResolver.resolveException(exception, request.getLocale(),messageSource);
		if(exception instanceof FtException){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			FtException fe = (FtException)exception;
			PrintWriter pw = null;
			try {
				StringBuffer sb = new StringBuffer();
				sb.append("[{\"messageKey\":\"")
					.append(fe.getMessageKey())
						.append("\",\"message\":\"")
							.append(fe.getMessageKey()+fe.getArgs()[0])
								.append("\"}]");
				pw = response.getWriter();
		        pw.write(sb.toString());
				pw.flush();
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
			return null;
		}else{
			return new ModelAndView(defaultErrorView).addObject("message", exception.getMessage());
		}
	}
}
