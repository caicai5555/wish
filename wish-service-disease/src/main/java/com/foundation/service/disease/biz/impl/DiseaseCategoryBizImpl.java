package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.service.disease.biz.IDiseaseCategoryBiz;
import com.foundation.service.disease.service.IDiseaseCategoryService;
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
public class DiseaseCategoryBizImpl implements IDiseaseCategoryBiz {
    private final static Logger logger = LoggerFactory.getLogger(DiseaseCategoryBizImpl.class);

    @Autowired
    private IDiseaseCategoryService diseaseCategoryService;

    @Override
    public boolean save(DiseaseCategory diseaseCategory) throws Exception {
        return diseaseCategoryService.save(diseaseCategory);
    }

    @Override
    public void update(DiseaseCategory diseaseCategory) throws Exception {
        diseaseCategoryService.update(diseaseCategory);
    }

    @Override
    public DiseaseCategory queryById(String id) throws Exception {
        return diseaseCategoryService.queryById(id);
    }

    @Override
    public List<DiseaseCategory> queryList(Map<String, Object> params) throws Exception {
        return diseaseCategoryService.queryList(params);
    }

}
