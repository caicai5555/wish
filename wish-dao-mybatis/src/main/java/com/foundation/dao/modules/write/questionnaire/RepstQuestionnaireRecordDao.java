package com.foundation.dao.modules.write.questionnaire;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireRecord;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface RepstQuestionnaireRecordDao extends MybatisBaseDao<String, RepstQuestionnaireRecord> {

}