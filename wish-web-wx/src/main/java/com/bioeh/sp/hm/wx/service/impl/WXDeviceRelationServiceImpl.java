/**
 * 
 */
package com.bioeh.sp.hm.wx.service.impl;

import com.bioeh.sp.hm.dal.ICommonDao;
import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.build.operator.Operator;
import com.bioeh.sp.hm.wx.common.constant.DBConstants;
import com.bioeh.sp.hm.wx.entity.WXDeviceRelation;
import com.bioeh.sp.hm.wx.service.IWXDeviceRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD微信device_id与设备mac关系
 */
@Service
public class WXDeviceRelationServiceImpl implements IWXDeviceRelationService{
	
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXDeviceRelationServiceImpl.class);
	
	/**
	 * JDBC接口
	 */
	@Autowired
	private ICommonDao commonDao;


	@Override
	public long addWXDeviceRelation(WXDeviceRelation wxDeviceRelation) throws Exception {
		int  resultId = 0;
		try {
			resultId = commonDao.saveEntity(wxDeviceRelation);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return resultId;
	}

	@Override
	public WXDeviceRelation getWXDeviceRelationByMac(String mac) throws Exception {
		WXDeviceRelation wxDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MAC , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ mac,0 });
			List<WXDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXDeviceRelationByMac]执行出错.",e);
		}

		return wxDeviceRelation;
	}

	@Override
	public WXDeviceRelation getWXDeviceRelationByDeviceId(String device_id) throws Exception {
		WXDeviceRelation wxDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.DEVICE_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ device_id,0 });
			List<WXDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXDeviceRelationByDeviceId]执行出错.",e);
		}

		return wxDeviceRelation;
	}
}
