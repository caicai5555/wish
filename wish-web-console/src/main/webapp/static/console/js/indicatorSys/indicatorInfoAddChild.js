$(function(){
	jQuery.ajax({
		type:"GET",
		url:$("#contextPath").val()+"/indicatorInfo/getParentIndicators",
		data:"",
		success:function(retVal){
			if(retVal != null && retVal.length>0){
				var zNotes = new Array();
				$.each(retVal,function(i,val){
					var checkedType=false;
					var a =val.id;
					var b = $("#parentId").val();
					if(val.id == $("#parentId").val()){
						checkedType=true;
					}
					zNotes.push({
						id:val.id,
						name:val.name,
						checked:checkedType
					});
				});
				$.fn.zTree.init($("#treeIndex"),settingIndex,zNotes)
			}
		}
	})
});

var settingIndex = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback :{
		onCheck :zTreeOnCheck
	}
};

function zTreeOnCheck(event, treeId, treeNode) {
	jQuery("#templeId").attr("value",treeNode.id);
};


function formSubmit() {
      var name = jQuery("#name").val();
      if($.trim(name) ==""){
    	layer.alert("名称不能为空！")
        return ;
      }
      var code = jQuery("#code").val();
      if($.trim(code)==""){
		  layer.alert("编码不能为空！")
        return ;
      }
      var dataVal = "name=" + name + "&code=" + code + "&parentId="+jQuery("#templeId").val()
      			+ "&id="+jQuery("#id").val()+"&archiveFlag=" +jQuery("input[name='archiveFlag']:checked").val();
	jQuery.ajax({
        type : "POST",
        url : jQuery("#contextPath").val() + "/indicatorInfo/saveIndicator",
        data : dataVal,
        success : function(retVal) {
        	var val = retVal.showClass || 0 ;
        	$('[value=' + val +']').not('[name=parentId]').click();
        	if(retVal=='success'){
        		layer.alert('操作成功', {
					shade : false
				// 不显示遮罩
				}, function(e) {
					parent.location.reload();
					// parent.renderTreegrid();
	                 // 关闭当前窗口
	                 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	                 parent.layer.close(index); //再执行关闭   
				});
        		
        	}else if(retVal=='rename'){
				layer.alert("有重复的名字请修改!");
        	}else if(retVal=='recode'){
				layer.alert("有重复的编码请修改!");
        	}else{
				layer.alert("操作失败请重试!");
        	}
        }
      });
    }
    function closePage() {
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
      parent.layer.close(index); //再执行关闭   
    }