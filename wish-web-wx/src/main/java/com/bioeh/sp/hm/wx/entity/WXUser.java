package com.bioeh.sp.hm.wx.entity;


import com.bioeh.sp.hm.dal.anno.Column;
import com.bioeh.sp.hm.dal.anno.Id;
import com.bioeh.sp.hm.dal.anno.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户实体类
 * lz 20160801
 */
@Table(value="bio_wx_user")
public class WXUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	@Id
	@Column("id")
	private long id;

	/**
	 *用户openid
	 */
	@Column("openid")
	private String openid;

	/**
	 *手机号
	 */
	@Column("mobile")
	private String mobile;

	/**
	 *创建日期
	 */
	@Column("create_date")
	private Date createDate;

	/**
	 * 用户状态 0代表可用 1代表不可用
	 * @return
	 */
	@Column("status")
	private int status;

	/**
	 * 用户在sys_user中主键
	 * @return
	 */
	@Column("uid")
	private int uid;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}