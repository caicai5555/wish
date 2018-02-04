package com.foundation.console.web.disease;

import com.foundation.common.persistence.Page;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import com.foundation.dao.entity.disease.*;
import com.foundation.service.common.ErrorCode;
import com.foundation.service.common.RespondMsgUtil;
import com.foundation.service.common.SymptomCategory;
import com.foundation.service.disease.biz.*;
import com.foundation.service.disease.biz.vo.DiseaseRelevance;
import com.foundation.service.dist.biz.IDistArchiveDescindicatorBiz;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @version V1.0
 * @ClassName: DiseaseController
 * @Description: 疾病信息管理
 * @date 2016/12/1 17:33
 */
@Controller
@RequestMapping("/admin/diseaseInfo")
public class DiseaseController extends BaseController {
    private Log logger = LogFactory.getLog(DiseaseController.class);
    @Autowired
    IDiseaseBiz diseaseBiz;
    @Autowired
    ISymptomBiz symptomBiz;
    @Autowired
    IGeneLociBiz geneLociBiz;
/*    @Autowired
    IDiseaseAppraisalConclusionBiz diseaseAppraisalConclusionBiz;*/
    @Autowired
    IDistArchiveDescindicatorBiz distArchiveDescindicatorBiz;
    @Autowired
    IDiseaseCategoryBiz categoryBiz;
    @Autowired
    IDiseaseIndicatorRelBiz indicatorRelBiz;
/*    @Autowired
    IDiseaseinfoAppraisalConclusionRelBiz conclusionRelBiz;*/
    @Autowired
IDiseaseDescindicatorRelBiz descindicatorRelBiz;

