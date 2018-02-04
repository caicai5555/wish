package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireRecord;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionnaireRecordDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionnaireRecordDao;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionnaireRecordServiceImpl
 * @Description: 问卷记录的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireRecordServiceImpl implements IRepstQuestionnaireRecordService{

    @Autowired(required=false)
    private RepstQuestionnaireRecordDao repstQuestionnaireRecordDao;

    @Autowired(required=false)
    private RepstQuestionnaireRecordDaoR repstQuestionnaireRecordDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception {
        long affectedRows = repstQuestionnaireRecordDao.save(repstQuestionnaireRecord);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionnaireRecord> repstQuestionnaireRecordList) throws Exception {
        repstQuestionnaireRecordDao.batchSave(repstQuestionnaireRecordList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception {
        repstQuestionnaireRecordDao.update(repstQuestionnaireRecord);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionnaireRecord queryById(String id) throws Exception {
        return repstQuestionnaireRecordDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionnaireRecord> queryPage(Map<String, Object> params, Page<RepstQuestionnaireRecord> page) throws Exception {
        List<RepstQuestionnaireRecord> list = repstQuestionnaireRecordDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireRecordDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionnaireRecordDao.delete(id);
        return true;
    }

    @Override
    public RepstQuestionnaireRecord queryDetailById(String id) throws Exception {
        return repstQuestionnaireRecordDaoR.queryDetailById(id);
    }

}
