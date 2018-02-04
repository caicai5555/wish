package com.foundation.service.disease.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.*;
import com.foundation.service.disease.biz.IDiseaseBiz;
import com.foundation.service.disease.biz.vo.DiseaseRelevance;
import com.foundation.service.disease.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Service
public class DiseaseBizImpl implements IDiseaseBiz {
    private final static Logger logger = LoggerFactory.getLogger(DiseaseBizImpl.class);

    @Autowired
    private IDiseaseService diseaseService;
    @Autowired
    private IDiseaseIndicatorRelService indicatorRelService;
/*    @Autowired
    private IDiseaseAppraisalConclusionRelService conclusionRelService;*/
    @Autowired
    private IDiseaseDescindicatorRelService descindicatorRelService;
    @Autowired
    private IDiseaseSymptomRelService symptomRelService;
    @Autowired
    private IDiseaseGenelociRelService genelociRelService;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Disease disease) throws Exception {
        return diseaseService.save(disease);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Disease disease) throws Exception {
        diseaseService.update(disease);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Disease queryById(String id) throws Exception {
        return diseaseService.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Disease> queryPage(Map<String, Object> params, Page<Disease> pageBounds) throws Exception {
        return diseaseService.queryPage(params,pageBounds);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Disease> queryList(Map<String, Object> params) throws Exception {
        return diseaseService.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRelevanceInfo(DiseaseRelevance diseaseVO) {
        try {
            //保存疾病信息
            String diseaseId = IdGen.uuid();
            diseaseVO.setId(diseaseId);
            Date date = new Date();
            diseaseVO.setCreateDate(date);
            diseaseVO.setUpdateDate(date);
            diseaseService.save(diseaseVO);

            //保存疾病指标关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getIndicatorIds())){
                //处理掉[]及空格
                String ids= diseaseVO.getIndicatorIds().replaceAll("[\\[\\] ]","");
                String[] indicatorIds=ids.split(",");
                for(String id : indicatorIds){
                    DiseaseIndicatorRel iBean = new DiseaseIndicatorRel();
                    iBean.setDiseaseId(diseaseId);
                    iBean.setIndicatorId(id);
                    indicatorRelService.save(iBean);
                }
            }

            //保存疾病评估结论关联信息(描述性指标)
            if(StringUtils.isNotEmpty(diseaseVO.getDescIndicatorIds())){
                //处理掉[]及空格
                String ids= diseaseVO.getDescIndicatorIds().replaceAll("[\\[\\] ]","");
                String[] conclusionIds=ids.split(",");
                for(String id : conclusionIds){
                    DiseaseDescindicatorRel cBean = new DiseaseDescindicatorRel();
                    cBean.setDiseaseId(diseaseId);
                    cBean.setDescindicatorId(id);
                    descindicatorRelService.save(cBean);
                }
            }
            //保存疾病症状关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getSymptomSelectedIds())){
                String[] symptoms = diseaseVO.getSymptomSelectedIds().split(",");
                for(String id : symptoms){
                    DiseaseSymptomRel rBean = new DiseaseSymptomRel();
                    rBean.setDiseaseId(diseaseId);
                    //再次分隔前端拼接的CategoryId#SymptomId
                    String[] array = id.split("#");
                    rBean.setCategoryId(array[0]);
                    rBean.setSymptomId(array[1]);
                    symptomRelService.save(rBean);
                }
            }
            //保存疾病基因关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getGeneLociSelectedIds())){
                String[] genelocIds=diseaseVO.getGeneLociSelectedIds().split(",");
                for(String id : genelocIds){
                    DiseaseGenelociRel gBean = new DiseaseGenelociRel();
                    gBean.setDiseaseId(diseaseId);
                    gBean.setGeneLociId(id);
                    genelociRelService.save(gBean);
                }
            }

        } catch (Exception e) {
            logger.error("[DiseaseBizImpl.saveRelevance]--------> error:", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRelevanceInfo(DiseaseRelevance diseaseVO) {
        try {
            //保存疾病信息
            Date date = new Date();
            diseaseVO.setUpdateDate(date);
            diseaseService.update(diseaseVO);

            String diseaseId = diseaseVO.getId();

            //删除疾病指标关联信息
            indicatorRelService.deleteByDiseaseId(diseaseVO.getId());
            //保存疾病指标关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getIndicatorIds())){
                String ids= diseaseVO.getIndicatorIds().replaceAll("[\\[\\] ]","");
                String[] indicatorIds=ids.split(",");
                for(String id : indicatorIds){
                    DiseaseIndicatorRel iBean = new DiseaseIndicatorRel();
                    iBean.setDiseaseId(diseaseId);
                    iBean.setIndicatorId(id);
                    indicatorRelService.save(iBean);
                }
            }

            //删除疾病评估结论关联信息
            descindicatorRelService.deleteByDiseaseId(diseaseVO.getId());
            //保存疾病评估结论关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getDescIndicatorIds())){
                String ids= diseaseVO.getDescIndicatorIds().replaceAll("[\\[\\] ]","");
                String[] conclusionIds=ids.split(",");
                for(String id : conclusionIds){
                    DiseaseDescindicatorRel cBean = new DiseaseDescindicatorRel();
                    cBean.setDiseaseId(diseaseId);
                    cBean.setDescindicatorId(id);
                    descindicatorRelService.save(cBean);
                }
            }
            //删除疾病症状关联信息
            symptomRelService.deleteByDiseaseId(diseaseVO.getId());
            //保存疾病症状关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getSymptomSelectedIds())){
                String[] symptoms = diseaseVO.getSymptomSelectedIds().split(",");
                for(String id : symptoms){
                    DiseaseSymptomRel rBean = new DiseaseSymptomRel();
                    rBean.setDiseaseId(diseaseId);
                    //再次分隔前端拼接的CategoryId#SymptomId
                    String[] array = id.split("#");
                    rBean.setCategoryId(array[0]);
                    rBean.setSymptomId(array[1]);
                    symptomRelService.save(rBean);
                }
            }

            //删除疾病基因关联信息
            genelociRelService.deleteByDiseaseId(diseaseVO.getId());
            //保存疾病基因关联信息
            if(StringUtils.isNotEmpty(diseaseVO.getGeneLociSelectedIds())){
                String[] genelocIds=diseaseVO.getGeneLociSelectedIds().split(",");
                for(String id : genelocIds){
                    DiseaseGenelociRel gBean = new DiseaseGenelociRel();
                    gBean.setDiseaseId(diseaseId);
                    gBean.setGeneLociId(id);
                    genelociRelService.save(gBean);
                }
            }

        } catch (Exception e) {
            logger.error("[DiseaseBizImpl.updateRelevance]--------> error:", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Disease> queryNameExist(Map<String, Object> params) throws Exception {
        return diseaseService.queryNameExist(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DiseaseRelations queryIndicatorById(String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            logger.error("[DiseaseBizImpl.queryIndicatorById]--------> 入参id为空");
            return null;
        }
        return diseaseService.queryIndicatorById(id);
    }
}
