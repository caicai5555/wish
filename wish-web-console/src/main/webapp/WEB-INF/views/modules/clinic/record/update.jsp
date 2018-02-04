<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <title>医师健康管理工作平台</title>
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/font-awesome/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/page.css" />
	<style>
		.red-hint:before {
			padding: 0 5px;
			color:red;
			content: "*";
		}
		.bracket:before{
			padding: 0 5px;
			content: "（";
		}
		.bracket:after{
			padding: 0 5px;
			content: "）";
		}
	</style>
</head>

<body>
	<div class="container sub-page">
	  <div class="sub-page-title clearfix">
	    <div class="col-xs-10">
	      <ol class="breadcrumb">
	        <li><a href="${content.contextPath}/bioOrder/page">服务</a></li>
	        <li><a href="/doctor/clinic/page?" class="multi-param">$!param.service <span id="used" class="bracket">$!param.used</span>/$!param.total</a></li>
	        <li class="active">$!param.service详情</li>
	      </ol>
	    </div>
	  </div>
	  
	   <div class="sub-page-cont">
	  	<div class="buyer">
	  		<ul class="clearfix">
	  			<li><h3><a class="btn1 user-name" href="${content.contextPath}/sysUser/memberInfo?id=$!{customer.sysUser.pkId}">$!{customer.sysUser.name}</a></h3></li>
	  			<li>性别：$!{customer.sexStr}</li>
	  			<li>民族：$!{customer.nationName}</li>
	  			<li>年龄：$!{customer.sysUser.age}</li>
	  			<li>电话：$!{customer.sysUser.mobile}</li>
	  			<li>出生日期：$!{customer.birthdayStr}</li>
	      	</ul>
	      </div>
	    </div>
	  
	  <div class="sub-page-cont thumbnail">
    	<div class="padding-top-30 padding-bottom-30">
    		<form class="form-horizontal" id="my-form">
    			<input type="text" name="pkId" value="$!entity.pkId" hidden="true"/>
 				<input type="text" name="orderId" id="orderId" value="$!entity.orderId" hidden="true"/>
 				<input type="text" name="serviceClassId" id="serviceClassId" value="$!entity.serviceClassId" hidden="true"/>
 				<input type="text" name="custId" id="custId" value="$!entity.custId" hidden="true"/>
 				<input type="text" name="orderItemId" id="orderItemId"  value="$!entity.orderItemId" hidden="true"/>
 				<input type="text" name="doctorId" id="doctorId" value="$!entity.doctorId" hidden="true"/>
 				<input type="text" name="managerId" id="managerId" value="$!entity.managerId" hidden="true"/>
 				<input type="text" name="status" id="status" value="$!entity.status" hidden="true"/>

				<div class="form-group row">
					<label class="col-xs-2 control-label red-hint">检验单名称：</label>
					<div class="col-xs-3">
						<p class="form-control-static">$!entity.inspectName</p>
					</div>
					<label class="col-xs-2 control-label red-hint">管理师：</label>
					<div class="col-xs-3">
						<input name="manager" value="$!entity.manager" id="manager" type="text" class="form-control" title="管理师" maxlength="64"  placeholder="60字以内"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-2 control-label red-hint">检验日期：</label>
					<div class="col-xs-3">
						<input class="form-control pull-left Wdate" type="text"  name="inspectDate1"  value="$!entity.inspectDate.toString().substring(0,10)" readonly="readonly"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%m-%d'})" title="检验日期"/>
					</div>
					<label class="col-xs-2 control-label red-hint">医院：</label>
					<div class="col-xs-3">
						<input name="hospital" value="$!entity.hospital" id="hospital" type="text" class="form-control" title="医院"  maxlength="60"  placeholder=""/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-2 control-label red-hint">科室：</label>
					<div class="col-xs-3">
						<input name="department" value="$!entity.department" id="department" type="text" class="form-control" title="科室"  maxlength="60" placeholder=""/>
					</div>
					<label class="col-xs-2 control-label red-hint">医生：</label>
					<div class="col-xs-3">
						<input name="doctor" value="$!entity.doctor" id="doctor" type="text" class="form-control" title="医生"  maxlength="64" placeholder="60字以内"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-2 control-label red-hint">地点：</label>
					<div class="col-xs-8">
						<input name="address" id="address" value="$!entity.address" type="text" class="form-control" placeholder="地点，60字以内" title="地点" maxlength="60"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-2 control-label red-hint">描述：</label>
					<div class="col-xs-8">
						<input name="description" id="description" value="$!entity.description" type="text" class="form-control" placeholder="描述，200字以内" title="描述"  maxlength="200"/>
					</div>
				</div>
				<br> <br>
				<div class="col-xs-9 col-xs-offset-1">
					<table class="table table-hover table-striped table-bordered table-fixed">
						<thead>
						<tr><th>检验项</th><th>值</th><th>单位</th><th>参考值</th></tr>
						</thead>
						<tbody>
							#foreach($!element in $!result.items)
							<tr>
								<td class="red-hint">$!element.itemName</td>
								<td>
									<input name="value" value="$!element.value" type="text" class="form-control num-required" title="检验项值" placeholder="必须是数字"/>
									<input name="englishName" value="$!element.englishName" type="text" class="form-control hide"/>
									<input name="reduceFormula" value="$!element.reduceFormula" type="text" class="form-control hide"/>
								</td>
								<td>$!element.units</td>
								<td>$!element.normalRange</td>
							</tr>
							#end
						</tbody>
					</table>
					<div class="form-group row">
						<label class="col-xs-2 ">结论：</label>
						<div class="col-xs-10">
							<textarea  name="conclusion"  class="form-control" rows="3" placeholder="结论" >$!entity.conclusion</textarea>
						</div>
					</div>
					<input name="_id" value="$!result.id" type="text" class="hide"/>
				</div>
    		</form>
    	</div>
    	<div class="serviceBtn row">
    		<div class="col-xs-offset-8 col-xs-4">
    			<button type="button" class="btn btn-default btn-lg delBtns">删除</button>
    			<button type="button" class="btn btn-success btn-lg" id="formSubmit">提交</button>
    		</div>
    	</div>
	  </div>
	</div>

	<!--页面跳转传递的数据-->
	<form class="hide" id="param">
		<input name="service" value="$!param.service" type="text">
		<input name="used" value="$!param.used" type="text">
		<input name="total" value="$!param.total" type="text">
		<input name="orderId" value="$!param.orderId" type="text">
		<input name="serviceClassId" value="$!param.serviceClassId" type="text">
		<input name="custId" value="$!param.custId" type="text">
		<input name="doctorId" value="$!param.doctorId" type="text">
		<input name="doctor" value="$!param.doctor" type="text">
		<input name="assitantRecordId" value="$!param.assitantRecordId" type="text">
	</form>

