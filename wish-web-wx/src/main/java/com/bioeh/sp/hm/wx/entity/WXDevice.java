package com.bioeh.sp.hm.wx.entity;


import com.bioeh.sp.hm.dal.anno.Column;
import com.bioeh.sp.hm.dal.anno.Id;
import com.bioeh.sp.hm.dal.anno.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信设备实体类
 * lz 20160801
 */
@Table(value="bio_wx_device")
public class WXDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	@Id
	@Column("id")
	private long id;

	/**
	 *硬件mac地址
	 */
	@Column("mac")
	private String mac;


	/**
	 *设备状态（0.可以使用,1代表不可使用 ）操作删除使用
	 */
	@Column("status")
	private int status;
	/**
	 *创建日期
	 */
	@Column("create_date")
	private Date createDate;

	/**
	 *是否可以公开 只有主账号才可以公开 （0代表可以公开 1表示不可以公开）
	 */
	@Column("is_public")
	private int isPublic;

	/**
	 *设备描述
	 */
	@Column("remark")
	private String remark;

	/**
	 *暂时 type 值 1代表 空气猫P6 2代表以后产品
	 */
	@Column("type")
	private int type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}