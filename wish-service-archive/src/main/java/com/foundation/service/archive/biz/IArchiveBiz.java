package com.foundation.service.archive.biz;


import com.foundation.common.utils.Collections3;
import com.foundation.mongo.entity.archive.Archive;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;

/**
 * @author wangsen
 * @ClassName: IArchiveBiz
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/25 1:47
 */
public interface IArchiveBiz {
    /**
     * @Description: 查询档案by userId
     * @param userId
     * @return
     * @throws Exception
     */
    public Archive queryByUserId(String userId) throws Exception;
    
    /**
     * 
    * @Title: queryByCertNum 
    * @Description: 根据身份证获取用户档案
    * @author chengchen
    * @date 2016年10月29日 下午9:04:31 
    * @param @param certNum
    * @param @return
    * @param @throws Exception    设定参数 
    * @return Archive    返回类型 
    * @throws
     */
    public Archive queryByCertNum(String certNum) throws Exception;
}
