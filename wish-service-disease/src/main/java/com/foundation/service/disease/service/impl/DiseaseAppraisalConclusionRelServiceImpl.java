package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;
import com.foundation.dao.modules.read.disease.DiseaseAppraisalConclusionRelDaoR;
import com.foundation.dao.modules.write.disease.DiseaseAppraisalConclusionRelDao;
import com.foundation.service.disease.service.IDiseaseAppraisalConclusionRelService;
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
public class DiseaseAppraisalConclusionRelServiceImpl implements IDiseaseAppraisalConclusionRelService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseAppraisalConclusionRelServiceImpl.class);

    @Autowired
    private DiseaseAppraisalConclusionRelDao dao;
    @Autowired
    private DiseaseAppraisalConclusionRelDaoR daoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseAppraisalConclusionRel bean) throws Exception{
        try{
            if (StringUtils.isBlank(bean.getId())) {
                bean.setId(IdGen.uuid());
            }
            Long num = dao.save(bean);
            if(num>0)return true;
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionRelServiceImpl.save]--------> error:", e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void batchSave(List<DiseaseAppraisalConclusionRel> beanList) throws Exception{
        try{
            dao.batchSave(beanList);
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionRelServiceImpl.batchSave]--------> error:", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByDiseaseId(String diseaseId) throws Exception{
        try{
            dao.deleteByDiseaseId(diseaseId);
        }catch(Exception e){
            logger.error("[DiseaseAppraisalConclusionRelServiceImpl.deleteByDiseaseId]--------> error:", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseAppraisalConclusionRel> queryList(Map<String, Object> params) throws Exception {
        return daoR.queryList(params);
    }
}
