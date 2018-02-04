package com.foundation.service.usercenter.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Log;
import com.foundation.service.usercenter.biz.ILogBiz;
import com.foundation.service.usercenter.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
public class LogBizImpl implements ILogBiz {
	@Autowired
	private ILogService logService;


	/**
	 * @param page
	 * @param log  @return
	 * @throws
	 * @Title:
	 * @Description: 分页查询日志
	 * @author chunyangli
	 * @date 2016/10/18 10:13
	 */
	@Override
	public Page<Log> findPage(Page<Log> page, Log log) {
		return logService.findPage(page,log);
	}
}
