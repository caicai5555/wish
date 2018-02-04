package com.foundation.mongo.entity.record;

import java.io.Serializable;

/**
 * @author wangsen
 * @ClassName: ConcIndicatorItem
 * @Description: 结论指标项实体
 * @date 2016/10/28 1:53
 */
public class ConcIndicatorItem implements Serializable {
    protected static final long serialVersionUID = 1L;

    /** 编码*/
    private String code;
    /** 中文名称*/
    private String name;
    /** 值*/
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
