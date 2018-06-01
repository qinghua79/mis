package com.framework.controller.message;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "响应结果")
public class ResponseMessage<T> implements Serializable {
     
	protected int status;
	protected String message;
	protected T data;
	private Long timestamp;
	
	public static<T> ResponseMessage<T> error(String message){
		return error(500, message);
	}
	
	public static<T> ResponseMessage<T> error(int status, String message){
		  ResponseMessage<T> msg = new ResponseMessage<T>();
		  msg.message = message;
	      msg.status(status);
	      return msg.putTimeStamp();
	}
	public static<T> ResponseMessage<T> ok(){
		return ok(null);
	}
	
	public static<T>  ResponseMessage<T> ok(T data){
		   return new ResponseMessage<T>()
	                .data(data)
	                .putTimeStamp()
	                .status(200);
	}
	
	 private ResponseMessage<T> putTimeStamp() {
	        this.timestamp = System.currentTimeMillis();
	        return this;
	 }
	 public ResponseMessage<T> status(int status) {
	        this.status = status;
	        return this;
	 } 
	  public ResponseMessage<T> data(T data) {
	        this.data = data;
	        return this;
	    }

    @ApiModelProperty(value = "状态码", required = true)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@ApiModelProperty("调用结果消息")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
   
	@ApiModelProperty("成功时响应数据")
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}  
	 
	  
	  
}
