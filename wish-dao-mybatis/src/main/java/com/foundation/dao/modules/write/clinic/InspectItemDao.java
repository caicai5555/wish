package com.foundation.dao.modules.write.clinic;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface InspectItemDao extends MybatisBaseDao<String, InspectItem> {

    int updateById(InspectItem item);

}