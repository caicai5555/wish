package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstQuestionCtgrRel
 * @Description: 试题与试题分类关系实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionCtgrRel extends DataEntity<RepstQuestionCtgrRel> {
    private static final long serialVersionUID = 1L;

    /** 试题分类 */
    private String questionCtgr;

    /** 试题ID */
    private String questionId;

    /** 排序号 */
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getQuestionCtgr() {
        return questionCtgr;
    }

    public void setQuestionCtgr(String questionCtgr) {
        this.questionCtgr = questionCtgr;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}