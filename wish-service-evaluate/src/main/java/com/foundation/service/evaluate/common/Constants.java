package com.foundation.service.evaluate.common;


/**
 * 
* @ClassName: Constants 
* @Description: 业务常量（评估）
* @author chengchen 
* @date 2016年10月17日 下午5:12:47 
*
 */
public interface Constants extends com.foundation.common.bean.Constants {
    
    interface Common{
    	/**返回信息key:success标识*/
    	String SUCCESS="success";
    	/**返回信息key:msg标识*/
    	String MSG = "msg";
    	/**返回信息key:data标识*/
    	String DATA = "data";
    	String ELSE = "else";
    	String IF = "if";
    	String CONCLUSION_DEFAULT = "不匹配";
    	String SUM_DEFAULT = "0";
    	String RETURN = "return";
    	String _RULE = "_rule";
    	String EQUALS = "equals";
    	String NULL = "null";
    }
    interface Configure{
    	String XSD_URL = "xsd.url";
    }
    interface  Pregnant{
    	/**孕检分类英文 */
    	String PREGNANT_MAN = "pregnantMan";
    	String PREGNANT_WOMAN = "pregnantWoman";
    	String PREGNANT_FAMILY = "pregnantFamily";
    	/**评估xml中参数的开始标记，m_为男，w_为女 */
    	String M_ = "m_";
    	String W_ = "w_";
    	/**计算中使用的标识*/
    	String M_FLAG = "man";
    	String W_FLAG = "woman";
    	String F_FLAG = "family";
    	/**是否异常默认值，1表示异常*/
    	int ABNORMAL_FLAG_DEFAULT = 1; 
    }
}
