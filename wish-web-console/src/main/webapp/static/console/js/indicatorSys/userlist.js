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

$(function() {
  TableManaged.init();
  // FormValidation.init();

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

function toggleExpand(type){
  var treeList = $("#treeList .tree-list-content").data("kendoTreeList");
  $("#treeList tbody>tr").each(function(i, e){
    treeList[type]($(e));
  });
}
$(function() { 
	window.treeListInstance = new TreeList({
	    dataURL: top.baseContextPath + '/sysMenu/find',
        childNodeName: 'childMenu',
        formID:'#userpost',
	    //listType:'list',
	    //convertRecord: function(dataRecord, exportObj, beginIndex){ },
	    fieldMap: {
			id: {
				value: 'pkId',
				type: 'number'
			},
			parentID: {
				value: 'parentId',
				type: 'number',
				condition: 'parentId !== 0'
			},
			num: {
				value: 'parentId === 0 ? $index :""',
				type: 'number',
				width: 60,
				title: '序号'
			},
			menuName: {
				value: '<span class="fa {#icon}" style="margin-right: 10px;"></span>{#name}',
				type: 'html',
				title:'菜单名称',
				width: 200,
				expandable: true
			},
			path: {
				value: 'href',
				type: 'string',
				title:'访问路径'
			},
			domain: {
				value: 'domain',
				type: 'string',
				title:'域名'
			},
			formatDate: {
				value: 'formatDate',
				type: 'string',
				title:'创建时间'
			},
			sort: {
				value: 'sort',
				type: 'number',
				title: '排序'
			},
			action: {
				value: [
				 '1::<a href="javascript:void(0)"  onclick="addChild($!{pkId})"> 添加子菜单 </a>\
                     <a href="javascript:void(0)" onclick="openDetailedMenu($!{pkId})"> 详情</a>\
                     <a href="javascript:void(0)" onclick="openUpdateMenu($!{pkId})"> 修改 </a>', 
	             '2::<a href="javascript:void(0)" onclick="deleteId($!{pkId});" id="modify$!{pkId}">禁用</a>',
	             '3::<a href="javascript:void(0)" onclick="deleteId($!{pkId});" id="modify$!{pkId}">启用</a>',
	             //child
	             '4::<a class="hidden" onclick="toFunctionList($!{pkId})" href="javascript:void(0)"> 添加子菜单功能点 </a>\
	                 <a onclick="openDetailedFunction($!{pkId})" href="javascript:void(0)"> 详细</a>\
	                 <a onclick="updateChildFunction($!{pkId})" href="javascript:void(0)"> 修改 </a>',
	             '5::<a href="javascript:void(0)" onclick="deleteId($!{pkId});" id="modify$!{pkId}">禁用</a>',
	             '6::<a onclick="deleteId($!{pkId});" id="modify$!{pkId}" href="javascript:void(0)">启用</a>'
	             ],
				type: 'html',
				title: '操作',
				width: 220,
				cellRender: function(templateMap, rowData, value){
					var d = rowData.$this;
					var template = '';
					if(d.parentId === 0){
						//一级
						template = templateMap[1];
						if(d.delFlag == 1){
							template += templateMap[2];
						} 
				        if(d.delFlag == -1){
				        	template += templateMap[3];
				        } 
					}else{
						//二级
						template = templateMap[4];
						if(d.delFlag == 1){
							template += templateMap[5];
						}else{
							template += templateMap[6];
						}
					}
					return template;
				}
			}
	}
}); 
	  //日历初始化
	    BUI.use('bui/calendar',function(Calendar){
	      var datepicker = new Calendar.DatePicker({
	        trigger:'.calendar-time',
	        showTime:false,
	        autoRender : true
	      });
	    });   	    
    
});
