package com.foundation.dao.modules.read.file;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.file.SysFile;
import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * 文件查询DAO接口
 * @author fqh
 * @version 2016-11-24
 */
@MyBatisRepository
public interface SysFileDaoR extends MybatisBaseDao<String, SysFile> {

}
