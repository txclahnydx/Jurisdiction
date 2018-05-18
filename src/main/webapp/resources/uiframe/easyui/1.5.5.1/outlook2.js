
$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();
})

function getRootPath(){  
     //pathName:--->   mbuy/user/login.action  
     var pathName = window.location.pathname.substring(1);  
     //webName:--->mbuy  
     var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));  
     //return:--->http://localhost:9999/mbuy/  
     return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';  
}  
	 var _menus = {"menus":[
						{"id":"1","iconCls":"icon-add","text":"系统管理",
							"children":[{"text":"菜单管理","iconCls":"icon-add","url":"system/menu/index"},
									{"text":"按钮设置","iconCls":"icon-set","url":"system/button/index"},
									{"text":"系统日志","iconCls":"icon-log","url":"demo12.html"}
								]
						},{"id":"8","iconCls":"icon-sys","text":"员工管理",
							"children":[{"text":"员工列表","iconCls":"icon-nav","url":"demo23.html"},
									{"text":"视频监控","iconCls":"icon-nav","url":"demo34.html"}
								]
						}
				]};
//初始化左侧
function InitLeftMenu() {
	$.each(_menus.menus, function (i, e) {//循环创建手风琴的项
        var accordionId = e.id;
        //循环创建顶级手风琴菜单
        $('#navMenu').accordion('add', {
            title: e.text,
            content: "<ul id='tree_"+accordionId+"'></ul>",
            selected: true,
            iconCls:e.iconCls
        });
        $.parser.parse();
        $("#tree_" + accordionId).tree({
            data: e.children,
            onClick : function(node){
          	  if(node.children){  //该节点下级子类
                    if(node.url !="" && node.url!="/admin" && node.url!="/"){
                  	  	var text = node.text;
                        var url = getRootPath() + node.url;
                        var iconCls = node.iconCls;
                        Navigate(url, text,iconCls);
                    }else{
                  	  //node如果有子类取消当前tree-node-selected样式(选中状态)
                      treeNodeChildrenStyle();
              		  if (node.state == 'closed'){
                            $(this).tree('expand', node.target);  
                        }else if (node.state == 'open'){
                            $(this).tree('collapse', node.target);  
                        }
                    }
                }else{
                    var text = node.text;
                    var url = getRootPath() + node.url;
                    var iconCls = node.iconCls;
                    Navigate(url, text,iconCls);
                }
            }
        });
	})
}

function Navigate(url, text,iconCls){
	var idx = -1;
	var tabs = $('#tabs').tabs("tabs");
	$.each(tabs, function(i,n){
		if(n.find("iframe").attr("src") == url)
			idx = i;
	});
	if(idx ==-1){
		addTab(text,url,iconCls)
	}else{
		$('#tabs').tabs('select',text);
	}
}

function addTab(title,url,icon){
	if(!$('#tabs').tabs('exists',title)){
		$('#tabs').tabs('add',{
			title:title,
			content:createFrame(url),
			closable:true,
			icon:icon,
			width:$('#mainPanle').width()-10,
			height:$('#mainPanle').height()-26
		});
	}else{
		$('#tabs').tabs('select',title);
	}
	tabClose();
}

function createFrame(url){
	return "<iframe id=\"frameContent\" src=\""+url+"\" frameborder=\"0\" width=\"100%\" height=\"100%\" scrolling=\"auto\" marginheight=\"0\" marginwidth=\"0\" frameSpacing=\"0\"></iframe>"
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close',subtitle);
	})

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY,
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		if(currtab_title!="数据库监控"){
			$('#tabs').tabs('close',currtab_title);
		}
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!="数据库监控"){
				$('#tabs').tabs('close',t);
			}
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title&&t!="数据库监控")
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t!="数据库监控"){
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

function clockon() {
    var now = new Date();
    var year = now.getFullYear(); //getFullYear getYear
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;

    $("#bgclock").html(time);

    var timer = setTimeout("clockon()", 200);
}

/** tree-node如果有子类取消当前tree-node-selected样式(选中状态) */
function treeNodeChildrenStyle(){
	var currTab = $('#tabs').tabs('getSelected');
	$.each($("#navMenu").find("li"),function(i,li){
		if($(li).text() == currTab.panel('options').title || ($(li).find("div").find(".tree-title").html().indexOf(currTab.panel('options').title) >-1)){
			li.className = "nav-selected";
			$(li).find("span").css("fontWeight","bold");
			$(li).find("div").addClass("tree-node-selected");
		}else{
			li.className = "nav-default";
			$(li).find("span").css("fontWeight","normal");
			$(li).find("div").removeClass("tree-node-selected");
		}
	});
}
