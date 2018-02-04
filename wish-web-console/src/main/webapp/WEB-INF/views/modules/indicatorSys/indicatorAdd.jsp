<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <title>指标添加页面</title>
    <script src="${ctxStatic}/console/js/indicatorSys/indicatorInfoAdd.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
    <style>
              .form-horizontal .control-label {
                   padding-top: 7px
               }

               .required {
                   color: red;
               }
    </style>
</head>

<body>
<div class="row-fluid ">
<input type="hidden" value="${ctx}" name="contextPath" id="contextPath">

<form role="form" class="form-horizontal">
    <input type="hidden" id="id" value="${indicatorInfo.id}" name="id"/>
    <input type="hidden" id="parentId" value="${indicatorInfo.parentId}" name="parentId"/>

    <div class="span6"></div>
    <div class="control-group">
        <label class="control-label"><span class="required"> * </span>
            <c:if test="${indicatorInfo.parentId=='0'}">
                类型名称
            </c:if>
            <c:if test="${indicatorInfo.parentId!='0'}">
                标签名称
            </c:if>
        </label>
        <div class="controls">
            <input type="text" class="form-control" id="name" name="name" value="${indicatorInfo.name}"
                   maxlength="100"><span style="color: red"></span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><span class="required"> * </span>编码</label>
        <div class="controls">
            <input type="text" class="form-control" id="code" name="code"
                   value="${indicatorInfo.code}" maxlength="30">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><span class="required">*</span> 显示分类</label>
        <div class="controls">
            <div class="radio-list">
                <label class="radio-inline"><input name="showClass" value="1" type="radio" checked="checked"
                                                   <c:if test="${indicatorInfo.showClass==1}">checked="checked"</c:if>/>
                    是</label>
                <label class="radio-inline"><input name="showClass" value="0" type="radio"
                                                   <c:if test="${indicatorInfo.showClass==0}">checked="checked"</c:if>/>
                    否</label>
            </div>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><span class="required"> * </span>归档</label>
        <div class="controls">
            <div class="radio-list">
                <label class="radio-inline"> <input type="radio" name="archiveFlag" value="1"
                                                    checked="checked"
                                                    <c:if test="${indicatorInfo.archiveFlag==1}">checked="checked"</c:if>>是</label>
                <label class="radio-inline"><input type="radio" name="archiveFlag" value="0"
                                                   <c:if test="${indicatorInfo.archiveFlag==0}">checked="checked"</c:if>>否</label>
            </div>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <input class="btn btn-primary" type="button" onclick="formSubmit()" value="保存">
            <input class="btn" type="button" onclick="closePage()" value="取消">
        </div>
    </div>
</form>
</div>
</body>
</html>