<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.js"></script>
</head>
<body>
	<%@include file="../index/head.jsp" %>
	<div id="bgwrap">	
	<div id="content">
	<jsp:include page="../index/left.jsp" >
  		<jsp:param name="sidebarType" value="second" /> 
	</jsp:include>	
	<div id="main">	
		<img src="img/sys/hello.jpg">
	</div>
	</div>
	</div>
	<%@include file="../index/foot.jsp"%>
</body>
</html>