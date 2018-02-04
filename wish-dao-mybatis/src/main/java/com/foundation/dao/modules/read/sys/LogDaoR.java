package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Log;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @Title: LogDaoR.java
 * @Package com.foundation.dao.modules.read.sys
 * @Description: 日志读类
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/11/21 10:06
 */
@MyBatisRepository
public interface LogDaoR extends MybatisBaseDao<String, Log> {

    public List<Log> findList(Log log);

}
