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
			alert("请选择商品图片");
			return false;
		}
		if($("#websrc").val()==""){
			alert("请填写商品链接");
			return false;
		}
		if($("#etc").val()==""){
			alert("请填写商品介绍");
			return false;
		}
		document.getElementById("addForm").submit();
	}
	function ajaxFileUpload() {
		if($("#time").val()==""){
			alert("请填写日期");
			return false;
		}
		$.ajaxFileUpload({
			url : 'fileUploadServlet?type=tao&time='+$("#time").val(),//用于文件上传的服务器端请求地址
			secureuri : false,//一般设置为false
			fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json',//返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
				$("#coversrc").attr("value", "img/tao/"+data.fileName);
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
		$('#myspan').html("<img src='img/tao/"+fileName+"'\/>");;
	}
	function checkUnique(){
		var nowdate = $("#time").val();
		jQuery.ajax({
			type : "POST",
			url : "tao.do",
			data : {
				"method" : "checkUnique",
				"time" : nowdate
			},
			success : function(result) {
				if(result==0){
					$("#msg").html("<font color=gray>日期正确<\/font>");//show(300).delay(10000).hide(300)
					setVol(nowdate);
				}else{
					$("#msg").html("<font color=red>日期已存在<\/font>");
					$("#time").val('');
					$("#time").focus();
				}
			}
		});
	}
	function setVol(nowdate){
		var startdate = new Date('2013-06-10');
		var enddate = new Date(nowdate);
		if(enddate<startdate){
			alert("时间有误，请选择2013-06-10以后的时间");
			$("#time").val('');
			$("#time").focus();
			return false;
		}
		var time = enddate.getTime() - startdate.getTime();
		var iDays = parseInt(time / (1000 * 60 * 60 * 24));
		var vol = iDays+1;
		if(vol<10)
			vol = "00"+vol;
		if(vol>=10&&vol<100)
			vol = "0"+vol;
		$("#vol").val(vol);
	}		
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form action="tao.do" method="post" id="addForm">
			<input type="hidden" name="method" value="doAdd"> 
			<input type="hidden" name="operator" value="${sessionScope.admin.name}"> 
			<table>
				<tr>
					<td>日期</td>
					<td>
						<input id="time" name="time"  onchange="checkUnique()" onclick="WdatePicker()" readonly="readonly" />
						<span id="msg"></span>
					</td>
				</tr>
				<tr>
					<td>刊号</td>
					<td>
						<input id="vol" name="vol" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>商品图片</td>
					<td>
						<input type="hidden" name="coversrc" id="coversrc" >
						<input type="file" id="file" name="file" />
						<input type="button" value="上传" onclick="ajaxFileUpload()" /><br>
						<span id="myspan"></span>
					</td>
				</tr>
				<tr>
					<td>商品链接</td>
					<td>
						<input id="websrc" name="websrc" size="50px" />
					</td>
				</tr>
				<tr>
					<td>商品介绍</td>
					<td>
						<textarea id="etc" name="etc" ></textarea>
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