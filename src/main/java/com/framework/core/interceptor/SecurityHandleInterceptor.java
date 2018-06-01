package com.framework.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framework.core.common.SessionObject;
import com.framework.core.util.SpringUtils;

public class SecurityHandleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if (! url.endsWith("/login.jsp")){
			SessionObject sessionObject = SpringUtils.get(SessionObject.BEAN_ID, SpringUtils.SCOPE_SESSION);
			
			if (sessionObject == null){
				request.setAttribute("resultJSON", "{\"redirecturl\":\"/index.jsp\",\"msg\":\"session过期,请重新登录!\"}");
				request.getRequestDispatcher("/202.jsp").forward(request, response);
				return false;
			}
		}
		return true;
	}


}
