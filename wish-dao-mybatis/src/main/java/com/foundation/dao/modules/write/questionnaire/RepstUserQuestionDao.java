package com.foundation.dao.modules.write.questionnaire;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface RepstUserQuestionDao extends MybatisBaseDao<String, RepstUserQuestion> {

}