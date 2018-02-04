package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstQuestionnaireCtgrRel
 * @Description: 问卷与试题分类关系实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionnaireCtgrRel extends DataEntity<RepstQuestionnaireCtgrRel> {
    private static final long serialVersionUID = 1L;

    /** 问卷ID */
    private String questionnaireId;

    /** 试题分类 */
    private String questionCtgr;

    /** 是否显示 */
    private int isShowFlag;

    /** 排序号 */
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionCtgr() {
        return questionCtgr;
    }

    public void setQuestionCtgr(String questionCtgr) {
        this.questionCtgr = questionCtgr;
    }

    public int getIsShowFlag() {
        return isShowFlag;
    }

    public void setIsShowFlag(int isShowFlag) {
        this.isShowFlag = isShowFlag;
    }
}