package com.foundation.dao.modules.write.followup;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.followup.Followup;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface FollowupDao extends MybatisBaseDao<String, Followup> {

}