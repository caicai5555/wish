package com.foundation.service.disease.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.Disease;
import com.foundation.dao.entity.disease.DiseaseRelations;
import com.foundation.dao.modules.read.disease.DiseaseDaoR;
import com.foundation.dao.modules.write.disease.DiseaseDao;
import com.foundation.service.disease.service.IDiseaseService;
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
public class DiseaseServiceImpl implements IDiseaseService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseServiceImpl.class);

    @Autowired
    private DiseaseDao diseaseDao;
    @Autowired
    private DiseaseDaoR diseaseDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Disease disease) throws Exception {
        try{
            if (StringUtils.isBlank(disease.getId())) {
                disease.setId(IdGen.uuid());
            }
            Long num = diseaseDao.save(disease);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[DiseaseServiceImpl.save]--------> error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Disease disease) throws Exception {
        try{
            diseaseDao.update(disease);
        }catch(Exception e){
            logger.error("[DiseaseServiceImpl.update]--------> error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Disease queryById(String id) throws Exception {
        Disease disease = null;
        try{
            disease = diseaseDaoR.queryById(id);
        }catch(Exception e){
            logger.error("[DiseaseServiceImpl.queryById]--------> error.", e);
        }
        return disease;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Disease> queryList(Map<String, Object> params) throws Exception {
        return diseaseDaoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Disease> queryPage(Map<String, Object> params, Page<Disease> pageBounds) throws Exception {
        try{
            Page<Disease> pagination = pageBounds;
            List<Disease> list = diseaseDaoR.queryPage(params, pageBounds);
            pagination.setList(list);
            return pagination;
        }catch(Exception e){
            logger.error("[DiseaseServiceImpl.queryPage]--------> error.", e);
        }
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Disease> queryNameExist(Map<String, Object> params) throws Exception {
        List<Disease> list = null;
        try{
            list = diseaseDaoR.queryNameExist(params);
        }catch(Exception e){
            logger.error("[DiseaseServiceImpl.queryNameExist]--------> error.", e);
        }
        return list;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DiseaseRelations queryIndicatorById(String id) throws Exception {
        return diseaseDaoR.queryIndicatorById(id);
    }
}
