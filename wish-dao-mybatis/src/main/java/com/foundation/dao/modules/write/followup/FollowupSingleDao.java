package com.foundation.dao.modules.write.followup;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.followup.FollowupSingle;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface FollowupSingleDao extends MybatisBaseDao<String, FollowupSingle> {

}