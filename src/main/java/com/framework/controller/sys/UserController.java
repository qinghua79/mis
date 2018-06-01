package com.framework.controller.sys;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.BaseController;
import com.framework.controller.message.ResponseMessage;
import com.framework.entity.sys.User;
import com.framework.model.sys.UserForm;
import com.framework.service.sys.UserService;

import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.framework.core.util.DateUtils;
import com.framework.entity.param.PageParam;
import com.framework.entity.param.PagerResult;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService  userService;
	
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据动态条件查询", responseReference = "get")
    public PagerResult<User>list(UserForm userForm) {
    	PageParam<Example>  pageParam = new PageParam(userForm.getPage(), userForm.getRows());
    	Example example = new Example(User.class);  
    	Criteria criteria = example.createCriteria();
    	if (null != userForm.getDepartid()){
    		criteria.andEqualTo("departid", userForm.getDepartid());
    	}
    	if ((null != userForm.getLoginname()) && (userForm.getLoginname().trim() != "")) {
    		criteria.andLike("loginname", userForm.getLoginname()+'%');
    	}    
    	pageParam.setCondition(example);
    	return userService.selectPager(pageParam);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新增或者修改")
    public ResponseMessage<Long> saveOrUpdate(UserForm userForm){
       return ResponseMessage.ok(userService.saveOrUpdate(modelToEntity(userForm)));
    } 
    
    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据主键查询")
    public ResponseMessage<User> get(@PathVariable("id") Long id){
    	 return ResponseMessage.ok(userService.selectByPk(id));
    }
    
    
    @RequestMapping(value="/user/{ids}", method = RequestMethod.DELETE)
    @ApiOperation("根据主键删除多条记录")
    @ResponseBody
    public ResponseMessage delete(@PathVariable("ids") String ids){
    	 return ResponseMessage.ok(userService.deleteByPk(ids));
    }
    
    public User modelToEntity(UserForm userForm){
    	  if (userForm != null) {
              User entity = new User();
              entity.setDepartid(userForm.getDepartid());
              entity.setId(userForm.getId());
              entity.setLoginname(userForm.getLoginname());
              entity.setPassword(userForm.getPassword());
              entity.setRemark(userForm.getRemark());
              entity.setSex(userForm.getSex());
              entity.setSortno(userForm.getSortno());
              entity.setStatus(userForm.getStatus());
              entity.setUsername(userForm.getUsername());
              entity.setUsertype(userForm.getUsertype());
              entity.setCreatetime(DateUtils.stringToDate(userForm.getCreatetime()));
              return entity;
          }
          return null;
    }
	
}
