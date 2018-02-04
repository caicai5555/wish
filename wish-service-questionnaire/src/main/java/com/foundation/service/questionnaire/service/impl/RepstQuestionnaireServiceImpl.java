package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaire;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionnaireDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionnaireDao;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionnaireServiceImpl
 * @Description: 问卷的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireServiceImpl implements IRepstQuestionnaireService{

    @Autowired(required=false)
    private RepstQuestionnaireDao repstQuestionnaireDao;

    @Autowired(required=false)
    private RepstQuestionnaireDaoR repstQuestionnaireDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionnaire repstQuestionnaire) throws Exception {
        long affectedRows = repstQuestionnaireDao.save(repstQuestionnaire);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionnaire> repstQuestionnaireList) throws Exception {
        repstQuestionnaireDao.batchSave(repstQuestionnaireList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionnaire repstQuestionnaire) throws Exception {
        repstQuestionnaireDao.update(repstQuestionnaire);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionnaire queryById(String id) throws Exception {
        return repstQuestionnaireDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionnaire> queryPage(Map<String, Object> params, Page<RepstQuestionnaire> page) throws Exception {
        List<RepstQuestionnaire> list = repstQuestionnaireDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionnaireDao.delete(id);
        return true;
    }

    @Override
    public RepstQuestionnaire queryDetailById(String id) throws Exception {
        return repstQuestionnaireDaoR.queryDetailById(id);
    }

}
