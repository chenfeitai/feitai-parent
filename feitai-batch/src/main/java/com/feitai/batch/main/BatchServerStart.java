package com.feitai.batch.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BatchServerStart {
	static Log log = LogFactory.getLog(BatchServerStart.class);
	
	public static void main(String[] args) {
			String[] configs = {
					"classpath:conf/spring-common.xml",
					"classpath:conf/batch-config.xml",
					"classpath:conf/batch-task.xml"
					};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
	}

}
