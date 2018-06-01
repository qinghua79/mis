package com.framework.dao;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

public interface UpdateMapper<T> extends Marker,
UpdateByPrimaryKeyMapper<T>,
UpdateByPrimaryKeySelectiveMapper<T>,
UpdateByConditionMapper<T>,
UpdateByConditionSelectiveMapper<T>,
UpdateByExampleSelectiveMapper<T>{

}
