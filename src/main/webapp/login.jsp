<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglibs.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>山西省考核办民意调查短信问答平台</title>
<%@include file="/WEB-INF/pages/common/metalinks.jsp" %>    
<link href="${ctx }/resources/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	//$('#username').focus();
	
});


</script>
</head>
<body>
<form id="iform" action="j_spring_security_check" method="post" >
<div class="Frame">
  <div class="logo"><img src="${ctx }/resources/images/login/logo_03.png" /></div>
  <div class="Login">
  <table width="100%" height="136" border="0" cellpadding="0" cellspacing="0" style="margin-left:86px;">
  <tr>
    <td width="65%" height="60" valign="top"><input name="j_username"  style="margin-top: 4px;" type="text" class="kuw easyui-validatebox" id="username"  data-options="required:true,validType:'minLength[5]' " missingMessage="请输入用户名"/></td>
  </tr>
  
  <tr>
    <td  height="50" valign="top"><input name="j_password"  style="margin-top: 6px; "  type="password" class="kuw easyui-validatebox"  id="password"   data-options="required:true,validType:['length[6,16]']" /></td>
  </tr>
  <tr>
  	<td height="25" valign="middle" ><font color="red" id="message">${message}</font></td>
  </tr>
  <tr>
    <td>
    	
    	<input name="Input" type="submit" class="bu1" value="" />
    </td>
  </tr>
</table>
</div>
</div>
</form>
</body>
</html>