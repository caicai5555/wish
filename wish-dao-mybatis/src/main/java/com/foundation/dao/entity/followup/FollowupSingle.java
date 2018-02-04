package com.foundation.dao.entity.followup;

import com.foundation.dao.entity.sys.User;
import com.foundation.dao.util.DataEntity;

import java.util.Date;

/**
 * @ClassName: FollowupSingle
 * @Description: 单次随访表 实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class FollowupSingle extends DataEntity<FollowupSingle> {
    private static final long serialVersionUID = 1L;

    /** 订单详情Id */
    private String followupId;

    /** 问卷模板Id */
    private String questId;

    /** 随访记录Id */
    private String recordId;

    /** 问卷模板名称 */
    private String questName;

    /** 随访计划 (服务名称) */
    private String name;

    /** 随访类型，0定期，1临时*/
    private String type;

    /**  单次随访状态 */
    private String status;

    /** 随访时间 */
    private Date followupDate;

    /**  完成时间 */
    private Date completeDate;

    /**  完成人 */
    private User completeBy;

    public String getFollowupId() {
        return followupId;
    }

    public void setFollowupId(String followupId) {
        this.followupId = followupId;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(Date followupDate) {
        this.followupDate = followupDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public User getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(User completeBy) {
        this.completeBy = completeBy;
    }
}