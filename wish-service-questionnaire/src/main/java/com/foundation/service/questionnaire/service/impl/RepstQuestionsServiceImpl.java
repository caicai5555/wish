package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestions;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionsDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionsDao;
import com.foundation.service.questionnaire.service.IRepstQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionsServiceImpl
 * @Description: 试题的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionsServiceImpl implements IRepstQuestionsService{

    @Autowired(required=false)
    private RepstQuestionsDao repstQuestionsDao;

    @Autowired(required=false)
    private RepstQuestionsDaoR repstQuestionsDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestions repstQuestions) throws Exception {
        long affectedRows = repstQuestionsDao.save(repstQuestions);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestions> repstQuestionsList) throws Exception {
        repstQuestionsDao.batchSave(repstQuestionsList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestions repstQuestions) throws Exception {
        repstQuestionsDao.update(repstQuestions);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestions queryById(String id) throws Exception {
        return repstQuestionsDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestions> queryPage(Map<String, Object> params, Page<RepstQuestions> page) throws Exception {
        List<RepstQuestions> list = repstQuestionsDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionsDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionsDao.delete(id);
        return true;
    }

    @Override
    public RepstQuestions queryDetailById(String id) throws Exception {
        return repstQuestionsDaoR.queryDetailById(id);
    }

    @Override
    public Page<RepstQuestions> queryDetailPage(Map<String, Object> params, Page<RepstQuestions> page) throws Exception {
        List<RepstQuestions> list = repstQuestionsDaoR.queryDetailPage(params, page);
        page.setList(list);
        return page;
    }

}
