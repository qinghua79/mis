package com.framework.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.CrudMapper;
import com.framework.entity.BaseEntity;
import com.framework.entity.param.PageParam;
import com.framework.entity.param.PagerResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;



public class GenericService<E extends BaseEntity<PK>,PK> extends BaseService<E,PK> implements IService<E,PK> {

	protected Class<PK> primaryKeyType;
	
	protected Class<E> entityType;

	@Autowired
	protected CrudMapper<E> crudMapper;
	
	public GenericService(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityType = (Class<E>) pt.getActualTypeArguments()[0];
		primaryKeyType = (Class<PK>) pt.getActualTypeArguments()[1];
	}
	
	@Override
	public E createEntity() {
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public E selectByPk(PK pk) {
		if (null == pk){
			return null;
		}
		return crudMapper.selectByPrimaryKey(pk);
		
	}

	@Override
	public List<E> selectByPk(List<PK> pks) {
	    if (null == pks || pks.isEmpty()){
	    	return new ArrayList<>();
	    }
	    String idsStr = StringUtils.join(pks.toArray(),",");
		return crudMapper.selectByIds(idsStr);
	}


	@Override
	public PagerResult<E> selectPager(PageParam param) {
		Objects.requireNonNull(param, "param is  null");
		PagerResult<E> pagerResult = new PagerResult<>();
		    
		Page<E> page = PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
		try{
		Object condition = param.getCondition();
		
		if (null == condition){
			crudMapper.selectAll();
		} else if (condition instanceof Condition){
			crudMapper.selectByCondition(condition);
		} else if (condition instanceof Example){
			crudMapper.selectByExample(condition);
		} else if ( entityType.isInstance(condition)){
			crudMapper.select((E) condition);
		}
		} finally{
			page.close();
		}
		pagerResult.setTotal(page.getTotal());
		pagerResult.setRows(page.getResult());
		return pagerResult;
	}

	@Override
	public List<E> selectAll() {
		return crudMapper.selectAll();
	}
	
	
	@Override
	public PK insert(E record) {
		Objects.requireNonNull(record, "record is  null");
	    //updateTime,createTime,getId
		crudMapper.insert(record);
		return record.getId();
	}

	@Override
	public int updateByPk(PK pk, E record) {
		Objects.requireNonNull(pk,"pk is null");
		Objects.requireNonNull(record, "record is null");
		record.setId(pk);
		return crudMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPkSelective(PK pk, E record) {
		Objects.requireNonNull(pk, "pk is null");
		Objects.requireNonNull(record, "record is null");
		record.setId(pk);
		return crudMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int deleteByPk(PK pk) {
		Objects.requireNonNull(pk, "pk is  null");
		return crudMapper.deleteByPrimaryKey(pk);
	}

	
	@Override
	public int deleteByPk(String pks) {
		Objects.requireNonNull(pks, "pks is null");
        return  crudMapper.deleteByIds(pks);
	}
	
	@Override
	public PK saveOrUpdate(E record) {
		Objects.requireNonNull(record, "data is null");
		
		if (null != record.getId() && null !=this.selectByPk(record.getId())){
			this.updateByPk(record.getId(),record);
		} else {
			insert(record);
		}
		return record.getId();
	}



}
