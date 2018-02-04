<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>检验单授权</title>
    #parse("/inc/form.html")
    <!-- 字体图标设置 -->
    <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/plugins/metronic/assets/css/simple-line-icons.min.css" />
    <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/plugins/metronic/assets/css/tree.css" />

    <link href="${content.contextPath}/viewjs/gene/css/util.css" rel="stylesheet">
    <script src="${content.contextPath}/resources/plugins/metronic/assets/jquery-validation/lib/jquery.form.js"></script>
    <script src="${content.contextPath}/resources/plugins/metronic/assets/scripts/bootstrap.min.js" type="text/javascript"></script>

    <!-- BEGIN tree -->
    <script src="${content.contextPath}/resources/plugins/metronic/assets/scripts/jstree.min.js" type="text/javascript"></script>
    <script src="${content.contextPath}/resources/plugins/metronic/assets/scripts/ui-tree.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${content.contextPath}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${content.contextPath}/resources/js/ztree/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${content.contextPath}/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${content.contextPath}/resources/js/ztree/jquery.ztree.excheck-3.5.js"></script>
    <!-- END tree -->
    <script src="${content.contextPath}/viewjs/questionnaire/repstAuthorization.js"></script>
</head>
<body>
<input type="hidden" value="${content.contextPath}" name="contextPath" id="contextPath" />
<input type="hidden" value="${orgId}" name="currentOrgId" id="currentOrgId"/>
<input type="hidden" value="${orgName}" name="currentOrgName" id="currentOrgName"/>
<input type="hidden" value=${orgId} id="pkid" name="pkid" />
<div class="page-content">
    <form class="form-horizontal" role="form" id="pbRepstInfoForm">
        <input type="hidden" name="pkId" value="$!entity.pkId"/>
        <input type="hidden" name="pubOrgId" value="$!orgIds" id="pubOrgId"/>
        <div class="form-group">
            <label class="col-xs-3 text-right" style="padding-top: 7px;">检验单名称：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control input-inline input-medium" id="inspectName" name="inspectName" value="$!entity.inspectName" readonly="readonly"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 text-right" style="padding-top: 7px;">订阅机构：</label>
            <div class="col-xs-8">
                <div style="padding-top: 7px; margin-bottom: 10px;">
                    <div class="content_wrap">
                        <div class="zTreeDemoBackground left">
                            <ul id="tree_2" class="ztree"></ul>
                        </div>
                    </div>
                </div>
              <textarea style="display:none;" rows="5" cols="10" class="form-control input-inline input-medium" id="orgName" name="orgName" readonly="readonly">
              </textarea>
                <input type="hidden" id="orgList" name="orgList" data-required="1" />
            </div>
        </div>
        <div class="text-center">
            <input type="button" class="btn btn-primary" value="确定" onclick="saveAuthorizeOrg()"/>
            <input type="button" class="btn" value="取消"/>
        </div>
    </form>
</div>
<script type="text/javascript">
    function saveAuthorizeOrg() {
        setOrgsReturn();
        var dataVal = jQuery("#pbRepstInfoForm").serialize();
        jQuery.ajax({
            type: "POST",
            url : jQuery("#contextPath").val()+"/inspectList/authorize",
            data: dataVal,
            success: function(msg){
                if(msg.statusCode === 200) {
                    layer.alert('操作成功！', function(){
                        parent.layer.closeAll();
                    });
                }
            },
            error: function(xhr) {
                alert("执行异常!");
            }
        });
        //ajax加载提示
        $(document).ajaxStart(function(){
            layer.load(0, {shade: [0.1,'#fff'] });  //0.1透明度的白色背景
        }).ajaxComplete(function(){
            layer.closeAll('loading');
        });

    }
</script>
</body>
</html>