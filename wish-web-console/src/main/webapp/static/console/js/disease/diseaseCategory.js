/**
 * create by wumengjun
 */
var TableManaged = function() {
	return {
		init : function() {
			jQuery('#userlist .group-checkable').change(function() {
				var set = jQuery(this).attr("data-set");
				var checked = jQuery(this).is(":checked");
				$(this.parentElement).toggleClass("checked");
				jQuery(set).each(function() {
					if (checked) {
						$(this).attr("checked", true);
						$(this).parents('tr').addClass("active");
						$(this).parents('span').toggleClass("checked");
					} else {
						$(this).attr("checked", false);
						$(this).parents('tr').removeClass("active");
						$(this).parents('span').toggleClass("checked");
					}
				});
				jQuery.uniform.update(set);
			});
			jQuery('#userlist').on('change', 'tbody tr .checkboxes',
					function() {
						$(this).parents('tr').toggleClass("active");
						$(this).parents('span').toggleClass("checked");
					});
			jQuery('#userlist_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify table
			// search input
			jQuery('#userlist_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall"); // modify table per page
			// dropdown
			// jQuery('#userlist_wrapper .dataTables_length select').select2();
			// // initialize select2 dropdown
		}
	};
}();
jQuery(document).ready(function() { 
	TableManaged.init();
	// FormValidation.init();
});

/**
 * 总行数 private long totalCount; 每页多少条记录 private int pageSize; 当前第几页 private int
 * pageNo; 结果集数量 private int rSum = 0; 结果集
 */
var options = {
	currentPage : parseInt($("#pageNo").val()),
	totalPages : parseInt($("#totalCount").val())
			/ parseInt($("#pageSize").val()) <= 1 ? 1 : Math.ceil(parseInt($(
			"#totalCount").val())
			/ parseInt($("#pageSize").val())),
	size : "normal",
	alignment : "center",
	numberOfPages : $("#pageSize").val(),
	itemTexts : function(type, page, current) {
		switch (type) {
		case "first":
			return "第一页";
		case "prev":
			return "<";
		case "next":
			return ">";
		case "last":
			return "最后页";
		case "page":
			return page;
		}
	},
	onPageChanged : function(e, oldPage, newPage) {
	//	 $("#pageNo").val(oldPage);
	//	 $("#userpost").submit();
	},
	onPageClicked : function(e, originalEvent, type, page) {
		$("#pageNo").val(page);
		
		$("#userpost").prepend("<input type='hidden' name='pageSearch' value='1' />");
		
		$("#userpost").submit();
	}
}


$(function() {
	$(".deletetag").click(function() {
		var delid = $(this).attr("deleteid");
		var currentbt = $(this);
		if (confirm('确定删除吗 ？')) { 
				currentbt.parent().parent().hide("normal", function() {
					currentbt.parent().parent().remove();
				}) 
		}
	});
	$(".updatetag").click(function() {
		var upid = $(this).attr("updateid");
		$("#id").val(upid);
	});
	$("#deletemore").click(function() {
		// $("input[name=items]").each(function() {
		// if ($(this).attr("checked")) {
		// text += ","+$(this).val();
		// }
		// });
	})

})


 
function showbase()
{
	 
	$(".treegrid-expander-collapsed").click();
}

function showall()
{
	$(".treegrid-expander-expanded").click();
}



function search()
{
	
 if($("#date").val()=="按时间")
 {
	 $("#date").val("");
 }
 if($("#name").val()=="关键字:菜单名称")
 {
	 $("#name").val("")
 }
 $("#userpost").submit();
}


$(function(){ 
	
	//alert(jQuery("#contextPath").val()); 
	$("#province").html();
	jQuery.ajax({
		 type:'POST', 
		 url :$("#contextPath").val()+"/diseaseCategory/parentCategory",
		 success:function(data){ 
			if(data.length>0)
			{
//				 $("#province").append( "<option value=''>请选择省份地区</option>");
//				 $("#city").append( "<option value=''>请选择城市</option>");
				 $("#province").change(function(i){
					 $("#parentCategory").val($(this).val());
					 $("#childCategory").val("");
					 loadcity();
				 }) 
				  $.each(data,function(i,j){ 
					 if(i==0)
					 { 
						 $("#province").append( "<option value=''>一级科室</option>");
						 $("#city").append( "<option value=''>二级科室</option>");
					 } 
					 if(j.pkId==$("#parentCategory").val())
	    			 {
	    			 	 $("#province").append("<option selected='true' value='"+j.pkId+"'>"+j.name+"</option>");
	    			 }
					 else
	    			 {
	    			 	 $("#province").append("<option   value='"+j.pkId+"'>"+j.name+"</option>");
	    			 }
				 })
				 
				 
			}
			if( $("#parentCategory").val()!="")
				{
					loadcity();
				}
		 }
	 });
})
function loadcity()
{
	 $("#city").html("");
	 var pro = $("#parentCategory").val()
	 if(pro==""||pro.length<1){
		 return;
	 }
	 jQuery.ajax({
		 type:'POST', 
		 data:"pid="+$("#parentCategory").val(),
		 url :$("#contextPath").val()+"/diseaseCategory/getCategoryByParent",
		 success:function(data){
			if(data.length>0)
			{
				 $("#city").change(function(i){
					 $("#childCategory").val($(this).val());
				 })
				 
				 $.each(data,function(i,j){
					 if(i==0)
					 {
						 $("#city").append( "<option value=''>二级科室</option>");
					 } 
					 if(j.pkId==$("#childCategory").val())
	    			 { 
	    			 	 $("#city").append("<option selected='true' value='"+j.pkId+"'>"+j.name+"</option>");
	    			 }
					 else
	    			 { 
	    			 	 $("#city").append("<option   value='"+j.pkId+"'>"+j.name+"</option>");
	    			 } 
				 })
			}
			else
			{
				 $("#city").append( "<option value=''>二级科室</option>");
			}
		 }
	 });
}


