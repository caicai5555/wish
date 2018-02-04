package com.foundation.service.usercenter.service.impl;

import com.foundation.common.date.DateUtils;
import com.foundation.dao.entity.sys.Log;
import com.foundation.dao.modules.read.sys.LogDaoR;
import com.foundation.dao.modules.write.sys.LogDao;
import com.foundation.service.usercenter.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.Page;

/**
 * 日志Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
@Transactional(readOnly = true)
public class LogServiceImpl  implements ILogService{

	@Autowired(required = false)
	private LogDaoR logDaoR;
	public Page<Log> findPage(Page<Log> page, Log log) {

		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		log.setPage(page);
		page.setList(logDaoR.findList(log));
		return page;
	}
	
}
