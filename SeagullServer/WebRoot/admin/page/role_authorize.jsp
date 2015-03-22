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
		<form action="role.do" method="post" id="addform">
			<input type="hidden" name="method" value="authorize">
			<input type="hidden" name="id" value="${role.id}">
			<table>
				<tr>
					<td width="15%">角色名</td>
					<td><input type="text" readonly="readonly" id="role_name" name="role.role_name" value="${role.role_name}"/></td>
				</tr>
				<tr>
					<td>权限点</td>
					<td>
						<ul style="list-style: none;">
							<c:forEach items="${listAuth}" var="item">
								<li style="float:left;width:25%;min-width:100px;margin-right:10px;line-height:30px;height:30px;margin-left:-20px;">
								<input type="checkbox" name="auth_id" value="${item.id}"
								<c:forEach items="${role.auth}" var="auth">
									<c:if test="${item.id==auth.id}"> checked=true</c:if>
								</c:forEach>
								> ${item.auth_name}
								</li>
							</c:forEach>
						</ul>
					</td>
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