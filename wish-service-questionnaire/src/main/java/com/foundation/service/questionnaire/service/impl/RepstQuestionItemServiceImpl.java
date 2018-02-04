package com.foundation.service.questionnaire.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionItem;
import com.foundation.dao.modules.read.questionnaire.RepstQuestionItemDaoR;
import com.foundation.dao.modules.write.questionnaire.RepstQuestionItemDao;
import com.foundation.service.questionnaire.service.IRepstQuestionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuestionItemServiceImpl
 * @Description: 试题项的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionItemServiceImpl implements IRepstQuestionItemService{

    @Autowired(required=false)
    private RepstQuestionItemDao repstQuestionItemDao;

    @Autowired(required=false)
    private RepstQuestionItemDaoR repstQuestionItemDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(RepstQuestionItem repstQuestionItem) throws Exception {
        long affectedRows = repstQuestionItemDao.save(repstQuestionItem);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<RepstQuestionItem> repstQuestionItemList) throws Exception {
        repstQuestionItemDao.batchSave(repstQuestionItemList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(RepstQuestionItem repstQuestionItem) throws Exception {
        repstQuestionItemDao.update(repstQuestionItem);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepstQuestionItem queryById(String id) throws Exception {
        return repstQuestionItemDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepstQuestionItem> queryPage(Map<String, Object> params, Page<RepstQuestionItem> page) throws Exception {
        List<RepstQuestionItem> list = repstQuestionItemDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionItemDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        repstQuestionItemDao.delete(id);
        return true;
    }

}
