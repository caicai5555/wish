<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-store">

    <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/bootstrap/3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctxStatic}/bootstrap/3.3.0/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
    <script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/common/layer.js"></script>

    <title>指标规则编辑</title>
</head>
<body id="body_ruleEdit">
<span id="notification"></span>
<!--规则添加修改层 -->
<div class="container-fluid" id="form_ruleEdit">
    <form class="form-horizontal" role="form" id="ruleEditForm" name="ruleEditForm">
        <div class="row-fluid">
            <div class="col-xs-6">
                <h4>表达式</h4>

                <div class="portlet-body form">
                    <div class="form-body">
                        <c:forEach var="ruleItem" items="${ruleItemses}">
                            <c:if test="${ruleItem.itemType ==1}">
                                <div class="form-group">
                                    <div class="row-fluid">
                                        <label class="col-xs-3 control-label">${ruleItem.itemName}
                                            <span class="text-danger"> * </span>
                                        </label>
                                        <input type="hidden" name="itemCode" value="${ruleItem.itemCode}"/>
                                        <div class="col-xs-3" style="border-left:1px solid #00b0ff;">
                                            <select class="form-control input-xsmall" name="itemOpt" data-labelFlag="${ruleItem.itemType}">
                                                <option value=">">></option>
                                                <option value=">=">>=</option>
                                                <option value="==">=</option>
                                            </select>
                                        </div>
                                        <div class="col-xs-5" >
                                            <input type="text" class="form-control" placeholder="请输入数值或表达式" name="itemValue">
                                        </div>
                                    </div>

                                    <div class="row-fluid" style="margin-top: 6px">
                                        <input type="hidden" name="itemCode" value="${ruleItem.itemCode}"/>
                                        <span class="text-danger"></span>
                                        <div class="col-xs-offset-3 col-xs-3" style="border-left:1px solid #00b0ff;">
                                            <select class="form-control input-xsmall" name="itemOpt" data-labelFlag="${ruleItem.itemType}">
                                                <option value="<"><</option>
                                                <option value="<="><=</option>
                                                <option value="==">=</option>
                                            </select>
                                        </div>
                                        <div class="col-xs-5">
                                            <input type="text" class="form-control" placeholder="请输入数值或表达式" name="itemValue" data-labelFlag="${ruleItem.itemType}">
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${ruleItem.itemType ==0}">
                                <div class="form-group">
                                    <div class="row-fluid" >
                                        <label class="col-xs-3 control-label">${ruleItem.itemName}
                                            <span class="text-danger"> * </span>
                                        </label>
                                        <input type="hidden" name="itemCode" value="${ruleItem.itemCode}"/>
                                        <div class="col-xs-3">
                                            <select class="form-control input-xsmall" readonly="true" name="itemOpt">
                                                <option value="==" selected>=</option>

                                            </select>
                                        </div>
                                        <div class="col-xs-3">
                                            <select class="form-control input-xsmall" name="itemValue">
                                                <c:if test="${ruleItem.itemCode=='sex'}">
                                                    <option value="0">女</option>
                                                    <option value="1">男</option>
                                                </c:if>
                                                <%--<c:forEach var="itemValue"  items="${ruleItem.itemValues}">
                                                    <option value="${itemValue.itemCode}">${itemValue.itemName}</option>
                                                </c:forEach>--%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>


            </div>


            <!----------------------------------------------------------------->
            <div class="col-xs-6">
                <h4>基础参数</h4>

                <div class="portlet-body form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-xs-3 control-label">标准值
                                <span class="text-danger"> * </span>
                            </label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" placeholder="正常:1,亚健康:3,疾病:0" name="valueTab">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">颜色
                                <span class="text-danger"> * </span>
                            </label>

                            <div class="col-xs-9">
                                <select class="form-control input-xsmall" name="color">
                                    <c:forEach var="color" items="${colors}">
                                        <option value="${color.itemCode}">${color.itemName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">结论(提示)
                                <span class="text-danger"> * </span>
                            </label>

                            <div class="col-xs-9">
                                <textarea class="form-control" rows="2" name="conclusion"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label" >建议</label>

                            <div class="col-xs-9">
                                <textarea class="form-control" rows="2" name="suggest"></textarea>
                            </div>
                        </div>

                        <div class="form-actions">
                            <div class="row">
                                <div class="col-xs-offset-3 col-xs-9">
                                    <button type="button" id="saveAndContinue" class="btn btn-primary">保存&继续添加</button>&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="close" class="btn btn-default">关闭窗口</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" id="ctxPath" value="${ctx}">
        <input type="hidden" id="indicatorId" name="indicatorId" value="${indicatorId}"/>
        <input type="hidden" id="groupId" name="groupId" value="${groupId}"/>
    </form>
</div>
<script src="${ctxStatic}/console/js/indicatorSys/indicatorRuleValidate.js"></script>
<script src="${ctxStatic}/console/js/indicatorSys/ruleAddPage.js"></script>
<script type="application/javascript">

</script>

</body>
</html>