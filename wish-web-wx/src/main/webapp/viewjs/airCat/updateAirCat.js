(function(w,j,r){

	function convertModel(resData) {

	}
	new Component(convertModel);
	j(".submit").click(function (event) {
		var mac  = $("#mac").val();

		if(!mac){
			alerttip("请输入设备ID");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}

		var position  =  $("#position").val();

		if(!position){
			alerttip("请输入设备位置");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}
		//是否允许其他微信号绑定
		var isPublic =  $("input[name='isPublic']:checked").val();

		var deviceId  =  $("#deviceId").val();
		var id  =  $("#id").val();
		var openId  =  $("#openId").val();

		r.postLayer('/wx/wxAirCat/updateWXDevice/',{mac:mac,position:position,isPublic:isPublic,openId:openId,id:id,deviceId:deviceId},function(data){
			if(data.tip == "success"){
				window.location.href = "/wx/wxAirCat/listWXDevice?openId="+openId;
			}else{
				alerttip(data.tip);
			}
		});
		event.stopPropagation();
		event.preventDefault();
		return false;

	});
})(window,$,requestUtil);

