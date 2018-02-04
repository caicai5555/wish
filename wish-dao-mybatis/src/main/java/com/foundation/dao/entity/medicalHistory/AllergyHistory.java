package com.foundation.dao.entity.medicalHistory;

import java.util.ArrayList;
import java.util.List;

import com.foundation.dao.util.DataEntity;

/**
 * 
* @ClassName: allergyHistory 
* @Description: 过敏史
* @author chengchen 
* @date 2016年11月21日 下午3:30:38 
*
 */
public class AllergyHistory extends DataEntity<AllergyHistory> {
	private static final long serialVersionUID = 1L;
	/** 用户id*/
	private String userId;
	
	/**过敏源Code逗号分隔字符串（VO）*/
	private List<String> allergyCodes = new ArrayList<>();
	/**过敏源Name逗号分隔字符串（VO）*/
	private String allergyNameStr;
	/**其他时填写的内容*/
	private String otherContent;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getAllergyCodes() {
		return allergyCodes;
	}
	public void setAllergyCodes(List<String> allergyCodes) {
		this.allergyCodes = allergyCodes;
	}
	public String getAllergyNameStr() {
		return allergyNameStr;
	}
	public void setAllergyNameStr(String allergyNameStr) {
		this.allergyNameStr = allergyNameStr;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
	
}
