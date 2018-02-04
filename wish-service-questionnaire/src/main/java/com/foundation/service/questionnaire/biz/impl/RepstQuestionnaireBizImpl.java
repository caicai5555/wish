package com.foundation.service.questionnaire.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.*;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireBiz;
import com.foundation.service.questionnaire.biz.IRepstQuestionsCtgrBiz;
import com.foundation.service.questionnaire.service.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RepstQuesionnaireBizImpl
 * @Description: 问卷的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
@Service
public class RepstQuestionnaireBizImpl implements IRepstQuestionnaireBiz {

    @Autowired
    private IRepstQuestionnaireService repstQuestionnaireService;

    @Autowired
    private IRepstQuestionsService repstQuestionsService;

    @Autowired
    private IRepstQuestionItemService repstQuestionItemService;

    @Autowired
    private IRepstQuestionCtgrRelService repstQuestionCtgrRelService;

    @Autowired
    private IRepstQuestionnaireCtgrRelService repstQuestionnaireCtgrRelService;

    @Autowired
    private IRepstQuestionsCtgrBiz repstQuestionsCtgrBiz;

    @Override
    @Transactional
    public boolean save(RepstQuestionnaire repstQuestionnaire) throws Exception {
        List<RepstQuestionsCtgr> questionsCtgrList = repstQuestionnaire.getRepstQuestionsCtgrList();    //  试题分类
        List<RepstQuestionnaireCtgrRel> questionnaireCtgrRelList = Lists.newArrayList();   // 问卷、试题分类 中间表
        String questionnaireId = repstQuestionnaire.getId();
        for (RepstQuestionsCtgr questionsCtgr : questionsCtgrList) {
            String ctgrId = questionsCtgr.getId();
            RepstQuestionnaireCtgrRel questionnaireCtgrRel = new RepstQuestionnaireCtgrRel();
            questionnaireCtgrRel.setId(IdGen.uuid());
            questionnaireCtgrRel.setQuestionnaireId(questionnaireId);
            questionnaireCtgrRel.setQuestionCtgr(ctgrId);
            List<RepstQuestionCtgrRel> questionCtgrRelList = Lists.newArrayList();  // 试题分类、试题 中间表
            List<RepstQuestions> questionsList = questionsCtgr.getRepstQuestionsList();
            for (RepstQuestions questions : questionsList) {
                RepstQuestionCtgrRel questionCtgrRel = new RepstQuestionCtgrRel();
                questionCtgrRel.setId(IdGen.uuid());
                questionCtgrRel.setQuestionCtgr(ctgrId);
                questionCtgrRel.setQuestionId(questions.getId());
                questionCtgrRelList.add(questionCtgrRel);
                List<RepstQuestionItem> questionItemList = questions.getRepstQuestionItemList();
                repstQuestionItemService.save(questionItemList); // 试题项 的保存
            }
            questionnaireCtgrRelList.add(questionnaireCtgrRel);
            repstQuestionCtgrRelService.save(questionCtgrRelList); // 试题分类、试题 中间表 的保存
            repstQuestionsService.save(questionsList);  // 试题 的保存
        }
        repstQuestionnaireCtgrRelService.save(questionnaireCtgrRelList); // 问卷、试题分类 中间表 的保存
        repstQuestionsCtgrBiz.save(questionsCtgrList); //试题分类的保存
        return repstQuestionnaireService.save(repstQuestionnaire);  //  问卷 的保存
    }

    @Override
    public boolean save(List<RepstQuestionnaire> repstQuestionnaireList) throws Exception {
        return repstQuestionnaireService.save(repstQuestionnaireList);
    }

    @Override
    public boolean update(RepstQuestionnaire repstQuestionnaire) throws Exception {
        return repstQuestionnaireService.update(repstQuestionnaire);
    }

    @Override
    public RepstQuestionnaire queryById(String id) throws Exception {
        return repstQuestionnaireService.queryById(id);
    }

    @Override
    public Page<RepstQuestionnaire> queryPage(Map<String, Object> params, Page<RepstQuestionnaire> page) throws Exception {
        if (params == null) params = Maps.newHashMap();
        return repstQuestionnaireService.queryPage(params, page);
    }

    @Override
    public int getCount(Map<String, Object> params) throws Exception {
        return repstQuestionnaireService.getCount(params);
    }

    @Override
    public boolean deleteReal(String id) throws Exception {
        return repstQuestionnaireService.deleteReal(id);
    }

    @Override
    public RepstQuestionnaire queryDetailById(String id) throws Exception {
        RepstQuestionnaire repstQuestionnaire = repstQuestionnaireService.queryDetailById(id);
        // 获取试题信息
        HashMap<String, Object> params = Maps.newHashMap();
        List<RepstQuestionsCtgr> questionsCtgrList = repstQuestionnaire.getRepstQuestionsCtgrList();
        if (questionsCtgrList != null && questionsCtgrList.size()>0) {
            //  试题分类的id
            List<String> ids = Lists.newArrayList();
            for (RepstQuestionsCtgr repstQuestionsCtgr : questionsCtgrList) {
                ids.add(repstQuestionsCtgr.getId());
            }
            params.put("ids", ids);
            Page<RepstQuestionsCtgr> pageCtgr = new Page<>(1, 100);
            repstQuestionsCtgrBiz.queryDetailPage(params, pageCtgr);
            questionsCtgrList = pageCtgr.getList();     // 带试题的 试题分类
            Map<String, RepstQuestionsCtgr> questionsCtgrMap = Maps.newHashMap();   // 试题分类id 试题分类 映射
            params.clear(); // 清空params，后面用于查找 试题
            ids.clear();    // 清空 ids，后面用于查找 试题
            for (RepstQuestionsCtgr questionsCtgr : questionsCtgrList) {
                questionsCtgrMap.put(questionsCtgr.getId(), questionsCtgr);
                for (RepstQuestions questions : questionsCtgr.getRepstQuestionsList()) {
                    ids.add(questions.getId());
                }
            }
            params.put("ids", ids);
            Page<RepstQuestions> page = new Page<>(1, 100);
            repstQuestionsService.queryDetailPage(params, page);
            List<RepstQuestions> questionsList = page.getList();

            // 试题id、试题间的映射
            Map<String, RepstQuestions> questionsMap = Maps.newHashMap();
            for (RepstQuestions repstQuestions : questionsList) {
                questionsMap.put(repstQuestions.getId(), repstQuestions);
            }
            // 将 详细信息 赋值给 问卷
            for (RepstQuestionsCtgr repstQuestionsCtgr : questionsCtgrList) {
                repstQuestionsCtgr = questionsCtgrMap.get(repstQuestionsCtgr.getId());
                List<RepstQuestions> repstQuestionsList = repstQuestionsCtgr.getRepstQuestionsList();
                for (int i = 0; i < repstQuestionsList.size(); i++) {
                    String questionId = repstQuestionsList.get(i).getId();
                    repstQuestionsList.set(i, questionsMap.get(questionId));
                }
            }
            repstQuestionnaire.setRepstQuestionsCtgrList(questionsCtgrList);
        }
        return repstQuestionnaire;
    }

}
