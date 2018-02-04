package com.foundation.service.archive.service.impl;


import com.foundation.common.persistence.Page;
import com.foundation.mongo.dao.IMongoBaseDao;
import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.service.archive.service.IArchiveService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: ArchiveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/20 14:07
 */
@Service
public class ArchiveService implements IArchiveService {
    private Log logger = LogFactory.getLog(ArchiveService.class);

    @Autowired
    protected IMongoBaseDao mongobaseDao;

    @Override
    public void saveArvhice(Archive archive) throws Exception {
        Map<String,BaseRecordEntity> dynMap =  archive.getDynamicValue();
        //archive.setDynamicValue(null);//设置dynamicValue为空json不做空处理
        //Map updateParams = JSON.parseObject(JSON.toJSONString(archive), Map.class);
        Map updateParams = new HashMap<>();
        updateParams.putAll(dynMap);

        //基础参数
        updateParams.put("event", archive.getEvent());
        updateParams.put("source", archive.getSource());
        updateParams.put("dateStr", archive.getDateStr());
        updateParams.put("updateTime", archive.getUpdateTime());

/*
        for(String key :dynMap.keySet()){
            updateParams.put(key,dynMap.get(key));
        }
*/

        //设置更新条件
        Map whereParams = new HashMap();
        whereParams.put("certNum",archive.getCertNum());//根据证件编号更新
        whereParams.put("userId",archive.getUserId());//根据userId更新

        mongobaseDao.updateInsert(whereParams,updateParams, Archive.class);
    }

    @Override
    public Archive queryByUserId(String userId) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        return this.queryOne(params);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page<Archive> queryPageList(int currentPage, int pageSize ,Map params) throws Exception {
        return mongobaseDao.queryPage(currentPage, pageSize,params,Archive.class);
    }

	@Override
	public Archive queryOne(Map<String,Object> params) throws Exception {
		return mongobaseDao.queryOne(params, Archive.class);
	}

}
