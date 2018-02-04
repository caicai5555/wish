package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.dao.modules.read.disease.DiseaseCategoryDaoR;
import com.foundation.dao.modules.write.disease.DiseaseCategoryDao;
import com.foundation.service.disease.service.IDiseaseCategoryService;
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
public class DiseaseCategoryServiceImpl implements IDiseaseCategoryService {
    private final static Logger logger = LoggerFactory.getLogger(DiseaseCategoryServiceImpl.class);

    @Autowired
    private DiseaseCategoryDao diseaseCategoryDao;
    @Autowired
    private DiseaseCategoryDaoR diseaseCategoryDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseCategory diseaseCategory) throws Exception {
        try{
            if (StringUtils.isBlank(diseaseCategory.getId())) {
                diseaseCategory.setId(IdGen.uuid());
            }
            Long num = diseaseCategoryDao.save(diseaseCategory);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[DiseaseCategoryServiceImpl.save] error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DiseaseCategory diseaseCategory) throws Exception {
        try{
            diseaseCategoryDao.update(diseaseCategory);
        }catch(Exception e){
            logger.error("[DiseaseCategoryServiceImpl.update] error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DiseaseCategory queryById(String id) throws Exception {
        DiseaseCategory diseaseCategory = null;
        try{
            diseaseCategory = diseaseCategoryDaoR.queryById(id);
        }catch(Exception e){
            logger.error("[DiseaseCategoryServiceImpl.queryById] error.", e);
        }
        return diseaseCategory;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseCategory> queryList(Map<String, Object> params) throws Exception {
        return diseaseCategoryDaoR.queryList(params);
    }

}
