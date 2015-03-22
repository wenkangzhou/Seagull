<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title>左侧菜单</title>
</head>
<body>	
	<div id="sidebar">
	   <shop:auth auth_code="SHOUYE"> 
	   <div style="display:   
	   <% if(!request.getParameter("sidebarType").equals("first"))
	    {   
	   %>
	   <%="none"%>
	   <%}%>
		">    
		<h2><a href="admin/index/index.jsp">首页 </a></h2>
			<ul >
				<li><a href="admin/index/index.jsp">hello guys</a></li>
			</ul>
		</div>	
		</shop:auth>
		
		<shop:auth auth_code="HOUTAIGUANLI"> 
		<div style="display:
		<% if(!request.getParameter("sidebarType").equals("second"))
	   {   
	   %>
	   <%="none"%>
	   <%}%>
		">  
		
		<h2><a href="admin/page/opManage.jsp">后台管理</a></h2>
			<ul>
				<shop:auth auth_code="NEIRONGGUANLI">
				<li>
					<a href="javascript:void(0)">内容管理</a>
					<ul>
						<shop:auth auth_code="TU">
						<li><a href="photo.do?method=List">图</a></li>
						</shop:auth>
						<shop:auth auth_code="YIN">
						<li><a href="music.do?method=List">音</a></li>
						</shop:auth>
						<shop:auth auth_code="TAO">
						<li><a href="tao.do?method=List">淘</a></li>
						</shop:auth>
						<shop:auth auth_code="SUI">
						<li><a href="sui.do?method=List">随</a></li>
						</shop:auth>
						<shop:auth auth_code="FANKUI">
						<li><a href="feedback.do?method=List">反馈</a></li>
						</shop:auth>	
						<shop:auth auth_code="TOUGAO">
						<li><a href="upload.do?method=List">投稿</a></li>
						</shop:auth>	
					</ul>
				</li>
				</shop:auth>
				<shop:auth auth_code="HOUTAIQUANXIANGUANLI">
				<li><a href="javascript:void(0)">后台权限管理</a>
					<ul>
						<li><a href="channel.do?method=toList">账户管理</a></li>
						<li><a href="role.do?method=toList">角色管理</a></li>
						<li><a href="auth.do?method=toList">权限管理</a></li>
					</ul>
				</li>
				</shop:auth>
			</ul>
		</div>	
		</shop:auth>
	</div>

</body>
</html>