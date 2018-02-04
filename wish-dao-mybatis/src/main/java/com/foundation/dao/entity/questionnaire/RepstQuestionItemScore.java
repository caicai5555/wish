package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstQuestionItemScore
 * @Description: 问题项计算复杂分数实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionItemScore extends DataEntity<RepstQuestionItemScore> {
    private static final long serialVersionUID = 1L;

    /** 问题项ID */
    private String itemId;

    /** 得分条件 */
    private String scoreCondition;

    /** 得分描述 */
    private String scoreDesc;

    /** 对应分数 */
    private String score;

    /** 排序号 */
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getScoreCondition() {
        return scoreCondition;
    }

    public void setScoreCondition(String scoreCondition) {
        this.scoreCondition = scoreCondition;
    }

    public String getScoreDesc() {
        return scoreDesc;
    }

    public void setScoreDesc(String scoreDesc) {
        this.scoreDesc = scoreDesc;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}