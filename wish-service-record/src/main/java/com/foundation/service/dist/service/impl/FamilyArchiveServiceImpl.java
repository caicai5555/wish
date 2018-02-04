package com.foundation.service.dist.service.impl;

import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.archive.FamilyArchive;
import com.foundation.dao.modules.read.archive.FamilyArchiveDaoR;
import com.foundation.dao.modules.write.archive.FamilyArchiveDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.service.IFamilyArchiveService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: FamilyArchiveServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/20 15:01
 */
@Service
public class FamilyArchiveServiceImpl implements IFamilyArchiveService {
    @Autowired
    FamilyArchiveDao familyArchiveDao;
    @Autowired
    FamilyArchiveDaoR familyArchiveDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public FamilyArchive save(FamilyArchive familyArchive) throws Exception {
        if(familyArchive == null
                || StringUtils.isEmpty(familyArchive.getId())){
            throw new Exception("[FamilyArchiveServiceImpl.save]方法入参id为空");
        }
        familyArchiveDao.save(familyArchive);
        return familyArchive;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FamilyArchive queryById(String id) throws Exception {
        return familyArchiveDaoR.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("delFlag", Constants.DEL_FLAG_DELETE);
        return familyArchiveDao.updateByMap(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(FamilyArchive familyArchive) throws Exception {
        if(familyArchive== null || StringUtils.isEmpty(familyArchive.getId())){
            throw new Exception("[FamilyArchiveServiceImpl.updateById]方法入参为null或更新条件id为空");
        }
        familyArchiveDao.update(familyArchive);
    }
    /**
     * {@inheritDoc}
     */
	@Override
	public List<FamilyArchive> queryList(Map<String, Object> params) throws Exception {
		return familyArchiveDaoR.queryList(params);
	}
}
