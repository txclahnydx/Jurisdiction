<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="${path }/resources/common/header.jsp"/>
<title>按钮设置</title>
<script type="text/javascript">

function initTreeList(){
	$('#treeList').treegrid({
	    url:'${path}/system/menu/getMenuList',
	    loader:treeGridLoader,
	    border:false,
		fit:true,
		idField:'id',//指示哪个字段是标识字段。
		treeField: 'name',
		animate:true,//定义当节点展开或折叠时是否显示动画效果
	    frozenColumns:[[
	    	{field:'ck',checkbox:true}
	    ]],
	    columns:[[
			{field:'name',title:'菜单名称',width:200,align:'center'},
			{field:'url',title:'链接地址',width:150,align:'center'},
			{field:'createBy',title:'创建者',width:100,align:'center'},
			{field:'createDate',title:'创建时间',width:150,align:'center'},
			{field:'status',title:'状态',width:100,align:'center',formatter: function(value,row,index){
				if (value==0){
					return '启用';
				} else {
					return "<span style='color:red;'>禁用<span>";
				}
			}}
	    ]],
	    loadMsg:'数据加载中,请稍后...',
	    rownumbers:true,
	    toolbar:'#treeList-toolBar'
	});
}

function btnAdd(){
	showPage('${path}/system/button/add', '添加按钮', 420, 260, true);
}
function btnEdit(){
	showPage('${path}/system/button/edit/'+selectRow.id, '按钮修改', 420, 260, true);
}
function btnDel(){
}

//js 初始函数
$(function (){
	initTreeList();
});
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'center'" style="padding:5px;">
    	<table id="treeList" class="easyui-treegrid"></table>
    </div>
      <div data-options="region:'east',title:'East',split:true" style="width:300px;">
      </div>
    
    <div id="treeList-toolBar">
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="btnAdd()">新增</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="btnEdit()">修改</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="btnDel()">删除</a>
    </div>
</body>
</html>