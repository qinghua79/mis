<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通用管理系统</title>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/globalJs.jsp" %>	
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />


<style type="text/css">
.content {
	padding: 0px 0px 0px 0px;
  }
.font_red{
   color:red;
   font-weight:bold 
}  
</style>

</head>
<body class="easyui-layout">
	<div data-options="region:'north'" border=false style="height: 95px;">

		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr height="72px" id="top_frm">
				<td
					background="${pageContext.request.contextPath}/images/index/logo.jpg"></td>
			</tr>
			<tr height="22px"
				background="${pageContext.request.contextPath}/images/index/topline_bg.jpg">
				<td>
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="50" align="center" valign="middle">&nbsp;</td>

							<td align="left" valign="middle">
								当前用户： <span class="font_red">   <shiro:user>  <shiro:principal /> </shiro:user> </span>
								今天是 <span class=""> <script>
									function showday() {
										var d, s = "", day;
										var weekday = new Array("日", "一", "二",
												"三", "四", "五", "六");
										d = new Date();
										s += d.getFullYear() + "-";
										s += (d.getMonth() + 1) + "-";
										s += d.getDate() + " ";
										day = d.getDay();
										s += "星期" + weekday[day];
										document.write(s);
									}
									showday();
								</script></span>


							</td>

							<td width="22" align="right" valign="bottom"><img
								src="${pageContext.request.contextPath}/images/index/topline_menu.jpg"></td>

							<td width="300" align="right" valign="middle"
								background="${pageContext.request.contextPath}/images/index/topline_menu2.jpg">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="18"><img
											src="${pageContext.request.contextPath}/images/index/icon_home.gif"
											width="14" height="17"></td>
										<td width="59" valign="bottom"><a
											href="javascript:frmMain.location.href='/');"
											class="menu_font">首页 </a></td>

										<td width="10"><img
											src="${pageContext.request.contextPath}/images/index/menu_line.gif"
											width="2" height="17"></td>

										<td width="18"><img
											src="${pageContext.request.contextPath}/images/index/icon_close.gif"
											width="14" height="17"></td>
										<td width="59" valign="bottom"><a style="cursor: hand"
											href="${pageContext.request.contextPath}/logout">退出</a></td>

										<td width="10"><img
											src="${pageContext.request.contextPath}/images/index/menu_line.gif"
											width="2" height="17"></td>

										<td width="10"><img
											src="${pageContext.request.contextPath}/images/index/topSplit.gif"
											width="18" height="17" onClick="topframeChange()"
											id="top_img"></td>
									</tr>
								</table>
							</td>

						</tr>

					</table>
				</td>
			</tr>
		</table>






	</div>

	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 180px;">
		<ul id="menu" class="easyui-tree"
			style="margin-top: 10px; margin-left: 5px;">
				<li><span>系统管理</span>
				<ul>
					<li data-options="attributes:{'url':'${path}/jsp/system/depart/departList.jsp'}">部门管理</li>
				</ul>
				
				<ul>
					<li data-options="attributes:{'url':'${path}/jsp/system/user/userList.jsp'}">用户管理</li>
				</ul>
				
				<ul>
					<li data-options="attributes:{'url':'${path}/jsp/system/role/roleList.jsp'}">角色管理</li>
				</ul>
				
				<ul>
					<li data-options="attributes:{'url':'${path}/jsp/system/permission/permissionList.jsp'}">权限管理</li>
				</ul>
				
				</li>
			
			
		</ul>
	</div>
	<div region="center"  >
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div id="first" title="首页" style="padding:10px">
	        
            </div>
		</div>
	</div>
   <div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee" >Copyright © 2015 power by <a href="http://www.baidu.net/" target="_blank">技术</a></div>
 
	<script type="text/javascript">
	
	function createFrame(url){
		var s = '<iframe  scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		return s;
	}
	
		$(function() {
			$('#menu').tree({
				onClick : function(node) {
					if ($('#menu').tree("isLeaf", node.target)) {
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab", node.text);
						if (tab) {
							tabs.tabs("select", node.text);
						} else {
							tabs.tabs('add', {
								title : node.text,
								// href :  node.attributes.url,// 不是完整页面
								content: createFrame(node.attributes.url), //完成页面
								selected:true,
								closable:true
							});
						}
					}
				}
			});
		}
    );  



	</script>
</body>
</html>