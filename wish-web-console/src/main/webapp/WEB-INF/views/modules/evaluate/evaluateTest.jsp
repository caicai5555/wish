<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估管理</title>
	<meta name="decorator" content="default"/>
	<script src="/static/common/layer.js"></script>
	<script src="/static/jquery-validation/1.11.1/lib/jquery.form.js"></script>
	<script type="text/javascript">
		function formSubmit(){
			var data = jQuery("#inputForm").formSerialize();
			$("#inputForm").ajaxSubmit({
				url : "${ctx}/evaluate/testExcute",
				data : data,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if(data.success&&data.data!=null){
						var msg="";
						$.each(data.data,function(i,item) {   
							msg = msg+item.name+"~~"+item.conclusion+"<br/>";      
				         });  
						layer.alert(msg);
					}else{
						alert(data.msg);
					}
				},
				error : function(result) {
					alert("执行异常!");
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/evaluate/index">评估列表</a></li>
		<li><a href="${ctx}/evaluate/test?id=${id}">评估测试</a></li>
	</ul>
	<form:form id="inputForm" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${id}" id="id"/>
		<input type="hidden" name="paramStr" value="${paramStr}" id="paramStr"/>
		<input type="hidden" name="paramNameStr" value="${paramNameStr}" id="paramNameStr"/>
		<c:forEach items="${paramList}" var="item">
			<div class="control-group">
	        	<label class="control-label">${item.name}：</label>
	            <div class="controls">
	            	<input type="text" name="${item.enname}" id="${item.enname}" />
	            </div>
	       	</div> 
		</c:forEach>
		<div class="form-actions">
			<input type="button" class="btn btn-primary" value="测试" onclick="formSubmit()"/>
		</div>
	</form:form>
</body>
</html>