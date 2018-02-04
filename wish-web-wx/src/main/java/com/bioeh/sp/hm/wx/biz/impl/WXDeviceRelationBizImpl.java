/**
 * 
 */
package com.bioeh.sp.hm.wx.biz.impl;

import com.bioeh.sp.hm.dal.util.PrimaryKeyGenerator;
import com.bioeh.sp.hm.wx.biz.IWXDeviceRelationBiz;
import com.bioeh.sp.hm.wx.entity.WXDeviceRelation;
import com.bioeh.sp.hm.wx.service.IWXDeviceRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName: BioDeviceBizImpl
 * @Description TOOD微信device_id与设备mac关系
 * @version V1.0
 */
@Service
public class WXDeviceRelationBizImpl implements IWXDeviceRelationBiz {

	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXDeviceRelationBizImpl.class);
	
	/**
	 * 设备物理层接口
	 */
	@Autowired
	private IWXDeviceRelationService iwxDeviceRelationService;


	@Override
	public long addWXDeviceRelation(WXDeviceRelation wxDeviceRelation) throws Exception {
		long id = 0;
		try {
			// 操作结果
			id = PrimaryKeyGenerator.getLongKey();
			wxDeviceRelation.setId(id);
			iwxDeviceRelationService.addWXDeviceRelation(wxDeviceRelation);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return id;
	}

	@Override
	public WXDeviceRelation getWXDeviceRelationByMac(String mac) throws Exception {
		WXDeviceRelation wxDeviceRelation = null;
		try {
			wxDeviceRelation = iwxDeviceRelationService.getWXDeviceRelationByMac(mac);
		} catch (Exception e) {
			logger.error("[WXDeviceBizImpl.getWXDeviceById]执行出错.", e);
		}
		return wxDeviceRelation;
	}

	@Override
	public WXDeviceRelation getWXDeviceRelationByDeviceId(String device_id) throws Exception {
		WXDeviceRelation wxDeviceRelation = null;
		try {
			wxDeviceRelation = iwxDeviceRelationService.getWXDeviceRelationByDeviceId(device_id);
		} catch (Exception e) {
			logger.error("[WXDeviceBizImpl.getWXDeviceById]执行出错.", e);
		}
		return wxDeviceRelation;
	}
}
