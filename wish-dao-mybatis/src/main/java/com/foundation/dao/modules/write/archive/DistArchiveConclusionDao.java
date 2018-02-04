package com.foundation.dao.modules.write.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.DistArchiveConclusion;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @Title: DistArchiveConclusionDao
 * @Description: 档案-结论项表数据持久层->CUD操作
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/14 16:39 
 */
 
@MyBatisRepository
public interface DistArchiveConclusionDao extends MybatisBaseDao<String, DistArchiveConclusion> {

    /**
     * @Description: 根据主键物理删除
     * @param id
     * @return
     */
/*    Integer deleteByPrimaryKey(String id);*/
    /**
     * @Description: 根据主键逻辑删除（更新del_flag=1）
     * @param id
     * @return
     */
/*    Integer deleteLogicById();*/

}