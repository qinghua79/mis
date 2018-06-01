package com.framework.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.controller.BaseController;
import com.framework.controller.message.ResponseMessage;
import com.framework.core.common.SessionObject;
import com.framework.core.util.SpringUtils;
import com.framework.entity.sys.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@Api(value = "登陆管理 ", tags = "用户管理-登陆管理")
public class LoginController extends BaseController{

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody	
	@ApiOperation("登陆管理")
	public ResponseMessage<User> login(String loginName, String password, HttpServletRequest request){
		Subject user = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
	    SessionObject sessionObject = new SessionObject();
	    sessionObject.setUserId(loginName);
	    try {
        	//:zhengqh sessionObject不全
            user.login(token);
         
            if (this.getSessionObject() != null){
            	request.getSession().removeAttribute(SessionObject.BEAN_ID);
            	//request.getSession().invalidate();
            }
            SpringUtils.put(SessionObject.BEAN_ID,  sessionObject, SpringUtils.SCOPE_SESSION);
            return ResponseMessage.ok();
        } catch (UnknownAccountException e) {
        	  return ResponseMessage.error("账号不存在！");
        } catch (DisabledAccountException e) {
        	  return ResponseMessage.error("账号未启用！");
        } catch (IncorrectCredentialsException e) {
         	  return ResponseMessage.error("密码错误！");
        } catch (Throwable e) {
       	  return ResponseMessage.error(e.getMessage());
        }
	}
	//loginout
}
