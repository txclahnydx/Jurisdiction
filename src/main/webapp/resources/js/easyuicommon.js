//easyui datagrid 加载数据的方法
function dataGridLoader(param,success,error){
	var options = $(this).datagrid('options');
	$.ajax({
		url:options.url,
		type:options.method,
		data:param,
		dataType:'json',
		success:function(data){
			if(data.success){
				success(data.data);
			}else{
				messageShow("ERROR","数据加载失败!");
				error();
			}
		}
	});
}
//easyui 消息弹出的方法
function messageShow(title,message){
	$.messager.show({  
        title:title,
        msg:message,
        timeout:2000,
        showType:'fade',
        style:{
            right:'',
            bottom:''
        }  
    }); 
}
//easyui 弹出层方法
//url：窗口调用地址，title：窗口标题，width：宽度，height：高度，shadow：是否显示背景阴影罩层  
function showPage(url, title, width, height, shadow) {
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';  
    var boarddiv = '<div id="win" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条  
    $(document.body).append(boarddiv);
    var win = $('#win').dialog({
        content: content,
        width: width,
        height: height,
        closed: false,
        collapsible: true,//	定义是否显示折叠按钮。
        minimizable: true,	//定义是否显示最小化按钮
        maximizable: true,	//定义是否显示最大化按钮。
        resizable: true,//	定义对话框是否可调整尺寸。
        modal: shadow,
        title: title
    });
    win.dialog('open');
} 

function closePageGridReload(gridId){
	parent.$('#win').window('close');
	parent.$('#'+gridId).datagrid("clearSelections");
	parent.$('#'+gridId).datagrid("reload");
}
function closePage(){
	parent.$('#win').window('close');
}
function getSelectGridId(gridId,itemId){
	var idArray = new Array();
	var selectRows = $("#"+gridId).datagrid("getSelections");
	$.each(selectRows,function(i,item){ 
		idArray.push(item[itemId]);
	}); 
	return idArray.join(",");
}
