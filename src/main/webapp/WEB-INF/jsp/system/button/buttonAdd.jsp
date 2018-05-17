<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="${path }/resources/common/header.jsp"/>
<title>按钮添加</title>
<script type="text/javascript">
function submitForm(){
	$.messager.progress();	// display the progress bar
	$('#buttonForm').form('submit', {
		url: '${path}/system/button/doAdd',
		onSubmit: function(){
			var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// hide progress bar while the form is invalid
			}
			return isValid;	// return false will stop the form submission
		},
		success: function(data){
			$.messager.progress('close');
			var result = jQuery.parseJSON(data);
			if(result.success){
				messageShow('提示','按钮添加成功!');
				closePageGridReload('dataList');
			}else{
				messageShow('错误',result.message);
			}
		}
	});
}
function resetForm(){
	$('#buttonForm').form('reset');
}
function cancelForm(){
	closePage();
}
</script>
</head>
<body>
<form id="buttonForm">
	<table width="100%">
		<tr>
			<td>名称:</td>
			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>Field:</td>
			<td><input class="easyui-validatebox" type="text" name="field" data-options="required:true" /></td>
		</tr>
		<!-- <tr>
			<td>描述:</td>
			<td>
				<textarea name="description" required="required" style="width:95%;border-radius:5px"></textarea>
			</td>
		</tr> -->
		<tr>
			<td>是否启用：</td>
			<td colspan="3">
			<input type="radio" name="status" value="0" checked="checked" style="vertical-align:middle;"/>
				<label>启用</label>
				<input type="radio" name="status" value="1" style="vertical-align:middle;"/>
				<label>不启用</label>
			</td>
		</tr>
	</table>
</form>
<div style="text-align:center;padding:5px 0">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm()" style="width:80px">重置</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelForm()" style="width:80px">取消</a>
</div>
</body>
</html>