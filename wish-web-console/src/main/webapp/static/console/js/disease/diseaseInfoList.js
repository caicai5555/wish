function newDiseaseInfo() {
    var url = $("#contextPath").val() + "/diseaseInfo/newDiseaseInfoPage";
    layer.open({
        type: 2,
        title: '新建疾病信息',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['600px', '700px'],
        content: url
    });

}

function openUpdate(id) {
    var url = $("#contextPath").val() + "/diseaseInfo/updatePage?id=" + id;

    layer.open({
        type: 2,
        title: '修改',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['600px', '700px'],
        content: url
    });
}

function openDetailedFunction(id) {
    var url = $("#contextPath").val() + "/diseaseCategory/detailedFunction?id=" + id;
    layer.open({
        type: 2,
        title: '分类单详情',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['600px', '400px'],
        content: url
    });

}

function infoDetail(id) {
    var url = $("#contextPath").val() + "/diseaseInfo/infoDetail?id=" + id;

    layer.open({
        type: 2,
        title: '疾病详情',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['600px', '400px'],
        content: url
    });

}

//启用禁用
function updateActiveFlag(id,activeFlag) {
    if (activeFlag == 0) {

        var index = layer.confirm("您确定禁用吗？", {
            shade: false // 不显示遮罩
        }, function (e) {
            layer.close(index);

            jQuery.ajax({
                type: "POST",
                url: jQuery("#contextPath").val() + "/diseaseInfo/updateActiveFlag",
                data: {"id":id,"activeFlag":1},
                success: function (retVal) {

                    layer.alert('禁用成功', {
                        shade: false // 不显示遮罩
                    }, function (e) {
                        window.location.reload();

                    });
                },
                error:function(){
                    layer.msg("操作异常，请重试");
                }
            });
        });
    } else if (activeFlag == 1) {

        var index = layer.confirm("您确定启用吗？", {
            shade: false // 不显示遮罩
        }, function (e) {
            layer.close(index);

            jQuery.ajax({
                type: "POST",
                url: jQuery("#contextPath").val() + "/diseaseInfo/updateActiveFlag",
                data: {"id":id,"activeFlag":0},
                success: function (retVal) {

                    layer.alert('启用成功', {
                        shade: false // 不显示遮罩
                    }, function (e) {
                        window.location.reload();
                    });
                },
                error:function(){
                    layer.msg("操作异常，请重试");
                }
            });
        });
    } else {
        return false;
    }
}


/*************ztree-select*********/
var setting = {
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick
    }
};


function beforeClick(treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    if (!check) alert("只能疾病类型...");
    return check;
}

function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeIndex"),
        nodes = zTree.getSelectedNodes(),
        v = "",//存储多选的name
        vv = "";//存储多选的value
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].name + ",";
        vv += nodes[i].id + ",";
    }
    if (v.length > 0) {
        v = v.substring(0, v.length - 1);
        vv = vv.substring(0, vv.length - 1);
    }
    var diseasObj = $("#diseasClasses");
    diseasObj.attr("value", v);
    $("#pid").attr("value", vv);
}

function showDiseaseClasses() {
    var diseasObj = $("#diseasClasses");
    var diseasOffset = $("#diseasClasses").offset();
    $("#menuContent").css({
        left: diseasOffset.left + "px",
        top: diseasOffset.top + diseasObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
        hideMenu();
    }
}
/*********************ztree-end***********/


$(function () {
    //获取疾病类别树
    jQuery.ajax({
        type: "GET",
        url: jQuery("#contextPath").val() + "/diseaseInfo/diseaseClasses",
        data: {"type":0},
        success: function (retVal) {
            if (retVal != null && retVal.length > 0) {
                var zNotes = new Array();
                $.each(retVal, function (i, val) {
                    zNotes.push({
                        id: val.id,
                        name: val.name,
                        pId: val.pid
                    });
                });
                $.fn.zTree.init($("#treeIndex"), setting, zNotes)
            }
        }
    });


    //加载疾病类别（disease_categroy）,一级类别
    jQuery.ajax({
        type: 'POST',
        url: $("#contextPath").val() + "/diseaseCategory/categorys",
        data:{"isParent":0},
        success: function (data) {
            if (data.length > 0) {
                $("#categoryParent").change(function (i) {
                    $("#parentCategory").val($(this).val());//设置hidden值
                    $("#categoryChild").empty();//清空二级
                    loadCategoryChild();
                })
                $.each(data, function (i, j) {
                    if (i == 0) {
                        $("#categoryParent").append("<option value='' selected='true'>一级科室</option>");
                        $("#categoryChild").append("<option value='' selected='true'>二级科室</option>");
                    }
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
})

//加载二级疾病类目录
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
                })

                $.each(data, function (i, j) {
                    if (i == 0) {
                        $("#categoryChild").append("<option value=''>二级科室</option>");
                    }
                    if (j.id == $("#childCategory").val()) {
                        $("#categoryChild").append("<option selected='true' value='" + j.id + "'>" + j.name + "</option>");
                    }
                    else {
                        $("#categoryChild").append("<option   value='" + j.id + "'>" + j.name + "</option>");
                    }
                })
            }
            else {
                $("#categoryChild").append("<option value='' selected='true'>二级科室</option>");
            }
        }
    });
}


