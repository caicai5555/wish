package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionCtgrRel;
import com.foundation.service.questionnaire.biz.IRepstQuestionCtgrRelBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionCtgrRelService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 试题与试题分类关系的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionCtgrRelBizImpl implements IRepstQuestionCtgrRelBiz {

    @Autowired
    private IRepstQuestionCtgrRelService repstQuestionCtgrRelService;

    @Override
    public boolean save(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception {
        return repstQuestionCtgrRelService.save(repstQuestionCtgrRel);
    }

    @Override
    public boolean save(List<RepstQuestionCtgrRel> repstQuestionCtgrRelList) throws Exception {
        return repstQuestionCtgrRelService.save(repstQuestionCtgrRelList);
    }

    @Override
    public boolean update(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception {
        return repstQuestionCtgrRelService.update(repstQuestionCtgrRel);
    }

    @Override
    public RepstQuestionCtgrRel queryById(String id) throws Exception {
        return repstQuestionCtgrRelService.queryById(id);
    }

    @Override
    public Page<RepstQuestionCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionCtgrRel> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionCtgrRelService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionCtgrRelService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionCtgrRelService.deleteReal(id);
    }


}
