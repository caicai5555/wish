(function(w,j,r){
	'use strict';
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '',
		type = param_obj.type || '1';
	r.postLayer('/wechat/wmps/ajax/invest/init/' + pid,{id:id,platform:platform},function(data){
		if(data.status){
			var info = data.info || {};

		}
	});
})(window,$,requestUtil);