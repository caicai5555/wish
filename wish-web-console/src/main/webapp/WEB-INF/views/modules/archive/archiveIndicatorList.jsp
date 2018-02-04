<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户档案指标记录</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container-fluid">
    <button class="btn btn-small btn-primary  icon-backward" type="button" onclick="history.go(-1)"> 返 回</button>
    <div class="well well-lg">
        <div class="row">
            <div class="span2">用户名：${user.name}</div>
            <div class="span3">证件号：${user.cardNo}</div>
            <div class="span3">手机号：${user.phone}</div>
        </div>
    </div>
    <div>
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#indicator" data-toggle="tab" data-id="indicator">指标项</a></li>
            <li><a href="#descIndicator" data-toggle="tab" data-id="descIndicator">描述性指标项</a></li>
            <li><a href="#concIndicator" data-toggle="tab" data-id="concIndicator">结论性指标项</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="indicator">
                <table id="t_indicator"></table>
                <div id="pager1"></div>
            </div>
            <div class="tab-pane fade in active" id="descIndicator">
                <table id="t_descIndicator"></table>
            </div>
            <div class="tab-pane fade" id="concIndicator">
                <table id="t_concIndicator"></table>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layer.js"></script>
<script>

    //指标项table
    $(document).ready(function () {

        $('#myTab li:eq(0) a').tab('show');

        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).attr("data-id");
            if (activeTab == "indicator") {
                //grid_indicator.init();
            }
            if (activeTab == "descIndicator") {
                initDescIndicator();
            }
            if (activeTab == "concIndicator") {
                initConcIndicator();
            }
        });

        var grid_indicator = $("#t_indicator").jqGrid({
            url: '${ctx}/archive/records?userId=${userId}&indicatorType=indicator',
            datatype: "json",
            /*	jsonReader: { // 自定义json数据格式
             root: "list", page: "pageNo", total: "totalPage",
             records: "count", subgrid: {root: "list"}
             },*/
            colNames: ['指标编号', '指标名称', '指标值', '更新日期', '数据来源', "数据事件","操作"],
            colModel: [
                {name: 'code', index: 'code', /* width: 55,*/ sortable: true, frozen: true},
                /*{name: 'name', index: 'name', *//* width: 55,*//* sortable: false, frozen: true},*/
                {
                    name: 'name', index: 'name'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    if(""==val ||null==val)
                    return "未存储";
                }
                },
                {name: 'value', index: 'value'/*, width: 300*/},
                {name: 'dateStr', index: 'dateStr', /*width: 360,*/ align: "center"},
                {name: 'source', index: 'source', /*width: 280,*/ align: "right"},
                {name: 'event', index: 'event', /*width: 280, */align: "right"},
                {
                    name: 'code', index: 'code'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    return '<a href="javascript:void(0)" onclick="openIndicatorPage('+'\'${userId}\''+',\''+val+'\')">更多</a>';
                }
                }
            ],
            width:'auto',
            height:'auto',
            rowNum: -1, // 显示行数，-1为显示全部
            rowList: [10, 20, 30],
            /* pager: '#pager1',*/
            //sortname: 'id',
            //sortorder: "asc",
            //viewrecords: true,
            rownumbers: true,	// 显示序号
            shrinkToFit: true, // 不按百分比自适应列宽
            //multiselect: true, // 显示多选复选框
            //multiboxonly: true, // 单击复选框时在多选
            footerrow: true, // 显示底部合计行
            //userDataOnFooter: true, // 使用json数据作为合计行数据
            altRows: true, // 斑马线样式，交替行altclass
            loadonce: true,//一次性加载
            beforeRequest: function (data) {
                //console.log(data);
            },
            loadComplete: function (data) {
                //console.log(data.page);
            },
            loadError: function (data) {
                //console.log(data);
            },
            gridComplete: function () {
                //var dataCount  = $(this).getCol('DataCount',false,'sum');
                //$(this).footerData("set",{name:"合计",
                //	DataCount:dataCount
                //});
                //$(".ui-jqgrid-sdiv").show();
                $(window).resize();
            }
            //caption:"JSON Example"
        });

       $(window).resize(function () {
            grid_indicator.jqGrid('setGridWidth', $(window).width()-50);
            grid_indicator.jqGrid('setGridHeight', $(window).height()/2);
        }).resize();
    })
    //描述性指标项
    var initDescIndicator = function () {
        return  $("#t_descIndicator").jqGrid({
            url: '${ctx}/archive/records?userId=${userId}&indicatorType=descIndicator',
            datatype: "json",
            /*	jsonReader: { // 自定义json数据格式
             root: "list", page: "pageNo", total: "totalPage",
             records: "count", subgrid: {root: "list"}
             },*/
            colNames: ['指标编号', '指标名称', '指标值', '更新日期', '数据来源', "数据事件","操作"],
            colModel: [
                {name: 'code', index: 'code', /* width: 55,*/ sortable: true, frozen: true},
                {
                    name: 'name', index: 'name'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    if(""==val ||null==val)
                        return "未存储";
                }
                },
                {name: 'value', index: 'value'/*, width: 300*/},
                {name: 'dateStr', index: 'dateStr', /*width: 360,*/ align: "center"},
                {name: 'source', index: 'source', /*width: 280,*/ align: "right"},
                {name: 'event', index: 'event', /*width: 280, */align: "right"},
                {
                    name: 'code', index: 'code'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    return '<a href="javascript:void(0)" onclick="openDescIndicatorPage('+'\'${userId}\''+',\''+val+'\')">更多</a>';
                }
                }
            ],
            width:'auto',
            height:$(window).height()/2,
            rowNum: -1, // 显示行数，-1为显示全部
            rowList: [10, 20, 30],
            /* pager: '#pager1',*/
            //sortname: 'id',
            //sortorder: "asc",
            //viewrecords: true,
            rownumbers: true,	// 显示序号
            shrinkToFit: true, // 不按百分比自适应列宽
            //multiselect: true, // 显示多选复选框
            //multiboxonly: true, // 单击复选框时在多选
            footerrow: true, // 显示底部合计行
            //userDataOnFooter: true, // 使用json数据作为合计行数据
            altRows: true, // 斑马线样式，交替行altclass
            loadonce: true,//一次性加载
            beforeRequest: function (data) {
                //console.log(data);
            },
            loadComplete: function (data) {
                //console.log(data.page);
            },
            loadError: function (data) {
                //console.log(data);
            },
            gridComplete: function () {
                //var dataCount  = $(this).getCol('DataCount',false,'sum');
                //$(this).footerData("set",{name:"合计",
                //	DataCount:dataCount
                //});
                //$(".ui-jqgrid-sdiv").show();
                $(window).resize();
            }
            //caption:"JSON Example"
        });
    }

    //结论性指标项
   var initConcIndicator= function () {
        return $("#t_concIndicator").jqGrid({
            url: '${ctx}/archive/records?userId=${userId}&indicatorType=concIndicator',
            datatype: "json",
            /*	jsonReader: { // 自定义json数据格式
             root: "list", page: "pageNo", total: "totalPage",
             records: "count", subgrid: {root: "list"}
             },*/
            colNames: ['指标编号', '指标名称', '指标值', '更新日期', '数据来源', "数据事件","操作"],
            colModel: [
                {name: 'code', index: 'code', /* width: 55,*/ sortable: true, frozen: true},
                {
                    name: 'name', index: 'name'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    if(""==val ||null==val)
                        return "未存储";
                }
                },
                {name: 'value', index: 'value'/*, width: 300*/},
                {name: 'dateStr', index: 'dateStr', /*width: 360,*/ align: "center"},
                {name: 'source', index: 'source', /*width: 280,*/ align: "right"},
                {name: 'event', index: 'event', /*width: 280, */align: "right"},
                {
                    name: 'code', index: 'code'/*, width: 500*/, formatter: function (val, obj, row, act) {
                    return '<a href="javascript:void(0)" onclick="openConcIndicatorPage('+'\'${userId}\''+',\''+val+'\')">更多</a>';
                }
                 }
            ],
            width:'auto',
            height:$(window).height()/2,
            rowNum: 10, // 显示行数，-1为显示全部
            rowList: [10, 20, 30],
            /* pager: '#pager1',*/
            //sortname: 'id',
            //sortorder: "asc",
            //viewrecords: true,
            rownumbers: true,	// 显示序号
            shrinkToFit: true, // 不按百分比自适应列宽
            //multiselect: true, // 显示多选复选框
            //multiboxonly: true, // 单击复选框时在多选
            footerrow: true, // 显示底部合计行
            //userDataOnFooter: true, // 使用json数据作为合计行数据
            altRows: true, // 斑马线样式，交替行altclass
            loadonce: true,//一次性加载
            beforeRequest: function (data) {
                //console.log(data);
            },
            loadComplete: function (data) {
                //console.log(data.page);
            },
            loadError: function (data) {
                //console.log(data);
            },
            gridComplete: function () {
                //var dataCount  = $(this).getCol('DataCount',false,'sum');
                //$(this).footerData("set",{name:"合计",
                //	DataCount:dataCount
                //});
                //$(".ui-jqgrid-sdiv").show();
                $(window).resize();
            }
            //caption:"JSON Example"
        });
    }


    function openIndicatorPage(userId,indicatorCode){
        var url = "${ctx}/archive/indicatorPage?userId="+userId+"&indicatorCode="+indicatorCode;
        var title = "用户指标项记录"
        layer.open({
            type: 2,
            title: title,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            shift: 2,
            content: url
        });
    }

    function openDescIndicatorPage(userId,indicatorCode){
        var url = "${ctx}/archive/descIndicatorPage?userId="+userId+"&indicatorCode="+indicatorCode;
        var title = "用户描述性指标项记录"
        layer.open({
            type: 2,
            title: title,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            shift: 2,
            content: url
        });
    }

    function openConcIndicatorPage(userId,indicatorCode){
        var url = "${ctx}/archive/concIndicatorPage?userId="+userId+"&indicatorCode="+indicatorCode;
        var title = "用户结论性指标项记录"
        layer.open({
            type: 2,
            title: title,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            shift: 2,
            content: url
        });
    }
</script>
</body>
</html>