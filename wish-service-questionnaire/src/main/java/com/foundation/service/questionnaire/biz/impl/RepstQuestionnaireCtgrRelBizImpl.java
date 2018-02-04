package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgrRel;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireCtgrRelBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireCtgrRelService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问卷与试题分类关系的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireCtgrRelBizImpl implements IRepstQuestionnaireCtgrRelBiz {

    @Autowired
    private IRepstQuestionnaireCtgrRelService repstQuestionnaireCtgrRelService;

    @Override
    public boolean save(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception {
        return repstQuestionnaireCtgrRelService.save(repstQuestionnaireCtgrRel);
    }

    @Override
    public boolean save(List<RepstQuestionnaireCtgrRel> repstQuestionnaireCtgrRelList) throws Exception {
        return repstQuestionnaireCtgrRelService.save(repstQuestionnaireCtgrRelList);
    }

    @Override
    public boolean update(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception {
        return repstQuestionnaireCtgrRelService.update(repstQuestionnaireCtgrRel);
    }

    @Override
    public RepstQuestionnaireCtgrRel queryById(String id) throws Exception {
        return repstQuestionnaireCtgrRelService.queryById(id);
    }

    @Override
    public Page<RepstQuestionnaireCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionnaireCtgrRel> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionnaireCtgrRelService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireCtgrRelService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionnaireCtgrRelService.deleteReal(id);
    }


}
