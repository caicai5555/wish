(function(w,j,r){

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
		var openId =  $("#openId").val();
		r.postLayer('/wx/wxAirCat/addDevice/',{mac:mac,position:position,openId:openId},function(data){
			if(data.tip == "success"){
				window.location.href = "/wx/wxAirCat/getAirCatData?openId="+$("#openId").val();
			}else{
				alerttip(data.tip);
			}
		});
		event.stopPropagation();
		event.preventDefault();
		return false;

	});
})(window,$,requestUtil);

