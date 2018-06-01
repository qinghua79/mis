package com.framework.service;

import java.util.List;

import com.framework.entity.BaseEntity;
import com.framework.entity.param.PageParam;
import com.framework.entity.param.PagerResult;

public interface IService<E extends BaseEntity, PK> {
	E createEntity();

	E selectByPk(PK pk);

	List<E> selectByPk(List<PK> pks);
	
	PagerResult<E> selectPager(PageParam param);

	List<E> selectAll();
	
	PK insert(E record);

	int updateByPk(PK pk, E record);
	
	int updateByPkSelective(PK pk, E record);
	
	int deleteByPk(PK pk);

	int deleteByPk(String pks);
	
	PK saveOrUpdate(E record);
}
