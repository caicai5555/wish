<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>指标子项添加页面</title>
    <%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <script src="${ctxStatic}/console/js/indicatorSys/indicatorInfoAddChild.js"
            type="text/javascript"></script>
    <script type="text/javascript">
    </script>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
        }

        .required {
            color: red;
        }
    </style>
</head>

<body>
<input type="hidden" value="${ctx}" name="contextPath" id="contextPath">
<%--ztree选取临时数据--%>
<input type="hidden" name="templeId" id="templeId" value="${indicatorInfo.parentId}" />
<input type="hidden" name="templeParentId" id="templeParentId" />

<div class="row-fluid ">
    <form class="form-horizontal">
        <input type="hidden" class="form-control" id="id" value="${indicatorInfo.id}" name="id"/>
        <input type="hidden" class="form-control" id="parentId" value="${indicatorInfo.parentId}" name="parentId"/>
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
                       maxlength="100"><span id="phoneNumberSpan" style="color: red"></span>
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


        <div class="control-group" style="position: relative;">
            <label class="control-label"></span>指标项移动</label>
            <div class="controls">
                <div id="treeIndex" class="ztree" style="margin-top: 0; width: 150px;"></div>
            </div>
        </div>

        <div class="control-group text-center">
            <input class="btn btn-primary" type="button" onclick="formSubmit()" value="保存">
            <input class="btn" type="button" onclick="closePage()" value="取消">
        </div>
    </form>
</div>
</body>
</html>