package com.foundation.mongo.entity.record;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsen
 * @ClassName: ConcIndicator
 * @Description: 结论性指标记录实体（mongo）
 * @date 2016/10/19 9:26
 */
@Document
public class ConcIndicator  extends BaseRecordEntity{
    @Transient
    private Log logger = LogFactory.getLog(ConcIndicator.class);

    /** 用户Id */
/*    private String userId;*/
    /** 结论性指标名称*/
    private String name;
    /** 结论性指标编码*/
/*    private String code;*/
    /** 结论 */
    private String value;

    /** 结论指标项*/
    private List<ConcIndicatorItem> concIndicatorItems = new ArrayList<>();

    @Override
    public boolean validate() {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(certNum)){
            logger.error("入参不正确(星号字段为必填)："+ this.toString());
            return false;
        }
        return true;
    }

/*    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }*/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ConcIndicatorItem> getConcIndicatorItems() {
        return concIndicatorItems;
    }

    public void setConcIndicatorItems(List<ConcIndicatorItem> concIndicatorItems) {
        this.concIndicatorItems = concIndicatorItems;
    }

    @Override
    public String toString() {
        return "ConcIndicator{" +
                "***userId='" + userId + '\'' +
                ",***certNum='" + certNum + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
