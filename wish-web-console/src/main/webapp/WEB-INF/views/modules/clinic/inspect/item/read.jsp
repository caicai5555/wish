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
    <li><a href="${ctx}/clinic/inspect/item/page">检验项列表</a></li>
    <li class="active"><a href="${ctx}/clinic/inspect/item/read?id=${entity.id}">检验项<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form id="inputForm" action="/admin/clinic/inspect/item/"  method="post" class="form-horizontal">
    <input type="text" class="hide" name="id" value="${entity.id}">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">指标项:</label>
        <div class="controls">
            <select name="indexId">
                <c:forEach items="${indicators}" var="item">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <%--
    <div class="control-group">
        <label class="control-label">检验项名称:</label>
        <div class="controls">
            <select name="itemId">
                <c:forEach items="${indicators}" var="item">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
            <input type="text" class="form-control"  name="itemName" value="${entity.itemName}" placeholder="检验项名称" maxlength="128"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">英文名:</label>
        <div class="controls">
            <input type="text" class="form-control" name="englishName" value="${entity.englishName}" placeholder="检验项英文名"  maxlength="128"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">单位:</label>
        <div class="controls">
            <input type="text" class="form-control" name="units" value="${entity.units}" placeholder="单位" maxlength="128"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">正常范围:</label>
        <div class="controls">
            <input type="text" class="form-control num-length"  name="normalRange" value='${entity.normalRange}' placeholder="正常最小值-最大值"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">可输入范围:</label>
        <div class="controls">
            <input type="text" class="form-control num-length"  name="min" value="${entity.min}" placeholder="可输最小值"/>-
            <input type="text" class="form-control num-length separator"  name="max" value="${entity.max}" placeholder="可输最大值"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">指标关联:</label>
        <div class="controls">
            <input type="text" class="form-control" name="indexName" value="${entity.indexName}" placeholder="指标关联"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">与指标换算:</label>
        <div class="controls">
            <input type="text" class="form-control" name="reduceFormula" value="${entity.reduceFormula}" placeholder="与指标的换算，以检验项英文名为未知数的四则运算" maxlength="128"/>
        </div>
    </div>--%>
    <div class="form-actions">
        <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>


<script src="/static/common/layer.js"></script>
<script type="text/javascript">
    $(function(){
        $('#btnSubmit').click(function(){
            var operator = 'update';
            var id = $('[name="id"]').val();
            if(id.trim() === "") operator = 'create';
            $('form').attr('action', $('form').attr('action') + operator);
     /*       $.ajax({
                url : '/admin/clinic/inspect/item/' + operator,
                type : 'post',
                data : $('form').serialize(),
                success : function(reply){
                    if(200 === reply.statusCode) layer.alert("操作成功！", function(){
                        parent.location.reload();
                        parent.layer.closeAll();
                    });
                    else layer.alert("操作失败！");
                },
                error : function(reply){
                    layer.msg("网络异常！");
                }
            })*/
        })

        //ajax加载提示
        $(document).ajaxStart(function(){
            layer.load(0, {shade: [0.1,'#fff'] });  //0.1透明度的白色背景
        }).ajaxComplete(function(){
            layer.closeAll('loading');
        });

    })
</script>
</body>
</html>