package com.framework.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.framework.core.common.ContextHolder;

public class SpringUtils {
	public static final String SCOPE_REQUEST = "requestContextHolder";
	public static final String SCOPE_SESSION = "sessionContextHolder";
	public static final String SCOPE_APPLICATION = "applicationContextHolder";
	
	private static ApplicationContext applicationContext;
	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}
	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null){
			return new ClassPathXmlApplicationContext("classpath:spring.xml");
		}
		return applicationContext;
	}
    public static <T> T getBean(String beanId){
    	return (T) getApplicationContext().getBean(beanId);
    }
    public static void put(String key, Object value, String scopeBeanId){
    	ContextHolder contextHolder = (ContextHolder) getBean(scopeBeanId);
    	if (contextHolder != null){
			contextHolder.put(key, value);
		}
    }
    public static <T> T get(String key, String scopeBeanId) {
    	ContextHolder contextHolder = (ContextHolder) getBean(scopeBeanId);
		if (contextHolder != null){
			return (T) contextHolder.get(key);
		}
		return null;
    }
    public static void clear(String scopeBeanId) {
    	ContextHolder contextHolder = (ContextHolder) getBean(scopeBeanId);
		if (contextHolder != null){
			contextHolder.clear();
	    }
    }
}
