<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>检验项管理</title>
	<style>
		.red-hint:before {
			padding: 0 5px;
			color:red;
			content: "*";
		}
	</style>
</head>

<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/clinic/record/page">临床记录列表</a></li>
	<li class="active"><a href="${ctx}/clinic/record/read?id=${entity.id}">临床记录修改<shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form id="inputForm" action="/admin/clinic/record/update"  method="post" class="form-horizontal">
	<input type="text" class="hide" name="id" value="${entity.id}">
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">记录类型:</label>
		<div class="controls">
			<input type="text" class="form-control" name="typeName" value="${entity.typeName}" placeholder="typeName"  maxlength="128" disabled="disabled"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">检验单名称:</label>
		<div class="controls">
			<input type="text" class="form-control" name="inspectName" value="${entity.inspectName}" placeholder="检验单名称"  maxlength="128" disabled="disabled"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">管理师:</label>
		<div class="controls">
			<select name="managerId" data-selected="${entity.managerId}">
				<c:forEach items="${users}" var="item">
					<option value="${item.id}">${item.name}</option>
					<%--<option value="${item.key}">${item.value}</option>--%>
				</c:forEach>
			</select>
			<input type="text" class="form-control hide" name="manager" value="${entity.manager}" placeholder="管理师"  maxlength="128"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">患者:</label>
		<div class="controls">
			<select name="custId" data-selected="${entity.custId}">
				<c:forEach items="${users}" var="item">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
			<%--<input type="text" class="form-control" name="manager" value="${entity.manager}" placeholder="管理师"  maxlength="128"/>--%>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">检验日期:</label>
		<div class="controls">
			<input type="text" class="form-control" name="inspectDate" value='<fmt:formatDate value="${entity.inspectDate}" pattern="yyyy-MM-dd"/>' placeholder="检验日期"
				   maxlength="128" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">医院:</label>
		<div class="controls">
			<input type="text" class="form-control" name="hospital" value="${entity.hospital}" placeholder="医院" maxlength="128"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">科室:</label>
		<div class="controls">
			<input type="text" class="form-control" name="department" value="${entity.department}" placeholder="科室" maxlength="128"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">医生:</label>
		<div class="controls">
			<select name="doctorId"  data-selected="${entity.doctorId}">
				<c:forEach items="${users}" var="item">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
			<input type="text" class="form-control hide" name="doctor" value="${entity.doctor}" placeholder="医生" maxlength="128"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">地点:</label>
		<div class="controls">
			<input type="text" class="form-control" name="address" value="${entity.address}" placeholder="地点" maxlength="128"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">描述:</label>
		<div class="controls">
			<textarea  class="form-control"  name="description" rows="3" maxlength="1024" placeholder="描述">${entity.description}</textarea>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">检验项:</label>
		<div class="controls">
		<table class="table table-hover table-striped table-bordered table-fixed">
			<thead>
			<tr><th>项目</th><th>值</th><th>单位</th><th>参考值</th></tr>
			</thead>
			<tbody>
			<c:forEach items="${detail.inspectItems}" var="item">
				<tr>
					<td>${item.itemName}</td>
					<td>
						<input type="text" name="inspectItems.i.value" value="${item.value}" data-init="${item.value}" class="changed"/>
						<input type="text" name="itemEnglishName" value="${item.englishName}" class="hide"/>
						<input type="text" name="itemName" value="${item.itemName}" class="hide"/>
					</td>
					<td>${item.units}</td>
					<td>${item.normalRange}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
			</div>
	</div>
	<div class="control-group">
		<label class="control-label">结论:</label>
		<div class="controls">
			<textarea  class="form-control"  name="conclusion" rows="3" maxlength="1024" placeholder="结论">${entity.conclusion}</textarea>
		</div>
	</div>

	<div class="form-actions">
		<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form>

<script type="text/javascript">
	$(function(){
		$('select').each(function(){
			$(this).val($(this).attr('data-selected'));
		});
		$('#btnSubmit').click(function(){
			$('.changed').each(function(i){
				if($(this).attr('data-init') === $(this).val()) $(this).val('');
			});
			$('[name="manager"]').val($('[name="managerId"] :selected').text());
			$('[name="doctor"]').val($('[name="doctorId"] :selected').text());
		})
	})
</script>
</body>
</html>