<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <title>检验单管理</title>
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
    <li><a href="${ctx}/clinic/inspect/list/page">检验单列表</a></li>
    <li class="active"><a href="${ctx}/clinic/inspect/list/read?id=${entity.id}">检验单<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form id="inputForm" action="/admin/clinic/inspect/list/"  method="post" class="form-horizontal">
    <input type="text" class="hide" name="id" value="${entity.id}">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">检验单名称:</label>
        <div class="controls">
            <input type="text" class="form-control"  name="inspectName" value="${entity.inspectName}" placeholder="检验单名称"  maxlength="128">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">英文名:</label>
        <div class="controls">
            <input type="text" class="form-control" name="englishName" value="${entity.englishName}" placeholder="检验单英文名"  maxlength="128"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">检验方法:</label>
        <div class="controls">
            <textarea  class="form-control"  name="inspectMethod" rows="3" maxlength="1024" placeholder="检验方法">${entity.inspectMethod}</textarea>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">所含检验项:</label>
        <div class="controls">
            <div id="itemTree" class="ztree" style="margin-top:3px;float:left;"></div>
            <input type="text" class="hide" name="itemIds" id="itemIds" value="${itemIds}"/>
            <input type="text" class="hide" name="itemIdsNew" id="itemIdsNew" value="${itemIds}"/>
            <input type="text" class="hide" name="itemIdsDel" id="itemIdsDel" value="${itemIds}"/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>

<script src="/static/common/layer.js"></script>
<script type="text/javascript">
    $(function(){
        /*tree begin*/
        var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
            data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
                tree.checkNode(node, !node.checked, true, true);
                return false;
            }}};
        // 检验项
        var zNodes=[
                <c:forEach items="${items}" var="item">{id:"${item.id}", pId:"0", name:"${item.itemName}"},
            </c:forEach>];
        // 初始化树结构
        var tree = $.fn.zTree.init($("#itemTree"), setting, zNodes);
        // 不选择父节点
        tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
        // 默认选择节点
        var ids = "${itemIds}".split(",");
        for(var i=0; i<ids.length; i++) {
            var node = tree.getNodeByParam("id", ids[i]);
            try{
                node.initChecked = true;/* 初始的时候是否被选中 */
                tree.checkNode(node, true, false);
            }catch(e){}
        }
        // 默认展开全部节点
        tree.expandAll(true);
        /* tree end */

        $('#btnSubmit').click(function(){
            var operator = 'update';
            var id = $('[name="id"]').val();
            if(id.trim() === "") operator = 'create';
            $('form').attr('action', $('form').attr('action') + operator);
            /* tree 的*/
            var ids = [], nodes = tree.getCheckedNodes(true),
                   cancelIds=[], cancelNodes = tree.getNodesByFilter(filterCancel),
                    newIds=[], newNodes = tree.getNodesByFilter(filterNew);
            for(var i=0; i<nodes.length; i++) {
                ids.push(nodes[i].id);
            }
            for(var i=0; i<cancelNodes.length; i++) {
                cancelIds.push(cancelNodes[i].id);
            }
            for(var i=0; i<newNodes.length; i++) {
                newIds.push(newNodes[i].id);
            }
            $("#itemIds").val(ids);
            $("#itemIdsNew").val(newIds);
            $("#itemIdsDel").val(cancelIds);
            function filterCancel(node){
                return node.initChecked && !node.checked;
            }
            function filterNew(node){
                return node.checked && (node.initChecked==undefined);
            }
        });

    });

</script>
</body>
</html>