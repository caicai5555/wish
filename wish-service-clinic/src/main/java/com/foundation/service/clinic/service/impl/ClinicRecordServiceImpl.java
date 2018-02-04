/**
 * 
 */
package com.foundation.service.clinic.service.impl;

import java.util.List;
import java.util.Map;

import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.mongo.entity.ClinicRecordDetail;
import com.foundation.mongo.entity.InspectItemDetail;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.dao.modules.read.clinic.ClinicRecordDaoR;
import com.foundation.dao.modules.write.clinic.ClinicRecordDao;
import com.foundation.mongo.dao.IMongoBaseDao;
import com.foundation.service.clinic.service.IClinicRecordService;

/**
 * @ClassName: ClinicRecordServiceImpl
 * @Description: 临床记录的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
@Service
public class ClinicRecordServiceImpl implements IClinicRecordService {
//	private Log logger = LogFactory.getLog(ClinicRecordServiceImpl.class);

	@Autowired(required=false)
	private ClinicRecordDao clinicRecordDao;
	@Autowired(required=false)
	private ClinicRecordDaoR clinicRecordDaoR;
	@Autowired(required = false)
	private IMongoBaseDao mongoBaseDao;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(ClinicRecord clinicRecord) throws Exception {
		ClinicRecordDetail clinicRecordDetail = convertToMongo(clinicRecord);
		if (clinicRecordDetail != null) {
			mongoBaseDao.save(clinicRecordDetail);
		}
		long affectedRows = clinicRecordDao.save(clinicRecord);
		return 1 == affectedRows ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(ClinicRecord clinicRecord) throws Exception {
		clinicRecordDao.update(clinicRecord);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClinicRecord queryById(String id) throws Exception {
		return clinicRecordDaoR.queryById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<ClinicRecord> queryPage(Map<String, Object> params, Page<ClinicRecord> page) throws Exception {
		List<ClinicRecord> list = clinicRecordDaoR.queryPageList(params, page);
		page.setList(list);
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return clinicRecordDaoR.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		clinicRecordDao.delete(id);
		return true;
	}

	/**
	 * 将 ClinicRecord 转换成 mongodb 的ClinicRecordDetail
	 * @param record
	 * @return ClinicRecordDetail
	 */
	private ClinicRecordDetail convertToMongo(ClinicRecord record){
		ClinicRecordDetail recordDetail = null;
		InspectList inspectList = record.getInspectList();
		if (inspectList != null) {
			recordDetail = new ClinicRecordDetail();
			recordDetail.setId(record.getId());
			recordDetail.setInspectId(inspectList.getId());
			recordDetail.setInspectName(inspectList.getInspectName());
			recordDetail.setEnglishName(inspectList.getEnglishName());
			recordDetail.setInspectMethod(inspectList.getInspectMethod());
			List<InspectItem> inspectItems = inspectList.getInspectItems();
			if (inspectItems != null && inspectItems.size() > 0){
				List<InspectItemDetail> detailList = Lists.newArrayList();
				for (InspectItem item : inspectItems) {
					InspectItemDetail itemDetail = new InspectItemDetail();
					itemDetail.setId(item.getId());
					itemDetail.setEnglishName(item.getEnglishName());
					itemDetail.setIndexId(item.getIndexId());
					itemDetail.setIndexName(item.getIndexName());
					itemDetail.setItemName(item.getItemName());
					itemDetail.setMax(item.getMax());
					itemDetail.setMin(item.getMin());
					itemDetail.setNormalRange(item.getNormalRange());
					itemDetail.setReduceFormula(item.getReduceFormula());
					itemDetail.setUnits(item.getUnits());
					detailList.add(itemDetail);
				}
				recordDetail.setInspectItems(detailList);
			}
		}
		return recordDetail;
	}

}
