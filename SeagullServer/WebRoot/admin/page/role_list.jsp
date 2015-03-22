<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<table class="fullwidth" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td>
				<table class="fullwidth" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td>
							<input onclick="javascript:location='role.do?method=toAdd'" value="添加" type="button" class="btn btn-green"/>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<table class="fullwidth" cellspacing="0" cellpadding="0" border="0">
			<thead style="font-size: 12px;">
				<tr>
					<th>编号</th>
					<th>角色名</th>
					<th>是否授权</th>
					<th>操作</th>
				</tr>
			</thead>
			<logic:notEmpty name="list">
				<logic:iterate id="item" name="list" scope="request">
					<tr>
						<td>${item.id}</td>
						<td>${item.role_name}</td>
						<td>
							<c:if test="${item.isAuthorize==0}">
								未授权
							</c:if>
							<c:if test="${item.isAuthorize==1}">
								已授权
							</c:if>
						</td>
						<td>
							<a href="role.do?method=toEdit&id=${item.id}">修改</a>
							<a href="role.do?method=toAuthorize&id=${item.id}">授权</a>
						</td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
			<logic:empty name="list">
				<tr>
					<td colspan="4" class="list_data_text"><font color="red">当前没有任何信息</font>
					</td>
				</tr>
			</logic:empty>
		</table>
		<div style="clear: both;"></div>
	</div>
	</div>
	<jsp:include page="../index/left.jsp" >
  		<jsp:param name="sidebarType" value="second" /> 
	</jsp:include>
	</div>
	<%@include file="../index/foot.jsp"%>
</body>
</html>