package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RepstQuestions
 * @Description: 试题实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestions extends DataEntity<RepstQuestions> {
    private static final long serialVersionUID = 1L;

    /** 试题名称 */
    private String questionName;

    /** 上级试题ID */
    private String parentQuestionId;

    /** 问卷ID */
    private String questionnaireId;

    /** 试题类型 */
    private int questionType;

    /** 适用性别 */
    private String suitSex;

    /** 填写类型 */
    private String fillType;

    /** 创建人 */
    private String sysUserId;

    /** 排序号 */
    private int orderId;

    /** 是否必答题 当为 1：必答 */
    private String qRequired;

    /** 空显示,1不显示禁用' */
    private String showState;

    /** 问题项 */
    private List<RepstQuestionItem> repstQuestionItemList = new ArrayList<>();

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getParentQuestionId() {
        return parentQuestionId;
    }

    public void setParentQuestionId(String parentQuestionId) {
        this.parentQuestionId = parentQuestionId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getSuitSex() {
        return suitSex;
    }

    public void setSuitSex(String suitSex) {
        this.suitSex = suitSex;
    }

    public String getFillType() {
        return fillType;
    }

    public void setFillType(String fillType) {
        this.fillType = fillType;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getqRequired() {
        return qRequired;
    }

    public void setqRequired(String qRequired) {
        this.qRequired = qRequired;
    }

    public String getShowState() {
        return showState;
    }

    public void setShowState(String showState) {
        this.showState = showState;
    }

    public List<RepstQuestionItem> getRepstQuestionItemList() {
        return repstQuestionItemList;
    }

    public void setRepstQuestionItemList(List<RepstQuestionItem> repstQuestionItemList) {
        this.repstQuestionItemList = repstQuestionItemList;
    }
}