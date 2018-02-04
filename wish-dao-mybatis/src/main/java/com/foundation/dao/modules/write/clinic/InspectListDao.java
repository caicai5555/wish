package com.foundation.dao.modules.write.clinic;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface InspectListDao extends MybatisBaseDao<String, InspectList> {

}