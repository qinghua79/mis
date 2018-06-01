package com.framework.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static Cookie setCookie(HttpServletResponse response, String name, String value, String path){
    	
    	return addCookie(response,name,value,path,60*30);
    	
    	
    }
	
    public static Cookie addCookie(HttpServletResponse response, String name, String value, String path, int maxAge){
    	Cookie cookie = null;
    	try{
    	   cookie = new Cookie(name, URLEncoder.encode(value,"UTF-8"));	
    	   cookie.setMaxAge(maxAge);
    	   if (null != path){
    		   cookie.setPath(path);
    	   }
    	} catch ( UnsupportedEncodingException e){
    		e.printStackTrace();
    	}
    	return cookie;
    	
    }
    
    public static ArrayList<Cookie> addCookies(HttpServletResponse response, Map<String,String> values, String path,int maxAge){
    	Set<Map.Entry<String, String>> entries = values.entrySet();
    	ArrayList<Cookie> cookies = new ArrayList<Cookie>();
    	try{
    		for (Map.Entry<String, String> entry: entries){
    			Cookie cookie = new Cookie(entry.getKey(),URLEncoder.encode(entry.getValue(),"UTF-8"));
    			cookie.setMaxAge(maxAge);
    			
    			if (null != path){
    				cookie.setPath(path);
    			}
    			
    			response.addCookie(cookie);
    			cookies.add(cookie);
    		}
    		
    	} catch (UnsupportedEncodingException e){
    		e.printStackTrace();
    	}
        return cookies;
    }
    
    public static String getCookie(HttpServletRequest request, String name){
    	return getCookie(request, null, name, false);
    }
	
    public static String getCookie(HttpServletRequest request,HttpServletResponse response, String name){
    	return getCookie(request,response,name, true);
    }
    public static String getCookie(HttpServletRequest request,HttpServletResponse response, String name,boolean isRemoved){
       String value = null;
       Cookie[] cookies = request.getCookies();
       if (cookies != null){
    	   for (Cookie cookie: cookies){
    		   if (cookie.getName().equals(name)){
    			   try{
    				   value = URLDecoder.decode(cookie.getValue(), "UTF-8");
    			   } catch (UnsupportedEncodingException e){
    				   e.printStackTrace();
    			   }
    			   if (isRemoved){
    				   cookie.setMaxAge(0);
    				   response.addCookie(cookie);
    			   }
    			   return value;
    		   }
    		   
    	   }
       }
      return value;	
    }
    
}
