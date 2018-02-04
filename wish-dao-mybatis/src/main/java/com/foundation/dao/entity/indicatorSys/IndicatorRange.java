package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;

/**
 * @ClassName: IndicatorRange
 * @Description: 指标区间表
 * @author wangsen
 * @date 2017/1/3 10:33
 */
public class IndicatorRange implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 指标主键**/
    private String indicatorId;
    /** 方法名称**/
    private String method;
    /** 方法描述**/
    private String remark;
    /** 区间最少值**/
    private Double rangeMin;
    /** 区间最大值**/
    private Double rangeMax;
    /** 删除标记**/
    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId == null ? null : indicatorId.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(Double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public Double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(Double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}