package com.foundation.service.followup.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.FollowupSingle;
import com.foundation.service.followup.biz.IFollowupSingleBiz;
import com.foundation.service.followup.service.IFollowupSingleService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FollowupSingleBizImpl
 * @Description: 单次随访的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class FollowupSingleBizImpl implements IFollowupSingleBiz {

    @Autowired
    private IFollowupSingleService followupSingleService;

    @Override
    public boolean save(FollowupSingle followupSingle) throws Exception {
        return followupSingleService.save(followupSingle);
    }

    @Override
    public boolean save(List<FollowupSingle> followupSingleList) throws Exception {
        return followupSingleService.save(followupSingleList);
    }

    @Override
    public boolean update(FollowupSingle followupSingle) throws Exception {
        return followupSingleService.update(followupSingle);
    }

    @Override
    public FollowupSingle queryById(String id) throws Exception {
        return followupSingleService.queryById(id);
    }

    @Override
    public Page<FollowupSingle> queryPage(Map<String, Object> params, Page<FollowupSingle> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return followupSingleService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return followupSingleService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return followupSingleService.deleteReal(id);
    }


}
