package com.foundation.service.archive.biz.impl;

import com.foundation.mongo.entity.archive.Archive;
import com.foundation.service.archive.biz.IArchiveBiz;
import com.foundation.service.archive.service.impl.ArchiveService;
import com.google.common.collect.Maps;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author wangsen
 * @ClassName: ArchiveBizImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/25 1:48
 */
@Service
public class ArchiveBizImpl implements IArchiveBiz {
    private Log logger = LogFactory.getLog(ArchiveBizImpl.class);

    @Autowired
	ArchiveService archiveService;
    
    /**
     * 
    * {@inheritDoc}
     */
    @Override
    public Archive queryByUserId(String userId) throws Exception {
        try {
			Assert.notNull(userId);
			Map<String,Object> params = Maps.newHashMap();
			params.put("userId",userId);
			return archiveService.queryOne(params);
		} catch (Exception e) {
			logger.error("ArchiveBizImpl.queryByCertNum error",e);
		}
		return null;
    }
    
    /**
     * 
    * {@inheritDoc}
     */
	@Override
	public Archive queryByCertNum(String certNum) throws Exception {
		try {
			Assert.notNull(certNum);
			Map<String,Object> params = Maps.newHashMap();
			params.put("certNum", certNum);
			return archiveService.queryOne(params);
		} catch (Exception e) {
			logger.error("ArchiveBizImpl.queryByCertNum error",e);
		}
		return null;
	}
}
