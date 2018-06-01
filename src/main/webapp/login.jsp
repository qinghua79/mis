<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>

<title>欢迎使用系统</title>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/globalJs.jsp" %>	

<link type="text/css" rel="stylesheet" href="${path}/css/style.css" />
<link rel="stylesheet" type="text/css"  href="${path}/css/main.css" />
</head>
<div style="background:url('${path}/images/login.png'); margin: 100px auto auto; border: 1px solid; width:310px; height:188px; text-align: center;">
		<table style="margin: 55px 0 10px;">
			<tr>
				<td width=40% style="text-align: right;">用户名 &nbsp</td>
				<td width=60%><input class="easyui-textbox"  type="text"
					id="loginName" name="loginName"  data-options="required:true" placeholder="请输入登录名" /></td>
			</tr>
			<tr>
			
				<td style="text-align: right;">密码 &nbsp</td>
				<td><input  class="easyui-passwordbox" type="text"   id="password"
					name="password"  data-options="required:true" placeholder="请输入密码" /></td>
			</tr>
		</table>
		<div>
			<P style="margin: 5px 0px 0px 0px;">
				<span style="float: left; width: 150px; border: 3px"> <img
					style="float: right;" src="${path}/images/btnlogin.gif"
					width="58" height="21" / onclick="login();">
				</span> <span style="float: right; width: 150px; border: 3px"> <img
					style="float: left;" src="${path}/images/btncancel.gif"
					width="58" height="21" onclick="window.close();" />
				</span>
			</P>
		</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#loginName").focus();
		$("#password").bind("keydown", function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				login();
			}
		});

		$("#loginName").bind("keydown", function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				event.preventDefault()
				login();
			}
		});
	});

        
function login() {
    var loginName = $("#loginName").textbox("getValue");
    var password = $("#password").textbox("getValue");	
	
    $.ajax({
        type: 'POST',
        url: '${path}/login',
        data: {loginName: loginName, password: password},
        dataType: 'json',
        success: function (data) {
            result = data;
	        if (result.status =200) {
	             window.location.href = jsBasePath + '/index.jsp';
	     	 } else {
			    $.messager.alert("提示", result.message, "error");
			}
        }
  });
}   
 </script>
</html>
