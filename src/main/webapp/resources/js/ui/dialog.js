/*
 * 加载弹出窗口
 * title : 标题 
 * widht: 宽度 
 * height:高度 
 * top: 顶部位置 
 * iconCls : 图标样式
*/
function loadDialog(width,height,type){
	if(width == null){width = 600;}
	if(height == null){height = 400;}
	var zable = true;
	if(type != null && type != '' && type != undefined && type == 'form'){zable=false;}
	$('#dlg').dialog({
        title: '保存',
        iconCls: 'icon-save',
        collapsible:true,
        //minimizable:true,
        maximizable:zable,
        resizable:zable,
        width: width,
        height: height,
       // top:100,
        closed: true,
        cache: false,
        modal: true,
        buttons:'#bbar'
    });
}

//打开弹出窗口
function openDialog(url){
	$('#dlg').dialog('open').dialog('refresh',url);
}

//关闭弹出窗口
function closeDialog(){
	$('#dlg').dialog('close');
}