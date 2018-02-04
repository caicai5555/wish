/**
 * Created by SamWang on 2016/9/22.
 */
$(document).ready(function () {
    var initFormDiv = $("#body_ruleEdit").html();
    var serviceBaseUrl = $("#ctxPath").val();
    $("#saveAndContinue").on("click",function(){
        submitUsable();
        //校验合法性
        if(!allValidate()){
            submitUsable();return false;
        }
        var formjson = $("#ruleEditForm").serializeObject();
        jQuery.ajax({
            type:'post',
            url :serviceBaseUrl+"/indicatorRule/addIndicatorRule",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(paramsObj(formjson)),
            success:function(data){
                var dataObj= JSON.parse(data);
                //成功
                if(dataObj.state==200){
                    //刷新父窗口树
                    //初始化页面
                    $("#body_ruleEdit").html(initFormDiv);
                    layerMsg("指标规则添加成功",1);
                }else{
                    layerMsg("指标规则未添加成功",2);
                    submitUsable();
                }
            },
            error:function(){
                layerMsg("操作未成功，请重试！",4,"t");
                submitUsable();



            }
        });

    });

    //表单序列化对象与后台DisIndicatorRuleVO--banding----------demo-------------
    /*    {"ruleExpItems":[{"itemCode":"value","itemOpt":">","itemValue":"11"},
     {"itemCode":"value","itemOpt":"<","itemValue":"22"},
     {"itemCode":"height","itemOpt":">","itemValue":"33"},
     {"itemCode":"height","itemOpt":"<","itemValue":"44"}],
     "valueTab":"1",
     "color":"红",
     "conclusion":"不正常",
     "suggest":"多运动",
     "indicatorId":"1473262422318"}*/
    $.fn.serializeObject = function()
    {
        var obj={},innerObj = {};
        obj.ruleExpItems=[];

        var s = this.serializeArray();
        $.each(s, function() {
            if(this.name=="indicatorId"  || this.name=="groupId"
                || this.name=="valueTab" ||this.name=="color"
                ||this.name=="conclusion" ||this.name=="suggest"){
                obj[this.name]=this.value;
            } else{
                innerObj[this.name]=this.value;
                if(this.name=="itemValue") {
                    obj.ruleExpItems.push(innerObj);
                    innerObj = {};
                }
            }
        });
        return obj;
    };

    //提交设置保存按钮不可用
    function submitUsable(){
        if( $("#saveAndContinue").attr('disabled')){
            $("#saveAndContinue").attr('disabled',false);
        }else{
            $("#saveAndContinue").attr('disabled',true);
        }
    };
    //关闭窗口
    $("#close").on("click",function(){
        window.parent.location.reload();
    });

    //获取父窗口相关参数
    var paramsObj = function (obj){
        var parentForm =window.parent.$("#form_indicatorAppendInfo").serializeArray();
        $.each(parentForm, function() {
            obj[this.name]= this.value;
        });
        return obj;
    };
});

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
        offset: ['80%', '80%'],
        time: 5*1000 //5s后自动消失

    });
}