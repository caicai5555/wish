
$(function () {
    /*
     * **获取疾病类别
     */
    jQuery.ajax({
        type : 'POST',
        data : "pid=" +1, //查一级菜单
        url : $("#contextPath").val() + "/diseaseInfo/diseaseClasses",
        success : function(data) {
            if (data.length > 0) {
                $.each(data, function(i, j) {
                    if (j.id == $("#diseaseClassId").val()) {
                        $("#diseaseClass").append(
                            "<option selected='true' value='" + j.id
                            + "'>" + j.name + "</option>");

                    } else {
                        $("#diseaseClass").append(
                            "<option   value='" + j.id + "'>" + j.name
                            + "</option>");
                    }
                })
            }
        }
    });

    /*
     * **获取疾病一二级科室
     */
    //加载疾病类别（disease_categroy）,一级类别
    jQuery.ajax({
        type: 'POST',
        url: $("#contextPath").val() + "/diseaseCategory/categorys",
        data:{"isParent":0},
        success: function (data) {
            if (data.length > 0) {
                $("#categoryParent").change(function (i) {
                    $("#parentCategory").val($(this).val());//设置hidden值
                    $("#diseaseCategoryId").val($(this).val());
                    $("#categoryChild").empty();//清空二级
                    loadCategoryChild();
                })
                $.each(data, function (i, j) {
                     if (j.id == $("#parentCategory").val()) {
                        $("#categoryParent").append("<option selected='true' value='" + j.id + "'>" + j.name + "</option>");
                    }
                    else {
                        $("#categoryParent").append("<option   value='" + j.id + "'>" + j.name + "</option>");
                    }
                })
            }
            if ($("#parentCategory").val() != "") {
                loadCategoryChild();
            }
        }
    });

    function loadCategoryChild() {
        var pro = $("#parentCategory").val();
        if (pro == "") {
            return;
        }
        jQuery.ajax({
            type: 'POST',
            url: $("#contextPath").val() + "/diseaseCategory/categorys",
            data:{"parentId":pro},
            success: function (data) {
                if (data.length > 0) {
                    $("#categoryChild").change(function (i) {
                        $("#childCategory").val($(this).val());
                        $("#diseaseCategoryId").val($(this).val());
                    })

                    $.each(data, function (i, j) {
                        if (j.id == $("#childCategory").val()) {
                            $("#categoryChild").append("<option selected='true' value='" + j.id + "'>" + j.name + "</option>");
                        }
                        else {
                            $("#categoryChild").append("<option   value='" + j.id + "'>" + j.name + "</option>");
                        }
                    })
                }
            }
        });
    };


    /*
     * **获取所有的指标
     */
    var indicatorIds = $("#indicatorIds").val();
    var data = "";

    jQuery.ajax({
        type: "GET",
        url: $("#contextPath").val() + "/indicatorInfo/indicatorList",
        data: data,
        success: function (retVal) {
            if (retVal != null && retVal.length > 0) {
                var zNodes = new Array();
                $.each(retVal, function (i, j) {
                    var checkedType = false;
                    if (indicatorIds.indexOf(j.id) >= 0) {
                        checkedType = true;
                    }
                    {
                        zNodes.push({
                            id: j.id,
                            pId: j.parentId,
                            name: j.name,
                            checked: checkedType,
                            open: checkedType

                        });
                    }
                });
                $.fn.zTree.init($("#ztree_indicator"), settingIndex, zNodes);
            }

//			if($("#_diseaseCategoryid").val()!="")
//			{
//				if($("#organinationid").val().trim()==j.pkId)
//				{
//					select=j.name
//				}
//				loaddep();
//			}
        }
    });


});

