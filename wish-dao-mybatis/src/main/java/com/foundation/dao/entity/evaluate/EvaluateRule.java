package com.foundation.dao.entity.evaluate;

import java.io.Serializable;

/**
 * 
 * @ClassName: EvaluateRule
 * @Description: 评估规则
 * @author chengchen
 * @date 2016年10月13日 下午7:31:30
 *
 */
public class EvaluateRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**编号*/
	private String id;
	/** 评估信息id */
	private String evaluateInfoId;
	/** 规则名称 */
	private String name;
	/** 规则 */
	private String rule;
	/**结果标签，对一组结果id用md5限制长度，多个以,分隔*/
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getResultTag() {
		return resultTag;
	}

	public void setResultTag(String resultTag) {
		this.resultTag = resultTag;
	}
}