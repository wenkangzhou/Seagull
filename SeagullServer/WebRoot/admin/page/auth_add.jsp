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
		document.getElementById("addform").submit();
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="auth.do" method="post" id="addform">
			<input type="hidden" name="method" value="add">
			<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
				<tr>
					<td >权限名</td>
					<td><input type="text" id="auth_name" name="auth.auth_name"/></td>
				</tr>
				<tr>
					<td>权限代码</td>
					<td><input type="text" id="auth_code" name="auth.auth_code"/></td>
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