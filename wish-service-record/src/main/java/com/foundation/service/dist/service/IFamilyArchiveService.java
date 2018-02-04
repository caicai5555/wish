package com.foundation.service.dist.service;

import com.foundation.dao.entity.archive.FamilyArchive;

import java.util.List;
import java.util.Map;

/**
 * @author fqh
 * @ClassName: IDistArchiveIndexService
 * @Description: 家庭字典档案-服务
 * @date 2016/10/13 12:56
 */
public interface IFamilyArchiveService {

    /**
     * @Description: 保存家庭档案字典实体
     * @param familyArchive
     * @return
     * @throws Exception
     */
    public FamilyArchive save(FamilyArchive familyArchive) throws Exception;

    /**
     * @Description: 根据主键查询实体
     * @return
     * @throws Exception
     */
    public FamilyArchive queryById(String id) throws Exception;


    /**
     * @Description: 根据id删除实体（逻辑删除）
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteIndexById(String id) throws Exception;

    /**
     * @Description: 根据主键更新实体
     * @param familyArchive
     * @return
     * @throws Exception
     */
    public  void updateById(FamilyArchive familyArchive)throws Exception;

    /**
     * 
    * @Title: queryList 
    * @Description: 获取夫妻家庭档案列表
    * @author chengchen
    * @date 2016年10月27日 上午10:39:24 
    * @param @param params
    * @param @return
    * @param @throws Exception    设定参数 
    * @return List<FamilyArchive>    返回类型 
    * @throws
     */
    public List<FamilyArchive> queryList(Map<String, Object> params) throws Exception;
}
