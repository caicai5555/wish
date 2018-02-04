package com.foundation.dao.modules.write.questionnaire;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestions;
import com.foundation.dao.modules.MybatisBaseDao;

@MyBatisRepository
public interface RepstQuestionsDao extends MybatisBaseDao<String, RepstQuestions> {

}