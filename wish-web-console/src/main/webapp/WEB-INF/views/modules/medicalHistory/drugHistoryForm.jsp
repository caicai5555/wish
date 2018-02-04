<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用药史管理</title>
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
		<li><a href="${ctx}/medicalHistory/drugHistory/index">用药史列表</a></li>
		<li class="active"><a href="${ctx}/medicalHistory/drugHistory/form">用药史${not empty history.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="history" action="${ctx}/medicalHistory/drugHistory/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		连续服用6个月以上，平均每日服用一次以上。
		<div class="control-group">
        	<label class="control-label"><font color="red">*</font>药物名称：</label>
            <div class="controls">
				<form:checkboxes path="drugCodes" items="${fns:getDictList('drug')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<textarea name="otherContent" id="otherContent" rows="5" class="form-control" cols="5">${history.otherContent }</textarea>
            </div>
       	</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>