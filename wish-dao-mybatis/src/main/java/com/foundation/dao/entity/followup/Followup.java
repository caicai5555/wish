package com.foundation.dao.entity.followup;

import com.foundation.dao.entity.sys.User;
import com.foundation.dao.util.DataEntity;

import java.util.Date;

/**
 * @ClassName: Followup
 * @Description: 订单详情 实体
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public class Followup extends DataEntity<Followup> {
    private static final long serialVersionUID = 1L;

    /** 随访计划 (服务名称) */
    private String name;

    /** 服务次数 */
    private long times;

    /**  问卷模板 */
    private String questId;

    /**  模板名称 */
    private String questName;

    /**  机构ID */
    private String orgId;

    /**  所属机构 */
    private String orgName;

    /**  随访间隔 */
    private int intervals;

    /**  类型，0定期，1临时 */
    private int type;

    /**  已使用次数 */
    private long used;

    /**  状态，0未启动，1进行中，2已完成3废弃 */
    private int status;

    /**  启动时间 */
    private Date startDate;

    /**  启动人 */
    private User startBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getStartBy() {
        return startBy;
    }

    public void setStartBy(User startBy) {
        this.startBy = startBy;
    }
}