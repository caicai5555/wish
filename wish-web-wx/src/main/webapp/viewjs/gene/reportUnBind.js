(function(w,j,r){

	validator.init();
	j("#getMessage").click(function () {
		var mobile  = $("#mobile").val();

		var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|17[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;

		if(!isMob.test(mobile)){
			alerttip("请输入正确的手机号码");
			return;
		}
		var openId = $("#openId").val();
		r.postLayer('/wx/public/sendMessage/',{mobile:mobile,openId:openId},function(data){
			if(data.tip == "success"){
				alerttip("短信验证码发送成功");
			}else{
				alerttip(data.tip);
			}
		});

	});

	j(".submit").click(function (event) {
		var mobile  = $("#mobile").val();

		var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|17[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;

		if(!isMob.test(mobile)){
			alerttip("请输入正确的手机号码");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}

		var isNum = /^\d{6}$/;

		var verificationNum  = $("#verificationNum").val();
		if(!isNum.test(verificationNum)){
			alerttip("请输入正确的短信验证码");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}

		var openId = $("#openId").val();

		r.postLayer('/wx/wxGene/unBind/',{mobile:mobile,verificationNum:verificationNum,openId:openId},function(data){
			if(data.tip == "success"){
				window.location.href = "/wx/wxGene/getReport?openId="+$("#openId").val();
			}else{
				alerttip(data.tip);
			}
		});
		event.stopPropagation();
		event.preventDefault();
		return false;

	});
})(window,$,requestUtil);

