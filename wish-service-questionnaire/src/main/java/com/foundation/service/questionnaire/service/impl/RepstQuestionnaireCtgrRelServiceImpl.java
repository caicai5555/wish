package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgrRel;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionnaireCtgrRelDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionnaireCtgrRelDao;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireCtgrRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionnaireCtgrRelServiceImpl
 * @Description: 问卷与试题分类关系的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireCtgrRelServiceImpl implements IRepstQuestionnaireCtgrRelService{

    @Autowired(required=false)
    private RepstQuestionnaireCtgrRelDao repstQuestionnaireCtgrRelDao;

    @Autowired(required=false)
    private RepstQuestionnaireCtgrRelDaoR repstQuestionnaireCtgrRelDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception {
        long affectedRows = repstQuestionnaireCtgrRelDao.save(repstQuestionnaireCtgrRel);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionnaireCtgrRel> repstQuestionnaireCtgrRelList) throws Exception {
        repstQuestionnaireCtgrRelDao.batchSave(repstQuestionnaireCtgrRelList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception {
        repstQuestionnaireCtgrRelDao.update(repstQuestionnaireCtgrRel);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionnaireCtgrRel queryById(String id) throws Exception {
        return repstQuestionnaireCtgrRelDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionnaireCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionnaireCtgrRel> page) throws Exception {
        List<RepstQuestionnaireCtgrRel> list = repstQuestionnaireCtgrRelDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireCtgrRelDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionnaireCtgrRelDao.delete(id);
        return true;
    }

}
