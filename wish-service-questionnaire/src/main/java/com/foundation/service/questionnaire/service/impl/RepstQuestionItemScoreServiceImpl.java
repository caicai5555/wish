package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionItemScore;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionItemScoreDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionItemScoreDao;
import com.foundation.service.questionnaire.service.IRepstQuestionItemScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionItemScoreServiceImpl
 * @Description: 问题项计算复杂分数的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionItemScoreServiceImpl implements IRepstQuestionItemScoreService{

    @Autowired(required=false)
    private RepstQuestionItemScoreDao repstQuestionItemScoreDao;

    @Autowired(required=false)
    private RepstQuestionItemScoreDaoR repstQuestionItemScoreDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionItemScore repstQuestionItemScore) throws Exception {
        long affectedRows = repstQuestionItemScoreDao.save(repstQuestionItemScore);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionItemScore> repstQuestionItemScoreList) throws Exception {
        repstQuestionItemScoreDao.batchSave(repstQuestionItemScoreList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionItemScore repstQuestionItemScore) throws Exception {
        repstQuestionItemScoreDao.update(repstQuestionItemScore);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionItemScore queryById(String id) throws Exception {
        return repstQuestionItemScoreDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionItemScore> queryPage(Map<String, Object> params, Page<RepstQuestionItemScore> page) throws Exception {
        List<RepstQuestionItemScore> list = repstQuestionItemScoreDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionItemScoreDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionItemScoreDao.delete(id);
        return true;
    }

}
