package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstUserQuestion
 * @Description: 用户答题实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstUserQuestion extends DataEntity<RepstUserQuestion> {
    private static final long serialVersionUID = 1L;

    /** 推送记录ID */
    private String recordId;

    /** 用户ID */
    private String userId;

    /** 问卷ID */
    private String questionnaireId;

    /** 试题ID */
    private String questionId;

    /** 适用性别 */
    private String suitSex;

    /** 填写类型 */
    private String fillType;

    /** 试题项ID */
    private String itemId;

    /** 试题项类型 */
    private String itemType;

    /** 试题项名称 */
    private String itemName;

    /** 试题项描述 */
    private String itemDesc;

    /** 试题项得分 */
    private String itemScore;

    /** 答题结果 */
    private String result;

    /** 排序号 */
    private int orderId;

    /** 用来控制属性的标示，目前只有0代表性别  */
    private String mark;

    /** 类型编码 (身高/体重/指标类型等) */
    private String typeEncoding;

    /** 档案类型 (指标1/基本信息2) */
    private String archiveType;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemScore() {
        return itemScore;
    }

    public void setItemScore(String itemScore) {
        this.itemScore = itemScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTypeEncoding() {
        return typeEncoding;
    }

    public void setTypeEncoding(String typeEncoding) {
        this.typeEncoding = typeEncoding;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }
}