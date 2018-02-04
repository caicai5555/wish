package com.foundation.service.common;

/**
 * @ClassName: IndexConstants
 * @Description: 指标常量类
 * User: SamWang
 * 2016/8/4 16:23
 */
public interface IndicatorConstants {

    /******       用于指标规则匹配入参统一修改定义常量start              *******/
    /**指标编号**/
    String INDICATOR_CODE = "indicatorCode";
    /**指标性别**/
    String SEX = "sex";/*sexValue*/
    /**指标比对值**/
    String VALUE = "value";
    /**指标年龄**/
    String AGE="age";
    /**指标体重**/
    String WEIGHT="weight";
    /******************** end ********************/

    /********规则表达式中参与运算占位符定义----以此为基准，记录用，有新的计算参数请更新  start ********/
    /**身高**/
    String F_HEIGHT = "height";

    /**********       end       *************/

    /********生成脚本表达式用到的占位符  start ********/
    String VALUE_SYMBOL = "{value}";
    String AGE_SYMBOL = "{age}";
    String WEIGHT_SYMBOL = "{weight}";

    String MATCH_EXPRESSION ="([a-zA-Z]+)";//匹配正则表达式

    //正则执行结束保留两位小数
    String REGEXP_FIXED2 = "($exp).toFixed(2)";
    /**********       end       *************/

    /******拼接符号******/
    String SYMBOL_COLON = ":";
    String SYMBOL_AND = "&&";
    String SYMBOL_OR = "||";
    /******end*****/

    /******指标参数项表基础数据组名******/
    String BASEDATA_COLOR="indColor";//基础数据—指标颜色组code
    String BASEDATA_PARAM="param";//基础数据-指标规则参数组code
    String BASEDATA_PARAM_TYPE_0="0";
}
