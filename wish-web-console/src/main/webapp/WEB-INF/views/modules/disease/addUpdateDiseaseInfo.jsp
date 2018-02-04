<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/views/include/treeview.jsp" %>
    <title>新增疾病</title>

    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
        }
    </style>
</head>
<body>
<input type="hidden" value="${ctx}" name="contextPath" id="contextPath">
<div class="content">
    <form class="form-horizontal">
        <input type="hidden" id="diseaseInfoId" name="diseaseInfoId" value="${diseaseInfo.id}"/>
        <%--存储疾病信息大类--%>
        <input type="hidden" id="diseaseClassId" name="diseaseClassId" value="${diseaseInfo.pid}"/>

        <%--存储疾病科室Id--%>
        <input type="hidden" id="diseaseCategoryId" name="diseaseCategoryId"
                <c:if test="${null !=childCategory && ''!=childCategory}">
                value="${childCategory}"
                </c:if>
                <c:if test="${null==childCategory || ''==childCategory}">
                    value="${parentCategory}"
                </c:if>
        />
        <%--存储指标信息主键组，逗号分隔--%>
        <input type="hidden" id="indicatorIds" name="indicatorIds" value="${indicatorIds}"/>
        <%--存储评估结论主键组，逗号分隔--%>
        <input type="hidden" id="diseaseAppraisalConclusionIds" name="diseaseAppraisalConclusionIds"
               value="${diseaseAppraisalConclusionIds}"/>
        <%--存储一级分类--%>
        <input type="hidden" id="parentCategory" name="parentCategory" value="${parentCategory}"/>
        <%--存储二级分类--%>
        <input type="hidden" id="childCategory" name="childCategory" value="${childCategory}"/>

        <div class="control-group">
            <label class="control-label"><span style="color:red;">*</span>疾病类别:</label>
            <div class="controls">
                <select id="diseaseClass" name="diseaseClass" style="width: 150px;"></select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><span style="color:red;">*</span>疾病名称:</label>
            <div class="controls">
                <input type="text" id="name" name="name" value="${diseaseInfo.name}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">疾病别名:</label>
            <div class="controls">
                <input type="text" id="alias" name="alias" value="${diseaseInfo.alias}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">英文名称:</label>
            <div class="controls">
                <input type="text" id="englishName" name="englishName" value="${diseaseInfo.englishName}"
                       maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">简介:</label>
            <div class="controls">
                <textarea class="form-control" name="info" id="info" maxlength="250">${diseaseInfo.info}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">预防:</label>
            <div class="controls">
                <input type="text" id="hint" name="hint" value="${diseaseInfo.hint}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">就诊科室:</label>
            <div class="controls">
                <textarea class="form-control" name="medicalDepartment" id="medicalDepartment" maxlength="50">
                    ${diseaseInfo.medicalDepartment}
                </textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">传染性:</label>
            <div class="controls">
                <textarea class="form-control" name="infectiousness" id="infectiousness" maxlength="50">
                    ${diseaseInfo.infectiousness}
                </textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">常见发病部位:</label>
            <div class="controls">
                <input type="text" id="commonSite" name="commonSite" value="${diseaseInfo.commonSite}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">常见病因:</label>
            <div class="controls">
                <input type="text" id="commonCause" name="commonCause" value="${diseaseInfo.commonCause}"
                       maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">检查:</label>
            <div class="controls">
                <input type="text" id="inspect" name="inspect" value="${diseaseInfo.inspect}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">治疗:</label>
            <div class="controls">
                <input type="text" id="cure" name="cure" value="${diseaseInfo.cure}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">饮食治疗(保健):</label>
            <div class="controls">
                <input type="text" id="dietericTreatment" name="dietericTreatment"
                       value="${diseaseInfo.dietericTreatment}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">专家解读:</label>
            <div class="controls">
                <input type="text" id="expertInterpretation" name="expertInterpretation"
                       value="${diseaseInfo.expertInterpretation}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">鉴别诊断:</label>
            <div class="controls">
                <input type="text" id="antidiastole" name="antidiastole" value="${diseaseInfo.antidiastole}"
                       maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否医保范围:</label>
            <div class="controls">
                <select name="medicalInsurance" id="medicalInsurance" class="form-control" style="width: 150px;">
                    <option value="0" <c:if test="${diseaseInfo.medicalInsurance==0}">selected="selected"</c:if>>在
                    </option>
                    <option value="1" <c:if test="${diseaseInfo.medicalInsurance==1}">selected="selected"</c:if>>不在
                    </option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">参考医疗费用:</label>
            <div class="controls">
                <input type="text" id="treatmentCost" name="treatmentCost" value="${diseaseInfo.treatmentCost}"
                       maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">治疗率:</label>
            <div class="controls">
                <input type="text" id="treatmentRate" name="treatmentRate" value="${diseaseInfo.treatmentRate}"
                       maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">多发人群:</label>
            <div class="controls">
                <input type="text" id="multiplePopulation" name="multiplePopulation"
                       value="${diseaseInfo.multiplePopulation}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">并发症:</label>
            <div class="controls">
                <input type="text" id="neopathy" name="neopathy" value="${diseaseInfo.neopathy}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">排序:</label>
            <div class="controls">
                <input type="text" id="sort" name="sort" value="${diseaseInfo.sort}" maxlength="50">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否启用:</label>
            <div class="controls">
                <select name="activeFlag" id="activeFlag" class="form-control" style="width: 150px;">
                    <option value="0" <c:if test="${diseaseInfo.activeFlag==0}">selected="selected"</c:if>>启用</option>
                    <option value="1" <c:if test="${diseaseInfo.activeFlag==1}">selected="selected"</c:if>>禁用</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">备注:</label>
            <div class="controls">
                <textarea id="remark" name="remark" value="${diseaseInfo.remark}" maxlength="150"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><span style="color: red">*</span>科室一级分类:</label>
            <div class="controls">
                <select name="categoryParent" id="categoryParent" style="width: 150px;">
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">科室二级分类:</label>
            <div class="controls">
                <select name="categoryChild" id="categoryChild" style="width: 150px;">
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><span style="color: red">*</span>指标:</label>
            <div class="controls">
                <div id="ztree_indicator" class="ztree" style="margin-top: 0; width: 150px;"></div>
            </div>
            <input type="hidden" value="$diseaseInfo.templeId" name="templeId" id="templeId"/>
        </div>
        <div class="control-group">
            <label class="control-label"><span style="color: red">*</span>评估结论:</label>
            <div class="controls">
                <div id="ztree_conclusion" class="ztree" style="margin-top: 0; width: 150px;"></div>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><span style="color: red">*</span>症状:</label>
            <div class="controls">
                <select id="symptomCategory" class="form-control" style="width: 100px;">
                    <option value="">请选择</option>
                    <c:forEach items="${symptomCategorys}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
                <select id="symptom" class="form-control" style="width: 100px;">
                    <option value="">请选择</option>
                    <c:forEach items="${symptoms}" var="symptom">
                        <option value="${symptom.id}">${symptom.name}</option>
                    </c:forEach>
                </select>
                &nbsp;&nbsp;<a href="javascript:void(0)" onclick="addSymptom()">增加</a>
                <input type="hidden" name="symptomSelected" id="symptomSelected" value="${symptomRelIds}"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"></label>
            <div class="controls">
                <table border="1" id="symptomSelectedDisplay" width="100%">
                    <tr>
                        <td>分类</td>
                        <td>症状</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${symptomRels}" var="map">
                        <tr>
                            <td>${map.categoryName}</td>
                            <td>${map.symptomName}</td>
                            <td><a href="javascript:void(0)" onclick="deleteTr(this,'${map.id}','symptomSelected')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><span style="color: red">*</span>基因:</label>
            <div class="controls">
                <select id="geneLoci" class="form-control" style="width: 150px;">
                    <option value="">请选择</option>
                    <c:forEach items="${geneLocis}" var="geneLoci">
                        <option value="${geneLoci.id}">${geneLoci.name}</option>
                    </c:forEach>
                </select>
                &nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="addGeneLoci()">增加</a>
                <input type="hidden" name="geneLociSelected" id="geneLociSelected" value="${geneLociRelIds}"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"></label>
            <div class="controls">
                <table border="1" id="geneLociSelectedDisplay" width="100%">
                    <tr>
                        <td>基因位点</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${geneLociRels}" var="geneLociRel">
                        <tr>
                            <td>${geneLociRel.name}</td>
                            <td><a href="javascript:void(0)"
                                   onclick="deleteTr(this,'${geneLociRel.id}','geneLociSelected')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>


        <div class="control-group">
            <div class="controls">
                <input class="btn btn-primary" type="button" onclick="diseaseInfoSubmit()" value="保存">
                <input class="btn" type="button" onclick="closePage()" value="取消">
            </div>
        </div>
    </form>
</div>

<script src="${ctxStatic}/console/js/disease/diseaseInfoAddUpdate.js"></script>
</body>
</html>