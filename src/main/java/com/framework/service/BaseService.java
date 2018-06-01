package com.framework.service;

import java.util.Set;
import java.util.function.Supplier;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.core.exception.ValidationException;
import com.framework.core.validator.SimpleValidateResults;
import com.framework.entity.BaseEntity;

public class BaseService<E extends BaseEntity, PK>{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	   
    @Autowired(required = false)
	private Validator validator;
 
    private <T> void validate(Supplier<Set<ConstraintViolation<T>>> validatorSetFunction) {
    
    	if (validator == null) {
            logger.warn("validator is null!");
    		return;
        }
    	
    	SimpleValidateResults results = new SimpleValidateResults();
    	validatorSetFunction.get()
    	     .forEach(v -> results.addResult(v.getPropertyPath().toString(), v.getMessage()));
    	
    	if ( !results.isSuccess()){
    		throw new ValidationException(results);
    	}
    	
    }
    
    protected void tryValidate(Object data, String property, Class... groups) {
        validate(() -> validator.validateProperty(data, property, groups));
    }

    protected <T> void tryValidate(Class<T> type, String property, Object value, Class... groups) {
        validate(() -> validator.validateValue(type, property, value, groups));
    }

    protected void tryValidate(Object data, Class... groups) {
        validate(() -> validator.validate(data, groups));
    }
	
}
