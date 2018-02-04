package com.foundation.service.usercenter.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Log;

/**
 * 日志Service
 * @author fqh
 * @version 2016-08-16
 */
public interface ILogBiz {
	/**
	 * @Title:
	 * @Description: 分页查询日志
	 * @author chunyangli
	 * @date 2016/10/18 10:13
	 * @param
	 * @return
	 * @throws
	 */

	public Page<Log> findPage(Page<Log> page, Log log);
	
}
