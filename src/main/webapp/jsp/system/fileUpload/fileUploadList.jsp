<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>文件上传下载</title>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/globalJs.jsp" %>
<script type="text/javascript" src="<c:url value='/js/utils/utils.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/utils/ajaxupload.js' />"></script>

<%
String refId = request.getParameter("refId");
String refType = request.getParameter("refType");
String exts = request.getParameter("exts");
exts ="txt,xls";
%>

</head>
<body class="easyui-layout">
   <div data-options="region:'center'">
   
<table id="_dataGridFile" class="easyui-datagrid" url="<c:url value="/fileUpload/list" />" singleSelect="true" toolbar="#_ToolBarFile" fit="true" pagination="true" rownumbers="true" fitColumns="true" pageSize="10" pageList="[10,20,30]">  
    <thead>  
        <tr>  
    			<th field="id" hidden="true"></th>
					<th field="serverPath" hidden="true"></th>
					<th field="serverFileName" hidden="true"></th>
					<th field="clientFileName" width="20%" align="center">文件名称</th>
					<th field="fileDesc" width="30%">文件描述</th>
					<th field="fileSize" width="10%" align="center">文件大小</th>
					<th field="uploadTime" width="10%">上传时间</th>
					<th field="uploadUserName" width="10%" align="center">上传人</th>
					<th field="downTimes" width="10%" align="center">下载次数</th>
					<th field="downLevel" width="10%" align="center" formatter="_formatOptionFile">操作</th>
        </tr>                            
    </thead>                                                         
</table> 
<form id="_downloadForm" action="<c:url value='/fileUpload/downLoad'/>"  method="post" style="margin: 0px">
   <input name="id" type="hidden"/>
</form>

   </div> 
<!-- 工具栏 -->
	<div id="_ToolBarFile">
		<a href="#" id="_BtnUpload" class="easyui-linkbutton" iconCls="icon-mini-add" plain="true">上传文件</a>
		<div class="datagrid-btn-separator"></div>
		<a href="#" class="easyui-linkbutton" iconCls="icon-mini-edit" plain="true" onclick="_openFileDialog()">编辑描述</a>
		<div class="datagrid-btn-separator"></div>
		<a href="#" class="easyui-linkbutton" onclick="_onDeleteFile()" iconCls="icon-no" plain="true">删除文件</a>
	</div>

	<div id="_FileProgressBar" class="easyui-progressbar" style="position: relative; float: right; background-color: white; width: 200px; border: 2px solid #f8ce3d; margin: 5px; display: none"></div>
 
 

<script>

$(function() {
	$('#_dataGridFile').datagrid({
		queryParams:{
			refId: "<%=refId%>",
			refType: "<%=refType%>"
		},
		onLoadSuccess: function(data){
			if (data.rows.length >0 ){
				$(this).datagrid('selectRow', 0);
			}
		}
		
		
	});
	
	
	
		//上传文件
		new AjaxUpload('_BtnUpload',{
	        action: '<c:url value="/fileUpload/ajaxUpload" />',
	        name: 'file',
	        onSubmit : function(file, ext){
	           if (! validateExt(ext,"<%=exts%>")){
	        	   $.messager.alert("提示", "上传格式不符,上传格式为(<%=exts%>)", "error");
	        	   return false;
	           }
	           this.disable();
	           _uuidFile = new UUID().id;
	           this.setData({
	        		id : _uuidFile,
	            	refId:"<%=refId%>",
					refType:"<%=refType%>"
	           });
	           //打开文件上传滚动条
	           var bar = $("#_FileProgressBar");
	           bar.css("display","block");
	           _internalFile = setInterval(_getProgress,500);
	        },
	        onComplete: function(file, response){
	           this.enable();
	           $("#_FileProgressBar").progressbar("setValue",0).css("display","none");//关闭进度条
	           clearInterval(_internalFile);
	           
	           
	           //返回结果处理
	            var operInfo = null;
	            try{
	            	operInfo = eval('(' + response + ')');
	            } catch(e){}
	            
	            if (operInfo){
	            	if(operInfo.isOk) {
						$.messager.alert("提示", operInfo.info, "info");
						$('#_DatagridFile').datagrid("reload");
				        //关闭文件上传滚动条
					} else {
						$.messager.alert("提示", operInfo.info, "error");
					}
	            } else {
	            	$.messager.alert("提示", "上传失败!上传文件大小不超过31M", "error");
	            }
	            
	           
	           
	        }
	    });
	});



//获取文件进度
function _getProgress(){
	var url = "<c:url value='/fileUpload/getFileProgress'/>";
	var data = {
		id : _uuidFile
     };
	$.ajax({
		url:url,
		data:data,
		async: false,
		type:"POST",
		dataType : "json",
		success:function(fileProgress){
			if(fileProgress){
				var bar = $("#_FileProgressBar");
				bar.progressbar("setValue",parseInt(fileProgress.lUploadedSize/fileProgress.lFileSize*100));
			}
		}
	});			
}



//删除按钮
function _onDeleteFile(){
	var row = $('#_DatagridFile').datagrid('getSelected');
	if(row){
		 $.messager.confirm("提示","确认删除该文件?",function(r){
			 if(r){
				var url = "<c:url value='/sys/uploadFile.do?method=deleteFile'/>";
				var data = {
						fileId : row.fileId
				};
				$.post(url,data,function(msg){
					var operInfo = eval('(' + msg + ')');
					if (operInfo.isOk) {
						$.messager.alert("提示", operInfo.info, "info");
						$('#_DatagridFile').datagrid('reload'); 
					} else {
						$.messager.alert("提示", operInfo.info, "error");
					}
				});	
			 }
		 });
	} else {
		$.messager.alert("提示","请选择要删除的文件!","info");
	}
}
//打开文件描述编辑
function _openFileDialog(){
	var row = $("#_DatagridFile").datagrid("getSelected");
	if(row){
		$("#_fileDesc").val(row.fileDesc);
		$("#_DialogFile").dialog("open");
	} else {
		$.messager.alert("提示", "请选择要编辑的文件!", "error");
	}
}
function _formatOptionFile(value ,rec){
	return "<a herf='#' onmouseover=\"this.style.cursor='hand'\" style='color:blue' onclick='_downLoadFile(\""+rec.id+"\")'>下载文件</a>";
}

//下载
function _downLoadFile(fileId){
	$("#_downloadForm [name='id']").val(fileId);
	$("#_downloadForm").submit();
}
</script>
</body>
</html>