package com.framework.core.exception;
/*
 * 应用级异常,可控（业务人员误操作产生的异常，如用户不存在、密码错误、用户名已经存在等）
 * 
 */
public class AppException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	public AppException(){
		
	}
	public AppException(Throwable cause){
		super(cause);
	}
	
	public AppException(String message, Throwable cause){
		super(message,cause);
	}
	public AppException(String message){
		super(message);
	}
	
}
