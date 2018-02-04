function openAddIndicator(parentId,id){
	var url = $("#contextPath").val()+"/indicatorInfo/indicatorAdd?parentId="+parentId+"&id="+id;
	var title = '新建标签类型';
	if(parentId!=''){
		title = "新建标签";
	}
	layer.open({
		type: 2,
		title:title,
		maxmin: true,
		shadeClose: true, //点击遮罩关闭层
		area : ['600px' , '500px'],
		content: url
	});
}

function addRule(parentId,indicatorId){
	var url = $("#contextPath").val()+"/indicatorRule/indicatorRulePage?indicatorId="+indicatorId;
	var title = '添加规则';
	layer.open({
	    type: 2,
	    title:title,
	    maxmin: true,
	    shadeClose: true, //点击遮罩关闭层
	    area : ['90%' , '100%'],
	    content: url
	 });
}

function reloadPage(){
	window.location.reload();
}