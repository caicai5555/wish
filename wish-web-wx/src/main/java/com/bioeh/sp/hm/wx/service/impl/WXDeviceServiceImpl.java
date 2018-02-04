/**
 * 
 */
package com.bioeh.sp.hm.wx.service.impl;

import com.bioeh.sp.hm.dal.ICommonDao;
import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.build.operator.Operator;
import com.bioeh.sp.hm.wx.common.constant.DBConstants;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.entity.WXDevice;
import com.bioeh.sp.hm.wx.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 微信设备Service实现
 */
@Service
public class WXDeviceServiceImpl implements IWXDeviceService{
	
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXDeviceServiceImpl.class);
	
	/**
	 * JDBC接口
	 */
	@Autowired
	private ICommonDao commonDao;
	
	@Override
	public long addWXDevice(WXDevice device) throws Exception {
		int  resultId = 0;
		try {
			resultId = commonDao.saveEntity(device);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return resultId;
	}
	/*
	 * 更新操作
	 */
	@Override
	public boolean updateWXDevicer( WXDevice device) throws Exception {
		boolean falg = false;
		//修改的字段和值
		Map<String,Object> map = WechatConstant.returnWXDeviceValue(device);
		try {
			falg = commonDao.updateEntityById(device.getId(), map, WXDevice.class);
		} catch (Exception e) {
			logger.error("updateWXDevicer操作失败" + e);
		}
		return falg;
	}

	@Override
	public boolean checkIsExist(String mac) throws Exception {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MAC , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ mac,0 });
			List<WXDevice> wxDeviceList = commonDao.findEntityList(condition, null, WXDevice.class);
			result = (null != wxDeviceList && wxDeviceList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
			throw e;
		}

		return result;
	}

	@Override
	public WXDevice getWXDeviceByMac(String mac) throws Exception {
		WXDevice wxDevice = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.MAC, DBConstants.Common.STATUS},
					new Operator[]{ Operator.EQ, Operator.EQ },
					new Object[]{ mac,0 });
			List<WXDevice> wxDeviceList = commonDao.findEntityList(condition, null, WXDevice.class);
			if(null != wxDeviceList && wxDeviceList.size() > 0) {
				wxDevice = wxDeviceList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXUserDeviceById]执行出错.",e);
		}

		return wxDevice;
	}
	@Override
	public WXDevice getWXDeviceById(Long id) throws Exception {
		WXDevice wxDevice = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.ID, DBConstants.Common.STATUS},
					new Operator[]{ Operator.EQ, Operator.EQ },
					new Object[]{ id,0 });
			List<WXDevice> wxDeviceList = commonDao.findEntityList(condition, null, WXDevice.class);
			if(null != wxDeviceList && wxDeviceList.size() > 0) {
				wxDevice = wxDeviceList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXDeviceById]执行出错.",e);
		}

		return wxDevice;
	}

}