$(function () {

    var ids = $("#diseaseAppraisalConclusionIds").val();
    var data = "";
    /*
     * **获取症状(描述性指标)
     */
    jQuery.ajax({
        type: "GET",
        url: jQuery("#contextPath").val() + "/diseaseInfo/appraisalConclusionList",
        data: data,
        success: function (retVal) {
            if (retVal != null ) {
                var zNodes = new Array();
                $.each(retVal, function (i, j) {
                    var checkedType = false;
                    if (ids.indexOf(j.id) >= 0) {
                        checkedType = true;
                    }
                    zNodes.push({
                        id: j.id,
                        pId: j.parentId,
                        name: j.name,
                        checked: checkedType,
                        open: checkedType

                    });

                });
                $.fn.zTree.init($("#ztree_conclusion"), settingIndex2, zNodes);
            }
        }
    });


});

var settingIndex = {
//		check: {
//			enable: true,
//			chkStyle: "radio",
//			radioType: "all"
//		},

    check: {
        enable: true,
        chkStyle: "checkbox",
//			chkboxType: { "Y" : "p", "N" : "p"}
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {

        onCheck: onClickIndex
    }
};

var settingIndex2 = {
//		check: {
//			enable: true,
//			chkStyle: "radio",
//			radioType: "all"
//		},

    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {"Y": "ps", "N": "ps"}
//			chkboxType: { "Y" : "p", "N" : "p"}
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {

        onCheck: onClickIndex2
    }
};

function onClickIndex(e, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("ztree_indicator"), nodes = treeObj.getCheckedNodes(true), v = "";
    for (var i = 0; i < nodes.length; i++) {
        //获取选中子节点的值
        if (!nodes[i].isParent) {
            v += nodes[i].id + ",";
        }

    }
    $("#indicatorIds").val(v.substr(0, v.length - 1));
}
function onClickIndex2(e, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("ztree_conclusion"), nodes = treeObj.getCheckedNodes(true), v = "";
    for (var i = 0; i < nodes.length; i++) {
        //获取选中子节点的值
        if (!nodes[i].isParent) {
            v += nodes[i].id + ",";
        }
    }
    $("#diseaseAppraisalConclusionIds").val(v.substr(0, v.length - 1));
}


/**
 * 页面增加一条症状
 */
function addSymptom(){
    var categoryId = $("#symptomCategory").val();
    var symptomId = $("#symptom").val();
    if(categoryId!=undefined&&categoryId!=''&&symptomId!=undefined&&symptomId!=''){
        var symptomSelected = $("#symptomSelected").val();
        var selected = categoryId+"#"+symptomId;
        if(symptomSelected.indexOf(selected)<=0){
            var categoryText = $("#symptomCategory").find("option:selected").text();
            var symptomText = $("#symptom").find("option:selected").text();
            $("#symptomSelectedDisplay").append("<tr><td>"+categoryText+"</td><td>"+symptomText+"</td><td><a href=\"javascript:void(0)\" onclick=\"deleteTr(this,\'"+selected+"\','symptomSelected')\">删除</a></td></tr>");
            $("#symptomSelected").val(symptomSelected+","+selected);
        }
    }
}
/**
 * 页面增加一条基因
 */
function addGeneLoci(){
    var geneLociId = $("#geneLoci").val();
    if(geneLociId!=undefined&&geneLociId!=''){
        var geneLociSelected = $("#geneLociSelected").val();
        if(geneLociSelected.indexOf(geneLociId)<=0){
            var geneLociText = $("#geneLoci").find("option:selected").text();
            $("#geneLociSelectedDisplay").append("<tr><td>"+geneLociText+"</td><td><a href=\"javascript:void(0)\" onclick=\"deleteTr(this,\'"+geneLociId+"\','geneLociSelected')\">删除</a></td></tr>");
            $("#geneLociSelected").val(geneLociSelected+","+geneLociId);
        }
    }
}

function deleteTr(element,value,controlName){
    $(element).parent().parent().remove();
    var newVal = $("#"+controlName).val().replace(","+value,"");
    $("#"+controlName).val(newVal);
}

//提交
function diseaseInfoSubmit() {


    if ("" == jQuery("#dieaseClass").val()) {
        layer.confirm('请选择疾病分类！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }
    if (""==jQuery("#name").val().trim()) {
        layer.confirm('疾病名称不能为空！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }



    if ("" == jQuery("#diseaseCategoryId").val().trim()) {
        layer.confirm('请选择疾病科室！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }
    if ("" == jQuery("#indicatorIds").val().trim()) {
        layer.confirm('指标不能为空！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }
    if ("" == jQuery("#diseaseAppraisalConclusionIds").val().trim())
    {

        layer.confirm('请选择评估结论！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }

    if ("" != jQuery("#sort").val().trim()) {
        var sortReg = new RegExp("^[0-9]*$");
        var obj = document.getElementById("sort");
        if(!sortReg.test(obj.value)){
            layer.confirm('排序只能为数字！', {
                btn : [ '确定' ], // 按钮
                shade : false, // 不显示遮罩
            });
            return;
        }
    }

    var symptomSelectedDisplayLen = $('#symptomSelectedDisplay').find('tr').length;
    var geneLociSelectedDisplayLen = $('#geneLociSelectedDisplay').find('tr').length;
    if ("" == jQuery("#symptomSelected").val().trim()||symptomSelectedDisplayLen<=1) {
        layer.confirm('症状不能为空！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }
    if ("" == jQuery("#geneLociSelected").val().trim()||geneLociSelectedDisplayLen<=1) {
        layer.confirm('基因不能为空！', {
            btn : [ '确定' ], // 按钮
            shade : false, // 不显示遮罩
        });
        return;
    }

    var dataVal = {
            "id": $("#diseaseInfoId").val(),
            "type":1,
            "pid": $("#diseaseClass").val(),
            "name": $("#name").val(),
            "alias": $("#alias").val(),
            "englishName": $("#englishName").val(),
            "info": $("#info").val(),
            "hint": $("#hint").val(),
            "medicalDepartment": $("#medicalDepartment").val(),
            "infectiousness": $("#infectiousness").val(),
            "commonSite": $("#commonSite").val(),
            "commonCause": $("#commonCause").val(),
            "inspect": $("#inspect").val(),
            "cure": $("#cure").val(),
            "dietericTreatment": $("#dietericTreatment").val(),
            "expertInterpretation": $("#expertInterpretation").val(),
            "antidiastole": $("#antidiastole").val(),
            "medicalInsurance": $("#medicalInsurance").val(),
            "treatmentCost": $("#treatmentCost").val(),
            "treatmentRate": $("#treatmentRate").val(),
            "multiplePopulation": $("#multiplePopulation").val(),
            "neopathy": $("#neopathy").val(),
            "sort": $("#sort").val(),
            "activeFlag": $("#activeFlag").val(),
            "remark": $("#remark").val(),
            "diseaseCategoryId": $("#diseaseCategoryId").val(),
            "indicatorIds": $("#indicatorIds").val(),
            "diseaseAppraisalConclusionIds": $("#diseaseAppraisalConclusionIds").val(),
            "symptomSelectedIds": $("#symptomSelected").val().substring(1),
            "geneLociSelectedIds": $("#geneLociSelected").val().substring(1)
        };



    jQuery.ajax({
        type : "POST",
        contentType:'application/json;charset=UTF-8', //contentType很重要
        url : jQuery("#contextPath").val() + "/diseaseInfo/saveUpdate",
        data : JSON.stringify(dataVal),
        success : function(data) {
            var dataObj= JSON.parse(data);
            if (dataObj.state==200) {
                layer.alert('操作成功', {
                    shade : false
                    // 不显示遮罩
                }, function(e) {
                    window.parent.location.reload();
                });

            } else {
                layer.alert("提示！" + "名称已经存在！");
            }
        },
        error:function () {
            layer.alert("操作失败!");
        }
    });
}
function closePage() {
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index); // 再执行关闭
}










