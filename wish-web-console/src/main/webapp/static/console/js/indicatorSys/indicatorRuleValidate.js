/**
 * Created by SamWang on 2016/9/10.
 */

$("#ruleEditForm").validate({
    errorElement: "span",
    errorClass: "help-block help-block-error",
    focusInvalid: !1,
    ignore: "",
    messages: {
        valueTab: "请输入一位合法数字！",
        color: "请选择颜色！",
        conclusion: "结论(提示)必填，100字内！",
        suggest:"建议信息，文字200字内！"
    },
    rules: {
        /* itemValue:{validItemValue:[]},*/
        valueTab: {required: !0, number: true, minlength: 1, maxlength: 1},
        color: {required: !0},
        conclusion: {required: !0, minlength: 1, maxlength: 100},
        suggest:{maxlength: 200}
    },
    highlight: function (e) {
        $(e).closest(".form-group").addClass("has-error")
    },
    unhighlight: function (e) {
        $(e).closest(".form-group").removeClass("has-error")
    },
    success: function (e) {
        e.closest(".form-group").removeClass("has-error")
    }
});

//由于jquery-validate对于同name只会验证第一个，暂时没事件研究源码，先手动校验
var validitemValue = function () {
    var flag = true;
    $("input[name=itemValue]").each(function () {
        if ( !validateExp($(this).val(),fields)) {
            $(this).after("<span class='help-block help-block-error'>请输入合法数字，或者输入合法JS算数表达式，如:(height*0.8-10)+20/3，长度200内！</span>");
            $(this).parent().addClass("has-error");
            flag = false;
        } else {
            $(this).next().remove();
            $(this).parent().removeClass("has-error");
        }
    });

    //判断如果是等号两个等号及值必须相当
    $("select[data-labelFlag='1']").each(function(){
        if($(this).val()=="=="){
            $(this).parents(".form-group").find("select").each(function(){
                if($(this).val()!="=="){
                    $(this).next("span").remove();
                    $(this).parents(".form-group").after("<div name='div_optInfo'class='col-xs-offset-3' ><span style='color: #00b0ff'>如果操作符为=，组操作符及值必须相同！</span></div>");
                    flag= false;
                    return false;
                }
            });
        }
    });


    return flag;
};

//--------------------------设置等号联动性start
//参数类型为区间类型，当比较符符为=时，左右区间值及比较符需一样
$("input[data-labelFlag='1']").on("blur",function(){
    //校验输入合法性
    if ( !validateExp($(this).val(),fields)) {
        $(this).next().remove();
        $(this).after("<span class='help-block help-block-error'>请输入合法数字，或者输入合法JS算数表达式，如:(height*0.8-10)+20/3，长度200内！</span>");
        $(this).parent().addClass("has-error");
        return false;
    } else {
        $(this).next().remove();
        $(this).parent().removeClass("has-error");
    }

    var val = $(this).val();

    //判定操作符是否为==
    if($(this).parent().prev().children("select").val()=="=="){
        //设置此组的所有操作符为=
        $(this).parents(".form-group").find("select").each(function(){
            $(this).val("==");

        });
        //设置此组的所有值
        $(this).parents(".form-group").find("input[data-labelFlag='1']").each(function(){
            $(this).val(val);
        });
    }

});

//参数类型为区间类型，当比较符符为=时，左右区操作符都修改为=
$("select[data-labelFlag='1']").on("change",function(){

    //判定操作符是否为==
    if($(this).val()=="=="){
        //设置此组的所有操作符为=
        $(this).parents(".form-group").find("select").each(function(){
            $(this).val("==");

        });

        var val= $(this).parent().next().children("input").val();
        //设置此组的所有值
        $(this).parents(".form-group").find("input[data-labelFlag='1']").each(function(){
            $(this).val(val);
        });

    }
});



//--------------------------设置等号联动性end

//集成jquery-validate和自定义校验
function allValidate() {
    var b2 = $("#ruleEditForm").valid();
    var b1 = validitemValue();
    return b1 && b2;
}


    /*
     * 假如待选变量：  ID,NUM,TOTAL，AVL TEST
     * 正确的公式例子：ID*NUM+(TOTAL/AVL)*0.5
     * 错误的公式例子：ID**|0.5
     * 后去补充添加，完善占位符从服务器验证----------------------------
     */

    var validateExp=function(string, obj){

  /*      // 剔除空白符
         string = string.replace(/\s/g, '');*/

        //长度过长或为0
        if(string.length>200||string.length==0){
            return false;
        }
        // 错误情况,不合法字符
        if(/[^a-zA-Z0-9\+\-\*\/\.\(\)]+/.test(string)){
            return false;
        }

        //小数点不对
        if(/-?\d{1,7}\./.test(String)){
            return false;
        }
        if(/\.\d/.test(String)){
            return false;
        }

        // 错误情况，运算符连续
        if( /^[\+\-\*\/\.\(\)]{2,}$/.test(string) ){
            return false;
        }

        // 空括号
        if(/\(\)/.test(string)){
            return false;
        }

    // 错误情况，括号不配对
    var stack = [];
    for(var i = 0, item; i < string.length; i++){
        item = string.charAt(i);
        if('(' === item){
            stack.push('(');
        }else if(')' === item){
            if(stack.length > 0){
                stack.pop();
            }else{
                return false;
            }
        }
    }
    if(0 !== stack.length){
        return false;
    }

    // 错误情况，(后面是运算符
    if(/\([\+\-\*\/]/.test(string)){
        return false;
    }

    // 错误情况，)前面是运算符
    if(/[\+\-\*\/]\)/.test(string)){
        return false;
    }

    // 错误情况，(前面不是运算符
    if(/[^\+\-\*\/]\(/.test(string)){
        return false;
    }

    // 错误情况，)后面不是运算符
    if(/\)[^\+\-\*\/]/.test(string)){
        return false;
    }

    // 错误情况，变量没有来自“待选公式变量”
    var tmpStr = string.replace(/[\(\)]{1,}/g,'').replace(/[\+\-\*\/]{1,}/g, '`');
    var array = tmpStr.split('`');
    for(var i = 0, item; i < array.length; i++){
        item = array[i];
        if( /[A-Z]/i.test(item) && 'undefined' === typeof(obj[item]) ){
            return false;
        }
    }

    return true;
}


// 自定义占位符，后去服务器动态校验
var fields = {
    'height': 1
};
// 提交到服务器端的字符串不应该包含空白符，或者应该禁止用户输入空白符
//alert(validateExp('ID*NUM+(TOTAL/AVL)*0.5', fields) );


