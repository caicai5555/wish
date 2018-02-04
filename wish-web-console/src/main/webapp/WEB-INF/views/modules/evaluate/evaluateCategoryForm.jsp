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
	<script src="/static/jquery-validation/1.11.1/lib/jquery.form.js"></script>
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
	<div id="mask" class="mask"></div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/evaluate/catgroyList">分类列表</a></li>
		<li><a href="${ctx}/evaluate/categoryForm">分类添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="category" action="${ctx}/evaluate/catgroySave" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">上级分类:</label>
			<div class="controls">
                <sys:treeselect id="category" name="pid" value="${category.parent.id}" labelName="category.name" labelValue="${category.parent.name}"
					title="分类" url="/evaluate/categoryTreeData" extId="${category.id}" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>分类名称：</label>
            <div class="controls">
            	<form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
            </div>
       	</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>分类名称：</label>
            <div class="controls">
            	<form:input path="enname" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
            </div>
       	</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>