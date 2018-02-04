package com.foundation.dao.modules.write.clinic;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.clinic.InspectListItem;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface InspectListItemDao extends MybatisBaseDao<String, InspectListItem> {

}