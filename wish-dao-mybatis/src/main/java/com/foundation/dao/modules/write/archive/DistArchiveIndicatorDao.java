package com.foundation.dao.modules.write.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.Map;

/**
 * @Title: DistArchiveIndexDaoR
 * @Description: 档案-指标字典表数据持久层->CUD操作
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/13 11:48
 */
@MyBatisRepository
public interface DistArchiveIndicatorDao extends MybatisBaseDao<String, DistArchiveIndicator> {
    /**
     * @Description: 根据指标主键删除指标项，逻辑删除，更新del_flag=1
     * @param params
     * @return
     */
    int deleteByPrimaryKey(Map<String, Object> params);


    /**
     * @Description: 根据指标编号删除指标项，逻辑删除，更新del_flag=1
     * @param params
     * @return
     */
    @Deprecated
    Integer deleteByCode(Map<String, Object> params);

    /**
     * @Description: 根据指标编号更新指标数据
     * @param distArchiveIndicator
     * @return
     */
    Integer updateByCode(DistArchiveIndicator distArchiveIndicator);
}