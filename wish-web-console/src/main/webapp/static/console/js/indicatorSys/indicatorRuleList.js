//基础服务路径
var serviceBaseUrl = $("#ctxPath").val();
// 添加规则
function addRule(indicatorId,itemCodes,groupId){
    var serviceURl = serviceBaseUrl + "/indicatorRule/ruleAddPage"
        + "?indicatorId=" + indicatorId + "&itemCodes=" + itemCodes + "&groupId=" + groupId;
    layer.open({
        type: 2,
        title:'添加规则',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['90%' , '80%'],
        content: serviceURl,
        cancel: function(){
            //右上角关闭回调
            window.location.reload();
            //reloadParentLayer();
        }
    });
}

//删除规则组
function delRules(groupId,itemNames){
    var index = layer.confirm("确定删除规则组<span style='color:red;'>" + itemNames + "</span>及组下规则吗？", {
        shade: false // 不显示遮罩
    }, function (e) {
        layer.close(index);

        jQuery.ajax({
            type: "POST",
            url: serviceBaseUrl + "/indicatorRule/delIndRuleGroup",
            data: {"groupId": groupId},
            success: function (data) {
                layer.alert("删除成功！",function(index){
                    //刷新窗口
                    window.location.reload();
                    layer.close(index);
                });
            },
            error: function (data) {
                layer.alert("删除未成功，请重试！");
            }
        });
    });
}
//编辑规则
function editRule(id){
    var serviceURl = serviceBaseUrl + "/indicatorRule/ruleEditPage"
        + "?indruleId=" + id;
    layer.open({
        type: 2,
        title:'编辑规则',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['90%' , '80%'],
        content: serviceURl,
        cancel: function(){
            //右上角关闭回调
            window.location.reload();
            //reloadParentLayer();
        }
    });
}

//删除规则
function delRule(id){
    var index = layer.confirm("确定删除此规则吗？", {
        shade: false // 不显示遮罩
    }, function (e) {
        layer.close(index);
        jQuery.ajax({
            type: "POST",
            url: serviceBaseUrl + "/indicatorRule/deleteRule",
            data: {"id": id},
            success: function (data) {
                layer.alert("删除成功！",function(index){
                    //刷新窗口
                    window.location.reload();
                    layer.close(index);
                });
            },
            error: function (data) {
                layer.alert("删除未成功，请重试！");
            }
        });
    });
}

//指标附加信息修改按钮事件
$("#saveAppenInfo").on("click",function () {
    debugger
    //校验合法性
    if (!$("#form_indicatorAppendInfo").valid()) {
        return false;
    }
    jQuery.ajax({
        type: 'post',
        async: true,
        data: {
            "indicatorId": $("#indicatorId").val(),
            "unit": $("#unit").val(),
            "defaultMin": $("#defaultMin").val(),
            "defaultMax": $("#defaultMax").val()
        },
        url: serviceBaseUrl + "/indicatorRule/updateIndRuleAppendInfo",
        success: function (data) {
            //console.log(data);
            var dataObj = JSON.parse(data);
            //成功
            if (dataObj.state == 200) {
                layerMsg("更新成功！", 6);
            } else if (dataObj.state == 1001) {
                layerMsg("无可更新的规则，请先添加规则！", 5);
            }
        },
        error: function () {
            layerMsg("操作未成功，请重试！", 5);
        }
    });

})


//offset: '100px'	只定义top坐标，水平保持居中
//offset: ['100px', '50px']	同时定义top、left坐标
//offset: 't'	快捷设置顶部坐标
//offset: 'r'	快捷设置右边缘坐标
//offset: 'b'	快捷设置底部坐标
//offset: 'l'	快捷设置左边缘坐标
//offset: 'lt'	快捷设置左上角
//offset: 'lb'	快捷设置左下角
//offset: 'rt'	快捷设置右上角
//offset: 'rb'	快捷设置右下角
function layerMsg(info,icon,offset){
    if(offset==null || ''==offset || undefined ==offset){
        offset = 'rt';//右下角
    }
    layer.msg(info, {
        icon: icon,
        time: 3*1000, //3s后自动消失
        offset: offset
    });
}

//添加规则组
function addRuleGroup(indicatorId){
    var serviceURl =serviceBaseUrl + "/indicatorRule/addRuleGroupPage?indicatorId="+indicatorId;
    layer.open({
        type: 2,
        title:'添加规则组',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['600px' , '400px'],
        content: serviceURl,
        cancel: function(){
            //右上角关闭回调
            window.location.reload();
            //reloadParentLayer();

        }
    });
}

//刷新父layer（type=2）
function reloadParentLayer(){
    var iframes = $('iframe[name*="layui-layer-iframe"]');
    if (iframes.length > 0) {
        console.log(iframes);
        iframes[iframes.length-1].contentWindow.location.reload();
    }
}