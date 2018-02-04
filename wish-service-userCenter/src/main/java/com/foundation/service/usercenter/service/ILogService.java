package com.foundation.service.usercenter.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Log;

/**
 * 日志Service
 * @author fqh
 * @version 2016-08-16
 */
public interface ILogService {

	public Page<Log> findPage(Page<Log> page, Log log);
	
}
