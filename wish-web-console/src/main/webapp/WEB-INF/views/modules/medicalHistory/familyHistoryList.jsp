<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>家族病史管理</title>
	<meta name="decorator" content="default"/>
	<script src="/static/common/layer.js"></script>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/medicalHistory/familyHistory/index");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/medicalHistory/familyHistory/index">家族病史列表</a></li>
		<!--<shiro:hasPermission name="sys:user:edit">-->
		<li><a href="${ctx}/medicalHistory/familyHistory/form">家族病史添加</a></li>
		<!--</shiro:hasPermission>-->
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/medicalHistory/familyHistory/index" method="post" class="breadcrumb form-search ">
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
			<th>亲属关系</th>
			<th>患病年龄</th>
			<th>所患疾病</th>
			<th>是否遗传</th>
			<th>记录人</th>
			<th>记录时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="history">
			<tr>
				<td>${fns:getDictLabel(history.relativeCode,'relative','')}</td>
				<td>${history.diseaseAge}</td>
				<td>
					<c:choose>
						<c:when test="${history.diseaseCode ne 'other'}">  
					         ${fns:getDictLabel(history.diseaseCode,'disease','')}    
						</c:when>
						<c:otherwise>
							${history.diseaseName}
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${history.geneticFlag eq '0'}">  
					    	是  
						</c:when>
						<c:when test="${history.geneticFlag eq '1'}">  
					       	否  
						</c:when>
						<c:otherwise>
							不确定
						</c:otherwise>
					</c:choose>
				</td>
				<td>${history.createBy}</td>
				<td><fmt:formatDate value="${history.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<a href ="${ctx}/medicalHistory/familyHistory/form?id=${history.id}">修改</a>
    				<a href="${ctx}/medicalHistory/familyHistory/delete?id=${history.id}" onclick="return confirmx('要删除该家族病史吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>