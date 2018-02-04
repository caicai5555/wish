package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgr;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireCtgrBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireCtgrService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问卷分类的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireCtgrBizImpl implements IRepstQuestionnaireCtgrBiz {

    @Autowired
    private IRepstQuestionnaireCtgrService repstQuestionnaireCtgrService;

    @Override
    public boolean save(RepstQuestionnaireCtgr repstQuestionnaireCtgr) throws Exception {
        return repstQuestionnaireCtgrService.save(repstQuestionnaireCtgr);
    }

    @Override
    public boolean save(List<RepstQuestionnaireCtgr> repstQuestionnaireCtgrList) throws Exception {
        return repstQuestionnaireCtgrService.save(repstQuestionnaireCtgrList);
    }

    @Override
    public boolean update(RepstQuestionnaireCtgr repstQuestionnaireCtgr) throws Exception {
        return repstQuestionnaireCtgrService.update(repstQuestionnaireCtgr);
    }

    @Override
    public RepstQuestionnaireCtgr queryById(String id) throws Exception {
        return repstQuestionnaireCtgrService.queryById(id);
    }

    @Override
    public Page<RepstQuestionnaireCtgr> queryPage(Map<String, Object> params, Page<RepstQuestionnaireCtgr> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionnaireCtgrService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireCtgrService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionnaireCtgrService.deleteReal(id);
    }


}
