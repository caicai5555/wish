package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;
import com.foundation.service.questionnaire.biz.IRepstUserQuestionBiz;
import com.foundation.service.questionnaire.service.IRepstUserQuestionService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 用户答题的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstUserQuestionBizImpl implements IRepstUserQuestionBiz {

    @Autowired
    private IRepstUserQuestionService repstUserQuestionService;

    @Override
    public boolean save(RepstUserQuestion repstUserQuestion) throws Exception {
        return repstUserQuestionService.save(repstUserQuestion);
    }

    @Override
    public boolean save(List<RepstUserQuestion> repstUserQuestionList) throws Exception {
        return repstUserQuestionService.save(repstUserQuestionList);
    }

    @Override
    public boolean update(RepstUserQuestion repstUserQuestion) throws Exception {
        return repstUserQuestionService.update(repstUserQuestion);
    }

    @Override
    public RepstUserQuestion queryById(String id) throws Exception {
        return repstUserQuestionService.queryById(id);
    }

    @Override
    public Page<RepstUserQuestion> queryPage(Map<String, Object> params, Page<RepstUserQuestion> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstUserQuestionService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstUserQuestionService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstUserQuestionService.deleteReal(id);
    }


}
