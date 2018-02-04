<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>问卷管理</title>
	<style>
		.form-group { margin-bottom: 10px!important;}
		table {
			table-layout: fixed;
		}
		td {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
	</style>
	<script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/questionnaire/page">问卷列表</a></li>
	<li><a href="${ctx}/questionnaire/create">问卷添加</a></li>
</ul>
<form id="searchForm" action="${ctx}/questionnaire/page" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<%--<label>问卷名称 ：</label><input type="text" name="inspectName"  maxlength="50" class="input-medium" value=""/> &nbsp;--%>
	<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>--%>
</form>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>序号</th> <th>创建时间</th> <th>问卷名称</th> <th>问卷类别</th> <th>创建人</th> <th>后台计算类名</th> <th>结果页面地址</th> <th>问卷描述</th> <th>问卷图片url</th> <th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="element" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td><fmt:formatDate value="${element.createDate}" pattern="yyyy-MM-dd"/></td>
			<td>${element.questionnaireName}</td>
			<td>
				<c:if test="${element.questionnaireCtgr == 0}"> 普通 </c:if>
				<c:if test="${element.questionnaireCtgr == 1}"> 随访 </c:if>
			</td>
			<td>${element.sysUserId} </td>
			<td>${element.backComputeClass}</td>
			<td>${element.resultUrl}</td>
			<td>${element.remark}</td>
			<td>${element.questionnaireUrl}</td>
			<shiro:hasPermission name="sys:dict:edit">
				<td>
					<a href="${ctx}/questionnaire/read?id=${element.id}">查看</a>
				</td>
			</shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>


<script>
    function closeWindow(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    }
    $(function(){
        var param = $('#param').serialize();

        $("#addRecord").click(function(){
            var title=$(this).attr("title");
            var content=$(this).attr("href") + param;
            parent.layerBox(title,content,"600px","250px");
            return false;
        })

        $('.multi-param').click(function(){
            $(this).attr('href', $(this).attr('href') + param);
        });

        $('.startup').click(function () {
            var startTime= $(this).closest('tr').find('[name="startTime"]').val();
            $(this).attr('href', $(this).attr('href') + startTime);
        })

        $('[name="startTime"]').each(function () {
            var val = $(this).val().trim();
            if (val != ''){
                $(this).closest('tr').find('.startup').remove();
            }
        })

    })
    //多行文字截取
    $(function(){
        $(".figCont").each(function(){
            var boxHeight = $(this).height();
            while ($(this).children("p").height() > boxHeight){
                $(this).children("p").text($(this).children("p").text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
            }
        });
    })
    $(window).resize(function(){
        $(".figCont").each(function(){
            var boxHeight = $(this).height();
            while ($(this).children("p").height() > boxHeight){
                $(this).children("p").text($(this).children("p").text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
            }
        });
    })
</script>
</body>
</html>



<%--

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <title>医师健康管理工作平台</title>
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/font-awesome/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/js/layer/skin/layer.css" />
  <link rel="stylesheet" type="text/css" href="${content.contextPath}/resources/css/page.css" />
</head>

<body>
	<input type="text"  id="Services" style="width: 50%;" name="services" value='$!{services}' hidden="true">
	<input type="text"  id="ServiceId" style="width: 50%;" name="serviceId" value='$!{serviceId}' hidden="true">
	<input type="text"  id="OrderId" style="width: 50%;" name="orderId" value='$!{orderId}' hidden="true">
	<input type="text"  id="ServiceClassId" style="width: 50%;" name="serviceClassId" value='$!{serviceClassId}' hidden="true">
	<input type="text"  id="OrderStatus" style="width: 50%;" name="orderStatus" value='$!{orderStatus}' hidden="true">
	<input type="text"  id="ContextPath" style="width: 50%;" name="contextPath" value='${content.contextPath}' hidden="true">
	<input type="text"  style="width: 50%;" name="custId" value='$!custVO.sysUser.pkId' hidden="true">
<div class="container sub-page">
  <div class="sub-page-title clearfix">
    <div class="col-xs-10">
      <ol class="breadcrumb">
        <li><a href="${content.contextPath}/bioOrder/page">服务</a></li>
        <li class="active"><!-- $!{product.productName}- -->跟踪随访（<span class="used">$!{useCount}</span>/$!{serviceCount}）</li>
      </ol>
    </div>
    <div class="col-xs-2 pull-right addBtn">
    	#if($orderStatus==2 || $orderStatus==4||("$!{currentUserOrderFlag}"!="true"&&"$!{orderAssistantRecordPkId}"==""))
    		<!-- <a href="javascript:"  title="新建随访任务">+ 新建</a> -->
    	#else
    		<a href="${content.contextPath}/followup/newOrderItem?orderId=$!{orderId}&serviceClassId=$!{serviceClassId}&orderStatus=$!{orderStatus}" class="layerBtn" title="新建随访任务">+ 新建</a>
    	#end
    </div>
  </div>
  
   <div class="sub-page-cont">
  	<div class="buyer">
  		<ul class="clearfix">
  			<li><h3><a class="btn1 user-name" href="${content.contextPath}/sysUser/memberInfo?id=$!{custVO.sysUser.pkId}">$!{custVO.sysUser.name}</a></h3></li>
  			<li>性别：$!{custVO.sexStr}</li>
  			<li>民族：$!{custVO.nationName}</li>
  			<li>年龄：$!{custVO.sysUser.age}</li>
  			<li>电话：$!{custVO.sysUser.mobile}</li>
  			<li>出生日期：$!{custVO.birthdayStr}</li>
      	</ul>
      </div>
    </div>
    
  <div class="sub-page-cont">
    <div class="alert alert-info">
      <h4 style="margin: 0;">统计：<small style="font-weight: normal;">当前共有 $!{orderItemCount} 个随访计划，共 $!{serviceCount} 次随访，已完成 <span class="used" id="used">$!{useCount}</span> 次，未完成<span id="rest">$!{unuseCount}</span>  次。</small></h4>
    </div>
    <input id="orderId" value="$!{orderId}" name="orderId" hidden="true"/>
    <input id="serviceClassId" value="$!{serviceClassId}" name="serviceClassId" hidden="true"/>
    <input id="OrderStatus" value="$!orderStatus" name="orderStatus" hidden="true"/>
    #if("$!{currentUserOrderFlag}"=="true"||"$!{orderAssistantRecordPkId}"!="")
    <button class="btn btn-primary" id="startup">启动</button>
    #end
    <div class="table margin-top-10">
      <table class="table table-center table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th></th>
            <th>启动时间</th>
            <th>随访计划</th>
            <th>所属机构</th>
            <th>类型</th>
            <th>模板</th>
            <th>随访间隔</th>
            <th>次数</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          #foreach($sysuser in $orderItems)
				<tr id="${sysuser.orderItemId}">
					 <td>
					 	#if(($!{sysuser.status}==0||!$!{sysuser.status}||$!{sysuser.status}=='')&&("$!{currentUserOrderFlag}"=="true"||"$!{orderAssistantRecordPkId}"!=""))
					 		<input type="checkbox"  value="${sysuser.orderItemId}" class="itemStart" name="orderItemId"/>
					 	#else
							√
						#end
					 </td>
					<td  class="singleFollowupUse" values="$!{sysuser.startTimeView}">
						#if($sysuser.status==0 || !$!{sysuser.status})
							<input class="Wdate dateStatic" type="text" value="$!{sysuser.startTimeView}" placeholder="--/--/--" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" name="beginTime"  #if("$!{currentUserOrderFlag}"!="true"&&"$!{orderAssistantRecordPkId}"=="") disabled="disabled" #end/>							
						#else
							$!{sysuser.startTimeView}
						#end
					</td>
					<td class="singleFollowupUse" values="$!{sysuser.serviceName}">$!{sysuser.serviceName}</td>
					<td>$!{sysuser.organizationName}</td>
					<td class="singleFollowupUse" values="$!{sysuser.type}">#if($sysuser.type==0||!$!{sysuser.type})
								定期
							#else
								临时
							#end
					</td>
					<td class="singleFollowupUse" values="$!{sysuser.questionnaireName}">$!{sysuser.questionnaireName}</td>
					<td class="singleFollowupUse" values="$!{sysuser.intervals}">$!{sysuser.intervals}天/次</td>
					<td class="singleFollowupUse" values="$!{sysuser.serviceNum}">$!{sysuser.serviceNum}次</td>
					<td>#if($sysuser.status==0 || !$!{sysuser.status})
								未启动								
							#elseif($sysuser.status==1)
								进行中
							#elseif($sysuser.status==2)
								已完成
							#elseif($sysuser.status==3)
								全部放弃
							#end
					</td>
					<td>#if($sysuser.status==0 || !$!{sysuser.status})
							#if($orderStatus==2 || $orderStatus==4||("$!{currentUserOrderFlag}"!="true"&&"$!{orderAssistantRecordPkId}"==""))
								<!-- <a href="javascript:"  title="新建随访任务">修改</a> -->
							#else
								<a href="${content.contextPath}/followup/listOrderItemById?orderItemId=$!{sysuser.orderItemId}&questionnaireId=$!{sysuser.questionnaireId}&orderStatus=$!{orderStatus}" class="layerBtn" title="随访详情">修改</a>
							#end
						#else
						<a href="${content.contextPath}/followup/listOrderItemDetail?orderItemId=$!{sysuser.orderItemId}&currentUserOrderFlag=$!{currentUserOrderFlag}&orderAssistantRecordPkId=$!{orderAssistantRecordPkId}" class="layerBtn" title="随访详情" 
						redDot="$!{sysuser.redDot}">查看</a>
						#end
					</td>
				</tr>
			#end
         </tbody>
      </table>
    </div>
    <div class="margin-top-10">
      <table class="table table-center table-bordered table-hover sortable" id="mytable">
      	 <thead>
          <tr class="singleFollowupDB">
            <th class="bg">随访日期</th>
            <th class="bg">随访计划</th>
            <th class="bg">类型</th>
            <th>模板</th>
            <th class="bg">随访状态</th>
            <th>操作</th>
          </tr>
        </thead>
         <tbody>
          #foreach($sysuser in $singleFollowups)
				<tr id="${sysuser.id}" class="singleFollowupDB">
					<td>$!{sysuser.followupTimeView}</td>
					<td>$!{sysuser.serviceName}</td>
					<td>#if($sysuser.type==0)
								定期
							#else
								临时
							#end
					</td> 
					<td>$!{sysuser.questionnaireName}</td>
					<td>#if($sysuser.status==1)
								已完成
							#elseif($sysuser.status==3 || $sysuser.status==2)
								已放弃
							#else
								未随访
							#end
					</td>
					<td>
						#if($sysuser.status==0 || !$!{sysuser.status})
							#if($orderStatus==2 || $orderStatus==4||("$!{currentUserOrderFlag}"!="true"&&"$!{orderAssistantRecordPkId}"==""))
								<!-- <a href="javascript:"  title="新建随访任务">建议放弃</a> -->
							#else
								<a href="javascript:void(0)" hrefLink="${sysuser.id}" title="推荐会员放弃本次随访" class="layerBtnDel">建议放弃</a>
							#end
						#elseif($sysuser.status==1)
							<a href="${content.contextPath}/repstUserQuestion/intoEdit?recordId=$!{sysuser.recordId}&questionnaireId=$!{sysuser.questionnaireId}&userId=$!{custVO.sysUser.pkId}"
							 title="$!{sysuser.questionnaireName}" class="layerBtn sfWatch" omaxmin="true" redDot="$!{sysuser.redDot}">查看</a>
						#elseif($sysuser.status==2)
							已放弃
						#elseif($sysuser.status==3)
							已放弃
						#end
					</td>
					
				</tr>
			#end
			
        </tbody>
      </table>
    </div>
  </div>
</div>

<script type="text/javascript" src="${content.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/My97-date/WdatePicker.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/main.js"></script>
<script type="text/javascript" src="${content.contextPath}/resources/js/sort.js"></script>
<script type="text/javascript">
  new TableSorter("mytable", 0, 1, 2, 4);
  $(function(){
    $("#mytable th").not(".pointer").css({"background":"none","cursor":"default"});
    
    var contextPath = $('#ContextPath').val();
    var orderId = $('#OrderId').val();
    var serviceClassId = $('#ServiceClassId').val();
    var orderStatus = $('#OrderStatus').val();
    var curFrameObj = $('#mainBox');
    
    //启动随访
    $("#startup").click(function(){
    	var ids = getCheckOrderItemIds()+"";
    	var dates =  getStartTimes()+"";
    	var orderStatus = $("#OrderStatus").val();
    	if(dates!='undefined' && dates.trim()!="" && ids!='undefined' && ids.trim()!=""){
    		$.ajax({
 	    		type: 'post',
 	    		url: contextPath+'/followup/startOrderItem',
 	    		data: {'ids': ids, 'dates': dates, 'orderStatus': orderStatus,orderAssistantRecordPkId : '$!{orderAssistantRecordPkId}'},
 	    		success: function(msg){
 	    			if("success"==msg.status){
 	    				layerMsg("启动成功!", function(){
 	    					orderStatus = msg.orderStatus;
 	 	    				var url = contextPath+'/followup/list?orderId='+orderId+'&serviceClassId='+serviceClassId+'&orderStatus='+orderStatus+'&custId='+$('[name="custId"]').val();
 	 	    				window.location.href = url;
 	 	    			});
 	    			}
 	    		},
 	    		error: function(msg){
 	    			layerMsg("启动失败!");
 	    		}
 	    	});
    	}else{
    		//alert("请添加启动时间或者启动项！");
    	}
	  });
    
    //添加小红点
    $(".layerBtn").each(function(){
    	var isRed = $(this).attr("redDot");
    	if(isRed==1){
    		$(this).addClass("done-btn");
    	}
    });
    
    //删除小红点
    $(".layerBtn").click(function(){
    	var isRed = $(this).attr("redDot");
    	if(isRed==1){
    		$(this).removeClass("done-btn");
    	}
    });
    
    //删除单词随访
    $(".layerBtnDel").click(function(){
    	var curObj = $(this);
    	var link = $(this).attr("hrefLink");
 		layerConfirm('确定放弃？', function(){
 			$.ajax({
 	    		type: 'post',
 	    		url: contextPath+'/followup/deleteSingleFollowup',
 	    		data: {id: link,orderAssistantRecordPkId:'$!{orderAssistantRecordPkId}'},
 	    		success: function(msg){
 	    			if("success"==msg.status){
 	    				layerMsg("操作成功!", function(){
 	    					curObj.closest('td').prev('td').text('已放弃');
 	 	    				curObj.closest('td').text('已放弃');
 	 	    				$('.used').text($('#used').text()-0+1);
 	 	    				$('#rest').text($('#rest').text()-0-1);
							var itemStatusTd = $('#' + msg.orderItem.orderItemId).find('td:eq(8)');
							var itemStatus = itemStatusTd.text();
							if (msg.orderItem.status == 2)  itemStatus = '已完成';
							else if(msg.orderItem.status == 3) itemStatus = '全部放弃';
							itemStatusTd.text(itemStatus);
 	 					});
 	    			}
 	    		},
 	    		error: function(msg){
 	    			layerMsg("建议消息发送失败!");
 	    		}
 	    	});
    	});
    })
    
    //选择随访计划
	/*  $(".itemStart").click(function(){
    	var flag = $(this).prop("checked");
    	if(flag){
    		var $tr = $(this).closest("tr");
    		var $tds = $tr.children("td");
    		var date = $tds.eq(1).children("input").val();
    		var name = $tds.eq(2).text();
    		var type = $tds.eq(4).text();
    		var template = $tds.eq(5).text();
    		var intervals = $tds.eq(6).attr("values");
    		var count = $tds.eq(7).attr("values");
    		if(date!=undefined && date.trim()!=""){
    			for(var i=0; i<count; i++){
        			var dateTd = getNewDay(date, ((i-0)*(intervals-0)));
        			var nameTd = name+"-"+(i-0+1);
        			 var newTr =  "<tr class='singleFollowupDB newSFTr'>"+
    			             "<td>"+dateTd+"</td>"+
    			             "<td>"+nameTd+"</td>"+
    			 			"<td>"+type+"</td>"+
    			             "<td>"+template+"</td>"+
    			             "<td>"+"</td>"+
    			             "<td>"+"</td>"+
    			           "</tr>";
    			     $(".singleFollowupDB").last().after(newTr);
        		}
    		}else{
    			layerMsg("请选择日期！");
				$(this).attr("checked", false);
    		}
    	}else{
    		var url = contextPath+'followup/list?orderId='+orderId+'&serviceClassId='+serviceClassId+'&orderStatus='+orderStatus;
    		console.log(url);
    		window.location.href = url;
    	}
    }) */
    var trsExist = $('#mytable tbody tr');//页面加载完成即存在的数据
    $('.table :checkbox').change(function(){
    	$('#mytable tbody').empty();
    	var arr = [];
    	var size = 0;
    	$(':checkbox:checked').each(function(i){
    		var tds = $(this).closest('td').nextAll('td');
    		var date = tds.eq(0).find('input').val().trim();
			var count = tds.eq(6).text().match(/\d+/)[0];
    		if(date == ''){
    			layerMsg("请选择日期！");
				$(this).attr("checked", false);
				return false;
    		}else if(count ==='' || count === '0'){
				layerMsg("随机计划次数不能为0！");
				$(this).attr("checked", false);
				return false;
			}else{
    			var plan = tds.eq(1).text().trim();
        		var type = tds.eq(3).text().trim();
        		var template = tds.eq(4).text().trim();
        		var interval = tds.eq(5).text().trim().match(/\d+/)[0]-0;
        		var time = tds.eq(6).text().trim().match(/\d+/)[0]-0;
        		for(var j=0; j<time; j++){
        			var dt =  new Date(date);
        			dt.setDate(date.split('-')[2]-0+(interval*j));
        			var month = dt.getMonth() + 1;  
        		    if (month < 10) month = "0" + month;  
        		    var dateStr = dt.getDate();  
        		    if (dateStr < 10) dateStr = "0" + dateStr;
        			var dts = dt.getFullYear()+'-'+month+'-'+dateStr;
        			var plans = plan+'-'+(j-0+1);
        			arr[size] = '<tr><td>'+dts+'</td><td>'+plans+'</td><td>'+type+'</td><td>'+template+'</td><td></td><td></td></tr>';
        			size++;
        		}
    		}
    	});
    	trsExist.each(function(i){
    		var tmp = '<tr>';
    		$(this).find('td').each(function(){
    			tmp += '<td>'+$(this).html().trim()+'</td>';
    		})
    		tmp += '</tr>';
    		arr[size+i] = tmp;
    	});
    	$('#mytable tbody').append(arr.sort());
    })
    
  })
  
 	 function getCheckOrderItemIds()
		{
			var ids="";
			$("td").each(function(){
			    if($(this).find("input[type=checkbox]:checked").val() != undefined)
			    {
			    	ids+=$(this).find("input[type=checkbox]:checked").val()+",";
			    }
			}); 
			if(ids.trim()=="")
			{
			  	var lay=layer.alert('请至少选择一行数据!', {
					shade: false // 不显示遮罩
				}, function(e){ 
					layer.close(lay); 
				}); 
			}
			return ids;
		}
  function getStartTimes()
	{
		var startTimes = "";
		$(".Wdate").each(function(){
		    if($(this).val() != undefined&&$(this).val() != "")
		    {
		    	startTimes += $(this).val()+",";
		    }
		}); 
		if(startTimes.trim()=="")
		{
		  	var lay=layer.alert('请至少选择一行数据!', {
				shade: false // 不显示遮罩
			}, function(e){ 
				layer.close(lay); 
			}); 
		}
		return startTimes;
	}
  
  	function getNewDay(dateTemp, days) {  
	    var dateTemp = dateTemp.split("-");  
	    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
	    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
	    var rDate = new Date(millSeconds);  
	    var year = rDate.getFullYear();  
	    var month = rDate.getMonth() + 1;  
	    if (month < 10) month = "0" + month;  
	    var date = rDate.getDate();  
	    if (date < 10) date = "0" + date;  
	    return (year + "-" + month + "-" + date);  
	}
  
	function reload()
	{
		var orderId = $("#orderId").val();
		var serviceClassId = $("#serviceClassId").val();
		alert("ccc");
		//window.location.href=$("#contextPath").val()+"/followup/list?orderId="+orderId+"&serviceClassId="+serviceClassId;
		parent.$("#mainBox").attr("src","http://localhost:8089/doctor/followup/list?orderId=1446720403467&serviceClassId=9")
	
	}
	

</script>
</body>
</html>
--%>
