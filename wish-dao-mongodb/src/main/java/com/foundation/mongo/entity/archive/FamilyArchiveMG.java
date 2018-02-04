package com.foundation.mongo.entity.archive;

import com.foundation.common.date.DateUtils;
import com.foundation.mongo.entity.record.FamilyConcIndicator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: FamilyArchive
 * @Description: 家庭档案实体
 * @date 2016/10/28 10:43
 */
@Document(collection="familyArchive")
public class FamilyArchiveMG implements Serializable{
    protected static final long serialVersionUID = 1L;

    @Transient
    private Log logger = LogFactory.getLog(FamilyArchiveMG.class);

    public FamilyArchiveMG() {
        Date date = new Date();
        this.updateTime = date;
        this.dateStr = DateUtils.formatDate(date);
        this.source = "baseSource";
        this.event = "baseEvent";
        this.status = 1;
    }

    /** _id*/
    @Id
    private String id;
    /** 家庭档案Id(mysql id)*/
    private String fId;
    /** 妻子证件号*/
    private String wifeCertNum;
    /** 妻子名称*/
    private String wifeName;
    /** 丈夫名称*/
    private String husbandCertNum;
    /** 丈夫证件号*/
    private String husbandName;
    /**
     * 结论性指标记录,查询用
     */
    private Map<String, FamilyConcIndicator> familyConcIndicator;
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

    /**
     * 档案状态 默认1：有效，0：无效，后期其他状态待定（如死后）
     */
    private Integer status;

    public boolean validate() {
        if (StringUtils.isEmpty(fId)) {
            logger.error("入参不正确(星号字段为必填)：" + this.toString());
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

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, FamilyConcIndicator> getFamilyConcIndicator() {
        return familyConcIndicator;
    }

    public void setFamilyConcIndicator(Map<String, FamilyConcIndicator> familyConcIndicator) {
        this.familyConcIndicator = familyConcIndicator;
    }

    @Override
    public String toString() {
        return "FamilyArchive{" +
                "id='" + id + '\'' +
                ", ***fId='" + fId + '\'' +
                ", wifeCertNum='" + wifeCertNum + '\'' +
                ", wifeName='" + wifeName + '\'' +
                ", husbandCertNum='" + husbandCertNum + '\'' +
                ", husbandName='" + husbandName + '\'' +
                ", familyConcIndicator='" + familyConcIndicator + '\'' +
                ", source='" + source + '\'' +
                ", event='" + event + '\'' +
                ", updateTime=" + updateTime +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}
