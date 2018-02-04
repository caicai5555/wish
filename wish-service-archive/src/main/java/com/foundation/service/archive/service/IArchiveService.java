package com.foundation.service.archive.service;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.entity.archive.Archive;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IArchiveService
 * @Description: 档案服务类
 * @date 2016/10/18 17:04
 */
public interface IArchiveService{

    /**
     * @Description: 保存档案
     * @param archive
     * @throws Exception
     */
    public void saveArvhice(Archive archive) throws Exception;


    /**
     * @Description: 查询档案by userId
     * @param userId
     * @return
     * @throws Exception
     */
    public Archive queryByUserId(String userId) throws Exception;

    /**
     * @Description: 查询档案列表页
     * @return
     * @throws Exception
     */
    public Page<Archive> queryPageList(int currentPage, int pageSize ,Map<String,Object> params) throws Exception;
    
    /**
     * 
    * @Title: queryOne 
    * @Description: 根据参数获取档案
    * @author chengchen
    * @date 2016年10月29日 下午8:56:17 
    * @param @param params
    * @param @return
    * @param @throws Exception    设定参数 
    * @return Archive    返回类型 
    * @throws
     */
    public Archive queryOne(Map<String,Object> params) throws Exception;
}
