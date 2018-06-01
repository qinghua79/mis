package com.framework.entity;

import java.util.Date;

public interface RecordCreationEntity<PK> extends BaseEntity<PK> {
       
	Date getCreateTime();

	void setCreateTime(Date createTime);

	Date getUpdateTime();

	void setUpdateTime(Date updateTime);
	
}
