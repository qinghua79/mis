<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/globalJs.jsp"%>
<script type="text/javascript"
	src="${path}/js/system/user/userList.js?_=${sysInitTime}"></script>
</head>
<body>

	<div id="userEdit"  data-options="modal:true,closed:true"  class="easyui-dialog" style="width:400px"  buttons="#userEditButton">
	     <div style="padding: 10px 20px">
	       <form id="userForm" name="userForm" method="post" action="${path}/user/user">
					<table id ="userInfo">
							<input type="hidden" id="id" name="id" >
							<tr>
								<td><label for="username">用户名</label></td>
								<td><input type="text" class="easyui-textbox"  data-options="required:true" id="username" name="username" placeholder="请输入用户名" /></td>
							</tr>

							<tr>
								<td><label for="loginname">登录名</label></td>
								<td><input type="text" class="easyui-textbox"
									data-options="required:true" id="loginname" name="loginname"
									placeholder="请输入登录名称" /></td>
							</tr>

							<tr>
								<td><label for="password">密码</label></td>
								<td><input type="password" class="easyui-textbox"
									data-options="required:true" id="password" name="password"
									placeholder="请输入密码" /></td>
							</tr>

							<tr>
								<td><label for="sex">性别</label></td>
								<td><select class="easyui-combobox" name="sex"
									style="width: 173px;" data-options="panelHeight:'auto'">
										<option value="0" selected="selected">男</option>
										<option value="1">女</option>
								</select></td>
							</tr>

							<tr>
								<td><label for="usertype">用户类型</label></td>
								<td><select class="easyui-combobox" name="usertype"
									style="width: 173px;" data-options="panelHeight:'auto'">
										<option value="0">用户</option>
										<option value="1" selected="selected">管理员</option>
								</select></td>
							</tr>




							<tr>
								<td><label for="status">用户状态</label></td>
								<td><select name="status" class="easyui-combobox"
									style="width: 173px;" data-options="panelHeight:'auto'"">
										<option value="0" selected="selected">正常</option>
										<option value="1">停用</option>
								</select></td>
							</tr>

							<tr>
								<td><label for="departid">部门</label></td>
								<td><select id="departidtree" name="departid"
									data-options="textField:'name',valueField:'id',multiple:true,required:true"
									style="width: 173px;">
								</select></td>
							</tr>

				</table>
				
	        </form>
		</div>
	</div>

	<div id="userEditButton">
		<table align="center">
			<tr>
				<td><a href="#" class="easyui-linkbutton"  iconCls="icon-ok" onclick="doSave()" /></td>
				<td><a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="$('#userEdit').dialog('close')" /></td>
			</tr>
		</table>
	</div>	

	<div id="Toolbar1">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()">新增</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="doReload()">刷新</a> 
		<span style="float: right; margin-right: 10px; padding: 1px">
		  <span>用户名:</span>	<input lang="searchForm" name="loginname" placeholder="请输入用户姓名" /> 
		  <input lang="searchForm" type=hidden id="departid" name="departid" /> 
		     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" plain="true" onclick="doClear()">清除</a>
		     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true" onclick="doSearch()">搜索</a>
		</span>
	</div>


	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',border:true,split:false,title:'组织机构'"
			style="width: 150px; overflow: hidden;">
			<ul id="departTree" style="width: 160px; margin: 10px 10px 10px 10px"></ul>
		</div>

		<div data-options="region:'center',border:true">
			<table id="userDataGrid" data-options="border:false"  style="width: 100%;" title="用户"/>
		</div>
	</div>


</body>
</html>

