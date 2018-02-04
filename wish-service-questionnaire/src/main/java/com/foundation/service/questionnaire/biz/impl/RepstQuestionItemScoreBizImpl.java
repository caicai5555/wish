package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionItemScore;
import com.foundation.service.questionnaire.biz.IRepstQuestionItemScoreBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionItemScoreService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问题项计算复杂分数的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionItemScoreBizImpl implements IRepstQuestionItemScoreBiz {

    @Autowired
    private IRepstQuestionItemScoreService repstQuestionItemScoreService;

    @Override
    public boolean save(RepstQuestionItemScore repstQuestionItemScore) throws Exception {
        return repstQuestionItemScoreService.save(repstQuestionItemScore);
    }

    @Override
    public boolean save(List<RepstQuestionItemScore> repstQuestionItemScoreList) throws Exception {
        return repstQuestionItemScoreService.save(repstQuestionItemScoreList);
    }

    @Override
    public boolean update(RepstQuestionItemScore repstQuestionItemScore) throws Exception {
        return repstQuestionItemScoreService.update(repstQuestionItemScore);
    }

    @Override
    public RepstQuestionItemScore queryById(String id) throws Exception {
        return repstQuestionItemScoreService.queryById(id);
    }

    @Override
    public Page<RepstQuestionItemScore> queryPage(Map<String, Object> params, Page<RepstQuestionItemScore> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionItemScoreService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionItemScoreService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionItemScoreService.deleteReal(id);
    }


}
