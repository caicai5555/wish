package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstPageOperation
 * @Description: js 页面验证 规则 实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstPageOperation extends DataEntity<RepstPageOperation> {
    private static final long serialVersionUID = 1L;

    /** jquery js事件 */
    private String operationType;

    /** 验证js正则表达式 */
    private String valiRule;

    /** 验证通过提示内容 */
    private String valiSuccess;

    /** 验证失败提示内容 */
    private String valiFail;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getValiRule() {
        return valiRule;
    }

    public void setValiRule(String valiRule) {
        this.valiRule = valiRule;
    }

    public String getValiSuccess() {
        return valiSuccess;
    }

    public void setValiSuccess(String valiSuccess) {
        this.valiSuccess = valiSuccess;
    }

    public String getValiFail() {
        return valiFail;
    }

    public void setValiFail(String valiFail) {
        this.valiFail = valiFail;
    }

}