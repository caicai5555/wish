package com.foundation.dao.modules.write.file;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.file.SysFile;
import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * 文档DAO接口
 * @author fqh
 * @version 2016-11-24
 */
@MyBatisRepository
public interface SysFileDao extends MybatisBaseDao<String, SysFile> {
}
