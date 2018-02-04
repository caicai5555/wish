package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.*;
import com.foundation.dao.entity.sys.User;
import com.foundation.mongo.entity.record.Indicator;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireBiz;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireRecordBiz;
import com.foundation.service.questionnaire.service.IRepstQuestionnaireRecordService;
import com.foundation.service.questionnaire.service.IRepstUserQuestionService;
import com.foundation.service.record.service.IIndicatorRecordService;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问卷记录的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireRecordBizImpl implements IRepstQuestionnaireRecordBiz {

    @Autowired
    private IRepstQuestionnaireRecordService repstQuestionnaireRecordService;

    @Autowired
    private IRepstQuestionnaireBiz repstQuestionnaireBiz;

    @Autowired
    private IRepstUserQuestionService repstUserQuestionService;

    @Autowired
    private IIndicatorRecordService indicatorRecordService;

    @Autowired
    private IUserBiz userBiz;

    @Override
    @Transactional
    public boolean save(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception {
        List<RepstUserQuestion> userQuestionList = repstQuestionnaireRecord.getRepstUserQuestionList();
        if(userQuestionList != null && userQuestionList.size() > 0 ){
            repstUserQuestionService.save(userQuestionList);
            // 存档
            String userId = userQuestionList.get(0).getUserId();
            User user = userBiz.getUser(userId);
            String cardNo = "";
            if (user != null) {
                cardNo = user.getCardNo();
            }
            for (RepstUserQuestion userQuestion : userQuestionList) {
                //  档案类型是指标，则归档
                if ("1".equals(userQuestion.getArchiveType())) {
                    Indicator indicator = new Indicator();
                    indicator.setId(IdGen.uuid());
                    indicator.setUserId(userId);
                    indicator.setCode(userQuestion.getTypeEncoding());
                    indicator.setSource("questionnaireSource");
                    indicator.setEvent("questionnaireEvent");
                    indicator.setName(userQuestion.getItemName());
                    indicator.setValue(userQuestion.getItemScore());
                    indicator.setCertNum(cardNo);
                    indicatorRecordService.saveIndicator(indicator, true);
                }
            }
        }
        return repstQuestionnaireRecordService.save(repstQuestionnaireRecord);
    }

    @Override
    public boolean save(List<RepstQuestionnaireRecord> repstQuestionnaireRecordList) throws Exception {
        return repstQuestionnaireRecordService.save(repstQuestionnaireRecordList);
    }

    @Override
    public boolean update(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception {
        return repstQuestionnaireRecordService.update(repstQuestionnaireRecord);
    }

    @Override
    public RepstQuestionnaireRecord queryById(String id) throws Exception {
        return repstQuestionnaireRecordService.queryById(id);
    }

    @Override
    public Page<RepstQuestionnaireRecord> queryPage(Map<String, Object> params, Page<RepstQuestionnaireRecord> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionnaireRecordService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireRecordService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionnaireRecordService.deleteReal(id);
    }

    @Override
    public RepstQuestionnaireRecord queryDetailById(String id) throws Exception {
        RepstQuestionnaireRecord repstQuestionnaireRecord = repstQuestionnaireRecordService.queryDetailById(id);
        RepstQuestionnaire repstQuestionnaire = repstQuestionnaireBiz.queryDetailById(repstQuestionnaireRecord.getQuestionnaireId());
        repstQuestionnaireRecord.setRepstQuestionnaire(repstQuestionnaire);
        List<RepstUserQuestion> userQuestionList = repstQuestionnaireRecord.getRepstUserQuestionList();
        HashMap<String, RepstUserQuestion> userQuestionMap = Maps.newHashMap();
        for (RepstUserQuestion userQuestion : userQuestionList) {
            userQuestionMap.put(userQuestion.getItemId(), userQuestion);
        }
        // 将用户选择的对应试题项 放入对应试题
        List<RepstQuestionsCtgr> questionsCtgrList = repstQuestionnaire.getRepstQuestionsCtgrList();
        for (RepstQuestionsCtgr questionsCtgr : questionsCtgrList) {
            for (RepstQuestions questions : questionsCtgr.getRepstQuestionsList()) {
                for (RepstQuestionItem questionItem : questions.getRepstQuestionItemList()) {
                    questionItem.setRepstUserQuestion(userQuestionMap.get(questionItem.getId()));
                }
            }
        }
        return repstQuestionnaireRecord;
    }

}
