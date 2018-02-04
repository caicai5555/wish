package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RepstQuestionnaire
 * @Description: 问卷实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionnaire extends DataEntity<RepstQuestionnaire> {
    private static final long serialVersionUID = 1L;

    /** 问卷名称 */
    private String questionnaireName;

    /** 问卷类别 */
    private String questionnaireCtgr;

    /** 是否显示试题结论0，1显示，2不显示 */
    private String isShowFlag;

    /** 创建人 */
    private String sysUserId;

    /** 后台计算类名 */
    private String backComputeClass;

    /** 结果页面地址 */
    private String resultUrl;

    /** 问卷描述 */
    private String remark;

    /** 排序号 */
    private String orderId;

    /** 问卷图片url */
    private String questionnaireUrl;

    /** 0都可以答，1用户答，2医师回答 */
    private String whoAnswer;

    /** 评估结论id */
    private String conclusionId;

    /** 问题分类 */
    private List<RepstQuestionsCtgr> repstQuestionsCtgrList = new ArrayList<>();

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getQuestionnaireCtgr() {
        return questionnaireCtgr;
    }

    public void setQuestionnaireCtgr(String questionnaireCtgr) {
        this.questionnaireCtgr = questionnaireCtgr;
    }

    public String getIsShowFlag() {
        return isShowFlag;
    }

    public void setIsShowFlag(String isShowFlag) {
        this.isShowFlag = isShowFlag;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getBackComputeClass() {
        return backComputeClass;
    }

    public void setBackComputeClass(String backComputeClass) {
        this.backComputeClass = backComputeClass;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQuestionnaireUrl() {
        return questionnaireUrl;
    }

    public void setQuestionnaireUrl(String questionnaireUrl) {
        this.questionnaireUrl = questionnaireUrl;
    }

    public String getWhoAnswer() {
        return whoAnswer;
    }

    public void setWhoAnswer(String whoAnswer) {
        this.whoAnswer = whoAnswer;
    }

    public String getConclusionId() {
        return conclusionId;
    }

    public void setConclusionId(String conclusionId) {
        this.conclusionId = conclusionId;
    }

    public List<RepstQuestionsCtgr> getRepstQuestionsCtgrList() {
        return repstQuestionsCtgrList;
    }

    public void setRepstQuestionsCtgrList(List<RepstQuestionsCtgr> repstQuestionsCtgrList) {
        this.repstQuestionsCtgrList = repstQuestionsCtgrList;
    }
}