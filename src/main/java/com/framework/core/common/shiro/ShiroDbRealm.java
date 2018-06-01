package com.framework.core.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.core.common.SessionObject;
import com.framework.core.util.SpringUtils;
import com.framework.entity.sys.User;
import com.framework.service.sys.UserService;


public class ShiroDbRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService ;
	

	/**
	 *  认证接口
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	 UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		 String loginName = token.getUsername();
		 if ((loginName != null ) && !"".equals(loginName)){
			
			User user = userService.getByLoginName(loginName);
			if (user != null){
				  return new SimpleAuthenticationInfo(user.getLoginname(),
	                        user.getPassword(), getName()); 
			} 
			 
		 }
		 return null;
    }
	
	/**
	 * 授权接口
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

       return null;
       
		 
	}



}
