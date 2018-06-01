package com.framework.core.common;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JacksonObjectMapper extends ObjectMapper {
	   public JacksonObjectMapper() {
	        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	    }
}
