<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3}).show();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/evaluate/catgroyList">分类列表</a></li>
		<li><a href="${ctx}/evaluate/categoryForm">分类添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
		<thead>
			<tr>
				<th>名称</th>
				<th>英文名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="category">
			<tr id="${category.id}" pId="${category.pid ne '1'?category.pid:'0'}">
				<td nowrap>${category.name}</td>
				<td nowrap>${category.enname}</td>
				<td nowrap>
					<a href="${ctx}/evaluate/categoryForm?id=${category.id}">修改</a>
					<a href="${ctx}/evaluate/categoryDelete?id=${category.id}" onclick="return confirmx('要删除该分类及所有子分类项吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>