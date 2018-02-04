<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <title>疾病管理</title>
    <%@ include file="/WEB-INF/views/include/bootstrap-datetimepicker.jsp" %>
    <%@ include file="/WEB-INF/views/include/treeview.jsp" %>
</head>

<body>
<input type="hidden" id="contextPath" value="${ctx}"/>
<div>
    <div id="sample_1_filter" class="dataTables_filter">
        <form class="form-horizontal" id="searchForm" method="post"
              action="${ctx}/diseaseInfo/diseaseInfoPage">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
            <%--临时存储一级科室--%>
            <input type="hidden" id="parentCategory" name="parentCategory" value="">
            <%--临时存储二级科室--%>
            <input type="hidden" id="childCategory" name="childCategory" value="">

            <!-- <label class="control-label">疾病组</label> -->
            <input  id="diseasClasses" name="diseasClasses" style="width: 150px;    " readonly
                   value="${params.diseasClasses}" type="text"
                   onclick="showDiseaseClasses(); return false;" placeholder="选择疾病分类">
            <div id="menuContent"
                 style="display: none; position: absolute; background-color:White;">
                <ul id="treeIndex" class="ztree" style="margin-top: 0; width: 150px;"></ul>
            </div>
            <input type="hidden" id="pid" name="pid" value="${params.pid}">

            <select id="categoryParent" name="categoryParent" class="form-control"
                    style="width: 150px;">
            </select>

            <select id="categoryChild" name="categoryChild" placeholder="二级科室"
                              class="form-control"  style="width: 150px;">
            </select>

            <input type="text"  class="form_datetime" id="createDate" name="createDate" value="${params.createDate}"  placeholder="创建日期" style="width: 150px;" readonly/>

            <input class="form-control" id="name" name="name" type="text" style="width: 150px;"
                    placeholder="关键字:疾病信息" value="${params.name}"/>

            <button class="btn btn-default" type="submit">
                <i class="fa fa-search"></i> 搜索
            </button>

            <div class="caption form-group pull-right">
                <a href="javascript:;" class="btn green"
                   onclick="newDiseaseInfo()"> 新建疾病信息</a>
            </div>
        </form>
    </div>


    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>疾病名称</th>
            <th>别名</th>
            <th>是否开启</th>
            <th>建立时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="diseaseInfo">
            <tr>
                <td>${diseaseInfo.name}</td>
                <td>${diseaseInfo.alias}</td>
                <td>
                    <c:if test="${diseaseInfo.activeFlag==0}">
                       启用
                    </c:if>
                    <c:if test="${diseaseInfo.activeFlag==1}">
                        禁用
                    </c:if>
                </td>
                <td><fmt:formatDate value="${diseaseInfo.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <shiro:hasPermission name="sys:user:edit">
                    <td>
                        <a href="javascript:void(0)" onclick="infoDetail('${diseaseInfo.id}')">详情 </a> <a
                            href="javascript:void(0)" onclick="openUpdate('${diseaseInfo.id}')">修改 </a>
                        <c:if test="${diseaseInfo.activeFlag==0}">
                            <a href="javascript:void(0)" onclick="updateActiveFlag('${diseaseInfo.id}','${diseaseInfo.activeFlag}');">禁用</a>
                        </c:if>
                        <c:if test="${diseaseInfo.activeFlag==1}">
                            <a href="javascript:void(0)" onclick="updateActiveFlag('${diseaseInfo.id}','${diseaseInfo.activeFlag}');">启用</a>
                        </c:if>
                    </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</div>
<script src="${ctxStatic}/console/js/disease/diseaseInfoList.js"></script>
<%--<script src="${ctxStatic}/console/js/disease/diseaseCategory.js"></script>--%>
<script type="text/javascript">

    //日历初始化
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0

    });

    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/diseaseInfo/diseaseInfoPage");
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>