package com.bioeh.sp.hm.wx.entity;


import com.bioeh.sp.hm.dal.anno.Column;
import com.bioeh.sp.hm.dal.anno.Id;
import com.bioeh.sp.hm.dal.anno.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信device_id与用户关系表
 * lz 20160801
 */
@Table(value="bio_wx_user_device_relation")
public class WXUserDeviceRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	@Id
	@Column("id")
	private long id;
	/**
	 *微信bio_wx_user主键
	 */
	@Column("user_id")
	private long userId;
	/**
	 *bio_wx_device设备主键
	 */
	@Column("wx_deviceid")
	private long wxDeviceId;

	/**
	 *硬件mac地址
	 */
	@Column("mac")
	private String mac;

	/**
	 *用户openId
	 */
	@Column("openid")
	private String openId;

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

	/**
	 *设备位置
	 */
	@Column("position")
	private String position;

	/**
	 *主账号 (0代表主账号 1代表非主账号)  扫码绑定的位主账户  手动绑定的是非主账户
	 */
	@Column("primary_account")
	private int primaryAccount;

	public long getWxDeviceId() {
		return wxDeviceId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(int primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setWxDeviceId(long wxDeviceId) {
		this.wxDeviceId = wxDeviceId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	@Override
	public String toString() {
		return "WXUserDeviceRelation{" +
				"id=" + id +
				", userId=" + userId +
				", wxDeviceId=" + wxDeviceId +
				", mac='" + mac + '\'' +
				", openId='" + openId + '\'' +
				", status=" + status +
				", createDate=" + createDate +
				", position='" + position + '\'' +
				", primaryAccount=" + primaryAccount +
				'}';
	}
}