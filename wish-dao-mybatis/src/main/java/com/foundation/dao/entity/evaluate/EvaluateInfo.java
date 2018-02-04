package com.foundation.dao.entity.evaluate;

import com.foundation.dao.util.DataEntity;

/**
 * 
 * @ClassName: evaluateInfo
 * @Description: 评估信息实体
 * @author chengchen
 * @date 2016年10月11日 上午10:29:42
 *
 */
public class EvaluateInfo extends DataEntity<EvaluateInfo> {
	private static final long serialVersionUID = 1L;
	/** 评估名称 */
	private String name;
	/** 英文名称 */
	private String enname;
	/**评估分类id*/
	private String evaluateCategoryId;
	/** 文件名 */
	private String fileName;
	/** 文件路径 */
	private String filePath;
	/** 状态，参考StatusEnum */
	private Integer status;
	/** 模式，参考RuleEnum */
	private Integer mode;
	/** 执行次数 */
	private Long times;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}

	public String getEvaluateCategoryId() {
		return evaluateCategoryId;
	}

	public void setEvaluateCategoryId(String evaluateCategoryId) {
		this.evaluateCategoryId = evaluateCategoryId;
	}

}