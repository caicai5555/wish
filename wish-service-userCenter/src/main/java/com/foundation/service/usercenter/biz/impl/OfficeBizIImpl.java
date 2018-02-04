package com.foundation.service.usercenter.biz.impl;

import com.foundation.dao.entity.sys.Office;
import com.foundation.service.usercenter.biz.IOfficeBiz;
import com.foundation.service.usercenter.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
public class OfficeBizIImpl implements IOfficeBiz {
	@Autowired
	private IOfficeService officeService;

	/**
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 查询所有的机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 */
	@Override
	public List<Office> findAll() {
		return officeService.findAll();
	}

	/**
	 * @param isAll
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 查询机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 */
	@Override
	public List<Office> findList(Boolean isAll) {
		return officeService.findList(isAll);
	}

	/**
	 * @param office
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 根据机构id查询机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 */
	@Override
	public List<Office> findList(Office office) {
		return officeService.findList(office);
	}

	/**
	 * @param office
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 保存机构
	 * @author chunyangli
	 * @date 2016/10/18 10:17
	 */
	@Override
	public void save(Office office) {
		officeService.save(office);

	}

	/**
	 * @param office
	 * @return
	 * @throws
	 * @Title:
	 * @Description: 保存机构
	 * @author chunyangli
	 * @date 2016/10/18 10:17
	 */
	@Override
	public void delete(Office office) {
		officeService.delete(office);

	}
}
