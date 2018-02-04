package com.foundation.dao.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foundation.common.bean.Constants;
import com.foundation.common.date.DateUtils;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 
* @ClassName: DataEntity 
* @Description: 数据Entity类
* @author fqh 
* @date 2016年08月16日
* 
* @param <T>
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	/**备注*/
	protected String remarks;
	/**创建日期*/
	protected Date createDate;
	/**创建者*/
	protected User createBy;
	/**更新者*/
	protected User updateBy;
	/**更新日期*/
	protected Date updateDate;
	/**删除标记（0：正常；1：删除；2：审核）*/
	protected Integer delFlag;
	/**删除时间*/
	protected Date delDate;

	/** 医师端目前所有的创建时间都为long类型 */
	private Long createDateL;

	/** 医师端目前所有的更新时间都为long类型 */
	private Long updateDateL;
	
	public DataEntity() {
		super();
		this.delFlag = Constants.DEL_FLAG_NORMAL;
	}
	
	public DataEntity(String id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		User user = User.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		User user = User.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public Long getCreateDateL() {
		return createDateL;
	}

	public void setCreateDateL(Long createDateL) {
		try{
			if(null!=createDateL){
				this.createDate = DateUtils.getDateByLongTime(createDateL);
			}
		}catch (Exception e){

		}

		this.createDateL = createDateL;
	}

	public Long getUpdateDateL() {
		return updateDateL;
	}

	public void setUpdateDateL(Long updateDateL) {
		try{
			if(null!=updateDateL){
				this.updateDate = DateUtils.getDateByLongTime(updateDateL);
			}
		}catch (Exception e){

		}
		this.updateDateL = updateDateL;
	}
}
