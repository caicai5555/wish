package com.foundation.mongo.entity.record;

import com.foundation.common.date.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangsen
 * @ClassName: BaseRecordEntity
 * @Description: 记录基础类（mongo）
 * @date 2016/10/19 10:47
 */
@Document
public abstract class BaseRecordEntity implements Serializable{
    protected static final long serialVersionUID = 1L;

    public BaseRecordEntity() {
            Date date = new Date();
            this.updateTime = date;
            this.dateStr = DateUtils.formatDate(date);
            this.source = "baseSource";
            this.event = "baseEvent";
    }

    /** _id */
    @Id
    protected String id;
    /** 用户Id*/
    protected String userId;
    /** 用户证件*/
    protected String certNum;
    /** 编码*/
    protected String code;
    /** 来源描述*/
    protected String source;
    /** 事件源*/
    protected String event;
    /** 更新时间*/
    protected Date updateTime;
    /** 事务*/
/*    private Integer transaction;*/
    /** 日期字符串*/
    protected String dateStr;

    /** 验证字段合法性 */
    public abstract boolean validate();



    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }
}
