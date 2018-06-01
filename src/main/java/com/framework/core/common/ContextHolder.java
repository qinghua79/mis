package com.framework.core.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContextHolder implements Serializable {
     private Map contexts;
     
     public ContextHolder(){
    	 this.contexts = new HashMap();
     }
     
     public Object get(String key){
    	 return this.contexts.get(key);
     }
     
     public void put(String key, Object value){
    	 this.contexts.put(key, value);
     }
     
     public void clear(){
    	 this.contexts.clear();
     }
}
