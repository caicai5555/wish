package com.foundation.service.archive.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.dao.IMongoBaseDao;
import com.foundation.mongo.entity.archive.FamilyArchiveMG;
import com.foundation.service.archive.service.IFamilyArchiveService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: FamilyArchiveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/28 14:36
 */
@Service
public class FamilyArchiveService implements IFamilyArchiveService {
    private Log logger = LogFactory.getLog(FamilyArchiveService.class);

    @Autowired
    protected IMongoBaseDao mongobaseDao;
    /**
     * {@inheritDoc}
    */
    @Override
    public void saveArvhice(FamilyArchiveMG archive) throws Exception {
        try{
            if(archive.validate()){
                //设置更新数据
                //Map updateParams = PropertyUtils.describe(archive);

                //-----------------------------如下手设会改成注解+bean内省
                Map updateParams = new HashMap();
                updateParams.putAll(archive.getFamilyConcIndicator());
                updateParams.put("fId",archive.getfId());
                updateParams.put("wifeCertNum",archive.getWifeCertNum());
                updateParams.put("wifeName",archive.getWifeName());
                updateParams.put("husbandCertNum",archive.getHusbandCertNum());
                updateParams.put("husbandName",archive.getHusbandCertNum());
                updateParams.put("source",archive.getSource());
                updateParams.put("event",archive.getEvent());
                updateParams.put("updateTime",archive.getUpdateTime());
                updateParams.put("dateStr", archive.getDateStr());
                updateParams.put("status",archive.getStatus());
                //设置更新条件
                Map whereParams = new HashMap();
                whereParams.put("fId",archive.getfId());//根据家庭编号

                mongobaseDao.updateInsert(whereParams,updateParams, FamilyArchiveMG.class);
            }
        }catch(Exception e){
            logger.error("[FamilyArchiveService.saveArvhice]保存家庭档案异常 error：" + e);
        }
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public FamilyArchiveMG queryByParams(Map<String, Object> queryParams) throws Exception {
        return mongobaseDao.queryOne(queryParams,FamilyArchiveMG.class);
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public Page queryPageList(int currentPage, int pageSize, Map<String, Object> params) throws Exception {
        return mongobaseDao.queryPage(currentPage, pageSize,params,FamilyArchiveMG.class);
    }
}
