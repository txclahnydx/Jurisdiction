<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="${path }/resources/common/header.jsp"/>
<title>按钮设置</title>
<script type="text/javascript">

function initButtonList(){
	$('#buttonList').datagrid({
		autoRowHeight: false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
	    url:'${path}/system/button/getButtonList',
	    loader:function(param,success,error){
	    	var options = $(this).datagrid('options');
	    	$.ajax({
	    		url:options.url,
	    		type:options.method,
	    		data:param,
	    		dataType:'json',
	    		success:function(data){
	    			//debugger;
	    			if(data.success){
	    				success(data.data);
	    			}else{
	    				$.messager.show({  
	    			        title:'提示',
	    			        msg:"数据加载失败!",
	    			        timeout:2000,
	    			        showType:'fade',
	    			        style:{
	    			            right:'',
	    			            bottom:''
	    			        }  
	    			    });  
	    				error();
	    			}
	    		}
	    	});
	    },
	    singleSelect:false,//设置为 true，则只允许选中一行
	    checkOnSelect:true,//如果设置为 true，当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。
	    selectOnCheck:true,//如果设置为 true，点击复选框将会选中该行。如果设置为 false，选中该行将不会选中复选框。
	    border:false,
		fit:true,
		idField:'id',//指示哪个字段是标识字段。
		striped:true,//设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
	    frozenColumns:[[
	    	{field:'ck',checkbox:true}
	    ]],
	    columns:[[
			{field:'name',title:'按钮名称',width:100},
			{field:'field',title:'域',width:100},
			{field:'createBy',title:'创建者',width:100},
			{field:'createDate',title:'创建时间',width:100},
			{field:'status',title:'状态',width:100}
	    ]],
	    loadMsg:'数据加载中,请稍后...',
	    pagination:false,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏。
	    toolbar:'#buttonList-toolBar'
	});
}

function btnAdd(){
	alert("新增按钮");
}
function btnEdit(){
	alert("修改按钮");
}
function btnDel(){
	alert("删除按钮");
}


//js 初始函数
$(function (){
	initButtonList();
});
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'center'" style="padding:5px;">
    	<table id="buttonList"></table>
    </div>
    
    <div id="buttonList-toolBar">
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="btnAdd()">新增</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="btnEdit()">修改</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="btnDel()">删除</a>
    </div>
    
</body>
</html>