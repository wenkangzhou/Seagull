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
			alert("请选择唱片封面");
			return false;
		}
		if($("#name").val()==""){
			alert("请填写歌曲名称");
			return false;
		}
		if($("#author").val()==""){
			alert("请填写演唱者");
			return false;
		}
		if($("#musicsrc").val()==""){
			alert("请填写歌曲链接");
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
			url : 'fileUploadServlet?type=music&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#coversrc").attr("value", "img/music/"+data.fileName);
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
		$('#myspan').html("<img src='img/music/"+fileName+"'\/>");;
	}
	function ajaxFileMusicUpload() {
		if($("#time").val()==""){
			alert("请填写日期");
			return false;
		}
		$.ajaxFileUpload({
			url : 'fileUploadServlet?type=m4a&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'musicfile',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#musicsrc").attr("value", "audio/"+data.fileName);
				setTimeout("m('"+data.fileName+"')",1000);	
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						$('#mymspan').html(data.message);
					} else {
						$('#mymspan').html(data.message);
					}
				}
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				$('#mymspan').html(e);
			}
		});
		return false;
	}
	function m(fileName){
		$('#mymspan').html("<audio src='audio/"+fileName+"'\controls=\"controls\" ><\/audio>");;
	}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="music.do" method="post" id="editForm">
			<input type="hidden" name="method" value="doEdit"> 
			<input type="hidden" name="id" value="${music.id}">
			<input type="hidden" name="operator" value="${sessionScope.admin.name}"> 
			<table>
				<tr>
					<td>日期</td>
					<td>
						<input id="time" name="time" value="${music.time}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>刊号</td>
					<td>
						<input id="vol" name="vol" value="${music.vol}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>唱片封面</td>
					<td>
						<input type="hidden" name="coversrc" id="coversrc" value="${music.coversrc}">
						<input type="file" id="file" name="file" />
						<input type="button" value="上传" onclick="ajaxFileUpload()" /><br>
						<span id="myspan"><img src="${music.coversrc}"></span>
					</td>
				</tr>
				<tr>
					<td>歌曲名称</td>
					<td>
						<input id="name" name="name" value="${music.name}" />
					</td>
				</tr>
				<tr>
					<td>演唱者</td>
					<td>
						<input id="author" name="author" value="${music.author}" />
					</td>
				</tr>
				<tr>
					<td>歌曲链接</td>
					<td>
						<input type="hidden" name="musicsrc" id="musicsrc" value="${music.musicsrc}">
						<input type="file" id="musicfile" name="musicfile" />
						<input type="button" value="上传" onclick="ajaxFileMusicUpload()" /><br>
						<span id="mymspan"><audio src="${music.musicsrc}" controls="controls"></audio></span>
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