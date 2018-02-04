package com.foundation.service.followup.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.Followup;
import com.foundation.service.followup.biz.IFollowupBiz;
import com.foundation.service.followup.service.IFollowupService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FollowupBizImpl
 * @Description: 随访诊断的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class FollowupBizImpl implements IFollowupBiz {

    @Autowired
    private IFollowupService followupService;

    @Override
    public boolean save(Followup followup) throws Exception {
        return followupService.save(followup);
    }

    @Override
    public boolean save(List<Followup> followupList) throws Exception {
        return followupService.save(followupList);
    }

    @Override
    public boolean update(Followup followup) throws Exception {
        return followupService.update(followup);
    }

    @Override
    public Followup queryById(String id) throws Exception {
        return followupService.queryById(id);
    }

    @Override
    public Page<Followup> queryPage(Map<String, Object> params, Page<Followup> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return followupService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return followupService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return followupService.deleteReal(id);
    }


}
