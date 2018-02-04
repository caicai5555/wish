package com.foundation.service.dist.service.impl;

import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import com.foundation.dao.modules.read.archive.DistArchiveIndicatorDaoR;
import com.foundation.dao.modules.write.archive.DistArchiveIndicatorDao;
import com.foundation.service.common.CacheConstants;
import com.foundation.service.common.IndicatorType;
import com.foundation.service.dist.service.IDistArchiveIndicatorService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/13 12:57
 */
@Service
public class DistArchiveIndicatorServiceImpl implements IDistArchiveIndicatorService {
    private Log logger = LogFactory.getLog(DistArchiveIndicatorServiceImpl.class);

    @Autowired
    DistArchiveIndicatorDao distArchiveIndexDao;//CUD操作
    @Autowired
    DistArchiveIndicatorDaoR distArchiveIndicatorDaoR;//Read操作

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DistArchiveIndicator save(DistArchiveIndicator distArchiveIndicator) throws Exception {
        try {
            if (distArchiveIndicator == null
                    || StringUtils.isEmpty(distArchiveIndicator.getId())
                    || StringUtils.isEmpty(distArchiveIndicator.getCode())) {
                throw new Exception("[DistArchiveIndexServiceImpl.save]方法入参id或code为空");
            }
            distArchiveIndexDao.save(distArchiveIndicator);

            //保存成功，放入缓存
            //指标类型缓存-opt:放入缓存
            RedisUtils.getTemplate()
                    .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + distArchiveIndicator.getCode(),
                            IndicatorType.INDICATOR.getType());
            //指标所属表缓存-opt:指标对应表清除表名对应的指标列表缓存
            RedisUtils.getTemplate().del(CacheConstants.CACHE_DISTARCHIVEINDICATOR_TABLENAME + distArchiveIndicator.getTableName());
        } catch (Exception e) {
            logger.error("[DistArchiveIndicatorServiceImpl.save] error info " + e);
        }

        return distArchiveIndicator;
    }

    @Override
    public void batchSave(List<DistArchiveIndicator> distArchiveIndicatorList) throws Exception {

    }

    @Override
    public List<DistArchiveIndicator> query() throws Exception {
        return null;
    }

    @Override
    public DistArchiveIndicator queryById(String id) throws Exception {
        return distArchiveIndicatorDaoR.queryById(id);
    }

    @Override
    public List<DistArchiveIndicator> queryByTableName(String tableName) throws Exception {
        List<DistArchiveIndicator> list =  new ArrayList<DistArchiveIndicator>();
        String key = CacheConstants.CACHE_DISTARCHIVEINDICATOR_TABLENAME + tableName;

        try {
            //先从缓存中获取
            String result = RedisUtils.getTemplate().get(key);
            if (StringUtils.isNotEmpty(result)) {
//                list = JsonUtils.fromJson(result, (new ArrayList<DistArchiveIndicator>()).getClass());
                list = com.alibaba.fastjson.JSONObject.parseArray(result,DistArchiveIndicator.class);
                return list;
            }
            //查库
            Map<String, String> params = new HashMap<>();
            params.put("tableName", tableName);
            list = distArchiveIndicatorDaoR.queryList(params);

            //放入缓存  -------------修改后开启
            if(null !=list&& list.size()>0){
//                String value = JSON.toJSONString(list);
                String value = com.alibaba.fastjson.JSONObject.toJSONString(list);
                RedisUtils.getTemplate().set(key, value);
            }
        } catch (Exception e) {
            logger.error("[DistArchiveIndicatorServiceImpl:queryByTableName]=> is error", e);
        }
        return list;
    }

    @Override
    @Deprecated
    public DistArchiveIndicator queryByCode(String indexCode) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("code", indexCode);
        return distArchiveIndicatorDaoR.queryObject(params);
    }

    @Override
    public DistArchiveIndicator queryObject(Map<String, Object> params) {
        return distArchiveIndicatorDaoR.queryObject(params);
    }

    @Override
    public List<DistArchiveIndicator> queryByParentId(String parentId) throws Exception {
        return null;
    }

    @Override
    public Integer deleteById(String id) throws Exception {
        int count=0;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("delFlag", Constants.DEL_FLAG_DELETE);
            count =  distArchiveIndexDao.deleteByPrimaryKey(params);

            //删除tableName为key的缓存
            if(count>0){
                String tableName = this.queryById(id).getTableName();
                RedisUtils.getTemplate().del(CacheConstants.CACHE_DISTARCHIVEINDICATOR_TABLENAME + tableName);
            }
        }catch(Exception e){
            logger.error("[DistArchiveIndicatorServiceImpl:deleteById]=> is error", e);
        }
        return count;
    }

    @Override
    public void deleteByCodeAndSex(String code, Integer sex) throws Exception {
        DistArchiveIndicator distArchiveIndicator = new DistArchiveIndicator();
        distArchiveIndicator.setDelFlag(Constants.DEL_FLAG_DELETE);
        distArchiveIndicator.setCode(code);
        distArchiveIndicator.setSex(sex);
        this.updateByCode(distArchiveIndicator);
    }

    @Override
    public Integer updateByCode(DistArchiveIndicator distArchiveIndicator) throws Exception {
        if (distArchiveIndicator == null
                || StringUtils.isEmpty(distArchiveIndicator.getCode())
                || distArchiveIndicator.getSex()== null) {
            throw new Exception("[DistArchiveIndexServiceImpl.updateByIndexCode]方法入参为null或更新条件code为空");
        }
        Integer count = distArchiveIndexDao.updateByCode(distArchiveIndicator);
        //删除tableName为key的缓存
        if(count>0){
            Map<String, Object> params = new HashMap<>();
            params.put("code",distArchiveIndicator.getCode());
            params.put("sex",distArchiveIndicator.getSex());
            DistArchiveIndicator bean = this.queryObject(params);
            String tableName = bean !=null? bean.getTableName():null;
            RedisUtils.getTemplate().del(CacheConstants.CACHE_DISTARCHIVEINDICATOR_TABLENAME + tableName);
        }


        return count;
    }

    @Override
    public List<DistArchiveIndicator> queryAll() throws Exception {
        return distArchiveIndicatorDaoR.queryAll();
    }

    @Override
    public Integer getCount(Map<String, Object> params) throws Exception {
        return distArchiveIndicatorDaoR.getCount(params);
    }
}
