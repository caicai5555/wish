package com.foundation.service.usercenter.service;

import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.modules.write.sys.AreaDao;
import com.foundation.service.usercenter.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 区域Service
 * @author fqh
 * @version 2016-08-16
 */
public interface ISysAreaService {

	public List<Area> findAll();

	public void save(Area area);

	public void delete(Area area);

	public Area get(Map<String,Object> params);
	
}
