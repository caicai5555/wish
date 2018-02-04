<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>家族病史管理</title>
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
		<li><a href="${ctx}/medicalHistory/familyHistory/index">家族病史列表</a></li>
		<li class="active"><a href="${ctx}/medicalHistory/familyHistory/form">家族病史${not empty history.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="history" action="${ctx}/medicalHistory/familyHistory/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>亲属关系：</label>
            <div class="controls">
				<form:select path="relativeCode" class="input-medium">
					<form:option value=""  label="请选择"/>
					<form:options items="${fns:getDictList('relative')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
            </div>
       	</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>诊断年龄：</label>
            <div class="controls">
				<form:input path="diseaseAge" htmlEscape="false" maxlength="150" class="required"/>
            </div>
       	</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>疾病名称：</label>
            <div class="controls">
				<form:select path="diseaseCode" class="input-medium">
					<form:option value=""  label="请选择"/>
					<form:options items="${fns:getDictList('disease')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
            </div>
       	</div>
		<div class="control-group">
            <div class="controls">
				<form:input path="diseaseName" htmlEscape="false" maxlength="150"/>
            </div>
       	</div>
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>是否遗传：</label>
            <div class="controls">
				<form:select path="geneticFlag" class="input-medium">
					<form:option value="2"  label="不确定"/>
					<form:option value="0"  label="是"/>
					<form:option value="1"  label="否"/>
				</form:select>
            </div>
       	</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>