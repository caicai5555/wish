<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <title>添加指标规则组</title>
</head>
<body>
<input type="hidden" id="ctxPath" value="${ctx}"/>
<input type="hidden" id="indicatorId" value="${indicatorId}"/>
<div class="container-fluid">
    <div class="offset1">
        <fieldset>
            <legend>请选择表达式项</legend>
            <select id="itemSelect" multiple="multiple" class="form-control select2" style="width: 80%">
                <optgroup label="参数项">
                    <c:forEach items="${ruleItems}" var="item">
                        <option value="${item.itemCode}"
                                <c:if test="${item.itemCode eq 'value'}">selected</c:if>>${item.itemName}</option>
                    </c:forEach>
                </optgroup>
            </select>
            <span class="help-block"></span>
            <button id="addRuleGroup" type="button" class="btn">添加</button>

            <div id="alertInfo" style="width: 80%">
            </div>
        </fieldset>
    </div>
</div>

<script type="application/javascript">
    var serviceBaseUrl = $("#ctxPath").val();

    $(document).ready(function () {
        $("#itemSelect").select2(
                {
                    minimumResultsForSearch: Infinity, //隐藏查询box
                    maximumSelectionLength: 3, //限制项选择数量
                }
        );
    });


    //规则参数页确定按钮事件
    $("#addRuleGroup").click(function () {
        var itemCodes = $("#itemSelect").select2().val();
        if (null == itemCodes) {
            alertInfo("请选择表达式项", "warning");
        } else {
            //保存数据
            var itemCodes = itemCodes.sort().join(":");
            //判断指标值已选择
            if (itemCodes.search(/value/) == -1) {
                alertInfo("<strong>指标值</strong>为必选参数，请添加指标值！！！", "warning");
                return false;
            }

            jQuery.ajax({
                type: 'post',
                data: {"itemCodes": itemCodes, "indicatorId":$("#indicatorId").val()},
                url: serviceBaseUrl+"/indicatorRule/addIndicatorGroup",
                success: function (data) {
                    var dataObj = JSON.parse(data);
                    //成功
                    if (dataObj.state == 200) {
                        alertInfo("规则参数组添加成功！", "success");
                    } else if (dataObj.state == 2005) { //组已存在
                        alertInfo("规则参数组已经存在，请在规则组内添加规则！", "warning");
                    }else if (dataObj.state == 2001) { //保存失败
                        alertInfo("保存失败，请重试！", "error");
                    }
                },
                error: function () {
                    alertInfo("操作未失败，请重试！", "error");
                }
            });
        }
    });

    //添加提示信息
    //msg：输出的提示信息
    //type：提示类型，warning，success,error
    function alertInfo(msg, type) {
        //定义基本信息模板
        var infoTmp = new Object();
        infoTmp["warning"] = '<div class="alert alert-warning" date-myalert="alert"><a href="#" class="close" data-dismiss="alert">&times;</a>' +
                '<strong>警告！</strong>' +
                msg +
                '</div>';
        infoTmp["success"] = '<div class="alert alert-success" date-myalert="alert"><a href="#" class="close" data-dismiss="alert">&times;</a>' +
                '<strong>成功！</strong>' +
                msg +
                '</div>';
        infoTmp["error"] = '<div class="alert alert-error" date-myalert="alert"><a href="#" class="close" data-dismiss="alert">&times;</a>' +
                '<strong>失败！</strong>' +
                msg +
                '</div>';

        /////////////////////////////////////////////////////
        $("#alertInfo").html(infoTmp[type]);

        //3秒关闭
/*        window.setTimeout(function () {
            $('[date-myalert="alert"]').alert('close');
        }, 3000)*/
    }



</script>
</body>
</html>