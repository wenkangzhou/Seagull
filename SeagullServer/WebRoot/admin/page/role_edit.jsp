<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<title><bean:message key="base" /></title>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.js"></script>
<script type="text/javascript">
	function checkForm() {
		if(document.getElementById("role_name").value=="")
		{
			alert("请输入角色名");
			return;
		}
		document.getElementById("addform").submit();
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="role.do" method="post" id="addform">
			<input type="hidden" name="method" value="edit">
			<input type="hidden" name="id" value="${item.id}">
			<table class="stylized full">
				<tr>
					<td>角色名</td>
					<td><input type="text" id="role_name" name="role.role_name" value="${item.role_name}"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input value="保存" type="button" class="btn btn-green" onclick="checkForm()" id="btn_ok" /></td>
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