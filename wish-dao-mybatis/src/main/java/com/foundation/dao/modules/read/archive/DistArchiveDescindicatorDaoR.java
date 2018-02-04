package com.foundation.dao.modules.read.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @author SamWang(wangsammu@gmail.com)
 * @Title: DistArchiveConclusionDaoR
 * @Description: 档案-描述性指标字典表数据持久层->R操作
 * @date 2016/10/14 16:39
 */

@MyBatisRepository
public interface DistArchiveDescindicatorDaoR extends MybatisBaseDao<String, DistArchiveDescindicator> {
    /**
     * @Description 根据疾病id查询列表
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<DistArchiveDescindicator> queryByDiseaseId(String diseaseId) throws Exception;
}