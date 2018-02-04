/**
 * 
 */
package com.foundation.service.clinic.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.mongo.dao.IMongoBaseDao;
import com.foundation.mongo.entity.ClinicRecordDetail;
import com.foundation.mongo.entity.record.Indicator;
import com.foundation.service.clinic.biz.IClinicRecordBiz;
import com.foundation.service.clinic.service.IClinicRecordService;
import com.foundation.service.record.service.impl.IndicatorRecordService;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ClinicRecordBizImpl
 * @Description: 临床记录的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
@Service
public class ClinicRecordBizImpl implements IClinicRecordBiz {
	private Log logger = LogFactory.getLog(ClinicRecordBizImpl.class);
	@Autowired
	private IClinicRecordService clinicRecordService;
	@Autowired(required = false)
	private IMongoBaseDao mongoBaseDao;
	@Autowired
	private IndicatorRecordService indicatorRecordService;
	@Autowired
	private IUserBiz userBiz;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(ClinicRecord clinicRecord) throws Exception {
		return clinicRecordService.save(clinicRecord);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(ClinicRecord clinicRecord) throws Exception {
//		mongoBaseDao.u
		return clinicRecordService.update(clinicRecord);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClinicRecord queryById(String id) throws Exception {
		return clinicRecordService.queryById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<ClinicRecord> queryPage(Map<String, Object> params, Page<ClinicRecord> page) throws Exception {
		if (params == null) params = Maps.newHashMap();
		return clinicRecordService.queryPage(params, page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return clinicRecordService.getCount(params);
	}

	@Override
	public boolean deleteReal(String id) throws Exception {
		return clinicRecordService.deleteReal(id);
	}

	@Override
	public ClinicRecordDetail queryDetailById(String recordId) throws Exception {
		return mongoBaseDao.queryById(recordId, ClinicRecordDetail.class);
	}

	@Override
	public boolean update(ClinicRecord clinicRecord, String[] inspectItemsValues, String[] codes, String[] names) throws Exception {
		Map<String, Object> paramsToMongo = Maps.newHashMap();
		for (int i = 0; i < inspectItemsValues.length; i++) {
			if (StringUtils.isNotEmpty(inspectItemsValues[i])) {
				paramsToMongo.put("inspectItems." + i + ".value", inspectItemsValues[i]);
			}
		}
		if (paramsToMongo.size() > 0) {
			String userId = clinicRecord.getCustId();
			String cardNo = userBiz.getUser(userId).getCardNo();
			mongoBaseDao.update(clinicRecord.getId(), paramsToMongo, ClinicRecordDetail.class);
			for (int i = 0; i < inspectItemsValues.length; i++) {
				Indicator indicator = new Indicator();
				indicator.setId(IdGen.uuid());
				indicator.setUserId(userId);
				indicator.setCode(codes[i]);
				indicator.setSource("inspectSource");
				indicator.setEvent("inspectEvent");
				indicator.setName(names[i]);
				indicator.setValue(inspectItemsValues[i]);
				indicator.setCertNum(cardNo);
				indicatorRecordService.saveIndicator(indicator, true);
			}
		}
		return update(clinicRecord);
	}

}
