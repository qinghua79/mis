package com.framework.core.exception;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONException;
import com.framework.controller.message.ResponseMessage;
import com.framework.core.validator.ValidateResults;
import com.framework.core.validator.SimpleValidateResults;
import com.framework.core.exception.AppException;

//@RestControllerAdvice
public class RestControllerExceptionTranslator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ExceptionHandler(JSONException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
	ResponseMessage handleException(JSONException exception){
		logger.error("json error", exception);
		return ResponseMessage.error(400, exception.getMessage());
	}
    
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseMessage<List<ValidateResults.Result>> handleException(ValidationException exception){
    	return null; //hibernate validation& Service  &Method
    }
	

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ResponseMessage handleException(NotFoundException exception){
        logger.error(exception.getMessage(), exception);
    	return ResponseMessage.error(404, exception.getMessage());
	}
    
    //unAuthorizedException,AccessDenyException
    
    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage handleException(AppException exception){
    	if (exception.getCause() != null ){
    		logger.error("{}:{}", exception.getMessage(), exception.getCause());
    	}
    	return ResponseMessage.error(500, exception.getMessage());
	}
    
    
    
    //通用
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage  handleException(RuntimeException exception){
    	logger.error(exception.getMessage(), exception);
    	return ResponseMessage.error(500, exception.getMessage());
    }
    
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage handleException(NullPointerException exception){
    	logger.error(exception.getMessage(), exception);
    	return ResponseMessage.error(500,  "服务器内部错误");
    }
    
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage  handleException(SQLException exception){
    	logger.error(exception.getMessage(), exception);
    	return ResponseMessage.error(500,  "服务器内部错误");
    }
    
    		
    	
}
    
    
    
    
