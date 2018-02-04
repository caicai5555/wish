package com.foundation.service.usercenter.biz.impl;

import com.foundation.dao.entity.sys.Area;
import com.foundation.service.usercenter.biz.ISysAreaBiz;
import com.foundation.service.usercenter.service.ISysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
public class SysAreaBizImpl implements ISysAreaBiz {
	@Autowired
	private ISysAreaService sysAreaService;


	/**
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 查询所有的区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 */
	@Override
	public List<Area> findAll() {
		return sysAreaService.findAll();
	}

	/**
	 * @param area
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 保存区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 */
	@Override
	public void save(Area area) {
		sysAreaService.save(area);

	}

	/**
	 * @param area
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 删除区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 */
	@Override
	public void delete(Area area) {
		sysAreaService.delete(area);

	}
}
