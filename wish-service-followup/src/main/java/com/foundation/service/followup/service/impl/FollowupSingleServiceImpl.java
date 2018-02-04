package com.foundation.service.followup.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.FollowupSingle;
import com.foundation.dao.modules.read.followup.FollowupSingleDaoR;
import com.foundation.dao.modules.write.followup.FollowupSingleDao;
import com.foundation.service.followup.service.IFollowupSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FollowupSingleServiceImpl
 * @Description: 单次随访的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class FollowupSingleServiceImpl implements IFollowupSingleService{

    @Autowired(required=false)
    private FollowupSingleDao followupSingleDao;

    @Autowired(required=false)
    private FollowupSingleDaoR followupSingleDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(FollowupSingle followupSingle) throws Exception {
        long affectedRows = followupSingleDao.save(followupSingle);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<FollowupSingle> followupSingleList) throws Exception {
        followupSingleDao.batchSave(followupSingleList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(FollowupSingle followupSingle) throws Exception {
        followupSingleDao.update(followupSingle);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowupSingle queryById(String id) throws Exception {
        return followupSingleDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FollowupSingle> queryPage(Map<String, Object> params, Page<FollowupSingle> page) throws Exception {
        List<FollowupSingle> list = followupSingleDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return followupSingleDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        followupSingleDao.delete(id);
        return true;
    }

}