<script type="text/javascript" src="${content.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/main.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/My97-date/WdatePicker.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/plugins/metronic/assets/inputFile/ajaxfileupload.js"></script>
<script>
  function closeWindow(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
  }
  
  $(function(){
	  $('[name="value"]').each(function(i){
		  $(this).attr('name', 'items.' + i + '.' + $(this).attr('name'));
	  });
	  $('[name="englishName"]').each(function(i){
		  $(this).attr('name', 'items.' + i + '.' + $(this).attr('name'));
	  });
	  $('[name="reduceFormula"]').each(function(i){
		  $(this).attr('name', 'items.' + i + '.' + $(this).attr('name'));
	  });
	  //提交更新的数据
	  $('.btn-success').click(function(){
		  var flag = true;
		  $(':text:visible').each(function(){
			  if ($(this).val().trim().length === 0) {
				  flag = false;
				  layerMsg($(this).attr('title') + "，不能为空！");
				  return false;
			  }
			  if ($(this).val().trim().length > $(this).attr('maxlength')-0) {
				  flag = false;
				  layerMsg($(this).attr('title') + "，输入字符过长！");
				  return false;
			  }
		  });
		  if(!flag) return false;
		  $('.num-required').each(function(){
			  var reg = /^[-+]?(([1-9]\d*)|0)(\.\d{1,4})?$/;
			  if (!reg.test($(this).val())) {
				  flag = false;
				  layerMsg($(this).attr('title') + '，必须是数字！');
				  return false;
			  }
		  });
		  if(!flag) return false;
			  $.ajax({
					type: 'post',
					url: '/doctor/clinic/update',
					data: $('#my-form').serialize(),
					success: function(reply){
						if(200 === reply.statusCode){
							layerMsg("操作成功！", function(){
								var pkId = $('[name="pkId"]').val();
								if ($('[name="status"]').val() != '1') {
									$('[name="used"]').val($('[name="used"]').val() - 0 + 1);
								}
								window.location.href = '/doctor/clinic/index?operate=detail&pkId=' + pkId + '&' + $('#param').serialize();
							});
						}
						else{ layerMsg("操作失败！"); }
					},
					error: function(msg){
						layerMsg("网络异常！");
					}
				});
		  });
	  
	  $('.delBtns').click(function(){
		  layerConfirm('确认删除？', 
				function(){
						$.ajax({
							url: '/doctor/clinic/delete',
							type: 'post',
							data: $('#my-form').serialize(),
							success: function(reply){
								if(200===reply.statusCode){
									layerMsg("删除成功！", function(){
										window.location.href = '/doctor/clinic/page?' + $('#param').serialize();
									});
								}else{
									layerMsg("删除失败！");
								}
							},
							error: function(reply){
								layerMsg("网络异常！");
							}
						});
				},
				function(){} 
		);
	  });

	  var param = $('#param').serialize();
	  $('.multi-param').click(function(){
		  $(this).attr('href', $(this).attr('href') + param);
	  })
	  
  });
</script>
</body>
</html>