<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>指标规则管理</title>
    <%@include file="/WEB-INF/views/include/treegrid.jsp" %>
</head>
<body>
<input type="hidden" id="ctxPath" value="${ctx}">
<input type="hidden" id="indicatorId" value="${indicatorRuleInfo.indicatorId}">
<div class="page-content">
    <!--指标相关信息form-->
    <div class=row-fluid>
        <button type="button" class="btn btn-primary" data-toggle="collapse"
                data-target="#demo">
            指标相关参数
        </button>
        <div id="demo" class="collapse in">
            <div class="portlet box purple">
                <div class="portlet-body form">
                    <form class="form-horizontal" role="form" id="form_indicatorAppendInfo">
                        <div class="form-body">
                            <div class="control-group">
                                <label class="col-xs-3 control-label">指标名称:
                                    <span class="required"> * </span></label>

                                <div class="col-xs-9">
                                    <input type="text" name="indicatorName" value="${indicatorRuleInfo.indicatorName}"
                                           class="form-control input-inline input-medium" placeholder="指标名称" readonly="readonly">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="col-xs-3 control-label">计量单位:
                                    <!--<span class="required"> * </span>--></label>

                                <div class="col-xs-9">
                                    <input type="text" id="unit" name="unit" value="${indicatorRuleInfo.unit}"
                                           class="form-control input-inline input-medium"
                                           placeholder="计量单位">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="col-xs-3 control-label">默认最小值
                                    <span class="required"> * </span></label>

                                <div class="col-xs-9">
                                    <input type="text" id="defaultMin" name="defaultMin"
                                           value="${indicatorRuleInfo.defaultMin}"
                                           class="form-control input-inline input-medium"
                                           placeholder="请输入有效数字，最多两位小数">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">默认最大值
                                    <span class="required"> * </span></label>

                                <div class="col-xs-9">
                                    <input type="text" id="defaultMax" name="defaultMax"
                                           value="${indicatorRuleInfo.defaultMax}"
                                           class="form-control input-inline input-medium"
                                           placeholder="请输入有效数字，最多两位小数">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="offset1">
                                    <button type="button" class='btn btn-primary' id="saveAppenInfo">修改</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="pull-right">
        <input class="btn btn-default" type="button" id="expandTree" onclick="$('.tree').treegrid('expandAll');"
               value="展开组"/>
        <input class="btn btn-default" type="button" id="collapseTree" onclick="$('.tree').treegrid('collapseAll');"
               value="折叠组"/>
        <button class="btn green" onclick="addRuleGroup('${indicatorRuleInfo.indicatorId}')"> 新建规则集</button>
    </div>
    <div class="row-fluid">
        <table class="tree table table-hover">
            <thead>
            <tr>
                <th width="15%">规则集合名</th>
                <th width="10%">标准值</th>
                <th width="10%">颜色</th>
                <th width="15%">结论</th>
                <th width="15%">建议</th>
                <th width="20%">表达式</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ruleGroup" items="${ruleGroupList}">
                <tr class="treegrid-${ruleGroup.ruleGroupId}">
                    <td>${ruleGroup.itemNames}</td><td></td><td></td><td></td><td></td></td><td>
                    <td>
                       <span class="row-fluid"> <a href="javascript:void(0)" id="addRule" onclick="addRule('${ruleGroup.indicatorId}','${ruleGroup.itemCodes}','${ruleGroup.ruleGroupId}')">添加规则</a></span>
                       <span class="row-fluid"> <a href="javascript:void(0)" id="delRules" onclick="delRules('${ruleGroup.ruleGroupId}','${ruleGroup.itemNames}')">删除规则组</a></span>
                    </td>
                </tr>
                <c:forEach var="rule" items="${ruleGroup.indicatorRules}">
                    <tr class="treegrid-${rule.id} treegrid-parent-${rule.groupId}" id="${rule.id}">
                        <td align="center"></td>
                        <td align="center">${rule.valueTab}</td>
                        <td align="center">${colorMap[rule.color]}</td>
                        <td align="center">${rule.conclusion}</td>
                        <td align="center">${rule.suggest}</td>
                        <td align="center">${rule.expression}</td>
                        <td align="center">
                            <span class="row-fluid"><a href="javascript:void(0)" id="editRule" onclick="editRule('${rule.id}')">编辑规则</a></span>
                            <span class="row-fluid"><a href="javascript:void(0)" id="delRule" onclick="delRule('${rule.id}')">删除规则</a></span>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${ctxStatic}/console/js/indicatorSys/indicatorRuleList.js"></script>
<script type="text/javascript">
    $(function () {
        $('.tree').treegrid({
            /*initialState: 'collapsed',*/
            expanderExpandedClass: 'text-hide treegrid-expander-collapsed',
            expanderCollapsedClass: 'text-hide treegrid-expander-expanded'
        });
    });

    /*从新绘制treegrid*/
    function renderTreegrid() {
        $('.tree').treegrid('render');
    }

</script>
</body>
</html>