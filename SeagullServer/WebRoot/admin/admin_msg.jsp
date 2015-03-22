<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>error</title>	
	</head>
	<body onkeydown="if(event.keyCode==27) return false;">
		<div style="font-size: 12px;" align="center"><div class="box message"><p>${msg }<br/>
		<a href="admin.do?method=toPassword">返回</a></p></div></div>
	</body>
</html>