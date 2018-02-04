(function(w,j,r){
	function convertModel(resData) {

	}
	new Component();

	$.curCSS = $.css;
	var dateTrigger = '.date-picker';
	var datePicker = '.date-picker-input';
	$(dateTrigger).click(function () {
		$(datePicker).trigger('touchstart');
	});
	$.datetimepicker.setLocale('zh');
	var date = new Date();
	var today = date.toISOString().match(/\d{4}-\d{2}-\d{2}/)[0];
	$(datePicker).datetimepicker({
		dayOfWeekStart : 1,
		lang:'zh',
		disabledDates:[today],
		startDate:	today
	});
	$(datePicker).datetimepicker({value:today + ' 00:00',step:10});
	$('.viewport').click(function (event) {
		var target = $(event.target);
		if (target.hasClass('checkbox-list-item')) {
			if (target.hasClass('checked')) {
				target.removeClass('checked');
				target.find('[type=checkbox]')[0].checked = false;
			} else {
				target.addClass('checked');
				target.find('[type=checkbox]')[0].checked = true;
			}
		}
		if (target.hasClass('radio-list-item')) {
			target.addClass('checked');
			target.find(':radio').attr('checked', true);
			target.siblings().removeClass('checked');
			target.siblings().find(':radio').attr('checked', false);
		}
	});

	j(".submit").click(function (event) {
		var mobile  = $("#mobile").val();

		var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|17[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;

		if(!isMob.test(mobile)){
			alerttip("请输入正确的手机号码");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}
		var openId = $("#openId").val();
		var name = $("#name").val();
		if(CheckNull(name)){
			alerttip("请输入姓名");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}
		var aspId =  $("input[name='aspId']:checked").val();
		if(CheckNull(aspId)){
			alerttip("请选择检编号");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}
		//var unscrambleDate = $("#unscrambleDate").val() + $("#time").val();
		var unscrambleDate = $("#unscrambleDate").val();

		if(CheckNull(unscrambleDate)){
			alerttip("请选择期望解读日期");
			event.stopPropagation();
			event.preventDefault();
			return false;
		}
		var relationship = $("input[name='relationship']:checked").val();
		/*var url = form.attr('action');*/
		function showDialog(content){
			var dataModel = {
				content: content,
				onCancel: function () {
					$('.shade, .dialog').remove();
				},
				onConfirm: function (data, event) {
					$('.shade, .dialog').remove();
				},
				isShowConfirm: true
			};
			new Component(null, dataModel, '#dialogTemplate');
		}
		r.postLayer('/wx/explanation/submitExplanation/',{mobile:mobile,openId:openId,name:name,unscrambleDate:unscrambleDate,relationship:relationship,aspId:aspId},function(data){
			if(data.tip == "success"){
				showDialog('<h3 style="text-align: center;color:#3BAF3D">提交成功！</h3><p>客服将在1个工作日与您联系，请注意接听来电: 010-807212138/807711387</p>');
			}else{
				alerttip(data.tip);
			}
		});
		event.stopPropagation();
		event.preventDefault();
		return false;
	});
})(window,$,requestUtil);

// 得到选中的数据aspId
function returnAspId(){
	var aspIds = "";
	$("input[name='geneSet']:checkbox").each(function(){
		if ($(this).prop("checked")) {
			aspIds += $(this).val() + ",";
		}
	});
	return aspIds;
}
function CheckNull(param){
	if(typeof(param) == "undefined"){
		return true;
	}
   if (JTrim(param) != ""){
	   return false;
	}
	return true;
}
function JTrim(s)
{
	return s.replace(/(^\s*)|(\s*$)/g, "");
}



