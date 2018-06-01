package com.framework.entity;

public interface CloneableEntity<PK> extends BaseEntity<PK>, Cloneable {
	   CloneableEntity clone();
}
