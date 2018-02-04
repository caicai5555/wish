package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstPageOperation;
import com.foundation.dao.modules.read.questionnaire.RepstPageOperationDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstPageOperationDao;
import com.foundation.service.questionnaire.service.IRepstPageOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstPageOperationServiceImpl
 * @Description: js页面验证的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstPageOperationServiceImpl implements IRepstPageOperationService{

    @Autowired(required=false)
    private RepstPageOperationDao repstPageOperationDao;

    @Autowired(required=false)
    private RepstPageOperationDaoR repstPageOperationDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstPageOperation repstPageOperation) throws Exception {
        long affectedRows = repstPageOperationDao.save(repstPageOperation);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstPageOperation> repstPageOperationList) throws Exception {
        repstPageOperationDao.batchSave(repstPageOperationList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstPageOperation repstPageOperation) throws Exception {
        repstPageOperationDao.update(repstPageOperation);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstPageOperation queryById(String id) throws Exception {
        return repstPageOperationDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstPageOperation> queryPage(Map<String, Object> params, Page<RepstPageOperation> page) throws Exception {
        List<RepstPageOperation> list = repstPageOperationDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstPageOperationDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstPageOperationDao.delete(id);
        return true;
    }

}
