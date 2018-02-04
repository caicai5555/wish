package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.service.disease.biz.IGeneLociBiz;
import com.foundation.service.disease.service.IGeneLociService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorServiceImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
@Service
public class GeneLociBizImpl implements IGeneLociBiz {

    private final static Logger logger = LoggerFactory.getLogger(GeneLociBizImpl.class);

    @Autowired
    private IGeneLociService geneLociService;

    @Override
    public boolean save(GeneLoci geneLoci) throws Exception {
        return geneLociService.save(geneLoci);
    }

    @Override
    public void update(GeneLoci geneLoci) throws Exception {
        try{
            geneLociService.update(geneLoci);
        }catch(Exception e){
            logger.error("[GeneLociBizImpl.update]--------> error.", e);
        }
    }

    @Override
    public GeneLoci queryById(String id) throws Exception {
        return geneLociService.queryById(id);
    }

    @Override
    public List<GeneLoci> queryList(Map<String, Object> params) throws Exception {
        return geneLociService.queryList(params);
    }

    @Override
    public void delete(String id) throws Exception {
        geneLociService.delete(id);
    }

    @Override
    public List<GeneLoci> queryByDiseaseId(String diseaseId) throws Exception {
        return geneLociService.queryByDiseaseId(diseaseId);
    }


}
