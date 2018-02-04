package com.foundation.mongo.entity.record;

import com.foundation.common.date.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangsen
 * @ClassName: ConcIndicator
 * @Description: 家庭结论性指标记录实体
 * @date 2016/10/19 9:26
 */
@Document(collection="concIndicator")
public class FamilyConcIndicator implements Serializable{
    private static final long serialVersionUID = 1L;

    @Transient
    private Log logger = LogFactory.getLog(FamilyConcIndicator.class);

    public FamilyConcIndicator() {
        Date date = new Date();
        this.updateTime = date;
        this.dateStr = DateUtils.formatDate(date);
        this.source = "baseSource";
        this.event = "baseEvent";
    }

    /** _id*/
    @Id
    private String id;
    /** 编号*/
    private String code;
    /** 家庭档案Id(mysql id)*/
    private String fId;
    /** 妻子证件号*/
    @Transient
    private String wifeCertNum;
    /** 妻子名称*/
    @Transient
    private String wifeName;
    /** 丈夫名称*/
    @Transient
    private String husbandCertNum;
    /** 丈夫证件号*/
    @Transient
    private String husbandName;
    /** 来源描述*/
    private String source;
    /** 事件源*/
    private String event;
    /** 更新时间*/
    private Date updateTime;
    /** 事务*/
/*    private Integer transaction;*/
    /** 日期字符串*/
    private String dateStr;

    /** 结论指标项*/
    private List<ConcIndicatorItem> concIndicatorItems = new ArrayList<>();

    public boolean validate() {
        if(StringUtils.isEmpty(fId)){
            logger.error("入参不正确(星号字段为必填)："+ this.toString());
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getWifeCertNum() {
        return wifeCertNum;
    }

    public void setWifeCertNum(String wifeCertNum) {
        this.wifeCertNum = wifeCertNum;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public String getHusbandCertNum() {
        return husbandCertNum;
    }

    public void setHusbandCertNum(String husbandCertNum) {
        this.husbandCertNum = husbandCertNum;
    }

    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
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

    public List<ConcIndicatorItem> getConcIndicatorItems() {
        return concIndicatorItems;
    }

    public void setConcIndicatorItems(List<ConcIndicatorItem> concIndicatorItems) {
        this.concIndicatorItems = concIndicatorItems;
    }

    @Override
    public String toString() {
        return "FamilyConcIndicator{" +
                "id='" + id + '\'' +
                ", ***fId='" + fId + '\'' +
                ", ***code='" + code + '\'' +
                ", wifeCertNum='" + wifeCertNum + '\'' +
                ", wifeName='" + wifeName + '\'' +
                ", husbandCertNum='" + husbandCertNum + '\'' +
                ", husbandName='" + husbandName + '\'' +
                ", source='" + source + '\'' +
                ", event='" + event + '\'' +
                ", updateTime=" + updateTime +
                ", dateStr='" + dateStr + '\'' +
                ", concIndicatorItems=" + concIndicatorItems +
                '}';
    }
}
