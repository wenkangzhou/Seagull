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
	function checkForm() {
		var password = $("#password").val();
		var password1 = $("#password1").val();
		var password2 = $("#password2").val();
		if (password == "") {
			alert("原密码不能为空");
			return;
		} else if (password1 == "") {		
			alert("新密码不能为空");
			return;
		} else if (password2 != password1) {	
			alert("两次密码不一致");
			return;
		} else if(password2.length <6){	
			alert("密码不能小于6位");
			return;
		}else{
			document.getElementById("updatePassword").submit();
		}
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="admin.do" method="post" id="updatePassword">
			<input type="hidden" name="method" value="doPassword">
			<table cellspacing="1" cellpadding="0" >
				<tr>
					<td>原密码</td>
					<td><input name="password" id="password" type="password" maxlength="20" /> 
					<span class="red_star">*</span>
					</td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input name="password1" id="password1" type="password" maxlength="20" /> 
					<span class="red_star">*</span>
					</td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input name="password2" id="password2" type="password" maxlength="20" /> 
					<span class="red_star">*</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="color:red">${msg}</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td><input value="保存" type="button" class="btn" onclick="checkForm();"></td>
				</tr>
			</table>
		</form>
	  </div>
	  </div>
	  <jsp:include page="../index/left.jsp" >
  		<jsp:param name="sidebarType" value="first" /> 
	  </jsp:include>
	</div>
	<%@include file="../index/foot.jsp"%>
</body>
</html>