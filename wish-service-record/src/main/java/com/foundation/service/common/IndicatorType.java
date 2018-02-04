package com.foundation.service.common;

/**
 * @author wangsen
 * @ClassName: IndicatorType
 * @Description: 指标类型枚举
 * @date 2016/10/27 19:14
 */
public enum IndicatorType {
    INDICATOR("indicator"),   //标准指标
    DESCINDICATOR("descIndicator"),  //描述性指标
    CONCINDICATOR("concIndicator");//结论性指标

    private String type;

    IndicatorType(String type) {
        this.type=type;
    }
    public String getType() {
        return type;
    }
    public static IndicatorType getByType(String type){
    	switch (type) {
		case "indicator":
			return INDICATOR;
		case "descIndicator":
			return DESCINDICATOR;
		case "concIndicator":
			return CONCINDICATOR;
		default:
			return null;
		}
    }

   @Override
    public String toString() {
        return this.type;
    }
}
