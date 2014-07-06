<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SWR</title>
  <%@ include file="/WEB-INF/pages/common/metalinks.jsp" %>
<script type="text/javascript" src="${ctx }/resources/js/ui/layout.js"></script>
<script type="text/javascript">
$(function(){
	$('#logout').click(function(){
		$.messager.confirm('确认','是否退出系统？',function(res){
			if(res){
				window.location.href = 'j_spring_security_logout';
			}
		});
	});
});
</script>
</head>

<body class="easyui-layout">
<!-- 头部 -->
    <div region="north" split="false" border="false" style="height:60px;">
    	<div style="height:60px;line-height: 60px;font-size:24px;">
    	SWR气象专业应用后台 <a style="float:right;" id="logout" href="#">注销</a>
    	</div>

    </div>
    <!-- 左边 -->
	<div region="west" split="false" title="用户："style="width:150px;overflow: hidden;font-size: 26px;" id="west">
			<div class="easyui-accordion" data-options="fit:true,border:false"  data-options="selected:true">
				<div title="终端管理"  data-options="iconCls:'icon-sum'" style="padding:5px;">
				<ul>
					<li onclick="addTab('插播预警','http://www.baidu.com','icon-sum')"><a href="javascript:void(0)"><span class="icon-sum"></span>插播预警</a></li>
				  	<li onclick="addTab('终端监控','${ctx}/terminalmanage/terminalMonitor','')"><a href="javascript:void(0)"><span class=""></span>终端监控</a></li>
				  	<li onclick="addTab('分组管理','${ctx}/terminalmanage/groupmanage','')"><a href="javascript:void(0)"><span class=""></span>分组管理</a></li>
				  	<li onclick="addTab('视频播放设置','${ctx}/surveyInfo/list/survey','')"><a href="javascript:void(0)"><span class=""></span>视频播放设置</a></li>
				  	<li onclick="addTab('终端宽带管理','${ctx}/surveyInfo/list/survey','')"><a href="javascript:void(0)"><span class=""></span>终端宽带管理</a></li>
				  	<li onclick="addTab('终端信息管理','${ctx}/surveyInfo/list/survey','')"><a href="javascript:void(0)"><span class=""></span>终端信息管理</a></li>
				  	<li onclick="addTab('程序升级管理','${ctx}/surveyInfo/list/survey','')"><a href="javascript:void(0)"><span class=""></span>程序升级管理</a></li>
				 </ul> 
				</div>
				<div title="统计管理" data-options="iconCls:''" style="padding:10px;">
				<ul>
				  	<li onclick="addTab('手动发布统计','${ctx}/surveyInfo/list/daily','')"><a href="javascript:void(0)"><span class=""></span>手动发布统计</a></li>
				  	<li onclick="addTab('自动发布统计','${ctx}/surveyInfo/list/daily','')"><a href="javascript:void(0)"><span class=""></span>自动发布统计</a></li>
				  	<li onclick="addTab('在线时长统计','${ctx}/surveyInfo/list/daily','')"><a href="javascript:void(0)"><span class=""></span>在线时长统计</a></li>
				</ul> 
				</div>
				<div title="基础数据" data-options="iconCls:''" style="padding:10px;">
				<ul>
				  <li onclick="addTab('地区管理','${ctx}/basedata/area','')"><a href="javascript:void(0)"><span class=""></span>地区管理</a></li>
				  <li onclick="addTab('机构管理','${ctx}/basedata/organization','')"><a href="javascript:void(0)"><span class=""></span>机构管理</a></li>
				  <li onclick="addTab('预警类型管理','${ctx}/basedata/warningtype','')"><a href="javascript:void(0)"><span class=""></span>预警类型管理</a></li>
				  
				 </ul> 
				</div>
				<div title="系统设置" data-options="iconCls:''" style="padding:10px;">
				<ul>
				  <li onclick="addTab('用户管理','${ctx}/userInfo/list','')"><a href="javascript:void(0)"><span class=""></span>用户管理</a></li>
				  <li onclick="addTab('角色管理','${ctx}/msgTable/list/msg','')"><a href="javascript:void(0)"><span class=""></span>角色管理</a></li>
				  <li onclick="addTab('权限管理','${ctx}/msgTable/list/msg','')"><a href="javascript:void(0)"><span class=""></span>权限管理</a></li>
				  
				 </ul> 
				</div>
				<div title="系统日志" data-options="iconCls:''" style="padding:10px;">
				<ul>
				  <li onclick="addTab('系统日志','${ctx}/groupInfo','')"><a href="javascript:void(0)"><span class=""></span>系统日志</a></li>
				 
				 </ul> 
				</div>
				
			</div>

    </div>
    <!-- 中间主界面 -->
    <div id="mainPanle" region="center" style="background: #eee;">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="主页" data-options="iconCls:'icon-search'" id="home" width="99%">
				<div style="line-height:100%; width: 100%;height: 100%;background: url('${ctx }/resources/images/login/welcome.jpg') no-repeat;background-size:100% 100%;">欢迎使用SWR后台管理系统</div>
			</div>
		</div>
    </div>
    
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>