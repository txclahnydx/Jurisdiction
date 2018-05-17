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
	$('#dataList').datagrid({
		autoRowHeight: false,//定义是否设置基于该行内容的行高度。设置为 false，则可以提高加载性能。
	    url:'${path}/system/button/getButtonList',
	    loader:dataGridLoader,
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
			{field:'name',title:'按钮名称',width:100,align:'center'},
			{field:'field',title:'域',width:100,align:'center'},
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
	    pagination:false,//设置为 true，则在数据网格（datagrid）底部显示分页工具栏。
	    toolbar:'#dataList-toolBar'
	});
}

function btnAdd(){
	showPage('${path}/system/button/add', '添加按钮', 420, 260, true);
}
function btnEdit(){
	var selectRow = $("#dataList").datagrid("getSelected");
	var selectRows = $("#dataList").datagrid("getSelections");
	if(selectRow==null){
		messageShow('提示','请选择一条需要修改的记录!');
		return;
	}
	if(selectRows.length>1){
		messageShow('提示','一次只能修改一条记录!');
		return;
	}
	showPage('${path}/system/button/edit/'+selectRow.id, '按钮修改', 420, 260, true);
}
function btnDel(){
	var ids = getSelectGridId('dataList','id');
	if(ids!=null&&$.trim(ids)!=''){
		$.post("${path}/system/button/delete",{ids:ids},function(data){
			if(data.success){
				messageShow('提示','删除成功!');
				$('#dataList').datagrid('reload');
			}else{
				messageShow('ERROR',data.message);
			}
		},'json');
	}else{
		messageShow('提示','请选择需要删除的按钮!');
	}
}

//js 初始函数
$(function (){
	initButtonList();
});
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'center'" style="padding:5px;">
    	<table id="dataList"></table>
    </div>
    
    <div id="dataList-toolBar">
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="btnAdd()">新增</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="btnEdit()">修改</a>
    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="btnDel()">删除</a>
    </div>
</body>
</html>