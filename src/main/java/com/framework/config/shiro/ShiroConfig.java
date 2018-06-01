package com.framework.config.shiro;


import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.framework.core.common.shiro.ShiroDbRealm;
import com.framework.core.filter.ShiroAjaxSessionFilter;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
@Configuration
public class ShiroConfig {
	
	 @Bean
	 public ShiroAjaxSessionFilter ajaxSessionFilter(){
		 return new ShiroAjaxSessionFilter();
	 }
	 
	 
	 @Bean
	 public ShiroDbRealm shiroDbRealm() {
		  ShiroDbRealm realm = new ShiroDbRealm();
	      return realm;
	  }
	
	@Bean
	public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDbRealm());
        return securityManager;
    }
	
	
	@Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setSuccessUrl("/index.jsp");
        shiroFilterFactoryBean.setUnauthorizedUrl("/jsp/error/403.jsp");
        
        Map<String,String> map = new HashMap<String, String>();
        // 登陆
        map.put("/login", "anon");
        // 登出
        map.put("/logout","logout");
        // 静态文件
        map.put("/css/**","anon");
        map.put("/i18n/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/webjars/**","anon");
        //对所有用户认证
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}
