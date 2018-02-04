package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.Symptom;
import com.foundation.dao.modules.read.disease.SymptomDaoR;
import com.foundation.dao.modules.write.disease.SymptomDao;
import com.foundation.service.disease.service.ISymptomService;
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
public class SymptomServiceImpl implements ISymptomService {

    private final static Logger logger = LoggerFactory.getLogger(SymptomServiceImpl.class);

    @Autowired
    private SymptomDao symptomDao;
    @Autowired
    private SymptomDaoR symptomDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Symptom symptom) throws Exception {
        try{
            if (StringUtils.isBlank(symptom.getId())) {
                symptom.setId(IdGen.uuid());
            }
            Long num = symptomDao.save(symptom);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[SymptomServiceImpl.save]--------> error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Symptom symptom) throws Exception {
        try{
            symptomDao.update(symptom);
        }catch(Exception e){
            logger.error("[SymptomServiceImpl.update]--------> error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Symptom queryById(String id) throws Exception {
        Symptom symptom = null;
        try{
            symptom = symptomDaoR.queryById(id);
        }catch(Exception e){
            logger.error("[SymptomServiceImpl.queryById]--------> error.", e);
        }
        return symptom;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Symptom> queryList(Map<String, Object> params) throws Exception {
        return symptomDaoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) throws Exception {
        symptomDao.delete(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Symptom> queryByDiseaseId(String diseaseId) throws Exception {
        return symptomDaoR.queryByDiseaseId(diseaseId);
    }


}
