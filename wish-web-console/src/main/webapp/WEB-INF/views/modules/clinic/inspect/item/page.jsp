<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>检验项管理</title>
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
    <li class="active"><a href="${ctx}/clinic/inspect/item">检验项列表</a></li>
        <li><a href="${ctx}/clinic/inspect/item/read">检验项添加</a></li>
</ul>
<form id="searchForm" action="${ctx}/clinic/inspect/item" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>检验项 ：</label><input type="text" name="itemName"  maxlength="50" class="input-medium" value="${itemName}"/> &nbsp;
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th> <th>检验项名称</th> <th>检验项英文名</th> <th>指标编码</th> <th>创建时间</th> <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="element" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td><a href="${ctx}/clinic/inspect/item/read?id=${element.id}" title="${element.itemName}">${element.itemName}</a></td>
            <td>${element.englishName} </td>
            <td>${element.indexName}</td>
            <td><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td>
            <shiro:hasPermission name="sys:dict:edit">
                <td>
                    <a href="${ctx}/clinic/inspect/item/read?id=${element.id}">修改</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>



</body>
<script src="${content.contextPath}/resources/plugins/metronic/assets/paginator/bootstrap-paginator.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        /**
         * 总行数 private long totalCount; 每页多少条记录 private int pageSize; 当前第几页 private int
         * pageNo; 结果集数量 private int rSum = 0; 结果集
         */
        var options = {
            currentPage : parseInt($("#pageNo").val()),
            totalPages : parseInt($("#totalCount").val())
            / parseInt($("#pageSize").val()) <= 1 ? 1 : Math.ceil(parseInt($(
                            "#totalCount").val())
                    / parseInt($("#pageSize").val())),
            size : "normal",
            alignment : "center",
            numberOfPages : $("#pageSize").val(),
            itemTexts : function(type, page, current) {
                switch (type) {
                    case "first":
                        return "第一页";
                    case "prev":
                        return "<";
                    case "next":
                        return ">";
                    case "last":
                        return "最后页";
                    case "page":
                        return page;
                }
            },
            onPageChanged : function(e, oldPage, newPage) {
                //	 $("#pageNo").val(oldPage);
                //	 $("#userpost").submit();
            },
            onPageClicked : function(e, originalEvent, type, page) {
                $("#pageNo").val(page);
                /* $("#userpost").prepend("<input type='hidden' name='pageSearch' value='1' />"); */
                    $('.container-fluid').load('${content.contextPath}/inspectItem/page table', $("#userpost").serialize());
            }
        }
        $('#dtpage').bootstrapPaginator(options);
        var initHtml = $('.container').html();
        $('form :button').click(function(){
            var key = $('#itemName').val();
            if (key === '') {
                $('.container-fluid').html(initHtml);
            }else{
                $("#pageNo").val(1);
                $('.container-fluid').load('${content.contextPath}/inspectItem/page table', $("#userpost").serialize());
            }
        })

        //ajax加载提示
        $(document).ajaxStart(function(){
            layer.load(0, {shade: [0.1,'#fff'] });  //0.1透明度的白色背景
        }).ajaxComplete(function(){
            layer.closeAll('loading');
        });
    });

    function newEntity(){
        layerBox('新增检验项', '/bsp/inspectItem/detail');
    }

    function updateEntity(pkId){
        layerBox('修改检验项', '/bsp/inspectItem/detail?pkId='+pkId);
    }
</script>
</html>