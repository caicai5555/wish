package com.foundation.dao.entity.evaluate;

import java.io.Serializable;

/**
 * 
* @ClassName: EvaluateConclusionContent 
* @Description: 评估结论内容
* @author chengchen 
* @date 2016年10月19日 下午1:46:48 
*
 */
public class EvaluateConclusionContent implements Serializable {
	private static final long serialVersionUID = 1L;
	/**编号*/
	private String id;
	/** 评估信息id */
	private String evaluateInfoId;
	/**评估规则id*/
	private String evaluateRuleId;
	/**名称*/
	private String name;
	/**英文名称*/
	private String enname;
	/**结论内容*/
	private String conclusion;
	/**结果标签，对一组结果id用md5限制长度*/
	private String resultTag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEvaluateInfoId() {
		return evaluateInfoId;
	}
	public void setEvaluateInfoId(String evaluateInfoId) {
		this.evaluateInfoId = evaluateInfoId;
	}
	public String getEvaluateRuleId() {
		return evaluateRuleId;
	}
	public void setEvaluateRuleId(String evaluateRuleId) {
		this.evaluateRuleId = evaluateRuleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getResultTag() {
		return resultTag;
	}
	public void setResultTag(String resultTag) {
		this.resultTag = resultTag;
	}

}
