package com.framework.core.exception;

import java.util.List;

import com.framework.core.validator.SimpleValidateResults;
import com.framework.core.validator.ValidateResults;

public class ValidationException extends RuntimeException {
	
      private ValidateResults results;
      
      public ValidationException(String message, String field){
    	  results =  new SimpleValidateResults().addResult(field, message);
      }
      
      public ValidationException(ValidateResults results){
    	  this.results = results;
      }
      
      public List<ValidateResults.Result> getResults() {
    	  if (results == null){
    		  return null;
    	  }
    	  return results.getResults();
      }
}


