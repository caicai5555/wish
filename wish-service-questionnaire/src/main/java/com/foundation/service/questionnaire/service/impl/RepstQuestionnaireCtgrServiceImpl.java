package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgr;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionnaireCtgrDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionnaireCtgrDao;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireCtgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionnaireCtgrServiceImpl
 * @Description: 问卷分类的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireCtgrServiceImpl implements IRepstQuestionnaireCtgrService{

    @Autowired(required=false)
    private RepstQuestionnaireCtgrDao repstQuestionnaireCtgrDao;

    @Autowired(required=false)
    private RepstQuestionnaireCtgrDaoR repstQuestionnaireCtgrDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionnaireCtgr repstQuestionnaireCtgr) throws Exception {
        long affectedRows = repstQuestionnaireCtgrDao.save(repstQuestionnaireCtgr);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionnaireCtgr> repstQuestionnaireCtgrList) throws Exception {
        repstQuestionnaireCtgrDao.batchSave(repstQuestionnaireCtgrList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionnaireCtgr repstQuestionnaireCtgr) throws Exception {
        repstQuestionnaireCtgrDao.update(repstQuestionnaireCtgr);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionnaireCtgr queryById(String id) throws Exception {
        return repstQuestionnaireCtgrDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionnaireCtgr> queryPage(Map<String, Object> params, Page<RepstQuestionnaireCtgr> page) throws Exception {
        List<RepstQuestionnaireCtgr> list = repstQuestionnaireCtgrDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireCtgrDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionnaireCtgrDao.delete(id);
        return true;
    }

}
