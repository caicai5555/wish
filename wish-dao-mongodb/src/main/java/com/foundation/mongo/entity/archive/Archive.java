package com.foundation.mongo.entity.archive;

import com.foundation.common.date.DateUtils;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.mongo.entity.record.ConcIndicator;
import com.foundation.mongo.entity.record.DescIndicator;
import com.foundation.mongo.entity.record.Indicator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: Archive
 * @Description: 档案记录实体（mongo）
 * @date 2016/10/19 9:26
 */
@Document
public class Archive implements Serializable {
    protected static final long serialVersionUID = 1L;

    @Transient
    private Log logger = LogFactory.getLog(Archive.class);

    public Archive() {
        Date date = new Date();
        this.updateTime = date;
        this.dateStr = DateUtils.formatDate(date);
        this.source = "baseSource";
        this.event = "baseEvent";
        this.status = 1;
    }

    /** 用户编号*/
    private String userId;
    /** 用户证件*/
    protected String certNum;
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
    /**
     * 档案状态 默认1：有效，0：无效，后期其他状态待定（如死后）
     */
    private Integer status;

    //-------------------------------------
    /**
     * 指标记录,查询用
     */
    private Map<String, Indicator> indicator;
    /**
     * 描述性指标记录,查询用
     */

    private Map<String, DescIndicator> descIndicator;
    /**
     * 结论性指标记录,查询用
     */
    private Map<String, ConcIndicator> concIndicator;

    //--------------------------------
    /**
     * 插入更新时用
     */
    @Transient
    private Map<String, BaseRecordEntity> dynamicValue;

    /**
     * @description 参数校验
     * @return
     */
    public boolean validate() {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(certNum)) {
            logger.error("入参不正确(星号字段为必填)：" + this.toString());
            return false;
        }
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Indicator> getIndicator() {
        return indicator;
    }

    public void setIndicator(Map<String, Indicator> indicator) {
        this.indicator = indicator;
    }

    public Map<String, DescIndicator> getDescIndicator() {
        return descIndicator;
    }

    public void setDescIndicator(Map<String, DescIndicator> descIndicator) {
        this.descIndicator = descIndicator;
    }

    public Map<String, ConcIndicator> getConcIndicator() {
        return concIndicator;
    }

    public void setConcIndicator(Map<String, ConcIndicator> concIndicator) {
        this.concIndicator = concIndicator;
    }

    public Map<String, BaseRecordEntity> getDynamicValue() {
        return dynamicValue;
    }

    public void setDynamicValue(Map<String, BaseRecordEntity> dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public String toString() {
        return "Archive{" +
                " ***userId='" + userId + '\'' +
                ", ***certNum='" + certNum + '\'' +
                ", source='" + source + '\'' +
                ", event='" + event + '\'' +
                ", updateTime=" + updateTime +
                ", dateStr='" + dateStr + '\'' +
                ", status=" + status +
                ", indicator=" + indicator +
                ", descIndicator=" + descIndicator +
                ", concIndicator=" + concIndicator +
                ", dynamicValue=" + dynamicValue +
                '}';
    }
}
