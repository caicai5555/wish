package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.dao.modules.read.disease.GeneLociDaoR;
import com.foundation.dao.modules.write.disease.GeneLociDao;
import com.foundation.service.disease.service.IGeneLociService;
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
public class GeneLociServiceImpl implements IGeneLociService {

    private final static Logger logger = LoggerFactory.getLogger(GeneLociServiceImpl.class);

    @Autowired
    private GeneLociDao geneLociDao;
    @Autowired
    private GeneLociDaoR geneLociDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(GeneLoci geneLoci) throws Exception {
        try{
            if (StringUtils.isBlank(geneLoci.getId())) {
                geneLoci.setId(IdGen.uuid());
            }
            Long num = geneLociDao.save(geneLoci);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[GeneLociServiceImpl.save]--------> error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GeneLoci geneLoci) throws Exception {
        try{
            geneLociDao.update(geneLoci);
        }catch(Exception e){
            logger.error("[GeneLociServiceImpl.update]--------> error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GeneLoci queryById(String id) throws Exception {
        GeneLoci geneLoci = null;
        try{
            geneLoci = geneLociDaoR.queryById(id);
        }catch(Exception e){
            logger.error("[GeneLociServiceImpl.queryById]--------> error.", e);
        }
        return geneLoci;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<GeneLoci> queryList(Map<String, Object> params) throws Exception {
        return geneLociDaoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) throws Exception {
        geneLociDao.delete(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<GeneLoci> queryByDiseaseId(String diseaseId) throws Exception {
        return geneLociDaoR.queryByDiseaseId(diseaseId);
    }
}
