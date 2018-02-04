package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;
import com.foundation.service.questionnaire.biz.IRepstQuestionsCtgrBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionsCtgrService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问卷试题分类的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionsCtgrBizImpl implements IRepstQuestionsCtgrBiz {

    @Autowired
    private IRepstQuestionsCtgrService repstQuestionsCtgrService;

    @Override
    public boolean save(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception {
        return repstQuestionsCtgrService.save(repstQuestionsCtgr);
    }

    @Override
    public boolean save(List<RepstQuestionsCtgr> repstQuestionsCtgrList) throws Exception {
        return repstQuestionsCtgrService.save(repstQuestionsCtgrList);
    }

    @Override
    public boolean update(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception {
        return repstQuestionsCtgrService.update(repstQuestionsCtgr);
    }

    @Override
    public RepstQuestionsCtgr queryById(String id) throws Exception {
        return repstQuestionsCtgrService.queryById(id);
    }

    @Override
    public Page<RepstQuestionsCtgr> queryPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionsCtgrService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionsCtgrService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionsCtgrService.deleteReal(id);
    }

    @Override
    public RepstQuestionsCtgr queryDetailById(String id) throws Exception {
        return repstQuestionsCtgrService.queryDetailById(id);
    }

    @Override
    public Page<RepstQuestionsCtgr> queryDetailPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionsCtgrService.queryDetailPage(params, page);
    }


}
