<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估管理</title>
	<meta name="decorator" content="default"/>
	<script src="/static/common/layer.js"></script>
	<script type="text/javascript">
		function test(id){
			var url = "${ctx}/evaluate/test?id="+id;
			var title = "测试"
			
			layer.open({
			    type: 2,
			    title: title,
			    maxmin: true,
			    shadeClose: true, //点击遮罩关闭层
			    area : ['800px' , '600px'],
			    content: url
			 });
		}
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/evaluate/index");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/evaluate/index">评估列表</a></li>
		<!--<shiro:hasPermission name="sys:user:edit">-->
		<li><a href="${ctx}/evaluate/add">评估添加</a></li>
		<!--</shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" action="${ctx}/evaluate/index" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label style="width:150px;">评估英文名称：</label><input name="enname" id="enname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>评估名称</th>
			<th>评估英文名称</th>
			<th>状态</th>
			<th>模式</th>
			<th>执行次数</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="info">
			<tr>
				<td>${info.name}</td>
				<td>${info.enname}</td>
				<td>${info.status}</td>
				<td>${info.mode}</td>
				<td>${info.times}</td>
				<td>
					<a href ="${ctx}/evaluate/test?id=${info.id}">测试</a>
    				<a href="${ctx}/evaluate/delete?id=${info.id}" onclick="return confirmx('要删除该评估规则吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>