package com.foundation.dao.modules.read.app;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.entity.app.AppVersion;

/**
 * Created by fqh on 2015/12/8.
 */
@MyBatisRepository
public interface AppVersionDaoR extends MybatisBaseDao<String, AppVersion> {
}
