/**
 * 
 */
package com.bioeh.sp.hm.wx.biz.impl;

import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.util.PrimaryKeyGenerator;
import com.bioeh.sp.hm.wx.biz.IWXDeviceBiz;
import com.bioeh.sp.hm.wx.entity.WXDevice;
import com.bioeh.sp.hm.wx.service.IWXDeviceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName: BioDeviceBizImpl
 * @Description: 设备信息接口实现类)
 * @version V1.0
 */
@Service
public class WXDeviceBizImpl implements IWXDeviceBiz {

	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXDeviceBizImpl.class);
	
	/**
	 * 设备物理层接口
	 */
	@Autowired
	private IWXDeviceService wxdeviceService;
	/**
	 * 添加设备
	 * @param device
	 * @return
	 */

	public long addDevice(WXDevice device) throws Exception {
		long id = 0;
		try {
			// 操作结果
			id = PrimaryKeyGenerator.getLongKey();
			device.setId(id);
			wxdeviceService.addWXDevice(device);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return id;
	}

	/**
	 * 修改设备信息
	 * @param device
	 * @return
	 */
	public boolean updateDevice(WXDevice device) {
		boolean flag = false;
		try {
			flag = wxdeviceService.updateWXDevicer(device);
		} catch (Exception e) {
			logger.error("操作失败" + e);			
		}
		return flag;
	}

	@Override
	public boolean checkIsExist(String mac) throws Exception {
		boolean flag = false;
		try {
			flag = wxdeviceService.checkIsExist(mac);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public WXDevice getWXDeviceByMac(String mac) throws Exception {
		WXDevice wxDevice = null;
		try {
			wxDevice = wxdeviceService.getWXDeviceByMac(mac);
		} catch (Exception e) {
			logger.error("[WXDeviceBizImpl.getWXDeviceByMac]执行出错.", e);
		}
		return wxDevice;
	}
	@Override
	public WXDevice getWXDeviceById(Long id) throws Exception {
		WXDevice wxDevice = null;
		try {
			wxDevice = wxdeviceService.getWXDeviceById(id);
		} catch (Exception e) {
			logger.error("[WXDeviceBizImpl.getWXDeviceById]执行出错.", e);
		}
		return wxDevice;
	}
}
