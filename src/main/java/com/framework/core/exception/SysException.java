package com.framework.core.exception;

public class SysException extends RuntimeException {

	/**
	 *    系统级异常（开发人员误操作或系统本身产生的异常，如数据库连接失败，空指针错误）的基类，
	 *  是一个不受控异常基类。 可不用捕捉或传throws方式传递下去。
	 */
	private static final long serialVersionUID = 1L;
	
	public SysException(){
		
	}
    public SysException(Throwable cause){
    	super(cause);
    }

	public SysException(String message, Throwable cause) {
		super(message, cause);
	}

	public SysException(String message) {
		super(message);
	}
}
