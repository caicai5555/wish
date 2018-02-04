package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstPageOperation;
import com.foundation.dao.entity.questionnaire.RepstQuestions;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;
import com.foundation.service.questionnaire.biz.IRepstPageOperationBiz;
import com.foundation.service.questionnaire.service.IRepstPageOperationService;
import com.foundation.service.questionnaire.service.IRepstQuestionsService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: js页面验证的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstPageOperationBizImpl implements IRepstPageOperationBiz {

    @Autowired
    private IRepstPageOperationService repstPageOperationService;

    @Override
    public boolean save(RepstPageOperation repstPageOperation) throws Exception {
        return repstPageOperationService.save(repstPageOperation);
    }

    @Override
    public boolean save(List<RepstPageOperation> repstPageOperationList) throws Exception {
        return repstPageOperationService.save(repstPageOperationList);
    }

    @Override
    public boolean update(RepstPageOperation repstPageOperation) throws Exception {
        return repstPageOperationService.update(repstPageOperation);
    }

    @Override
    public RepstPageOperation queryById(String id) throws Exception {
        return repstPageOperationService.queryById(id);
    }

    @Override
    public Page<RepstPageOperation> queryPage(Map<String, Object> params, Page<RepstPageOperation> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstPageOperationService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstPageOperationService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstPageOperationService.deleteReal(id);
    }


}
