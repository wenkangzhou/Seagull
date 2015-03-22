<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.js"></script>
<script type="text/javascript">
	function checkForm(){
		var jq = jQuery.noConflict(); 
		var loginName = jq("#code").attr("value");
		if (loginName == "") {
			alert("用户名不能为空");
			return;
		}
		document.getElementById('addform').submit();
	}
	function goBack() {
		location.href = "channel.do?method=toList";
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="channel.do" method="post" id="addform">
			<input type="hidden" name="method" value="save">
			<input type="hidden" id="id" name="id" value="${channel.id}">
			<table class="stylized full">
				<tr>
					<td>
						用户名
					</td>
					<td>
						<input  name="code" id="code" value="${channel.channel}" maxlength="50"/>
						<span class="red_star">*
						<logic:present name="errMsg">${errMsg}</logic:present>
						</span>
					</td>
				</tr>
				<tr>
					<td>
						昵称
					</td>
					<td>
						<input name="name" id="name" value="${channel.name}" maxlength="50"/>
					</td>
				</tr>						
				<tr>
					<td>
						所属角色
					</td>
					<td>
						<select name="role_id">
						<c:forEach items="${listRole}" var="role">
						<option value="${role.id}"
						<c:if test="${role.id==channel.role.id}">selected</c:if>>${role.role_name}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input value="保存" type="button" class="btn btn-green" onclick="checkForm();" id="btn_ok" />
						<input value="返回" type="button" class="btn btn-green" id="btn_back" onclick="javascript:goBack();loading();" />
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