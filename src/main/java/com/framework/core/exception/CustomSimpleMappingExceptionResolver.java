package com.framework.core.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.framework.core.util.WebUtils;

@Component
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	   @Override  
	    protected ModelAndView doResolveException(HttpServletRequest request,  
	            HttpServletResponse response, Object handler, Exception ex) {
		   String viewName = determineViewName(ex, request);
		   
		   if (viewName != null){
			   if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
	                    .getHeader("X-Requested-With")!= null && request  
	                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
				     
				    // 如果不是异步请求  
	                // Apply HTTP status code for error views, if specified.  
	                // Only apply it if we're processing a top-level request.  
	                Integer statusCode = determineStatusCode(request, viewName);  
	                if (statusCode != null) {  
	                    applyStatusCodeIfPossible(request, response, statusCode);  
	                }  
	                return getModelAndView(viewName, ex, request); 
			   } else  {
				   try {  
	                    PrintWriter writer = response.getWriter();  
	                    writer.write(ex.getMessage());  
	                    writer.flush();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	                return null;
				   
			   }
			   
			   
		   } else {
			   return null;
			   
			   
		   }
		   
		   
		   
		   
	
		   
		   
		   
		   
	   }  
	
	  
}
