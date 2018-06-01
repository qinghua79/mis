package com.framework.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.core.common.JacksonObjectMapper;



public class JsonUtils {
    private JsonUtils(){}
	
    private static class JacksonHolder {
        private static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }
    
    private static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }
    public static String toJson(Object object){
    	
    	try {
			return getInstance().writeValueAsString(object);
		} catch (JsonProcessingException e) {
	
			throw new RuntimeException(e);
		}
    	
  
    	
    }
    
    public static<T> T parse(String jsonString, Class<T> value){
    	
    	return null;
    }
}
