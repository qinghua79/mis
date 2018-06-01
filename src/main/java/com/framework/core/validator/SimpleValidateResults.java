package com.framework.core.validator;

import java.util.ArrayList;
import java.util.List;

public class SimpleValidateResults implements ValidateResults {
    
	private List<ValidateResults.Result> results = new ArrayList<>();
	
	public SimpleValidateResults addResult(String field,String message){
		results.add(new Result(field, message));
		return this;
	}
	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return results == null || results.isEmpty();
	}

	@Override
	public List<ValidateResults.Result> getResults() {

		return results;
	}
	
	
	class Result implements ValidateResults.Result{
		  private  String field;
		  private  String message;
		  
		  public Result(String field, String message){
			  this.field = field;
			  this.message = message;
		  }

		@Override
		public String getField() {
			return this.field;
		}

		@Override
		public String getMessage() {
			 return this.message;
		}

		@Override
		public String toString() {
			 return "{" +
	                    "\"field\":\"" + field + '\"' +
	                    ", \"message:\"" + message + '\"' +
	                 '}';
		}
		  
		  
		
		
	}
	

}
