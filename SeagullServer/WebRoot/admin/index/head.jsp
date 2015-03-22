<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title>头部</title>
<link type="text/css" href="${ctx}/resources/css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/resources/js/base/easyTooltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/superfish.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/custom.js"></script>
<script type="text/javascript">
function banBackSpace(e){
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;
    var t = obj.type || obj.getAttribute('type');
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    var flag1= ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")&& (vReadOnly==true || vDisabled==true);
    var flag2= ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ;
    if(flag2 || flag1)return false;
}
document.onkeydown=banBackSpace;
</script>
</head>
<body>	
	<div id="header">		
		<div id="top">
			<div class="meta">
				<p>欢迎 ,${admin.name }(${admin.channel})</p>
				<ul>
					<li><a href="admin.do?method=logout" title="登出" class="tooltip"><span class="ui-icon ui-icon-power"></span>登出</a></li>
					<li><a href="admin.do?method=toPassword" title="修改密码" class="tooltip"><span class="ui-icon ui-icon-wrench"></span>修改密码</a></li>
				</ul>	
			</div>
		</div>
		<div id="navbar">
			<ul class="nav">
				<li><a href="admin/index/index.jsp">首页</a></li>
				<li><a href="admin/page/opManage.jsp">后台管理</a></li>
			</ul>
		</div>
	</div>
</body>
</html>