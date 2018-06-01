package com.framework.core.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.framework.core.util.SpringUtils;

public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent context) {

		SpringUtils.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(context.getServletContext()));
        try{
        	//CacheManage.init();
        } catch(Exception e){
        	e.printStackTrace();
        }
	}

	@Override
	public void contextDestroyed(ServletContextEvent context) {
        try{
        	//CacheManage.destroy();
        } catch (Exception e){
        	e.printStackTrace();
        }

	}

}
