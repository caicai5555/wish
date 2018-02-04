package com.foundation.dao.modules.write.archive;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.archive.FamilyArchive;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @author fqh
 * @Title: FamilyArchiveDaoR
 * @Package com.foundation.dao.modules.read.archive
 * @Description: 家庭档案->写Dao
 */
@MyBatisRepository
public interface FamilyArchiveDao extends MybatisBaseDao<String,FamilyArchive> {
}
