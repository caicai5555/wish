<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">     
	    .mask {       
	            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
	            z-index: 1002; left: 0px;     
	            opacity:0.5; -moz-opacity:0.5;     
	        }     
	</style> 
	<script src="/static/common/layer.js"></script>
	<script type="text/javascript">
	    $(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/evaluate/index/">评估列表</a></li>
		<li class="active"><a href="${ctx}/evaluate/add">评估添加</a></li>
	</ul>  
	<form:form id="inputForm" action="${ctx}/evaluate/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label">归属分类:</label>
			<div class="controls">
                <sys:treeselect id="category" name="category" value="${category.parent.id}" 
                	labelName="category.name" labelValue="${category.parent.name}"
                	notAllowSelectParent="true"
					title="分类" url="/evaluate/categoryTreeData?type=1" extId="${category.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>上传附件：</label>
            <div class="controls">
                <input name="file" type="file" id="file" multiple="multiple"> </input>
            </div>
       	</div>
       	<div class="control-group">
        	<label class="control-label">备注：</label>
            <div class="controls">
                <textarea name="remarks" id="remarks" rows="5" class="form-control" cols="5"></textarea>
            </div>
       	</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>