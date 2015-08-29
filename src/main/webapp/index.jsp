<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>缓存测试</title>
	</head>
	<body>
		<input type="button" value="查询缓存1"/><br>
		<input type="button" value="查询缓存2"/><br>
		<input type="button" value="删除缓存1"/><br>
		<input type="button" value="删除缓存2"/><br>
		<br>
	</body>
</html>
