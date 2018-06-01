package com.framework.core.common;

import java.io.Serializable;
import java.util.Date;

public class SessionObject implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	public static final String BEAN_ID="sessionObject";
    
	private String userId;
	private String userName;
	private String loginName;
	
	private String orgId;
	private String orgName;
	private String orgLevelCode;
	private String roleId;
	private String roleName;
	private Date loginTime;
	private String ipAddr;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgLevelCode() {
		return orgLevelCode;
	}
	public void setOrgLevelCode(String orgLevelCode) {
		this.orgLevelCode = orgLevelCode;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	
}
