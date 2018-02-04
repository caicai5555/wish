<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户档案-指标项记录</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container-fluid">
<%--    <div class="well well-lg">
        <div class="row">
            <div class="span2">用户名：${user.name}</div>
            <div class="span3">证件号：${user.cardNo}</div>
            <div class="span3">手机号：${user.phone}</div>
        </div>
    </div>--%>
    <div>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="indicator">
                <table id="t_indicator"></table>
                <div id="gridpager"></div>
            </div>
        </div>
    </div>
</div>
<script>


    //指标项
    $(document).ready(function () {
        var grid_indicator = $("#t_indicator").jqGrid({
            url: '${ctx}/archive/indicatorList?userId=${userId}'+'&code=${indicatorCode}',
            datatype: "json",
                jsonReader: { // 自定义json数据格式
             root: "list", page: "pageNo", total: "last",
             records: "count", subgrid: {root:"list"}
             },
            colNames: ['指标编号', '指标名称', '指标值', '更新日期', '数据来源', "数据事件"],
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
                {name: 'event', index: 'event', /*width: 280, */align: "right"}
            ],
            width:'auto',
            height:'auto',
            rowNum: 10, // 显示行数，-1为显示全部
            rowList: [10, 20, 30],
            pager: '#gridpager',
            //sortname: 'id',
            //sortorder: "asc",
            //viewrecords: true,
            rownumbers: true,	// 显示序号
            shrinkToFit: true, // 不按百分比自适应列宽
            //multiselect: true, // 显示多选复选框
            //multiboxonly: true, // 单击复选框时在多选
            //ooterrow: true, // 显示底部合计行
            //userDataOnFooter: true, // 使用json数据作为合计行数据
            altRows: true, // 斑马线样式，交替行altclass
            //loadonce: true,//一次性加载
            emptyrecords: "无数据",
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
               // $(window).resize();
            }
            //caption:"JSON Example"
        });

        $(window).resize(function () {
            grid_indicator.jqGrid('setGridWidth', $(window).width()-50);
            grid_indicator.jqGrid('setGridHeight', $(window).height() -100);
        }).resize();
    })
</script>
</body>
</html>