package com.framework.entity;

import java.util.List;

import com.google.common.collect.Lists;

public interface TreeEntity<PK> extends BaseEntity<PK> {
	
	 PK getParentId();

	 void setParentId(PK parentId);
	 
	 <T extends TreeEntity> List<T> getChildren();
	 
	 
	
}
