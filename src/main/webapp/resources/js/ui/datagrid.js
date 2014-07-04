/**
 * @author adam
 * 2013-11-4
 */

/**加载列表
 * title 标题
 * url 地址
 * type ［boolean,boolean］[是否多选,是否分页]
 */
function loadDatagrid(title,url,type){
	var isSingleSelect=false; //是否多选 默认多选
	var isPagination=true;//是否分页  //默认分页
	if(type != undefined){
		if( typeof type == 'object' && type.constructor == Array){
			if(typeof type[0] == 'boolean'){
				isSingleSelect = type[0];
			}else{
				console.error('the "type[0]" is not Boolean');
			}
			if(type[1] != undefined){
				if(typeof type[1] == 'boolean'){
					isPagination = type[1];
				}else{
					console.error('the "type[1]" is not Boolean');
				}
			}
		}else{
			console.error('the "type" is not Array');
		}
	}
	$('#grid').datagrid({  
		title:title,
		//iconCls:'icon-edit',//图标 
		method:'get',
		queryParams:{t:new Date().getTime()},
		url:url,
		fit: true, //是否填充
		loadMsg:'数据加载中......',
		rownumbers:true,//行号
		singleSelect:isSingleSelect,//是否单选
		pagination:isPagination,//分页控件
		pageSize: 15,//每页显示的记录条数，默认为10  
	    pageList: [10,15,20,50,100],//可以设置每页记录条数的列表
		toolbar:'#tbar'
	 });
}



//刷新列表
function reloadList(){
	$("#grid").datagrid("reload");
}