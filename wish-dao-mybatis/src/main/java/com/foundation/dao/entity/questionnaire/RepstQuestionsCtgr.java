package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RepstQuestionsCtgr
 * @Description: 问卷试题分类实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionsCtgr extends DataEntity<RepstQuestionsCtgr> {
    private static final long serialVersionUID = 1L;

    /** 试题分类名称 */
    private String questionCtgrName;

    /** 上级试题分类 */
    private String parentQuestionCtgr;

    /** 排序号 */
    private long orderId;

    /** 试题 */
    private List<RepstQuestions> repstQuestionsList = new ArrayList<>();

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getQuestionCtgrName() {
        return questionCtgrName;
    }

    public void setQuestionCtgrName(String questionCtgrName) {
        this.questionCtgrName = questionCtgrName;
    }

    public String getParentQuestionCtgr() {
        return parentQuestionCtgr;
    }

    public void setParentQuestionCtgr(String parentQuestionCtgr) {
        this.parentQuestionCtgr = parentQuestionCtgr;
    }

    public List<RepstQuestions> getRepstQuestionsList() {
        return repstQuestionsList;
    }

    public void setRepstQuestionsList(List<RepstQuestions> repstQuestionsList) {
        this.repstQuestionsList = repstQuestionsList;
    }
}