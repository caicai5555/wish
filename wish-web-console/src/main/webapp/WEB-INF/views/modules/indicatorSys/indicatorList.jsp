<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
  <meta charset="utf-8" />
  <title>指标管理</title>
  <meta name="decorator" content="default"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@include file="/WEB-INF/views/include/treegrid.jsp" %>
</head>
<body>
  <input type="hidden" value="${ctx}" name="contextPath" id="contextPath">
  <div class="page-content">
    <div class="clearfix">
      <div id="sample_1_filter" class="dataTables_filter pull-left">
        <form class="form-horizontal" id="searchForm" method="post" action="${ctx}/indicatorInfo/indicatorPageList">
<%--          <div class="form-group" style="display: none;">
            <input type="text" id="date" name="date" readonly="readonly" placeholder="按时间" value="${date}"  class="calendar calendar-time form-control" />
          </div>--%>
              <input type="hidden" id="pageNo" name="pageNo"/>
              <input type="hidden" id="pageSize" name="pageSize"/>
              <input class="form-control" id="name" name="name" type="text"  placeholder="关键字:指标类型" value="${params.name}"  />
              <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="submit()"/>

        </form>
      </div>
    </div>

    <div class="caption form-group pull-right">
      <input class="btn btn-default" type="button" id="expandTree" onclick="$('.tree-2').treegrid('expandAll');" value="显示全部指标" />
      <input class="btn btn-default" type="button" id="collapseTree" onclick="$('.tree-2').treegrid('collapseAll');"  value="只显示一级指标" />
      <button class="btn green" onclick="openAddIndicator('','')"> 新建类型</button>
    </div>

    <table class="table tree-2 table-bordered table-hover">
      <thead>
        <tr>
          <th width="15%">类型/指标名称</th>
          <th width="15%">类型/指标编码</th>
          <th width="15%">创建时间</th>
          <th width="25%">操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="info" items="${pageInfo.list}" varStatus="status">
        <tr class="treegrid-${status.index}">
          <td>${info.name}</td>
           <td>${info.code}</td>
           <td><fmt:formatDate value="${info.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
          <td>
            <a href="javascript:void(0)" onclick="openAddIndicator('${info.id}','')"> 添加子指标 </a>
            <a href="javascript:void(0)" onclick="openAddIndicator('${info.parentId}','${info.id}')">修改</a>
          </td>
        </tr>
          <c:forEach var="leaf" items="${info.leafIndicator}" varStatus="leafStatus">


        <tr class="treegrid-a treegrid-parent-${status.index}" id="${leaf.id}">
          <td align="center" id="" >${leaf.name}</td>
           <td align="center" id="" >${leaf.code}</td>
          <td align="center"><fmt:formatDate value="${leaf.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
           <td align="center">
	           <a href="javascript:void(0)" onclick="openAddIndicator('${info.id}','${leaf.id}')">修改</a>
	           <a href="javascript:void(0)" onclick="addRule('${info.id}','${leaf.id}')">添加规则</a>
           </td>
        </tr>
          </c:forEach>
        </c:forEach>
      </tbody>
    </table>
    <div class="pagination">${pageInfo}</div>
  </div>
<script src="${ctxStatic}/console/js/indicatorSys/indicatorList.js" type="text/javascript"></script>
<%--  <script src="${ctxStatic}/console/js/indicatorSys/userlist.js"></script>--%>
  <script type="text/javascript">
    $(function() {
       $('.tree-2').treegrid({
        'initialState': 'collapsed',
        expanderExpandedClass: 'text-hide treegrid-expander-collapsed',
        expanderCollapsedClass: 'text-hide treegrid-expander-expanded'
      });
    });

    /*从新绘制treegrid*/
    function renderTreegrid(){
        $('.tree-2').treegrid('render');
    }

    //日历初始化
/*    BUI.use('bui/calendar',function(Calendar){
      var datepicker = new Calendar.DatePicker({
        trigger:'.calendar-time',
        showTime:false,
        autoRender : true
      });
    });*/

    /*分页调用函数 */
    function page(n,s){
      if(n) $("#pageNo").val(n);
      if(s) $("#pageSize").val(s);
      $("#searchForm").submit();
      return false;
    }
  </script>
</body>
</html>