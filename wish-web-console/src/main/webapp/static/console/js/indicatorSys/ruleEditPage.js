/**
 * Created by SamWang on 2016/9/22.
 */
$(document).ready(function () {
    //var initFormDiv = $("#body_ruleEdit").html();
    var serviceBaseUrl = $("#ctxPath").val();
    $("#save").on("click",function(){
        submitUsable();
        //校验合法性
        if(!allValidate()){
            submitUsable();return false;
        }
        var formjson = $("#ruleEditForm").serializeObject();
        console.log(JSON.stringify(paramsObj(formjson)));
        jQuery.ajax({
            type:'post',
            url :serviceBaseUrl+"/indicatorRule/updateIndicatorRule",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(paramsObj(formjson)),
            success:function(data){
                var dataObj= JSON.parse(data);
                //成功
                if(dataObj.state==200){
                    //初始化页面
                    layerMsg("指标规则添加成功",1);
                    submitUsable();
                }else{
                    layerMsg("指标规则未添加成功",2);
                    submitUsable();
                }
                //删除indicatorRuleValidate.js校验操作符=的提示信息
                $("div[name='div_optInfo']").remove();

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
            if(this.name=="indruleId"  || this.name=="groupId"
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
        if( $("#save").attr('disabled')){
            $("#save").attr('disabled',false);
        }else{
            $("#save").attr('disabled',true);
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