    /**
     * @param request
     * @param response
     * @param model
     * @return
     * @Description 获取疾病信息列表
     */
    @RequestMapping("/diseaseInfoPage")
    public String diseaseInfoPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> params = new HashedMap();
        try {
            params = initQueryParams(request);
            params.put("type", 1);//设置查询条件，查询疾病信息类型为1的，即：疾病

            Page<Disease> page = diseaseBiz.queryPage(params, new Page<Disease>(request, response));

            model.addAttribute("page", page);
            model.addAttribute("params", params);
        } catch (Exception e) {
            logger.error("[DiseaseController.diseaseInfoPage]----> erro:", e);
        }
        return "modules/disease/diseaseInfoList";
    }

    /**
     * @param request
     * @return
     * @Description 从request中获取查询参数
     */
    private Map<String, Object> initQueryParams(HttpServletRequest request) {
        Map<String, Object> params = new HashedMap();
        //diseasClasses不参与查询，回显用
        if (StringUtils.isNotEmpty(request.getParameter("diseasClasses"))) {
            params.put("diseasClasses", request.getParameter("diseasClasses"));
        }

        if (StringUtils.isNotEmpty(request.getParameter("pid"))) {
            params.put("pid", request.getParameter("pid"));
        }

        if (StringUtils.isNotEmpty(request.getParameter("name"))) {
            params.put("name", request.getParameter("name"));
        }

        return params;
    }

    /**
     * @Description获取疾病信息分类
     * @return
     */
    @ResponseBody
    @RequestMapping("/diseaseClasses")
    public List<Disease> diseaseClasses(HttpServletRequest request) {
        Map<String, Object> params = new HashMap();
        List<Disease> diseases = null;
        try {
            params.put("type", request.getParameter("type"));//设置查询条件，0：疾病组
            params.put("pid", request.getParameter("pid"));
            diseases = diseaseBiz.queryList(params);
        } catch (Exception e) {
            logger.error("[DiseaseController.diseaseClasses]----> erro:", e);
        }
        return diseases;
    }

    /**
     * @Description启用/禁用
     * @param id
     * @param activeFlag
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateActiveFlag")
    public String updateActiveFlag(String id, String activeFlag) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(activeFlag)) {
            logger.error("[DiseaseController.updateActiveFlag]---->入参不正确：id=" + id + ",activeFlag="+activeFlag);
            return "error";
        }
        Disease disease = new Disease();
        disease.setId(id);
        disease.setActiveFlag(Integer.valueOf(activeFlag));

        try {
            diseaseBiz.update(disease);
        } catch (Exception e) {
            logger.error("[DiseaseController.updateActiveFlag]---->error,e:"+e);
        }

        return "success";
    }

    /**
     * @Description 跳转到新增页面
     * @return
     */
    @RequestMapping("/newDiseaseInfoPage")
    public String InfoPage(Model model) {
        try {
            //查询症状
            List<Symptom> symptoms =symptomBiz.queryList(new HashMap());

            //基因位点查询
            List<GeneLoci> geneLocis = geneLociBiz.queryList(new HashedMap());

            model.addAttribute("symptoms",symptoms);
            model.addAttribute("symptomCategorys", SymptomCategory.values());//症状分类暂时使用枚举，后期改成字典
            model.addAttribute("geneLocis",geneLocis);

        } catch (Exception e) {
            logger.error("[DiseaseController.InfoPage]---->error,e:"+e);
        }

        return "modules/disease/addUpdateDiseaseInfo";
    }

    /**
     * @Description 跳转到更新页面
     * @param id
     * @return
     */
    @RequestMapping("/updatePage")
    public String updatePage(String id, Model model){
        if (StringUtils.isEmpty(id) ) {
            logger.error("[DiseaseController.updatePage]---->入参不正确：id=" + id);
            return "error";
        }

        try {
            ////查询指标基本信息
            Disease disease = diseaseBiz.queryById(id);
            model.addAttribute("diseaseInfo",disease);

            ////查询疾病关联科室信息
            if(StringUtils.isNotEmpty(disease.getDiseaseCategoryId())){
                DiseaseCategory diseaseCategory = categoryBiz.queryById(disease.getDiseaseCategoryId());
                //如果父节点为0，则只设置一级科室
                if(null!= diseaseCategory&& "0".equals(diseaseCategory.getParentId())){
                    model.addAttribute("parentCategory",diseaseCategory.getId());
                }
                //如果父节点为0，设置一二级科室
                if(null!= diseaseCategory&& !"0".equals(diseaseCategory.getParentId())){
                    model.addAttribute("parentCategory",diseaseCategory.getParentId());
                    model.addAttribute("childCategory",diseaseCategory.getId());
                }
            }

            ////查询疾病关联指标
            List indicatorIds = indicatorRelBiz.getIndicatorIds(id);
            model.addAttribute("indicatorIds",indicatorIds);

            ////查询疾病关联结论
            /*List diseaseAppraisalConclusionIds =conclusionRelBiz.getConclusionIds(id);*/
            //查询疾病关联描述性指标-由原关联结论改为
            List diseaseDescindicatorIds =descindicatorRelBiz.getDescIndicatorIds(id);
            model.addAttribute("diseaseAppraisalConclusionIds",diseaseDescindicatorIds);
            ////查询疾病关联症状
            List<Symptom> symptomRelss =symptomBiz.queryByDiseaseId(id);
            List<Map<String,Object>> symptomRels = new ArrayList<>();
            StringBuffer ids = new StringBuffer();
            for(Symptom symptom : symptomRelss){
                Map<String,Object> map = new HashMap<>();
                map.put("categoryName", SymptomCategory.getById(Integer.valueOf(symptom.getCategoryId())).getName());
                map.put("symptomName", symptom.getName());
                map.put("id", symptom.getCategoryId()+"#"+symptom.getId());
                ids.append(",").append(symptom.getCategoryId()+"#"+symptom.getId());
                symptomRels.add(map);
            }
            model.addAttribute("symptomRels", symptomRels);
            model.addAttribute("symptomRelIds", ids.toString());


            ////查询疾病关联基因
            List<GeneLoci> geneLociRels = geneLociBiz.queryByDiseaseId(id);
            model.addAttribute("geneLociRels",geneLociRels);
            model.addAttribute("geneLociRelIds",getGeneLociRelIds(geneLociRels));


            //查询症状
            List<Symptom> symptoms =symptomBiz.queryList(new HashMap());
            model.addAttribute("symptoms",symptoms);
            model.addAttribute("symptomCategorys", SymptomCategory.values());//症状分类暂时使用枚举，后期改成字典
            //基因位点查询
            List<GeneLoci> geneLocis = geneLociBiz.queryList(new HashedMap());
            model.addAttribute("geneLocis",geneLocis);

        } catch (Exception e) {
            logger.error("[DiseaseController.updatePage]---->error:" + e);
        }


        return "modules/disease/addUpdateDiseaseInfo";
    }

    /**
     * @Description 获取基因关联主键
     * @param geneLociRels
     * @return
     */
    private String getGeneLociRelIds(List<GeneLoci> geneLociRels) {
        StringBuffer ids = new StringBuffer();
        for(GeneLoci bean :geneLociRels){
            ids.append(",").append(bean.getId());
        }
        return ids.toString();
    }

    /**
     * @Description 跳转到详情页
     * @param id
     * @return
     */
    @RequestMapping("infoDetail")
    public String infoDetail(String id, Model model){
        if (StringUtils.isEmpty(id) ) {
            logger.error("[DiseaseController.infoDetail]---->入参不正确：id=" + id);
            return "error";
        }
        try {
            ////查询指标基本信息
            Disease disease = diseaseBiz.queryById(id);
            model.addAttribute("diseaseInfo",disease);

            ////查询疾病关联科室信息
            if(StringUtils.isNotEmpty(disease.getDiseaseCategoryId())){
                DiseaseCategory diseaseCategory = categoryBiz.queryById(disease.getDiseaseCategoryId());
                //如果父节点为0，则只设置一级科室
                if(null!= diseaseCategory&& "0".equals(diseaseCategory.getParentId())){
                    model.addAttribute("parentCategory",diseaseCategory.getId());
                }
                //如果父节点为0，设置一二级科室
                if(null!= diseaseCategory&& !"0".equals(diseaseCategory.getParentId())){
                    model.addAttribute("parentCategory",diseaseCategory.getParentId());
                    model.addAttribute("childCategory",diseaseCategory.getId());
                }
            }

            ////查询疾病关联指标
            List indicatorIds = indicatorRelBiz.getIndicatorIds(id);
            model.addAttribute("indicatorIds",indicatorIds);

            ////查询疾病关联结论
            //List diseaseAppraisalConclusionIds =conclusionRelBiz.getConclusionIds(id);
            //查询疾病关联描述性指标-由原关联结论改为
            List diseaseDescindicatorIds =descindicatorRelBiz.getDescIndicatorIds(id);

            model.addAttribute("diseaseAppraisalConclusionIds",diseaseDescindicatorIds);
            ////查询疾病关联症状
            List<Symptom> symptomRelss =symptomBiz.queryByDiseaseId(id);
            List<Map<String,Object>> symptomRels = new ArrayList<>();
            StringBuffer ids = new StringBuffer();
            for(Symptom symptom : symptomRelss){
                Map<String,Object> map = new HashMap<>();
                map.put("categoryName", SymptomCategory.getById(Integer.valueOf(symptom.getCategoryId())).getName());
                map.put("symptomName", symptom.getName());
                map.put("id", symptom.getCategoryId()+"#"+symptom.getId());
                ids.append(",").append(symptom.getCategoryId()+"#"+symptom.getId());
                symptomRels.add(map);
            }
            model.addAttribute("symptomRels", symptomRels);
            model.addAttribute("symptomRelIds", ids.toString());


            ////查询疾病关联基因
            List<GeneLoci> geneLociRels = geneLociBiz.queryByDiseaseId(id);
            model.addAttribute("geneLociRels",geneLociRels);
            model.addAttribute("geneLociRelIds",getGeneLociRelIds(geneLociRels));


            //查询症状
            List<Symptom> symptoms =symptomBiz.queryList(new HashMap());
            model.addAttribute("symptoms",symptoms);
            model.addAttribute("symptomCategorys", SymptomCategory.values());//症状分类暂时使用枚举，后期改成字典
            //基因位点查询
            List<GeneLoci> geneLocis = geneLociBiz.queryList(new HashedMap());
            model.addAttribute("geneLocis",geneLocis);

        } catch (Exception e) {
            logger.error("[DiseaseController.infoDetail]---->error,e:"+e);
        }
        return  "modules/disease/diseaseInfoDetail";
    }


    /**
     * @Description获取症状列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/appraisalConclusionList")
    public List<DistArchiveDescindicator> appraisalConclusion(){
        List<DistArchiveDescindicator> list = null;
        try {
            list = distArchiveDescindicatorBiz.queryList(new HashedMap());
        } catch (Exception e) {
            logger.error("[DiseaseController.appraisalConclusion]---->error,e:"+e);
        }
        return list;
    }

    /**
     * @Discription 执行保存或更新
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveUpdate")
    public String saveUpdate(@RequestBody DiseaseRelevance diseaseVO){

        try {

            Map<String,Object> params = new HashMap();


            //主键为空-新增，否则更新
            if(StringUtils.isEmpty(diseaseVO.getId())){
                //判断疾病组内名称是否重复
                //params.put("id",diseaseInfoVO.getId());
                params.put("name",diseaseVO.getName());
                List list = diseaseBiz.queryNameExist(params);
                if(null!=list&&list.size()>0){
                    return RespondMsgUtil.respondMsg(ErrorCode.DATA_EXISTED, null);
                }
                diseaseBiz.saveRelevanceInfo(diseaseVO);
            }else{
                //判断疾病组内名称是否重复
                params.put("id",diseaseVO.getId());
                params.put("name",diseaseVO.getName());
                List list = diseaseBiz.queryNameExist(params);
                if(null!=list&&list.size()>0){
                    return RespondMsgUtil.respondMsg(ErrorCode.DATA_EXISTED, null);
                }

                diseaseBiz.updateRelevanceInfo(diseaseVO);
            }
        } catch (Exception e) {
            logger.error("[DiseaseController.saveUpdate]---->error,e:"+e);
        }

        return RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
    }
}
