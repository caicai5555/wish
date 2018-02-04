<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>检验单管理</title>
    <style>
        .form-group { margin-bottom: 10px!important;}
        table {
            table-layout: fixed;
        }
        td {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
    <script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/clinic/inspect/list">检验单列表</a></li>
    <li><a href="${ctx}/clinic/inspect/list/read">检验单添加</a></li>
</ul>
<form id="searchForm" action="${ctx}/clinic/inspect/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>检验单名称 ：</label><input type="text" name="inspectName"  maxlength="50" class="input-medium" value="${inspectName}"/> &nbsp;
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th> <th>检验单名称</th> <th>检验单英文名</th> <th>创建时间</th> <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="element" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td><a href="${ctx}/clinic/inspect/list/read?id=${element.id}" title="${element.inspectName}">${element.inspectName}</a></td>
            <td>${element.englishName} </td>
            <td><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td>
            <shiro:hasPermission name="sys:dict:edit">
                <td>
                    <a href="${ctx}/clinic/inspect/list/read?id=${element.id}">修改</a>
                    <a href="${ctx}/clinic/inspect/list/authorize?id=${element.id}">授权</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>

</body>
</html>