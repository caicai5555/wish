package com.bioeh.sp.hm.wx.entity;


import com.bioeh.sp.hm.dal.anno.Column;
import com.bioeh.sp.hm.dal.anno.Id;
import com.bioeh.sp.hm.dal.anno.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信device_id与设备mac关系表
 * lz 20160801
 */
@Table(value="bio_wx_device_relation")
public class WXDeviceRelation implements Serializable {

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
	 *微信分配的设备id
	 */
	@Column("device_id")
	private String deviceId;

	/**
	 *关系状态（0.可以使用,1代表不可使用 ）操作删除使用
	 */
	@Column("status")
	private int status;
	/**
	 *创建日期
	 */
	@Column("create_date")
	private Date createDate;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}