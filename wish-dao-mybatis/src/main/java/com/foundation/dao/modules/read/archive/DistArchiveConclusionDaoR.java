package com.foundation.dao.modules.read.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.DistArchiveConclusion;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @Title: DistArchiveConclusionDaoR
 * @Description: 档案-结论项字典表数据持久层->R操作
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/14 16:39 
 */
 
@MyBatisRepository
public interface DistArchiveConclusionDaoR extends MybatisBaseDao<String, DistArchiveConclusion> {

}