package com.foundation.dao.modules.write.clinic;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface ClinicRecordDao extends MybatisBaseDao<String, ClinicRecord> {

}