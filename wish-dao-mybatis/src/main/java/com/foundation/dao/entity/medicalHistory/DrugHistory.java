package com.foundation.dao.entity.medicalHistory;

import java.util.ArrayList;
import java.util.List;

import com.foundation.dao.util.DataEntity;

/**
 * 
* @ClassName: drugHistory 
* @Description: 用药史
* @author chengchen 
* @date 2016年11月21日 下午3:27:35 
*
 */
public class DrugHistory  extends DataEntity<DrugHistory>{
	private static final long serialVersionUID = 1L;
	/** 用户id*/
	private String userId;

	/**用药Code逗号分隔字符串（VO）*/
	private List<String> drugCodes = new ArrayList<>();
	/**用药Name逗号分隔字符串（VO）*/
	private String drugNameStr;
	/**其他时填写的内容*/
	private String otherContent;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getDrugCodes() {
		return drugCodes;
	}
	public void setDrugCodes(List<String> drugCodes) {
		this.drugCodes = drugCodes;
	}
	public String getDrugNameStr() {
		return drugNameStr;
	}
	public void setDrugNameStr(String drugNameStr) {
		this.drugNameStr = drugNameStr;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
	
}
