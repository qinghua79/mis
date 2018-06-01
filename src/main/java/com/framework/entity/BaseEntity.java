package com.framework.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public interface BaseEntity<PK> extends Serializable {

	 PK getId();

	 void setId(PK id); 
	 
	 
	 
/*
	
	@Override 
  	default int hashCode() {
  		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override 
	default boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	@Override 
	default String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
*/
}
