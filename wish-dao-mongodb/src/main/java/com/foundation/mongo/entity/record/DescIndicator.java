package com.foundation.mongo.entity.record;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author wangsen
 * @ClassName: DescIndicator
 * @Description: 描述性指标记录实体（mongo）
 * @date 2016/10/19 9:25
 */
@Document
public class DescIndicator extends BaseRecordEntity{
    @Transient
    private Log logger = LogFactory.getLog(DescIndicator.class);

    /** 用户Id */
/*    private String userId;*/
    /** 描述性指标名称*/
    private String name;
    /** 描述性指标编码*/
/*    private String code;*/
    /** 指标值 */
    private String value;

    @Override
    public boolean validate() {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(certNum)){
            logger.error("入参不正确："+ this.toString());
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

/*    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DescIndicator{" +
                "****userId='" + userId + '\'' +
                ",***certNum='" + certNum + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
