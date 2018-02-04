package com.foundation.service.dist.biz.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.FamilyArchive;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.foundation.service.dist.biz.IFamilyArchiveBiz;
import com.foundation.service.dist.service.IFamilyArchiveService;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: FamilyArchiveBizImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/20 15:09
 */
@Service
public class FamilyArchiveBizImpl implements IFamilyArchiveBiz {
    private Log logger = LogFactory.getLog(FamilyArchiveBizImpl.class);
    @Autowired
    IFamilyArchiveService familyArchiveService;

    /**
     * {@inheritDoc}
    */
    @Override
    public FamilyArchive save(FamilyArchive familyArchive) throws Exception {
        if(familyArchive!=null && StringUtils.isEmpty(familyArchive.getId())){
            familyArchive.setId(IdGen.uuid());
        }
        try{
            familyArchiveService.save(familyArchive);
        }catch(Exception e){
            logger.error("[FamilyArchiveBizImpl.save] error:"+e);
        }
        return familyArchive;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FamilyArchive queryById(String id) throws Exception {
        return familyArchiveService.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {

        return familyArchiveService.deleteIndexById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(FamilyArchive familyArchive) throws Exception {
    }
    /**
     * {@inheritDoc}
     */
	@Override
	public List<FamilyArchive> queryList(Map<String, Object> params) throws Exception {
		return familyArchiveService.queryList(params);
	}
    /**
     * {@inheritDoc}
     */
	@Override
	public FamilyArchive queryByPregnancyArchiveId(String pregnancyArchiveId) throws Exception {
		Assert.notNull(pregnancyArchiveId);
		Map<String,Object> params = Maps.newHashMap();
		params.put("pregnancyArchiveId", pregnancyArchiveId);
		List<FamilyArchive> list = this.queryList(params);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
