package com.foundation.dao.modules.read.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @Title: DistArchiveIndexDaoR
 * @Description: 档案-指标字典表数据持久层->R操作
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/13 11:48 
 */
@MyBatisRepository
public interface DistArchiveIndicatorDaoR extends MybatisBaseDao<String, DistArchiveIndicator> {

    /**
     * @Description: 根据指标code获取指标项实体
     * @param indexCode
     * @return
     */
/*    DistArchiveIndicator selectByIndexCode(String indexCode);*/

    /**
     * @Description: 根据父id获取指标列表
     * @param parentId
     * @return
     */
/*    List<DistArchiveIndicator> queryByParentId(String parentId);*/

    /**
     * @Description: 获取所有指标列表
      * @return
     */
    List<DistArchiveIndicator> queryAll();
}