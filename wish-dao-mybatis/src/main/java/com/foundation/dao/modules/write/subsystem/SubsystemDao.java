package com.foundation.dao.modules.write.subsystem;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.subsystem.Subsystem;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface SubsystemDao extends MybatisBaseDao<String, Subsystem> {

}