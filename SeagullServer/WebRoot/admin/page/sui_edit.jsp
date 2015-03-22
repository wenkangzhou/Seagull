<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/ajaxfileupload.js"></script>
<script type='text/javascript' src="${ctx }/resources/js/calendar/WdatePicker.js"></script>
<script type="text/javascript">
	function checkForm() {
		if($("#time").val()==""){
			alert("请填写日期");
			return false;
		}
		if($("#vol").val()==""){
			alert("请填写刊号");
			return false;
		}
		if($("#coversrc").val()==""){
			alert("请选择图片");
			return false;
		}
		if($("#websrc").val()==""){
			alert("请填写链接");
			return false;
		}
		if($("#etc").val()==""){
			alert("请填写介绍");
			return false;
		}
		if($("#type").val()==""){
			alert("请选择类型");
			return false;
		}
		if($("#typesrc").val()==""){
			alert("请选择图标");
			return false;
		}
		if($("#title").val()==""){
			alert("请填写标题");
			return false;
		}
		document.getElementById("editForm").submit();
	}
	function ajaxFileUpload() {
		if($("#time").val()==""){
			alert("请填写日期");
			return false;
		}
		$.ajaxFileUpload({
			url : 'fileUploadServlet?type=sui&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#coversrc").attr("value", "img/sui/"+data.fileName);
				setTimeout("t('"+data.fileName+"')",1000);	
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						$('#myspan').html(data.message);
					} else {
						$('#myspan').html(data.message);
					}
				}
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				$('#myspan').html(e);
			}
		});
		return false;
	}
	function t(fileName){
		$('#myspan').html("<img src='img/sui/"+fileName+"'\/>");;
	}
	function ajaxIconFileUpload() {
		if($("#time").val()==""){
			alert("请填写日期");
			return false;
		}
		$.ajaxFileUpload({
			url : 'fileUploadServlet?type=typeicon&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'iconfile',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#typesrc").attr("value", "img/sui/"+data.fileName);
				setTimeout("i('"+data.fileName+"')",1000);	
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						$('#icon').html(data.message);
					} else {
						$('#icon').html(data.message);
					}
				}
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				$('#icon').html(e);
			}
		});
		return false;
	}
	function i(fileName){
		$('#icon').html("<img width='40' height='40' src='img/sui/"+fileName+"'\/>");;
	}
	function changeicon(){
		if($('#type').val()=='网页'){
			$('#icon').html("<img width='40' height='40' src='img/sui/sui_web.jpg'>");
			$("#typesrc").attr("value", "img/sui/sui_web.jpg");
		}
		if($('#type').val()=='问答'){
			$('#icon').html("<img width='40' height='40' src='img/sui/sui_zhi.jpg'>");
			$("#typesrc").attr("value", "img/sui/sui_zhi.jpg");
		}
		if($('#type').val()=='电影'){
			$('#icon').html("<img width='40' height='40' src='img/sui/sui_dou.jpg'>");
			$("#typesrc").attr("value", "img/sui/sui_dou.jpg");
		}
		if($('#type').val()=='资源'){
			$('#icon').html("<img width='40' height='40' src='img/sui/sui_share.jpg'>");
			$("#typesrc").attr("value", "img/sui/sui_share.jpg");
		}
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="sui.do" method="post" id="editForm">
			<input type="hidden" name="method" value="doEdit"> 
			<input type="hidden" name="id" value="${sui.id}">
			<input type="hidden" name="operator" value="${sessionScope.admin.name}"> 
			<table>
				<tr>
					<td>日期</td>
					<td>
						<input id="time" name="time" value="${sui.time}"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>刊号</td>
					<td>
						<input id="vol" name="vol" value="${sui.vol}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>图片</td>
					<td>
						<input type="hidden" name="coversrc" id="coversrc" value="${sui.coversrc}">
						<input type="file" id="file" name="file" />
						<input type="button" value="上传" onclick="ajaxFileUpload()" /><br>
						<span id="myspan"><img src="${sui.coversrc}"></span>
					</td>
				</tr>
				<tr>
					<td>链接</td>
					<td>
						<input id="websrc" name="websrc" value="${sui.websrc}" size="70px" />
					</td>
				</tr>
				<tr>
					<td>介绍</td>
					<td>
						<textarea id="etc" name="etc" >${sui.etc}</textarea>
					</td>
				</tr>
				<tr>
					<td>类型</td>
					<td>
						<select id="type" name="type" onchange="changeicon()">
							<option value="网页" <c:if test="${sui.type == '网页' }">selected</c:if>>网页</option>
							<option value="应用" <c:if test="${sui.type == '应用' }">selected</c:if>>应用</option>
							<option value="问答" <c:if test="${sui.type == '问答' }">selected</c:if>>问答</option>
							<option value="游戏" <c:if test="${sui.type == '游戏' }">selected</c:if>>游戏</option>
							<option value="电影" <c:if test="${sui.type == '电影' }">selected</c:if>>电影</option>
							<option value="资源" <c:if test="${sui.type == '资源' }">selected</c:if>>资源</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>类型图标</td>
					<td>
						<input type="hidden" name="typesrc" id="typesrc" value="${sui.typesrc}">
						<span id="icon"><img width="40" height="40" src="${sui.typesrc}"></span>
						<input type="file" id="iconfile" name="iconfile" />
						<input type="button" value="上传" onclick="ajaxIconFileUpload()" />
					</td>
				</tr>
				<tr>
					<td>标题</td>
					<td>
						<input id="title" name="title" value="${sui.title}"  />
					</td>
				</tr>
				<tr>
					<td>
						<input value="返回" type="button" class="btn btn-green" onclick="javascript:history.go(-1)" />
					</td>
					<td>
						<input value="保存" type="button" class="btn btn-green" onclick="checkForm();"  />
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