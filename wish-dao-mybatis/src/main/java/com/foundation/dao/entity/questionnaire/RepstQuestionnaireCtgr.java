package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstQuestionnaireCtgr
 * @Description: 问卷分类实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionnaireCtgr extends DataEntity<RepstQuestionnaireCtgr> {
    private static final long serialVersionUID = 1L;

    /** 问卷类别 */
    private String questionnaireCtgr;

    /** 类别名称 */
    private String questionnaireCtgrName;

    /** 创建人 */
    private String sysUserId;

    /** 备注 */
    private String remark;

    /** 排序号 */
    private long orderId;

    public String getQuestionnaireCtgr() {
        return questionnaireCtgr;
    }

    public void setQuestionnaireCtgr(String questionnaireCtgr) {
        this.questionnaireCtgr = questionnaireCtgr;
    }

    public String getQuestionnaireCtgrName() {
        return questionnaireCtgrName;
    }

    public void setQuestionnaireCtgrName(String questionnaireCtgrName) {
        this.questionnaireCtgrName = questionnaireCtgrName;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}