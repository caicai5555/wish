package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionCtgrRel;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionCtgrRelDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionCtgrRelDao;
import com.foundation.service.questionnaire.service.IRepstQuestionCtgrRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionCtgrRelServiceImpl
 * @Description: 试题与试题分类关系的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionCtgrRelServiceImpl implements IRepstQuestionCtgrRelService{

    @Autowired(required=false)
    private RepstQuestionCtgrRelDao repstQuestionCtgrRelDao;

    @Autowired(required=false)
    private RepstQuestionCtgrRelDaoR repstQuestionCtgrRelDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception {
        long affectedRows = repstQuestionCtgrRelDao.save(repstQuestionCtgrRel);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionCtgrRel> repstQuestionCtgrRelList) throws Exception {
        repstQuestionCtgrRelDao.batchSave(repstQuestionCtgrRelList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception {
        repstQuestionCtgrRelDao.update(repstQuestionCtgrRel);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionCtgrRel queryById(String id) throws Exception {
        return repstQuestionCtgrRelDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionCtgrRel> page) throws Exception {
        List<RepstQuestionCtgrRel> list = repstQuestionCtgrRelDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionCtgrRelDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionCtgrRelDao.delete(id);
        return true;
    }

}
