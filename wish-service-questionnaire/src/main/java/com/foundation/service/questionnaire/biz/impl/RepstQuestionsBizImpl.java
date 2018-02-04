package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestions;
import com.foundation.service.questionnaire.biz.IRepstQuestionsBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionsService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 试题的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionsBizImpl implements IRepstQuestionsBiz {

    @Autowired
    private IRepstQuestionsService repstQuestionsService;

    @Override
    public boolean save(RepstQuestions repstQuestions) throws Exception {
        return repstQuestionsService.save(repstQuestions);
    }

    @Override
    public boolean save(List<RepstQuestions> repstQuestionsList) throws Exception {
        return repstQuestionsService.save(repstQuestionsList);
    }

    @Override
    public boolean update(RepstQuestions repstQuestions) throws Exception {
        return repstQuestionsService.update(repstQuestions);
    }

    @Override
    public RepstQuestions queryById(String id) throws Exception {
        return repstQuestionsService.queryById(id);
    }

    @Override
    public Page<RepstQuestions> queryPage(Map<String, Object> params, Page<RepstQuestions> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionsService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionsService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionsService.deleteReal(id);
    }

    @Override
    public RepstQuestions queryDetailById(String id) throws Exception {
        RepstQuestions repstQuestions = repstQuestionsService.queryDetailById(id);
        return repstQuestions;
    }

    @Override
    public Page<RepstQuestions> queryDetailPage(Map<String, Object> params, Page<RepstQuestions> page) throws Exception {
        return repstQuestionsService.queryDetailPage(params, page);
    }

}
