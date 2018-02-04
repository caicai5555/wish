package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionsCtgrDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionsCtgrDao;
import com.foundation.service.questionnaire.service.IRepstQuestionsCtgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionsCtgrServiceImpl
 * @Description: 问卷试题分类的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionsCtgrServiceImpl implements IRepstQuestionsCtgrService{

    @Autowired(required=false)
    private RepstQuestionsCtgrDao repstQuestionsCtgrDao;

    @Autowired(required=false)
    private RepstQuestionsCtgrDaoR repstQuestionsCtgrDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception {
        long affectedRows = repstQuestionsCtgrDao.save(repstQuestionsCtgr);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionsCtgr> repstQuestionsCtgrList) throws Exception {
        repstQuestionsCtgrDao.batchSave(repstQuestionsCtgrList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception {
        repstQuestionsCtgrDao.update(repstQuestionsCtgr);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionsCtgr queryById(String id) throws Exception {
        return repstQuestionsCtgrDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionsCtgr> queryPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception {
        List<RepstQuestionsCtgr> list = repstQuestionsCtgrDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionsCtgrDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionsCtgrDao.delete(id);
        return true;
    }

    @Override
    public RepstQuestionsCtgr queryDetailById(String id) throws Exception {
        return repstQuestionsCtgrDaoR.queryDetailById(id);
    }

    @Override
    public Page<RepstQuestionsCtgr> queryDetailPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception {
        List<RepstQuestionsCtgr> list = repstQuestionsCtgrDaoR.queryDetailPage(params, page);
        page.setList(list);
        return page;
    }

}
