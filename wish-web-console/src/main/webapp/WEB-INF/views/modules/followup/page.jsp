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
	<li class="active"><a href="${ctx}/followup/page">随访列表</a></li>
	<li><a href="${ctx}/followup/read">随访添加</a></li>
</ul>
<form id="searchForm" action="${ctx}/followup/page" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<%--<label>检验单名称 ：</label><input type="text" name="inspectName"  maxlength="50" class="input-medium" value=""/> &nbsp;--%>
	<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>--%>
</form>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>序号</th> <th>启动时间</th> <th>随访计划</th> <th>所属机构</th> <th>类型</th> <th>模板</th> <th>随访间隔</th> <th>次数</th> <th>状态</th> <th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="element" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>
				<input type="text" class="form-control" name="startDate" value='<fmt:formatDate value="${element.startDate}" pattern="yyyy-MM-dd"/>' placeholder="开启日期"
					   maxlength="128" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" data-id = "${element.id}"/>
			</td>
			<td><a href="#" title="${element.name}">${element.name}</a></td>
			<td>${element.orgName} </td>
			<td>
				<c:if test="${element.type == 0}"> 定期 </c:if>
				<c:if test="${element.type == 1}"> 临时 </c:if>
			</td>
			<td>${element.questName}</td>
			<td>${element.intervals}</td>
			<td>${element.times}</td>
			<td>
				<c:if test="${element.status == 0}"> 未开始 </c:if>
				<c:if test="${element.status == 1}"> 进行中 </c:if>
				<c:if test="${element.status == 2}"> 已完成 </c:if>
			</td>
			<shiro:hasPermission name="sys:dict:edit">
				<td>
					<a href="${ctx}/followup/read?id=${element.id}">修改</a>
					<a href="${ctx}/followup/startup?id=${element.id}&status=1&times=${element.times}
					&intervals=${element.intervals}&questName=${element.questName}&name=${element.name}
					&type=${element.type}&questId=${element.questId}&startDate=" class="startup">启动</a>
				</td>
			</shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th class="bg">随访日期</th> <th class="bg">随访计划</th> <th class="bg">类型</th> <th>模板</th> <th class="bg">随访状态</th> <th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${pageItem.list}" var="element" varStatus="status">
		<tr>
			<td><fmt:formatDate value="${element.followupDate}" pattern="yyyy-MM-dd"/></td>
			<td>${element.name} </td>
			<td>
				<c:if test="${element.type == 0}"> 定期 </c:if>
				<c:if test="${element.type == 1}"> 临时 </c:if>
			</td>
			<td>${element.questName}</td>
			<td>
				<c:if test="${element.status == 0}"> 未随访 </c:if>
				<c:if test="${element.status == 1}"> 已随访 </c:if>
			</td>
			<shiro:hasPermission name="sys:dict:edit">
				<td>
					<c:if test="${element.status == 1}">
						<a href="${ctx}/questionnaire/detail?id=${element.recordId}">查看</a>
					</c:if>
					<c:if test="${element.status == 0}">
						<a href="${ctx}/questionnaire/record?id=${element.questId}&detailId=${element.id}">填写问卷</a>
					</c:if>
				</td>
			</shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${pageItem}</div>

<script>
    function closeWindow(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
    $(function(){
        var param = $('#param').serialize();

        $("#addRecord").click(function(){
            var title=$(this).attr("title");
            var content=$(this).attr("href") + param;
            parent.layerBox(title,content,"600px","250px");
            return false;
        })

        $('.multi-param').click(function(){
            $(this).attr('href', $(this).attr('href') + param);
        });

        $('.startup').click(function () {
            var startDate= $(this).closest('tr').find('[name="startDate"]').val();
            $(this).attr('href', $(this).attr('href') + startDate);
        })

		$('[name="startDate"]').each(function () {
			var val = $(this).val().trim();
			if (val != ''){
			    $(this).closest('tr').find('.startup').remove();
			}
        })

    })
    //多行文字截取
    $(function(){
        $(".figCont").each(function(){
            var boxHeight = $(this).height();
            while ($(this).children("p").height() > boxHeight){
                $(this).children("p").text($(this).children("p").text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
            }
        });
    })
    $(window).resize(function(){
        $(".figCont").each(function(){
            var boxHeight = $(this).height();
            while ($(this).children("p").height() > boxHeight){
                $(this).children("p").text($(this).children("p").text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
            }
        });
    })
</script>
</body>
</html>
