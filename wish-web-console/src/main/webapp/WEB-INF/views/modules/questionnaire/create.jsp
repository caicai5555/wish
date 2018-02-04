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
<%--  <li><a href="${ctx}/followup/page">随访列表</a></li>
  <li class="active"><a href="${ctx}/questionnaire/read?id=${entity.id}">问卷查看<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
  --%>
</ul><br/>
<form id="inputForm" action="/admin/questionnaire/create"  method="post" class="form-horizontal" enctype="multipart/form-data">

  <input type="file" name="file" />

  <div class="form-actions">
    <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
  </div>
</form>

<script type="text/javascript">
 /*   $(function(){
        $('select').each(function(){
            $(this).val($(this).attr('data-selected'));
        });
        $('#btnSubmit').click(function(){
            $('.changed').each(function(i){
                if($(this).attr('data-init') === $(this).val()) $(this).val('');
            });
            $('[name="manager"]').val($('[name="managerId"] :selected').text());
            $('[name="doctor"]').val($('[name="doctorId"] :selected').text());
        })
    })*/
</script>
</body>
</html>
