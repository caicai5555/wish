package com.foundation.service.indicator.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;
import com.foundation.service.indicator.biz.IIndicatorBiz;
import com.foundation.service.indicator.service.IIndicatorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndicatorBizImpl implements IIndicatorBiz {
    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IIndicatorService indicatorService;

    @Override
    public boolean save(Indicator indicator) throws Exception {
        return indicatorService.save(indicator);
    }

    @Override
    public void update(Indicator indicator) throws Exception {
        indicatorService.update(indicator);
    }

    @Override
    public Indicator queryById(String id) throws Exception {
        return indicatorService.queryById(id);
    }

    @Override
    public Page<Indicator> queryPage(Map<String, Object> params, Page<Indicator> pageBounds) throws Exception {
        return indicatorService.queryPage(params,pageBounds);
    }

    @Override
    public List<Indicator> queryList(Map<String, Object> params) throws Exception {
        return indicatorService.queryList(params);
    }

    @Override
    public Integer getCount(Map<String, Object> params) throws Exception {
        return indicatorService.getCount(params);
    }

    @Override
    public Integer getCountByParams(Map<String, Object> params) throws Exception {
        return indicatorService.getCountByParams(params);
    }

    @Override
    public Page<IndicatorTreeDO> queryTree(Map<String, Object> params, Page<IndicatorTreeDO> basePage) throws Exception{

        //手动设置分页总条数
        //判断有无查询条件调用不同统计方法
        if(StringUtils.isNotEmpty((String)params.get("name"))){
            basePage.setCount(this.getCountByParams(params));
        } else {
            basePage.setCount(this.getCount(params));
        }
        //组装入参查询实体，mybatisMapper中通过pageNo,pageSize获取分页信息
        //计算每页起始位置
        params.put("pageNo",(basePage.getPageNo()-1)*basePage.getPageSize());
        //每页条数
        params.put("pageSize",basePage.getPageSize());
        basePage.setList(indicatorService.queryTree(params));

        return basePage;
    }
}
