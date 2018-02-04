<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>档案信息</title>
	<meta name="decorator" content="default"/>
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
		function updatePerInfo(){
			$("#savePerInfoB").css("display", "");
			$("#canelPerInfoB").css("display", "");
			$("#updatePerInfoB").css("display", "none");
			$("input[extParam1='param1']").removeAttr("readonly");
		}
		function updatePerInfo2(){
			$("#savePerInfoC").css("display", "");
			$("#canelPerInfoC").css("display", "");
			$("#updatePerInfoC").css("display", "none");
			$("input[extParam1='param3']").removeAttr("readonly");
		}

		function canelPerInfo(){
			$("input[extParam1='param1']").attr("readonly","readonly");
			$("#savePerInfoB").css("display", "none");
			$("#canelPerInfoB").css("display", "none");
			$("#updatePerInfoB").css("display", "");

			$("#name").val($("#name_dis").val());
			$("#cardNo").val($("#cardNo_dis").val());
			$("#email").val($("#email_dis").val());
			$("#phone").val($("#phone_dis").val());
			$("#mobile").val($("#mobile_dis").val());
			$("#address").val($("#address_dis").val());


			$("#addressProvince").val($("#addressProvince_dis").val());
			$("#addressCity").val($("#addressCity_dis").val());
			$("#addressCounty").val($("#addressCounty_dis").val());
			$("#addressTown").val($("#addressTown_dis").val());
			$("#addressVillage").val($("#addressVillage_dis").val());

			$("#hukouProvince").val($("#hukouProvince_dis").val());
			$("#hukouCity").val($("#hukouCity_dis").val());
			$("#hukouCounty").val($("#hukouCounty_dis").val());
			$("#hukouTown").val($("#hukouTown_dis").val());
			$("#hukouVillage").val($("#hukouVillage_dis").val());
		}

		function canelPerInfo2(){
			$("input[extParam1='param3']").attr("readonly","readonly");
			$("#savePerInfoC").css("display", "none");
			$("#canelPerInfoC").css("display", "none");
			$("#updatePerInfoC").css("display", "");

			$("#birthday").val($("#birthday_dis").val());
			$("#sex").val($("#sex_dis").val());
			$("#marrayType").val($("#marrayType_dis").val());
			$("#height").val($("#height_dis").val());
			$("#physicalCoefficient").val($("#physicalCoefficient_dis").val());
			$("#profession").val($("#profession_dis").val());
			$("#bloodType").val($("#bloodType_dis").val());
		}

		function savePerInfo(){
			var name = $("#name").val();
			var cardNo =$("#cardNo").val();
			var email =$("#email").val();
			var phone =$("#phone").val();
			var mobile =$("#mobile").val();
			var address =$("#address").val();
			var addressProvince =$("#addressProvince").val();
			var addressCity=$("#addressCity").val();
			var addressCounty =$("#addressCounty").val();
			var addressTown =$("#addressTown").val();
			var addressVillage=$("#addressVillage").val();
			var hukouProvince =$("#hukouProvince").val();
			var hukouCity =$("#hukouCity").val();
			var hukouCounty =$("#hukouCounty").val();
			var hukouTown =$("#hukouTown").val();
			var hukouVillage =$("#hukouVillage").val();
			if(booValue(name)){
				layer.alert("用户的名字不能为空,请重新编辑");
				return;
			}
			if(booValue(cardNo)){
				layer.alert("身份证号不能为空，请重新编辑");
				return;
			}
			if(booValue(email)){
				layer.alert("邮件不能为空,请重新编辑");
				return;
			}
			if(booValue(phone)){
				layer.alert("电话号码不能为空,请重新编辑");
				return;
			}
			if(booValue(mobile)){
				layer.alert("手机号不能为空,请重新编辑");
				return;
			}
			if(booValue(addressProvince)){
				layer.alert("现住址省份不能为空,请重新编辑");
				return;
			}
			if(booValue(addressCity)){
				layer.alert("现住址市不能为空,请重新编辑");
				return;
			}
			if(booValue(addressCounty)){
				layer.alert("现住址县不能为空,请重新编辑");
				return;
			}

			if(booValue(addressTown)){
				layer.alert("现住址乡（街道）不能为空,请重新编辑");
				return;
			}

			if(booValue(addressVillage)){
				layer.alert("现住址村（小区）不能为空,请重新编辑");
				return;
			}

			if(booValue(hukouProvince)){
				layer.alert("户籍省份不能为空,请重新编辑");
				return;
			}
			if(booValue(hukouCity)){
				layer.alert("户籍市不能为空,请重新编辑");
				return;
			}
			if(booValue(hukouCounty)){
				layer.alert("户籍县不能为空,请重新编辑");
				return;
			}
			if(booValue(hukouTown)){
				layer.alert("户籍乡（街道）不能为空,请重新编辑");
				return;
			}
			if(booValue(hukouProvince)){
				layer.alert("户籍村（小区）不能为空,请重新编辑");
				return;
			}
			var userId = $("#userId").val();
			var dataVal = "name="+name
					+"&cardNo="+cardNo
					+"&email="+email
					+"&mobile="+mobile
					+"&phone="+phone
					+"&addressProvince="+addressProvince
					+"&addressCity="+addressCity
					+"&addressCounty="+addressCounty
					+"&addressTown="+addressTown
					+"&addressVillage="+addressVillage
					+"&hukouProvince="+hukouProvince
					+"&hukouCity="+hukouCity
					+"&hukouCounty="+hukouCounty
					+"&hukouTown="+hukouTown
					+"&hukouVillage="+hukouVillage
					+"&id="+userId
					+"&dbType=mysql";
			jQuery.ajax({
				type:"POST",
				url :"${ctx}/sys/user/updatePerInfo",
				data:dataVal,
				success:function(retVal){
					if(!(null==retVal) && !(""==retVal) && !(null==retVal.result) && !(""==retVal.result)){
						if(retVal.result=='操作成功'){
							layer.alert('操作成功', {
								shade : false
								// 不显示遮罩
							}, function(e) {


								parent.location.reload();

								// 关闭当前窗口
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭
							});
						}else{
							layer.alert(retVal.result)
						}
					}else{
						layer.alert('更新失败，请重试')
					}



				}
			});
		}


		function savePerInfo2(){
			var birthday = $("#birthday").val();
			var sex =$("#sex").val();
			var marrayType =$("#marrayType").val();
			var height =$("#height").val();
			var physicalCoefficient =$("#physicalCoefficient").val();
			var profession =$("#profession").val();
			var bloodType =$("#bloodType").val();

			if(booValue(birthday)){
				layer.alert("生日不能为空,请重新编辑");
				return;
			}
			if(booValue(sex)){
				layer.alert("性别不能为空,请重新编辑");
				return;
			}
			if(booValue(marrayType)){
				layer.alert("婚姻状况不能为空,请重新编辑");
				return;
			}
			if(booValue(height)){
				layer.alert("身高不能为空,请重新编辑");
				return;
			}

			if(booValue(physicalCoefficient)){
				layer.alert("体力系数不能为空,请重新编辑");
				return;
			}
			if(booValue(profession)){
				layer.alert("职业不能为空,请重新编辑");
				return;
			}
			if(booValue(bloodType)){
				layer.alert("血型不能为空,请重新编辑");
				return;
			}

			var userId = $("#userId").val();
			var dataVal = "birthday="+birthday
					+"&sex="+sex
					+"&married="+marrayType
					+"&height="+height
					+"&pal="+physicalCoefficient
					+"&job="+profession
					+"&blood="+bloodType
					+"&id="+userId
					+"&dbType=mongo";
			jQuery.ajax({
				type:"POST",
				url :"${ctx}/sys/user/updatePerInfo",
				data:dataVal,
				success:function(retVal){

					if(!(null==retVal) && !(""==retVal) && !(null==retVal.result) && !(""==retVal.result)){
						if(retVal.result=='操作成功'){
							layer.alert('操作成功', {
								shade : false
								// 不显示遮罩
							}, function(e) {


								parent.location.reload();

								// 关闭当前窗口
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
								parent.layer.close(index); //再执行关闭
							});
						}else{
							layer.alert(retVal.result)
						}
					}else{
						layer.alert('更新失败，请重试')
					}

				}
			});
		}


		function booValue(vv){
			var ree = ''
			if(null==''||''==vv){
				ree = 'fal';
				return ree;
			}

			var result=vv.replace(/(^\s+)|(\s+$)/g,"");//去掉前后空格
			if(null==result || ''==result){
				ree = 'fal';
			}
			return ree;
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>账户信息</a></li>
	</ul><br/>
	<table style="width: 100%">
		<tr>
			<td colspan="2" align="right" style="margin-right: 200px;">
				<input type="button" style="width: 50px;" value="编辑" onclick="updatePerInfo();" id="updatePerInfoB">
				<input type="button" style="width: 50px;display: none;" value="保存" onclick="savePerInfo();" id="savePerInfoB">
				<input type="button" style="width: 50px;display: none;" value="取消" onclick="canelPerInfo();" id="canelPerInfoB">
			</td>
		</tr>
		<tr>


			<td style="width: 70%;">

				<form:form id="inputForm_mysql" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
					<sys:message content="${message}"/>

					<div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true" id="name" extParam1="param1"/>
							<form:input path="id" htmlEscape="false" maxlength="50" class="required" readonly="true" id="userId" cssStyle="display: none;"/>
							<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true" id="name_dis" cssStyle="display: none;" extParam1="param2"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证:</label>
						<div class="controls">
							<form:input path="cardNo" htmlEscape="false" maxlength="50"  readonly="true" id="cardNo" extParam1="param1"/>
							<form:input path="cardNo" htmlEscape="false" maxlength="50"  readonly="true" id="cardNo_dis" extParam1="param2" cssStyle="display: none;"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">邮箱:</label>
						<div class="controls">
							<form:input path="email" htmlEscape="false" maxlength="50" class="email"  readonly="true" id="email" extParam1="param1"/>
							<form:input path="email" htmlEscape="false" maxlength="50" class="email"  readonly="true" id="email_dis" extParam1="param2" cssStyle="display: none;"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">电话:</label>
						<div class="controls">
							<form:input path="phone" htmlEscape="false" maxlength="50"  readonly="true" id="phone" extParam1="param1"/>
							<form:input path="phone" htmlEscape="false" maxlength="50"  readonly="true" id="phone_dis" extParam1="param2" cssStyle="display: none;"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">手机:</label>
						<div class="controls">
							<form:input path="mobile" htmlEscape="false" maxlength="11"  readonly="true" id="mobile" extParam1="param1"/>
							<form:input path="mobile" htmlEscape="false" maxlength="11"  readonly="true" id="mobile_dis" extParam1="param2" cssStyle="display: none;"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">现住址:</label>
						<div class="controls">
							<input value="${user.addressProvince}"  readonly="true" id="addressProvince" style="width:80px;" extParam1="param1"/>
							<input value="${user.addressProvince}"  readonly="true" id="addressProvince_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（省）</font> </span>
							<input value="${user.addressCity}"   readonly="true" id="addressCity" style="width:80px;" extParam1="param1"/>
							<input value="${user.addressCity}"   readonly="true" id="addressCity_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（市）</font> </span>
							<input value="${user.addressCounty}"   readonly="true" id="addressCounty" style="width:80px;" extParam1="param1"/>
							<input value="${user.addressCounty}"   readonly="true" id="addressCounty_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（县）</font> </span>
							<input value="${user.addressTown}"    readonly="true" id="addressTown" style="width:80px;" extParam1="param1"/>
							<input value="${user.addressTown}"    readonly="true" id="addressTown_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（乡）</font> </span>
							<input value="${user.addressVillage}"    readonly="true" id="addressVillage" style="width:150px;" extParam1="param1"/>
							<input value="${user.addressVillage}"    readonly="true" id="addressVillage_dis" style="width:150px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（村）</font> </span>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">户籍所在地:</label>
						<div class="controls">
							<input value="${user.hukouProvince}"  readonly="true" id="hukouProvince" style="width:80px;" extParam1="param1"/>
							<input value="${user.hukouProvince}"  readonly="true" id="hukouProvince_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（省）</font> </span>
							<input value="${user.hukouCity}"   readonly="true" id="hukouCity" style="width:80px;" extParam1="param1"/>
							<input value="${user.hukouCity}"   readonly="true" id="hukouCity_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（市）</font> </span>
							<input value="${user.hukouCounty}"   readonly="true" id="hukouCounty" style="width:80px;" extParam1="param1"/>
							<input value="${user.hukouCounty}"   readonly="true" id="hukouCounty_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（县）</font> </span>
							<input value="${user.hukouTown}"    readonly="true" id="hukouTown" style="width:80px;" extParam1="param1"/>
							<input value="${user.hukouTown}"    readonly="true" id="hukouTown_dis" style="width:80px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（乡）</font> </span>
							<input value="${user.hukouVillage}"    readonly="true" id="hukouVillage" style="width:150px;" extParam1="param1"/>
							<input value="${user.hukouVillage}"    readonly="true" id="hukouVillage_dis" style="width:150px;display: none;" extParam1="param2"/>
							<span class="help-inline"><font >（村）</font> </span>
						</div>
					</div>
					<%--<div class="form-actions">--%>
						<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>--%>
					<%--</div>--%>
				</form:form>

			</td>
		</tr>
		<tr>
			<td colspan="2">
				<ul class="nav nav-tabs">
					<li class="active"><a>基本信息</a></li>
				</ul><br/>
			</td>

		</tr>
		<tr>
			<td colspan="2" align="right" style="margin-right: 200px;">
				<input type="button" style="width: 50px;" value="编辑" onclick="updatePerInfo2();" id="updatePerInfoC">
				<input type="button" style="width: 50px;display: none;" value="保存" onclick="savePerInfo2();" id="savePerInfoC">
				<input type="button" style="width: 50px;display: none;" value="取消" onclick="canelPerInfo2();" id="canelPerInfoC">
			</td>
		</tr>



		<tr>
			<td style="width: 70%;">

				<form:form id="inputForm_mysql" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
					<sys:message content="${message}"/>

					<div class="control-group">
						<label class="control-label">出生年月:</label>
						<div class="controls">
							<form:input path="birthday" htmlEscape="false" maxlength="50" class="required" readonly="true" id="birthay" extParam1="param3"/>
							<form:input path="birthday" htmlEscape="false" maxlength="50" class="required" readonly="true" id="birthday_dis" cssStyle="display: none;" extParam1="param4"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别:</label>
						<div class="controls">
							<form:input path="sex" htmlEscape="false" maxlength="50"  readonly="true" id="sex" extParam1="param3"/>
							<form:input path="sex" htmlEscape="false" maxlength="50"  readonly="true" id="sex_dis" extParam1="param4" cssStyle="display: none;"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">婚姻状况:</label>
						<div class="controls">
							<form:input path="marrayType" htmlEscape="false" maxlength="50" class="email"  readonly="true" id="marrayType" extParam1="param3"/>
							<form:input path="marrayType" htmlEscape="false" maxlength="50" class="email"  readonly="true" id="marrayType_dis" extParam1="param4" cssStyle="display: none;"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">身高:</label>
						<div class="controls">
							<form:input path="height" htmlEscape="false" maxlength="50"  readonly="true" id="height" extParam1="param3"/>
							<form:input path="height" htmlEscape="false" maxlength="50"  readonly="true" id="height_dis" extParam1="param4" cssStyle="display: none;"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">重力系数:</label>
						<div class="controls">
							<form:input path="physicalCoefficient" htmlEscape="false" maxlength="11"  readonly="true" id="physicalCoefficient" extParam1="param3"/>
							<form:input path="physicalCoefficient" htmlEscape="false" maxlength="11"  readonly="true" id="mphysicalCoefficient_dis" extParam1="param4" cssStyle="display: none;"/>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">职业:</label>
						<div class="controls">
							<form:input path="profession" htmlEscape="false" maxlength="11"  readonly="true" id="profession" extParam1="param3"/>
							<form:input path="profession" htmlEscape="false" maxlength="11"  readonly="true" id="profession_dis" extParam1="param4" cssStyle="display: none;"/>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">血型:</label>
						<div class="controls">
							<form:input path="bloodType" htmlEscape="false" maxlength="11"  readonly="true" id="bloodType" extParam1="param3"/>
							<form:input path="bloodType" htmlEscape="false" maxlength="11"  readonly="true" id="bloodType_dis" extParam1="param4" cssStyle="display: none;"/>
						</div>
					</div>



					<%--<div class="form-actions">--%>
					<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>--%>
					<%--</div>--%>
				</form:form>

			</td>

		</tr>
	</table>

</body>
</html>