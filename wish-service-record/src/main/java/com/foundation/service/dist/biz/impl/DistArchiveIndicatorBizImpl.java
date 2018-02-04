package com.foundation.service.dist.biz.impl;


import com.foundation.common.bean.Constants;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.biz.IDistArchiveIndicatorBiz;
import com.foundation.service.dist.service.impl.DistArchiveIndicatorServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexBizImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/13 13:34
 */
@Service
public class DistArchiveIndicatorBizImpl implements IDistArchiveIndicatorBiz {
    private Log logger = LogFactory.getLog(DistArchiveIndicatorBizImpl.class);

    @Autowired
    DistArchiveIndicatorServiceImpl distArchiveIndexService;

    @Override
    public DistArchiveIndicator save(DistArchiveIndicator distArchiveIndicator) throws Exception {
        if(distArchiveIndicator != null &&StringUtils.isEmpty(distArchiveIndicator.getId())){
            distArchiveIndicator.setId(IdGen.uuid());
        }
        try{
            distArchiveIndexService.save(distArchiveIndicator);
        }catch (Exception e){
            logger.error("[DistArchiveIndexBizImpl.save] error:"+e);
        }
        return distArchiveIndicator;
    }

    @Override
    public DistArchiveIndicator queryById(String id) throws Exception {
        return distArchiveIndexService.queryById(id);
    }

    @Override
    @Deprecated
    public DistArchiveIndicator queryByCode(String code) throws Exception {
        return distArchiveIndexService.queryByCode(code);
    }

    @Override
    public DistArchiveIndicator queryByCodeAndSex(String code, Integer sex) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        params.put("sex",sex);
        params.put("delFlag",Constants.DEL_FLAG_NORMAL);
        return distArchiveIndexService.queryObject(params);
    }

    @Override
    public List<DistArchiveIndicator> queryByTableName(String tableName) throws Exception {
        return distArchiveIndexService.queryByTableName(tableName);
    }

/*    @Override
    public List<DistArchiveIndex> queryByParentId(String parentId) throws Exception {
        return distArchiveIndexService.queryByParentId(parentId);
    }*/

    @Override
    public Integer deleteById(String id) throws Exception {
        return distArchiveIndexService.deleteById(id);
    }

    @Override
    public Integer deleteByCodeAndSex(String code,Integer sex) throws Exception{
        DistArchiveIndicator distArchiveIndicator = new DistArchiveIndicator();
        distArchiveIndicator.setDelFlag(Constants.DEL_FLAG_DELETE);
        distArchiveIndicator.setCode(code);
        distArchiveIndicator.setSex(sex);
        return distArchiveIndexService.updateByCode(distArchiveIndicator);
    }

    @Override
    public Integer updateByCode(DistArchiveIndicator distArchiveIndicator) throws Exception {
        Integer num =0;
        try {
            num = distArchiveIndexService.updateByCode(distArchiveIndicator);
        } catch (Exception e) {
            logger.error("[DistArchiveIndexBizImpl.updateByCode] error:"+e);
        }
        return num;
    }

    @Override
    public List<DistArchiveIndicator> queryAll() throws Exception {
        return distArchiveIndexService.queryAll();
    }
}
