package com.foundation.service.disease.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;
import com.foundation.dao.modules.read.disease.DiseaseAppraisalConclusionDaoR;
import com.foundation.dao.modules.write.disease.DiseaseAppraisalConclusionDao;
import com.foundation.service.disease.service.IDiseaseAppraisalConclusionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Service
public class DiseaseAppraisalConclusionServiceImpl implements IDiseaseAppraisalConclusionService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseAppraisalConclusionServiceImpl.class);

    @Autowired
    private DiseaseAppraisalConclusionDao dao;
    @Autowired
    private DiseaseAppraisalConclusionDaoR daoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception {
        try{
            if (StringUtils.isBlank(diseaseAppraisalConclusion.getId())) {
                diseaseAppraisalConclusion.setId(IdGen.uuid());
            }
            Long num = dao.save(diseaseAppraisalConclusion);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionServiceImpl.save]--------> error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception {
        try{
            dao.update(diseaseAppraisalConclusion);
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionServiceImpl.update]--------> error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DiseaseAppraisalConclusion queryById(String id) throws Exception {
        DiseaseAppraisalConclusion diseaseAppraisalConclusion = null;
        try{
            diseaseAppraisalConclusion = daoR.queryById(id);
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionServiceImpl.queryById]--------> error.", e);
        }
        return diseaseAppraisalConclusion;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseAppraisalConclusion> queryList(Map<String, Object> params) throws Exception {
        return daoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<DiseaseAppraisalConclusion> queryPage(Map<String, Object> params, Page<DiseaseAppraisalConclusion> pageBounds) throws Exception {
        try{
            Page<DiseaseAppraisalConclusion> pagination = pageBounds;
            List<DiseaseAppraisalConclusion> list = daoR.queryPage(params, pageBounds);
            pagination.setList(list);
            return pagination;
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionServiceImpl.queryPage]--------> error.", e);
        }
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseAppraisalConclusion> queryByDeseaseId(String diseaseId) throws Exception {
        return daoR.queryByDeseaseId(diseaseId);
    }


}
