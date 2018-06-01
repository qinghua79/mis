package com.framework.dao.sys;

import com.framework.dao.CrudMapper;
import com.framework.entity.sys.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends CrudMapper<User>{

}