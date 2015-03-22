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
		if($("#src").val()==""){
			alert("请选择图片");
			return false;
		}
		if($("#name").val()==""){
			alert("请填写名称");
			return false;
		}
		if($("#author").val()==""){
			alert("请填写作者");
			return false;
		}
		if($("#quotation").val()==""){
			alert("请填写语录");
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
			url : 'fileUploadServlet?type=photo&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#src").attr("value", "img/photo/"+data.fileName);
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
		$('#myspan').html("<img src='img/photo/"+fileName+"'\/>");;
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="photo.do" method="post" id="editForm">
			<input type="hidden" name="method" value="doEdit"> 
			<input type="hidden" name="id" value="${photo.id}">
			<input type="hidden" name="operator" value="${sessionScope.admin.name}"> 
			<table>
				<tr>
					<td>日期</td>
					<td>
						<input id="time" name="time" value="${photo.time}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>刊号</td>
					<td>
						<input id="vol" name="vol" value="${photo.vol}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>图片</td>
					<td>
						<input type="hidden" name="src" id="src" value="${photo.src}">
						<input type="file" id="file" name="file" />
						<input type="button" value="上传" onclick="ajaxFileUpload()" /><br>
						<span id="myspan"><img src="${photo.src}"></span>
					</td>
				</tr>
				<tr>
					<td>名称</td>
					<td>
						<input id="name" name="name" value="${photo.name}" />
					</td>
				</tr>
				<tr>
					<td>作者</td>
					<td>
						<input id="author" name="author" value="${photo.author}" />
					</td>
				</tr>
				<tr>
					<td>语录</td>
					<td>
						<input id="quotation" name="quotation" value="${photo.quotation}" size="108px" />
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