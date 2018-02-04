package com.foundation.service.record.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.dao.IMongoBaseDao;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.mongo.entity.record.FamilyConcIndicator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.record.service.IBaseRecordService;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IndicatorRecordServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/18 17:40
 */
@Service
public class BaseRecordServiceImpl implements IBaseRecordService {
    private Log logger = LogFactory.getLog(BaseRecordServiceImpl.class);

    @Autowired(required = false)
    protected IMongoBaseDao mongobaseDao;

    @Override
    public boolean saveRecord(BaseRecordEntity recordEntity) throws Exception {
        //调用指标记录实体的validate方法校验必要入参
        if(recordEntity.validate()){
            mongobaseDao.save(recordEntity);
            return true;
        }else {
            return false;
        }
    }

    public boolean saveRecord(FamilyConcIndicator recordEntity) throws Exception {
        try{
            if(recordEntity.validate()){
                mongobaseDao.save(recordEntity);
                return true;
            }
        }catch(Exception e){
            logger.error("error:::[BaseRecordServiceImpl.saveRecord(FamilyConcIndicator recordEntity)],"+e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List queryList(Map params, Class clazz) throws Exception {
        logger.info("[BaseRecordServiceImpl.queryList],input param:(1)params:"+params.toString()+",(2)clazz:"+clazz);
        try{
            mongobaseDao.queryList(params,clazz);
        }catch(Exception e){
            logger.error("error:::[BaseRecordServiceImpl.queryList],"+e);
            e.printStackTrace();
        }
        return mongobaseDao.queryList(params,clazz);
    }

    @Override
    public Page queryPageList(int currentPage, int pageSize, Map params,Class clazz) throws Exception {
        return mongobaseDao.queryPage(currentPage, pageSize,params,clazz);
    }
}
