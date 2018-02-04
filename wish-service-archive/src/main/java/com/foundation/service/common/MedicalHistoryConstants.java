package com.foundation.service.common;

/**
 * 
* @ClassName: MedicalHistoryConstants 
* @Description: 病史常量类
* @author chengchen 
* @date 2016年11月24日 下午3:41:15 
*
 */
public interface MedicalHistoryConstants {
	
	interface Dict{
		/**过敏史*/
		String DICT_ALLERGY_TYPE = "allergy";
		/**用药史*/
		String DICT_DRUG_TYPE = "drug";
		/**手术史*/
		String DICT_SURGERY_TYPE = "surgery";
	}
	
	interface Common{
		String OTHER = "other";
	}

}
