package com.foundation.service.usercenter.service;

import com.foundation.dao.entity.sys.Office;
import com.foundation.dao.modules.write.sys.OfficeDao;
import com.foundation.service.usercenter.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 机构Service
 * @author fqh
 * @version 2016-08-16
 */

public interface IOfficeService {

	public List<Office> findAll();

	public List<Office> findList(Boolean isAll);
	
	public List<Office> findList(Office office);
	
	public void save(Office office);
	
	public void delete(Office office);

	public Office get(Map<String,Object> params);
	
}
