<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../index/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<bean:message key="base"/>">
<meta http-equiv="content-type" content="text/html; charset=<bean:message key="charset"/>">
<title><bean:message key="title" /></title>
<script type="text/javascript" src="${ctx}/resources/js/comm.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/base/jquery.js"></script>
<script type='text/javascript' src="${ctx }/resources/js/calendar/WdatePicker.js"></script>
<script type="text/javascript">
function checkForm(){
	document.getElementById('queryform').submit();
}
function csubstr(str, len) {
	if (str.length > len) {
		return str.substring(0, len) + "...";
	} else {
		return str;
	}
}
</script>
</head>
<body>
	<%@include file="../index/head.jsp"%>
	<div id="bgwrap">
	<div id="content">
	<div id="main">
		<form id="queryform" action="photo.do" method="post">
			<input type="hidden" name="method" value="List"> 
			<input type="hidden" id="pageNo" name="pageNo" value="${page.currentPage}">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.everyPage}">
				<table class="fullwidth" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td>开始时间</td>
						<td>
							<input id="startTime" name="startDate" value="${form.startDate}" onclick="WdatePicker()" readonly="readonly" />
						</td>
						<td>结束时间</td>
						<td>
							<input id="endTime" name="endDate" value="${form.endDate}" onclick="WdatePicker()" readonly="readonly" />
						</td>
						<td>操作员</td>
						<td>
							<input id="operator" name="operator" value="${form.operator}" />
						</td>
						<td>
							<input value="查询" type="button" class="btn btn-green" onclick="checkForm();">
							<input value="添加" type="button" class="btn btn-green" onclick="javascript:location='photo.do?method=toAdd';">
						</td>
					</tr>
				</table>
				<table class="fullwidth" cellspacing="0" cellpadding="0" border="0">
					<thead style="font-size: 12px;">
						<tr>
							<th>日期</th>
							<th>刊号</th>
							<th>图片</th>
							<th>名称</th>
							<th>作者</th>
							<th>语录</th>
							<th>操作员</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="list">
							<logic:iterate id="item" name="list">
								<tr>
									<td>
										${item.time}
									</td>
									<td>
										${item.vol}
									</td>
									<td>
										<img width="108" height="78" src="${item.src}">		
									</td>
									<td>
										${item.name}
									</td>
									<td>
										${item.author}
									</td>
									<td>
										<script type="text/javascript">
											document.writeln(csubstr('${item.quotation}',10));
										</script>
									</td>
									<td>
										${item.operator}
									</td>
									<td>
										<a href="photo.do?method=toEdit&id=${item.id}">编辑</a>
										<a href="photo.do?method=Preview&id=${item.id}" target="_blank">预览</a>
										<c:if test="${sessionScope.admin.channel == 'admin' }">
										<a href="javascript:#;" onclick="javascript:if(confirm('确认删除?'))location='photo.do?method=doDelete&id=${item.id}';">删除</a>
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
						<logic:notPresent name="list">
							<tr>
								<td colspan="9" class="list_data_text"><font color="red">当前没有任何信息</font>
								</td>
							</tr>
						</logic:notPresent>
					</tbody>
				</table>
				<div class="paging">共${page.totalCount }条记录 每页 
					<select onchange="changePageSize(this.value);">
						<logic:iterate id="size" name="size">
							<option value="${size }"
								<logic:equal value="${size}" name="page" property="everyPage">
								selected
								</logic:equal>>
								${size }
							</option>
						</logic:iterate>
					</select> 
					条 第 
					<input value="${page.currentPage }" size="2" />
					页/共${page.totalPage }页
					<a href="javascript:changePage(1,${page.totalPage},${page.everyPage})">第一页</a>
					<a href="javascript:changePage(${page.currentPage-1},${page.totalPage},${page.everyPage})">上一页</a>
					<a href="javascript:changePage(${page.currentPage+1},${page.totalPage},${page.everyPage})">下一页</a>
					<a href="javascript:changePage(${page.totalPage},${page.totalPage},${page.everyPage})">最后一页</a>
					转到 <input id="goto" size="2" /> 页
					 <input value="GO" type="button" width="20" class="common_button" onclick="changePage('goto',${page.totalPage},${page.everyPage});">
				</div>
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