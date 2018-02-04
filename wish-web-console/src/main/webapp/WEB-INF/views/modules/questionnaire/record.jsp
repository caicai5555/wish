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
<form id="inputForm" action="/admin/questionnaire/record"  method="post" class="form-horizontal">
  <input type="text" class="hide" name="questionnaireId" value="${entity.id}">
  <input type="text" class="hide" name="detailId" value="${detailId}">
  <sys:message content="${message}"/>
  <div class="control-group">
    <label class="control-label">问卷:</label>
    <div class="controls">
      <span class="form-control"> ${entity.questionnaireName}</span>
    </div>
  </div>

  <c:forEach items="${entity.repstQuestionsCtgrList}" var="element" varStatus="status">
    <div class="control-group">
      <label class="control-label">试题分类${status.count}:</label>
      <div class="controls">
        <span > ${element.questionCtgrName}</span>
      </div>
    </div>
    <c:forEach items="${element.repstQuestionsList}" var="question" varStatus="questionStatus">
      <div class="control-group">
        <label class="control-label">试题${questionStatus.count}:</label>
        <div class="controls">
          <span > ${question.questionName}</span>
        </div>
      </div>
      <c:if test="${question.questionType == '62'}">
        <div class="control-group">
          <label class="control-label"></label>
          <c:forEach items="${question.repstQuestionItemList}" var="item" varStatus="status">
            <label class="checkbox inline">
              <input type="radio" value="${item.id}" name="repstUserQuestionList[${questionStatus.count-1}].itemId"> ${item.itemName}
              <input type="text" value="${item.typeEncoding}" name="repstUserQuestionList[${questionStatus.count-1}].typeEncoding" disabled="disabled" style="display: none">
              <input type="text" value="${item.archiveType}" name="repstUserQuestionList[${questionStatus.count-1}].archiveType" disabled="disabled" style="display: none">
            </label>
        </c:forEach>
       </div>
      </c:if>
    </c:forEach>
  </c:forEach>


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
            $('.changed').each(function(i){
                if($(this).attr('data-init') === $(this).val()) $(this).val('');
            });
            $('[name="manager"]').val($('[name="managerId"] :selected').text());
            $('[name="doctor"]').val($('[name="doctorId"] :selected').text());
            //  被选题项的其他数据
            $(':checked').each(function () {
                $(this).nextAll(':text').removeAttr('disabled');
            })
        })
    })
</script>
</body>
</html>
