<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ include file="index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="Content-Type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<link type="text/css" href="${ctx}/resources/css/login.css" rel="stylesheet" />	
<script type="text/javascript" src="resources/js/base/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#J_submit").click( function() {
		  var username = $("#username"),
		  	  password = $("#password");
		  if(username.val()=="")
		  {
			$("#Mes").html("请输入用户名！").show(300).delay(2000).hide(300);
			return false;
		  }
		  if(password.val()=="")
		  {
			$("#Mes").html("请输入密码！").show(300).delay(2000).hide(300);
			return false;
		  }
		  $("#loginform").submit();
		 });
	
	//将cookie中的值取出
	var username = getCookieValue("channel_username");
	if (username != 'undefined' && username != null && username != 'null') {
		$("#username").attr("value", getCookieValue("channel_username"));
		$("#password").attr("value", getCookieValue("channel_password"));
		$("#remember").attr("checked", true);
	}
	
});
function getCookieValue(name){   
    var name = escape(name);   
    var allcookies = document.cookie;   
    name += "=";   
    var pos = allcookies.indexOf(name);   
    if (pos != -1){
        var start = pos + name.length;                  
        var end = allcookies.indexOf(";",start);          
        if (end == -1) end = allcookies.length;         
        var value = allcookies.substring(start,end);  
        return unescape(value);                          
        }   
    else return ""; 
} 
function keyLogin(){  
    if (event.keyCode==13)   
         document.getElementById("J_submit").click(); 
} 
</script>
</head>
<body onkeydown="keyLogin();">
	<div id="container">
		<div class="logo">
		</div>
		<div id="box">
			<form id="loginform" action="admin.do" method="post">
				<input type="hidden" name="method" value="login" />
				<p class="main">
					<label>Username: </label>
					<input id="username" name="loginName" /> 
					<label>Password: </label>
					<input id="password" type="password" name="password" >	
				</p>
				<div id="Mes">${error}</div>
				<p class="space">
					<span><input type="checkbox" id="remember" value="1" name="remember"/>Remember me</span>
					<span><a id="J_submit" href="javascript:;" class="login">登录</a></span>
				</p>
			</form>
		</div>
	</div>
</body>
</html>