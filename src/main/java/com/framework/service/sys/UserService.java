package com.framework.service.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.sys.UserMapper;
import com.framework.entity.param.PagerResult;
import com.framework.entity.sys.User;

import com.framework.model.sys.UserForm;
import com.framework.service.GenericService;



@Service
public class UserService extends GenericService<User,Long> {

	public User getByLoginName(String loginName) {
		User user =  new User();
		user.setLoginname(loginName);
		return this.crudMapper.selectOne(user);
	
	}
	
    
}
