$(function(){
			tabClose();
			tabCloseEven();
			$("#mm").data("root","主页");
});

//左边菜单栏的js
function ShowMenu(obj,n){
	
	 var Nav = obj.parentNode;
	 if(!Nav.id){
		  var BName = Nav.getElementsByTagName("ul");
		  var HName = Nav.getElementsByTagName("h2");
		  var t = 2;
	 }else{
		  var BName = document.getElementById(Nav.id).getElementsByTagName("span");
		  var HName = document.getElementById(Nav.id).getElementsByTagName("h1");
		  var t = 1;
	 }
	 for(var i=0; i<HName.length;i++){
	  HName[i].innerHTML = HName[i].innerHTML.replace("menu_07","menu_10");
	  HName[i].className = "";
	 }
	 obj.className = "h" + t;
	 for(var i=0; i<BName.length; i++){
	 	if(i!=n){BName[i].className = "no";
	 	}
	 }
	 if(BName[n].className == "no"){
	  BName[n].className = "";
	  obj.innerHTML = obj.innerHTML.replace("menu_10","menu_07");
	 }else{
	  BName[n].className = "no";
	  obj.className = "";
	  obj.innerHTML = obj.innerHTML.replace("menu_07","menu_10");
	 }
}		
		//添加tab
		function addTab(subtitle,url,icon){
			//判断session是否为空
			//if(session==null || session==""){
			//	window.document.location = '${ctx }/login_logOut.action';
			//}
			if(!$('#tabs').tabs('exists',subtitle)){
				$('#tabs').tabs('add',{
					title:subtitle,
					iconCls:icon,
					//content:createFrame(url),//通过创建 iframe 生成新tabpage
                    href:url,//通过嵌套div 生成 tabpage ，跨域请求会有问题
					closable:true,
                    fit:true,
					width:$('#mainPanle').width()-10,
					height:$('#mainPanle').height()-26
				});
			}else{
				$('#tabs').tabs('select',subtitle);
				var y=0;
				$('.tabs-inner li').each(function(i,n){
					var t = $(n).text();
					if(t == subtitle){
						y = i;
					}
				});	
				//refreshTab({tabTitle:subtitle,url:url,index:y});
				//刷新已经打开的tabs
			}
			tabClose();
		}
		
		//刷新方法
		function refreshTab(cfg){ 
			//获取刷新的iframe
    		var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');
    		if(refresh_tab && refresh_tab.find('iframe').length > 0){
    			if(cfg.index>0){
    				cfg.index = cfg.index;
    			}
    			var _refresh_ifram = refresh_tab.find('iframe')[cfg.index];
    			var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
    			_refresh_ifram.contentWindow.location.href=refresh_url;
        	}
        }
		
		//创建iframe对象 
		function createFrame(url)
		{
			var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
			return s;
		}
		
		function tabClose()
		{
			/*双击关闭TAB选项卡*/
			$(".tabs-inner").dblclick(function(){
				var subtitle = $(this).children("span").text();
				if(subtitle!=$("#mm").data("root"))
				$('#tabs').tabs('close',subtitle);
			})
		
			$(".tabs-inner").bind('contextmenu',function(e){
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
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
				if(currtab_title!=$("#mm").data("root"))
				$('#tabs').tabs('close',currtab_title);
				
			})
			//全部关闭
			$('#mm-tabcloseall').click(function(){
				$('.tabs-inner span').each(function(i,n){
					var t = $(n).text();
					if(t!=$("#mm").data("root"))
					$('#tabs').tabs('close',t);
				});	
			});
			//关闭除当前之外的TAB
			$('#mm-tabcloseother').click(function(){
				var currtab_title = $('#mm').data("currtab");
				$('.tabs-inner span').each(function(i,n){
					var t = $(n).text();
					if(t!=currtab_title)
						if(t!=$("#mm").data("root"))
						$('#tabs').tabs('close',t);
				});	
			});
			//关闭当前右侧的TAB
			$('#mm-tabcloseright').click(function(){
				var nextall = $('.tabs-selected').nextAll();
				if(nextall.length==0){
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
					return false;
				}
				prevall.each(function(i,n){
					var t=$('a:eq(0) span',$(n)).text();
					if(t!=$("#mm").data("root"))
					$('#tabs').tabs('close',t);
				});
				return false;
			});
		
			//退出
			$("#mm-exit").click(function(){
				$('#mm').menu('hide');
			})
		}