<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>检验项管理</title>
	<style>
		.red-hint:before {
			padding: 0 5px;
			color:red;
			content: "*";
		}
	</style>
</head>

<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/clinic/record/page">临床记录列表</a></li>
	<li class="active"><a href="${ctx}/clinic/record/ready">临床记录添加<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form id="inputForm" action="/admin/clinic/record/create"  method="post" class="form-horizontal">
	<input type="text" class="hide" name="id" value="${entity.id}">
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">记录类型:</label>
		<div class="controls">
			<select name="typeId">
				<c:forEach items="${types}" var="item">
					<option value="${item.key}">${item.value}</option>
				</c:forEach>
			</select>
			<input type="text" class="hide" name="typeName" value="">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">检验单名称:</label>
		<div class="controls">
			<select name="inspectId">
				<c:forEach items="${inspectLists}" var="item">
					<option value="${item.id}">${item.inspectName}</option>
				</c:forEach>
			</select>
			<input type="text" class="hide" name="inspectName" value="">
		</div>
	</div>
	<div class="form-actions">
		<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form>

<script type="text/javascript">
	$(function(){
		$('#btnSubmit').click(function(){
			$('[name="typeName"]').val($('[name="typeId"] :selected').text());
			$('[name="inspectName"]').val($('[name="inspectId"] :selected').text());
		})
	})
</script>
</body>
</html>