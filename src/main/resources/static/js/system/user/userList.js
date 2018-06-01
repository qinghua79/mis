
var OrgTree;
var UserList;
var UserEdit;
var departTree;
var userForm;
$(function(){
	OrgTree = $("#departTree");
	UserList = $("#userDataGrid");
	UserEdit =$("#userEdit");   
	
	OrgTree.tree({
	     method: 'get',
	     url:  jsBasePath+'/depart/tree',
	     onSelect: function (node) {
	    	  $('#departid').val(node.id);
	    	  UserList.datagrid({queryParams: {departid: node.id}});
	     }
	});
	
	
	UserList.datagrid({
	    url: jsBasePath+"/user/users",
	    method: 'get',
	    pagination: true,
	    pageSize: 10,
	    toolbar: '#Toolbar1',
	    singleSelect: false,
	    collapsible: false,
	    columns: [[
	            {field: 'ck', checkbox: true},
	            {field: 'id', title: '主键id', hidden: true},
	            {field: 'loginname', title: '登录名', width: '8.636%', hidden: false},
	            {field: 'sex', title: '性别', width: '8.636%', hidden: false,formatter:function(value,row,index){return value==0?'男':'女';}},
	            {field: 'usertype', title: '用户类别', width: '8.636%', hidden: false,formatter:function(value,row,index){return value==0?'用户':'管理员';}},
	            {field: 'createtime', title: '创建时间', width: '10%', hidden: false,formatter: formatDatebox}
	          ]]
	       
	 });
	
	
	
});
	



function doAdd(){
   editType = "add";
   $('#userEdit').dialog('open').dialog('setTitle', '新增用户');
   $('#userForm').form('clear');
   $('#userForm').form('load', {sex:1,usertype:1,status:1});
   $("#userEdit [name='id']").attr("readonly", false);
 
   departTree = $("#departidtree"); 
   departTree.combotree({
		   url:  jsBasePath+"/depart/tree",
		   method: "get",
		   panelHeight: "auto",
		   multiple: "false"
				   
	   })	
  // $("##userForm [name='editType']").val("add");
}

function doEdit(){
	var sels =  UserList.datagrid("getSelections");
	if (sels.length < 1){
		$.messager.alert("对话框", "请至少选择一行");
		return;
	}
	
	if (sels.length > 1){
		$.messager.alert("对话框","只能选择一行");
		return;
	}

	if (sels){
		$("#userEdit").dialog("open").dialog("setTitle","编辑用户");
		$("#userForm").form("load",sels[0]);
	}
				 	
	departTree = $("#departidtree"); 
	departTree.combotree({
		url: jsBasePath+"/depart/tree",
		method: "get",
		panelHeight: "auto",
		multiple: "false",
		onLoadSuccess: function (){
			departTree.combotree('setValue',sels[0].departid); 
		   }  
		 })	
}
				 	
function doSave(){
	$('#userForm').form('submit', {    
	    url:  jsBasePath+"/user/user",    
	    onSubmit: function(){    
	       
	    },    
	    success:function(data){   
	    	  var jsonObj = $.parseJSON(data)
	    	  if (jsonObj.status == 200) {
            	  $("#userEdit").dialog("close");
            	  UserList.datagrid("reload");
              }
	    }    
	}); 
	
}


function getSelectIds(){
	var sels =  UserList.datagrid("getSelections");
	var ids = [];
	for (var i in sels){
		ids.push(sels[i].id);
	}
	ids = ids.join(",");
	return ids;
}


function doDelete(){
	var ids = getSelectIds();
	if (ids.length ==0){
	    $.messager.alert("对话框","请至少选择一行");
	}
	$.messager.confirm({
		 title: '确认提示框',
         msg: '你确定要删除吗？',
         fn: function (r){
        	 if (r){
        		 
        		 $.ajax({
        			 type: "DELETE",
        			 url  :  jsBasePath+"/user/user/"+ids,
        			 success: function(){
        				 UserList.datagrid("reload");
        			 }
        		 })
        		 
        		 
        	 }
        	 
         }
		
	})
	
}

function doReload(){
	$("#userDataGrid").datagrid("reload");
}

function doClear(){
	$("input[lang='searchForm']").each(
	   function (){
          $(this).val("");		   
	   }		
	)	
}

function doSearch() {
    var searchName = [];
    var searchValue = [];
    $("input[lang='searchForm']").each(function (i) {
        searchName[i] = $(this).attr("name");
        searchValue[i] = $(this).val();
    });

    var queryParamsArr = [];
    for (var i = 0; i < searchName.length; i++) {
        queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
    }
    var queryParams = "{" + queryParamsArr.join(",") + "}";

    UserList.datagrid({
        queryParams: eval('(' + queryParams + ')'),
        onLoadSuccess: function (data) {
            //回显搜索内容
            $("input[lang='searchSysUser']").each(function (i) {
                $(this).val(searchValue[i]);
            });
        }
    });
}


   
    

    

    


