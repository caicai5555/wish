package com.foundation.dao.modules.write.app;

import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.entity.app.AppVersion;
import com.foundation.common.persistence.annotation.MyBatisRepository;

/**
 * Created by fqh on 2015/12/8.
 */
@MyBatisRepository
public interface AppVersionDao extends MybatisBaseDao<String, AppVersion> {

    public String save();
}
