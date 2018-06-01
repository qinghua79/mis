package com.framework.core.util;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

public class WebUtils {
     public static boolean isAjax(HandlerMethod handlerMethod){
    	 ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
    	 if (null != responseBody){
    		 return true;
    	 }
    	 
    	 RestController restController =  handlerMethod.getBeanType().getAnnotation(RestController.class);
    	 if (null != restController) {
 			return true;
 		 }
 
    	 return false;
     }
}
