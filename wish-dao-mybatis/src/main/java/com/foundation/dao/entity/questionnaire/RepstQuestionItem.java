package com.foundation.dao.entity.questionnaire;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: RepstQuestionItem
 * @Description: 试题项实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class RepstQuestionItem extends DataEntity<RepstQuestionItem> {
    private static final long serialVersionUID = 1L;

    /** 上级试题项ID */
    private String parentItemId;

    /** 试题ID */
    private String questionId;

    /** 试题项类型 */
    private String itemType;

    /** 试题项名称 */
    private String itemName;

    /** 试题项描述 */
    private String itemDesc;

    /** 试题项得分 */
    private String itemScore;

    /** 是否计分 */
    private int isComputeScore;

    /** 排序号 */
    private int orderId;

    /** 一级item 是否有 子item 需要被控制 能否使用
     * 注： 当此 item 为 问题下 一级item时才可以设置此字段值为1 或 不设置为空)
     */
    private String itemLimited;

    /** 控制此子item 不能使用 的父 item 的 id
     * (注： 当此 item 为 一级item 下的 子item 时才可以设置此字段值为控制它的 item id 多个以","分隔
     * 或 不设置为空)
     * */
    private String itemIdDis;

    /** 控制此子item 可以使用 的父 item 的 id
     * (注： 当此 item 为 一级item 下的 子item 时才可以设置此字段值为控制它的 item id 多个以","分隔
     * 或 不设置为空)
     * */
    private String eItemIds;

    /** 一级item 是否有 问题 需要被控制 是否可答
     * (注： 当此 item 为 一级item 当它需要控制其它问题是否可答 时才可以设置此字段值为1 或 不设置为空)
     * */
    private String qLimited;

    /** 一级item 是否有 问题 需要被控制 不可以答
     * (注： 当此 item 为 一级item 当它需要控制其它问题不可答 时 才可以设置此字段值为它控制的 问题 id 多个以","分隔  或 不设置为空)
     * */
    private String qIds;

    /** 一级item 是否有 问题 需要被控制 可以答
     * (注： 当此 item 为 一级item 当它需要控制其它问题可答 时 才可以设置此字段值为它控制的 问题 id 多个以","分隔  或 不设置为空)
     * */
    private String qEnable;

    /** 当此item为 输入框 input 且在需要 页面做 值效验 时 才有此值 与表 repst_page_operation 对应 */
    private String operationId;

    /** 空是不控制0隐藏提1是展示题 */
    private String isControlQuest;

    /** item的属性标签 0 性别; 1 姓名; 2 出生年月; 3联系电话; 4 主要事项; 5 病史 */
    private String mark;

    /** 类型编码 (身高/体重/指标类型等) */
    private String typeEncoding;

    /** 档案类型 (指标1/基本信息2) */
    private String archiveType;

    /** 用户填写的问题试题项记录 */
    private RepstUserQuestion repstUserQuestion;

    public String getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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

    public int getIsComputeScore() {
        return isComputeScore;
    }

    public void setIsComputeScore(int isComputeScore) {
        this.isComputeScore = isComputeScore;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemLimited() {
        return itemLimited;
    }

    public void setItemLimited(String itemLimited) {
        this.itemLimited = itemLimited;
    }

    public String getItemIdDis() {
        return itemIdDis;
    }

    public void setItemIdDis(String itemIdDis) {
        this.itemIdDis = itemIdDis;
    }

    public String getqLimited() {
        return qLimited;
    }

    public void setqLimited(String qLimited) {
        this.qLimited = qLimited;
    }

    public String getqIds() {
        return qIds;
    }

    public void setqIds(String qIds) {
        this.qIds = qIds;
    }

    public String getqEnable() {
        return qEnable;
    }

    public void setqEnable(String qEnable) {
        this.qEnable = qEnable;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getIsControlQuest() {
        return isControlQuest;
    }

    public void setIsControlQuest(String isControlQuest) {
        this.isControlQuest = isControlQuest;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public RepstUserQuestion getRepstUserQuestion() {
        return repstUserQuestion;
    }

    public void setRepstUserQuestion(RepstUserQuestion repstUserQuestion) {
        this.repstUserQuestion = repstUserQuestion;
    }

    public String geteItemIds() {
        return eItemIds;
    }

    public void seteItemIds(String eItemIds) {
        this.eItemIds = eItemIds;
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