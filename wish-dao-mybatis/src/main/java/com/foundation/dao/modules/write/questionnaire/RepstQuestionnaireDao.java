package com.foundation.dao.modules.write.questionnaire;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaire;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface RepstQuestionnaireDao extends MybatisBaseDao<String, RepstQuestionnaire> {

}