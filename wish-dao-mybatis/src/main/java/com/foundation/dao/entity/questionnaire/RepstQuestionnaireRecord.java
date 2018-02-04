package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: RepstQuestionnaireRecord
 * @Description: 问卷推送记录实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionnaireRecord extends DataEntity<RepstQuestionnaireRecord> {
    private static final long serialVersionUID = 1L;

    /** 问卷ID */
    private String questionnaireId;

    /** 订单服务项目主键ID */
    private String orderItemId;

    /** 推送人ID */
    private String sysUserId;

    /** 接收人ID */
    private String userId;

    /** 状态：2代表评估，1代表问卷 */
    private String status;

    /** 排序号 */
    private String orderId;

    /** 答题结果 */
    private String respResult;

    /** 答题完成时间 */
    private Date respTime;

    /** 评论 */
    private String evaluation;

    /** 答卷开始时间 */
    private Date answerStartTime;

    /** 子订单记录主键 */
    private String serviceUseRecordId;

    /** 助手表id */
    private String assistantRecordId;

    /** 答题记录 */
    private List<RepstUserQuestion> repstUserQuestionList = new ArrayList<>();

    /** 问卷 */
    private RepstQuestionnaire repstQuestionnaire;

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRespResult() {
        return respResult;
    }

    public void setRespResult(String respResult) {
        this.respResult = respResult;
    }

    public Date getRespTime() {
        return respTime;
    }

    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getAnswerStartTime() {
        return answerStartTime;
    }

    public void setAnswerStartTime(Date answerStartTime) {
        this.answerStartTime = answerStartTime;
    }

    public String getServiceUseRecordId() {
        return serviceUseRecordId;
    }

    public void setServiceUseRecordId(String serviceUseRecordId) {
        this.serviceUseRecordId = serviceUseRecordId;
    }

    public String getAssistantRecordId() {
        return assistantRecordId;
    }

    public void setAssistantRecordId(String assistantRecordId) {
        this.assistantRecordId = assistantRecordId;
    }

    public List<RepstUserQuestion> getRepstUserQuestionList() {
        return repstUserQuestionList;
    }

    public void setRepstUserQuestionList(List<RepstUserQuestion> repstUserQuestionList) {
        this.repstUserQuestionList = repstUserQuestionList;
    }

    public RepstQuestionnaire getRepstQuestionnaire() {
        return repstQuestionnaire;
    }

    public void setRepstQuestionnaire(RepstQuestionnaire repstQuestionnaire) {
        this.repstQuestionnaire = repstQuestionnaire;
    }
}