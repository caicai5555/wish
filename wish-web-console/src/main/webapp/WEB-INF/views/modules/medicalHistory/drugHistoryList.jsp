<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用药史管理</title>
	<meta name="decorator" content="default"/>
	<script src="/static/common/layer.js"></script>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/medicalHistory/drugHistory/index");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/medicalHistory/drugHistory/index">用药史列表</a></li>
		<!--<shiro:hasPermission name="sys:user:edit">-->
		<li><a href="${ctx}/medicalHistory/drugHistory/form">用药史添加</a></li>
		<!--</shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/medicalHistory/drugHistory/index" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>药物名称</th>
			<th>记录人</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="history">
			<tr>
				<td>${history.drugNameStr}</td>
				<td>${history.createBy}</td>
				<td><fmt:formatDate value="${history.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<a href ="${ctx}/medicalHistory/drugHistory/form?id=${history.id}">修改</a>
    				<a href="${ctx}/medicalHistory/drugHistory/delete?id=${history.id}" onclick="return confirmx('要删除该用药史吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>