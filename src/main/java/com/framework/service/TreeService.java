package com.framework.service;

import java.util.List;
import java.util.Objects;

import com.framework.controller.message.Tree;
import com.framework.entity.TreeEntity;
import com.framework.entity.param.TreeNode;


public class TreeService<E extends TreeEntity<PK>, PK> extends GenericService<E, PK> {

	public List<E> selectChildren(PK parentId) {
		Objects.requireNonNull(parentId, "parentId is null");
		try {
			E e = this.entityType.newInstance();
			e.setParentId(parentId);
			return this.crudMapper.select(e);
		} catch (InstantiationException | IllegalAccessException e) {
			// log.error("selectChildren occurs error, caused by: ", e);
			throw new RuntimeException("selectChildren occurs error", e);
		}
	}
	
	
  
}
