package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;
import com.foundation.dao.modules.read.questionnaire.RepstUserQuestionDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstUserQuestionDao;
import com.foundation.service.questionnaire.service.IRepstUserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstUserQuestionServiceImpl
 * @Description: 用户答题的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstUserQuestionServiceImpl implements IRepstUserQuestionService{

    @Autowired(required=false)
    private RepstUserQuestionDao repstUserQuestionDao;

    @Autowired(required=false)
    private RepstUserQuestionDaoR repstUserQuestionDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstUserQuestion repstUserQuestion) throws Exception {
        long affectedRows = repstUserQuestionDao.save(repstUserQuestion);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstUserQuestion> repstUserQuestionList) throws Exception {
        repstUserQuestionDao.batchSave(repstUserQuestionList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstUserQuestion repstUserQuestion) throws Exception {
        repstUserQuestionDao.update(repstUserQuestion);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstUserQuestion queryById(String id) throws Exception {
        return repstUserQuestionDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstUserQuestion> queryPage(Map<String, Object> params, Page<RepstUserQuestion> page) throws Exception {
        List<RepstUserQuestion> list = repstUserQuestionDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstUserQuestionDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstUserQuestionDao.delete(id);
        return true;
    }

}
