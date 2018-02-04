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
<input type="hidden" id="ctxPath" value="${ctx}">
<div class="container" id="form_ruleEdit">
    <form class="form-horizontal" role="form" id="ruleEditForm" name="ruleEditForm">

        <input type="hidden" id="indruleId" name="indruleId" value="${indicatorRule.id}"/>
        <div class="row">
            <div class="col-xs-6">
                <h4>表达式</h4>

                <div class="portlet-body form">
                    <div class="form-body">
                        <!--外层循环参数项-->
                        <c:forEach items="${ruleItems}" var="ruleItem">
                        <c:if test="${ruleItem.itemType==1}">
                        <div class="form-group">
                            <c:set var="labelFlag" value="1"/><!--标识符，指标规则参数是区间只显示一次参数项名-->
                            <c:set var="eqFlag" value="0"/><!--用于操作符为=时，>,>=,,,<,<=符号组单一 -->
                            <c:forEach var="ruleExpItem" items="${ruleItem.ruleExpItems}">
                                <c:set var="eqFlag" value="${eqFlag+1}"/>
                            <div class="row">
                                <label class="col-xs-3 control-label">
                                    <c:if test="${labelFlag == 1}">
                                        ${ruleItem.itemName}<span class="text-danger">*</span>
                                    </c:if>
                                </label>
                                <input type="hidden" name="id" value="${ruleExpItem.id}"/>
                                <input type="hidden" name="itemCode" value="${ruleItem.itemCode}"/>
                                <div class="col-xs-3" style="border-left:1px solid #00b0ff;">
                                    <select class="form-control input-xsmall" name="itemOpt" data-labelFlag="${ruleItem.itemType}">
                                        <c:if test="${ruleExpItem.itemOpt eq '>' ||ruleExpItem.itemOpt eq '>='||(ruleExpItem.itemOpt eq '==' && eqFlag%2==1)}">
                                        <option value=">" <c:if test="${ruleExpItem.itemOpt eq '>'}">selected</c:if>> > </option>
                                        <option value=">=" <c:if test="${ruleExpItem.itemOpt eq '>='}">selected</c:if>> >= </option>
                                        <option value="==" <c:if test="${ruleExpItem.itemOpt eq '=='}">selected</c:if>> = </option>
                                        </c:if>
                                        <c:if test="${ruleExpItem.itemOpt eq '<'||ruleExpItem.itemOpt eq '<='||(ruleExpItem.itemOpt eq '=='&& eqFlag%2==0)}">
                                        <option value="<" <c:if test="${ruleExpItem.itemOpt eq '<'}">selected</c:if>> < </option>
                                        <option value="<=" <c:if test="${ruleExpItem.itemOpt eq '<='}">selected</c:if>> <= </option>
                                        <option value="==" <c:if test="${ruleExpItem.itemOpt eq '=='}">selected</c:if>> = </option>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="col-xs-5" >
                                    <input type="text" class="form-control" data-labelFlag="${ruleItem.itemType}" placeholder="请输入数值或表达式" name="itemValue" value="${ruleExpItem.itemValue}">
                                </div>
                            </div>
                                <c:set var="labelFlag" value="0"/>
                            </c:forEach>
                            </c:if>
                        </div>
                        <c:if test="${ruleItem.itemType ==0}">
                        <div class="form-group">
                            <div class="row" >
                                <label class="col-xs-3 control-label">${ruleItem.itemName}
                                    <span class="text-danger">*</span>
                                </label>
                                <input type="hidden" name="itemCode" value="${ruleItem.itemCode}"/>
                                <input type="hidden" name="id" value="${ruleItem.ruleExpItems[0].id}"/>
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
                                        <%--<c:forEach var="ruleItemValue" items="${ruleItem.itemValues}">
                                        <option value="${ruleItemValue.itemCode}" <c:if test="${ruleItem.ruleExpItems[0].itemValue==ruleItemValue.itemCode}">selected</c:if>>${ruleItemValue.itemName}</option>
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
                                <input type="text" class="form-control" placeholder="正常:1,亚健康:3,疾病:0" name="valueTab" value="${indicatorRule.valueTab}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">颜色
                                <span class="text-danger"> * </span>
                            </label>

                            <div class="col-xs-9">
                                <select class="form-control input-xsmall" name="color">
                                    <c:forEach var="color" items="${colors}">
                                    <option value="${color.itemCode}" <c:if test="${indicatorRule.color==color.itemCode}">selected</c:if>>${color.itemName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">结论(提示)
                                <span class="text-danger"> * </span>
                            </label>

                            <div class="col-xs-9">
                                <textarea class="form-control" rows="2" name="conclusion">${indicatorRule.conclusion}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label" >建议</label>

                            <div class="col-xs-9">
                                <textarea class="form-control" rows="2" name="suggest">${indicatorRule.suggest}</textarea>
                            </div>
                        </div>

                        <div class="form-actions">
                            <div class="row">
                                <div class="col-xs-offset-3 col-xs-9">
                                    <button type="button" id="save" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="close" class="btn btn-default">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="${ctxStatic}/console/js/indicatorSys/indicatorRuleValidate.js"></script>
<script src="${ctxStatic}/console/js/indicatorSys/ruleEditPage.js"></script>
<script type="application/javascript">

</script>

</body>
</html>