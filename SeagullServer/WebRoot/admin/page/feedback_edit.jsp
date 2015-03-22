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
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="photo.do" method="post" id="editForm">
			<input type="hidden" name="id" value="${feedback.id}">
			<table>
				<tr>
					<td>日期</td>
					<td>
						<fmt:formatDate value="${feedback.time}" var="time" pattern="yyyy-MM-dd"/>
						<input id="time" name="time" value="${time}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>留言内容</td>
					<td>
						<textarea id="msgcontent" name="msgcontent" readonly="readonly" >${feedback.msgcontent}</textarea>
					</td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td>
						<input id="contact" name="contact" value="${feedback.contact}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>
						<input value="返回" type="button" class="btn btn-green" onclick="javascript:history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	  </div>
	  </div>
	  <jsp:include page="../index/left.jsp" >
  		<jsp:param name="sidebarType" value="second" /> 
	  </jsp:include>
	  </div>
	<%@include file="../index/foot.jsp"%>	
</body>
</html>