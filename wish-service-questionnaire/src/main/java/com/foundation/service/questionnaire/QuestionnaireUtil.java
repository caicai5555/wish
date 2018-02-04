package com.foundation.service.questionnaire;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.*;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionnaireUtil
 * @Description: 问卷的工具类
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class QuestionnaireUtil {
    /**
     * @Title: saveQuestionnaireByExcel
     * @Description: 保存问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param is 参数
     * @Param repstQuestionnaireBiz 参数
     * @Return String 返回类型 主键
     * @Throws exception
     */
    public static String saveQuestionnaireByExcel(InputStream is, IRepstQuestionnaireBiz repstQuestionnaireBiz) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        // sheet 1 : 问卷
        HSSFSheet sheetQuestionnaire = workbook.getSheetAt(0);
        List<RepstQuestionnaire> questionnaireList = Lists.newArrayList();
        for (int i = 1; i <= sheetQuestionnaire.getLastRowNum(); i++) {
            HSSFRow row = sheetQuestionnaire.getRow(i);
            RepstQuestionnaire questionnaire = new RepstQuestionnaire();
            questionnaire.setId(row.getCell(0).toString());
            questionnaire.setQuestionnaireName(row.getCell(1).getStringCellValue());
            questionnaireList.add(questionnaire);
        }
        // sheet 2 : 问卷 试题分类 中间表
        HSSFSheet sheetQuestionnaireCategory = workbook.getSheetAt(1);
        List<RepstQuestionnaireCtgrRel> questionniareCategoryList = Lists.newArrayList();
        for (int i = 1; i <= sheetQuestionnaireCategory.getLastRowNum(); i++) {
            HSSFRow row = sheetQuestionnaireCategory.getRow(i);
            RepstQuestionnaireCtgrRel entity = new RepstQuestionnaireCtgrRel();
            entity.setId(row.getCell(0).toString());
            entity.setQuestionnaireId(row.getCell(1).toString());
            entity.setQuestionCtgr(row.getCell(2).toString());
            entity.setIsShowFlag(((int) row.getCell(3).getNumericCellValue()));
            entity.setOrderId(((long) row.getCell(4).getNumericCellValue()));
            questionniareCategoryList.add(entity);
        }
        Map<String, List<String>> questionnaireCategoryMap = Maps.newHashMap(); // 问卷id 、试题分类id 的映射
        for (RepstQuestionnaireCtgrRel questionnaireCtgrRel : questionniareCategoryList) {
            String key = questionnaireCtgrRel.getQuestionnaireId();
            List<String> value = questionnaireCategoryMap.get(key);
            if (value ==null ){
                value = Lists.newArrayList();
            }
            value.add(questionnaireCtgrRel.getQuestionCtgr());
            questionnaireCategoryMap.put(key, value);
        }

        // sheet 3 : 试题分类
        Map<String, RepstQuestionsCtgr> questionCategoryMap = Maps.newHashMap(); // 试题分类id，试题的映射
        HSSFSheet sheetQuestionCategory = workbook.getSheetAt(2);
        List<RepstQuestionsCtgr> questionCategoryList = Lists.newArrayList();
        for (int i = 1; i <= sheetQuestionCategory.getLastRowNum(); i++) {
            HSSFRow row = sheetQuestionCategory.getRow(i);
            RepstQuestionsCtgr entity = new RepstQuestionsCtgr();
            entity.setId(row.getCell(0).toString());
            entity.setQuestionCtgrName(row.getCell(1).getStringCellValue());
//            entity.setParentQuestionCtgr(row.getCell(2).getStringCellValue());
            entity.setOrderId(((int) row.getCell(3).getNumericCellValue()));
            questionCategoryList.add(entity);
            questionCategoryMap.put(entity.getId(), entity);
        }

        // sheet 4 : 试题分类 试题中间表
        HSSFSheet sheetCategoryQuestionn = workbook.getSheetAt(3);
        List<RepstQuestionCtgrRel> questionCategoryRelList = Lists.newArrayList();
        for (int i = 1; i <= sheetCategoryQuestionn.getLastRowNum(); i++) {
            HSSFRow row = sheetCategoryQuestionn.getRow(i);
            RepstQuestionCtgrRel entity = new RepstQuestionCtgrRel();
            entity.setId(row.getCell(0).toString());
            entity.setQuestionCtgr(row.getCell(1).toString());
            entity.setQuestionId(row.getCell(2).toString());
            entity.setOrderId(((int) row.getCell(3).getNumericCellValue()));
            questionCategoryRelList.add(entity);
        }
        Map<String, List<String>> questionnaireQuestionMap = Maps.newHashMap(); // 试题分类id 、试题Id 的映射
        for (RepstQuestionCtgrRel questionCtgrRel : questionCategoryRelList) {
            String key = questionCtgrRel.getQuestionCtgr();
            List<String> value = questionnaireQuestionMap.get(key);
            if (value == null) {
                value = Lists.newArrayList();
            }
            value.add(questionCtgrRel.getQuestionId());
            questionnaireQuestionMap.put(key, value);
        }

        // sheet 5 : 试题
        Map<String, RepstQuestions> questionMap = Maps.newHashMap();    //  试题id， 试题的映射
        HSSFSheet sheetQuestion= workbook.getSheetAt(4);
        List<RepstQuestions> questionList = Lists.newArrayList();
        for (int i = 1; i <= sheetQuestion.getLastRowNum(); i++) {
            HSSFRow row = sheetQuestion.getRow(i);
            RepstQuestions entity = new RepstQuestions();
            entity.setId(row.getCell(0).toString());
            entity.setQuestionName(row.getCell(1).getStringCellValue());
//            entity.setParentQuestionId(row.getCell(2).getStringCellValue());
            entity.setQuestionType(Integer.parseInt(row.getCell(4).getStringCellValue()));
            entity.setOrderId((int)row.getCell(8).getNumericCellValue());
            entity.setqRequired((int)row.getCell(11).getNumericCellValue() + "");
            questionList.add(entity);
            questionMap.put(entity.getId(), entity);
        }

        // sheet 6 : 试题项
        Map<String, List<RepstQuestionItem>> questionItemMap = Maps.newHashMap(); // 试题、试题项 的映射
        HSSFSheet sheetQuestionItem = workbook.getSheetAt(5);
        List<RepstQuestionItem> questionItemList = Lists.newArrayList();
        for (int i = 1; i <= sheetQuestionItem.getLastRowNum(); i++) {
            HSSFRow row = sheetQuestionItem.getRow(i);
            RepstQuestionItem entity = new RepstQuestionItem();
            entity.setId(row.getCell(0).toString());
            entity.setQuestionId(row.getCell(2).toString());
            entity.setItemType((int)row.getCell(3).getNumericCellValue() + "");
            entity.setItemName( row.getCell(4).toString());
            entity.setItemScore( row.getCell(6).toString());
            entity.setOrderId((int) row.getCell(8).getNumericCellValue());
            questionItemList.add(entity);
            List<RepstQuestionItem> repstQuestionItems = questionItemMap.get(entity.getQuestionId());
            if (repstQuestionItems == null) {
                repstQuestionItems = Lists.newArrayList();
            }
            repstQuestionItems.add(entity);
            questionItemMap.put(entity.getQuestionId(), repstQuestionItems);
        }
        //组成问卷
        RepstQuestionnaire questionnaire = questionnaireList.get(0);
        String id = questionnaire.getId();
        List<RepstQuestionsCtgr> repstQuestionsCtgrList = questionnaire.getRepstQuestionsCtgrList();
        List<String> ctgrIdList = questionnaireCategoryMap.get(id); // id问卷下的 试题分类Id
        for (String ctgrId : ctgrIdList) {
            repstQuestionsCtgrList.add(questionCategoryMap.get(ctgrId));
        }
        for (RepstQuestionsCtgr questionsCtgr : repstQuestionsCtgrList) {
            List<RepstQuestions> questionLists = Lists.newArrayList();
            List<String> questionIdList = questionnaireQuestionMap.get(questionsCtgr.getId());
            for (String questionId : questionIdList) {
                RepstQuestions questions = questionMap.get(questionId);
                questions.setRepstQuestionItemList(questionItemMap.get(questionId));
                questionLists.add(questions);
            }
            questionsCtgr.setRepstQuestionsList(questionLists);
        }
        // 更新 问卷、试题分类、试题、试题项 的id
        questionnaire.setId(IdGen.uuid());
        for (RepstQuestionsCtgr questionsCtgr : questionnaire.getRepstQuestionsCtgrList()) {
            questionsCtgr.setId(IdGen.uuid());
            for (RepstQuestions questions : questionsCtgr.getRepstQuestionsList()) {
                String questionId = IdGen.uuid();
                questions.setId(questionId);
                for (RepstQuestionItem item : questions.getRepstQuestionItemList()) {
                    item.setId(IdGen.uuid());
                    item.setQuestionId(questionId);
                }
            }
        }
        questionnaire.setCreateDate(new Date());
        repstQuestionnaireBiz.save(questionnaire);
        return questionnaire.getId();
    }
}
