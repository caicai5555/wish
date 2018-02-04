package com.foundation.dao.modules.write.subsystem;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.subsystem.SubsystemPlatformUser;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface SubsystemPlatformUserDao extends MybatisBaseDao<String, SubsystemPlatformUser> {

}