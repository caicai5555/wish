package com.foundation.service.followup.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.Followup;
import com.foundation.dao.modules.read.followup.FollowupDaoR;
import com.foundation.dao.modules.write.followup.FollowupDao;
import com.foundation.service.followup.service.IFollowupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FollowupServiceImpl
 * @Description: 随访诊断的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class FollowupServiceImpl implements IFollowupService{

    @Autowired(required=false)
    private FollowupDao followupDao;

    @Autowired(required=false)
    private FollowupDaoR followupDaoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Followup followup) throws Exception {
        long affectedRows = followupDao.save(followup);
        return 1 == affectedRows ? true : false;
    }

    @Override
    public boolean save(List<Followup> followupList) throws Exception {
        followupDao.batchSave(followupList);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Followup followup) throws Exception {
        followupDao.update(followup);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Followup queryById(String id) throws Exception {
        return followupDaoR.queryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Followup> queryPage(Map<String, Object> params, Page<Followup> page) throws Exception {
        List<Followup> list = followupDaoR.queryPageList(params, page);
        page.setList(list);
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return followupDaoR.getCount(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteReal(String id) throws Exception {
        followupDao.delete(id);
        return true;
    }

}
