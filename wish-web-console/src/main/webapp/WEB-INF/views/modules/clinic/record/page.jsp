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
	<li class="active"><a href="${ctx}/clinic/record">临床记录列表</a></li>
	<li><a href="${ctx}/clinic/record/ready">临床记录添加</a></li>
</ul>
<form id="searchForm" action="${ctx}/clinic/record" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<label>检验单名称 ：</label><input type="text" name="inspectName"  maxlength="50" class="input-medium" value="${inspectName}"/> &nbsp;
	<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>序号</th> <th>检验单名称</th> <th>日期</th> <th>介绍</th> <th>状态</th> <th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="element" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td><a href="#" title="${element.inspectName}">${element.inspectName}</a></td>
			<td><fmt:formatDate value="${element.inspectDate}" pattern="yyyy-MM-dd"/></td>
			<td>${element.description} </td>
			<td>${element.status}</td>
			<shiro:hasPermission name="sys:dict:edit">
				<td>
					<a href="${ctx}/clinic/record/read?id=${element.id}">修改</a>
				</td>
			</shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>


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