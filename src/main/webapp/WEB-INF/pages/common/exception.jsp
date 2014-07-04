<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>异常页面</title>
</head>
<body>
<%
Exception ex = (Exception) request.getAttribute("exception");
out.print("{\"code\":\"-1\";\"msg\":\""+ex.getClass()+"\n"+ex.getMessage()+"\"}");
%>
<%-- <c:set var="ex" value="${exception }"></c:set>
<H2>Exception:
<font color="red">${ex.class }</font><br/><br/>
<font color="blue">message:${ex.message }</font></H2>--%>
</body>
</html>