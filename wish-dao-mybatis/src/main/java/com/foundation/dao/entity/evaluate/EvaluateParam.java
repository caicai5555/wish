package com.foundation.dao.entity.evaluate;

import java.io.Serializable;

/**
 * 
 * @ClassName: EvaluateParam
 * @Description: 评估参数
 * @author chengchen
 * @date 2016年10月13日 下午1:38:51
 *
 */
public class EvaluateParam implements Serializable {
	private static final long serialVersionUID = 1L;
	/**编号*/
	private String id;
	/** 评估信息id */
	private String evaluateInfoId;
	/** 参数名称 */
	private String name;
	/** 参数英文名称 */
	private String enname;
	/** 参数类型 */
	private String type;

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

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}