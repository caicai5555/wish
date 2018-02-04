<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
  <meta name="decorator" content="default"/>
  <title>检验项管理</title>
  <style>
    .red-hint:before {
      padding: 0 5px;
      color:red;
      content: "*";
    }
  </style>
</head>

<body>
<ul class="nav nav-tabs">
  <li><a href="${ctx}/followup/page">随访列表</a></li>
  <li class="active"><a href="${ctx}/followup/read?id=${entity.id}">随访修改<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form id="inputForm" action="/admin/followup/update"  method="post" class="form-horizontal">
  <input type="text" class="hide" name="id" value="${entity.id}">
  <sys:message content="${message}"/>
  <div class="control-group">
    <label class="control-label">随访计划:</label>
    <div class="controls">
      <input type="text" class="form-control" name="name" value="${entity.name}" placeholder="随访计划"  maxlength="128"/>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">随访类型:</label>
    <div class="controls">
      <%--<input type="text" class="form-control" name="type" value="${entity.type}" placeholder="随访类型"  maxlength="128"/>--%>
      <select name="type" data-selected="${entity.type}">
        <option value="0">定期</option>
        <option value="1">临时</option>
      </select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">随访间隔:</label>
    <div class="controls">
      <input type="text" class="form-control" name="intervals" value="${entity.intervals}" placeholder="随访间隔"  maxlength="128"/>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">随访次数:</label>
    <div class="controls">
      <input type="text" class="form-control" name="times" value="${entity.times}" placeholder="随访次数"  maxlength="128"/>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label">请选择模板:</label>
    <div class="controls">
      <select name="questId" data-selected="${entity.questId}">
        <c:forEach items="${questionnaires}" var="item">
          <option value="${item.id}">${item.questionnaireName}</option>
        </c:forEach>
      </select>
      <input type="text" class="hide" name="questName" value="${entity.questName}" placeholder="模板名称"  maxlength="128"/>
    </div>
  </div>

  <div class="form-actions">
    <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
  </div>
</form>

<script type="text/javascript">
    $(function(){
        $('select').each(function(){
            $(this).val($(this).attr('data-selected'));
        });
        $('#btnSubmit').click(function(){
            $('[name="questName"]').val($('[name="questId"] :selected').text());
        })
    })
</script>
</body>
</html>

