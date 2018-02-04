package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.Symptom;
import com.foundation.service.disease.biz.ISymptomBiz;
import com.foundation.service.disease.service.ISymptomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SymptomBizmpl implements ISymptomBiz {

    private final static Logger logger = LoggerFactory.getLogger(SymptomBizmpl.class);

    @Autowired
    private ISymptomService iSymptomService;

    @Override
    public boolean save(Symptom symptom) throws Exception {
        return iSymptomService.save(symptom);
    }

    @Override
    public void update(Symptom symptom) throws Exception {
        iSymptomService.update(symptom);
    }

    @Override
    public Symptom queryById(String id) throws Exception {
        return iSymptomService.queryById(id);
    }

    @Override
    public List<Symptom> queryList(Map<String, Object> params) throws Exception {
        return iSymptomService.queryList(params);
    }

    @Override
    public void delete(String id) throws Exception {
        iSymptomService.delete(id);
    }

    @Override
    public List<Symptom> queryByDiseaseId(String diseaseId) throws Exception {
        return iSymptomService.queryByDiseaseId(diseaseId);
    }


}
