package com.framework.dao;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

public interface QueryMapper<T>
	extends Marker,
    SelectOneMapper<T>,
    tk.mybatis.mapper.common.base.select.SelectMapper<T>,
    SelectAllMapper<T>,
    SelectCountMapper<T>,
    SelectByPrimaryKeyMapper<T>,
    ExistsWithPrimaryKeyMapper<T>,
    SelectByIdsMapper<T>,
    SelectByConditionMapper<T>,
    SelectCountByConditionMapper<T>,
    SelectByExampleMapper<T>{ 
}
