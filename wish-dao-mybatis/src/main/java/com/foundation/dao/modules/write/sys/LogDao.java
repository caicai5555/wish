package com.foundation.dao.modules.write.sys;

import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.entity.sys.Dict;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Log;

/**
 * 日志DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface LogDao extends MybatisBaseDao<String, Log> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(Log entity);
}
