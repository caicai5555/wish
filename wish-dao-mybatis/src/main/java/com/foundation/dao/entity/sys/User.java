package  com.foundation.dao.entity.sys;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import com.foundation.common.bean.Constants;
import com.foundation.common.cache.CacheUtils;
import com.foundation.common.security.PwdSha1Util;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.dao.modules.read.sys.RoleDaoR;
import com.foundation.dao.modules.read.sys.UserDaoR;
import com.foundation.dao.modules.write.sys.RoleDao;
import com.foundation.dao.security.Principal;
import com.foundation.common.utils.Collections3;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.foundation.common.config.Global;
import com.foundation.dao.util.DataEntity;
import com.foundation.common.excel.annotation.ExcelField;

/**
 * 用户Entity
 * @author fqh
 * @version 2013-12-05
 */
public class User extends DataEntity<User>{

	private static final long serialVersionUID = 1L;
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private String loginName;// 登录名
	private String password;// 密码
	private String cardNo;		// 身份证
	private String name;	// 姓名
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 手机
	private String address;	// 住址
	private Integer userType;// 用户类型
	private String loginIp;	// 最后登陆IP
	private Date loginDate;	// 最后登陆日期
	private String loginFlag;	// 是否允许登陆
	private String photo;	// 头像
	private Integer age;//建档时候的年龄
	private String nation;//民族
	private Integer education;//文化程度
	private Integer certificateType;//证件类型(1身份证2军官证3护照4其他有效证件)
	private String certificateNumber;//证件号码
	private Integer occupation;//职业
	private String occupationOthers;//其他职业
	private String postCode;//邮编
	private String followupType;//做过的随访类型
	private Integer hukouType;//户口性质（0=农业户口）（1=非农业户口）
	private String addressProvince;//现住址—省
	private String addressCity;//现住址—市
	private String addressCounty;//现住址—县
	private String addressTown;//现住址—乡镇
	private String addressVillage;//现住址—村
	private String hukouProvince;//户口所在地——省
	private String hukouCity;//户口所在地——市
	private String hukouCounty;//户口所在地——县
	private String hukouTown;//户口所在地——乡镇
	private String hukouVillage;//户口所在地——村
	private Date marryDate;//结婚日期

	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码

	private String oldLoginIp;	// 上次登陆IP
	private Date oldLoginDate;	// 上次登陆日期

	private Role role;	// 根据角色查询用户条件

	private Date birthday;//出生年月

	private String pregnancyArchiveId;//档案id

	/**
	 * 外接平台所需
	 */

	/**
	 * 公司名字
	 */
	private String companyName;
	/**
	 * 部门名字
	 */
	private String officeName;
	/**
	 * 当前用户
	 */
	private User currentUser;

	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	//mongo 描述性指标字段
	private String height;//身高
	private String weight;//体重
	private Integer sex;//性别
	private String sexCode;//性别code
	private String bloodType;//血型
	private String bloodCode;//血液code
	private String marrayType;//婚姻状况
	private String marrayCode;//婚姻状况code
	private String physicalCoefficient;//体力系数
	private String profession;//职业
	private String professionCode;//职业code




	public User() {
		super();
		this.loginFlag = Global.YES;
		this.delFlag= Constants.DEL_FLAG_NORMAL;
	}

	public User(String id){
		super(id);
	}

	public User(String id, String loginName){
		super(id);
		this.loginName = loginName;
	}

	public User(String loginName,Integer userType) {
		this.userType=userType;
		this.loginName=loginName;
		//this.password= PwdSha1Util.entryptPassword("123456");//默认登陆密码
		this.loginFlag = Global.YES;
		this.delFlag= Constants.DEL_FLAG_NORMAL;
	}


	public User(Role role){
		super();
		this.role = role;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}

	@JsonIgnore
	@NotNull(message="归属公司不能为空")
	@ExcelField(title="归属公司", align=2, sort=20)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	@ExcelField(title="登录名", align=2, sort=30)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JsonIgnore
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}

	/*@Length(min=1, max=18, message="身份证长度必须介于 1 和 18 之间")*/
	@ExcelField(title="身份证", align=2, sort=45)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200, message="手机长度必须介于 1 和 200 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min=0, max=200, message="家庭住址长度必须介于 1 和 200 之间")
	@ExcelField(title="家庭住址", align=2, sort=70)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@ExcelField(title="创建时间", type=0, align=1, sort=90)
	public Date getCreateDate() {
		return createDate;
	}

	@ExcelField(title="最后登录IP", type=1, align=1, sort=100)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="最后登录日期", type=1, align=1, sort=110)
	public Date getLoginDate() {
		return loginDate;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getBloodCode() {
		return bloodCode;
	}

	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode;
	}

	public String getMarrayCode() {
		return marrayCode;
	}

	public void setMarrayCode(String marrayCode) {
		this.marrayCode = marrayCode;
	}

	public String getProfessionCode() {
		return professionCode;
	}

	public void setProfessionCode(String professionCode) {
		this.professionCode = professionCode;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getSex() {
		return sex;
	}

	public String getSexStr() {
		if(sex!=null && Constants.SEX_MAN.intValue()==sex){
			return "男";
		}else if(sex!=null && Constants.SEX_WOMAN.intValue()==sex){
			return "女";
		}
		return "男女不详";
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getMarrayType() {
		return marrayType;
	}

	public void setMarrayType(String marrayType) {
		this.marrayType = marrayType;
	}

	public String getPhysicalCoefficient() {
		return physicalCoefficient;
	}

	public void setPhysicalCoefficient(String physicalCoefficient) {
		this.physicalCoefficient = physicalCoefficient;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Integer getOccupation() {
		return occupation;
	}

	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}

	public String getOccupationOthers() {
		return occupationOthers;
	}

	public void setOccupationOthers(String occupationOthers) {
		this.occupationOthers = occupationOthers;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFollowupType() {
		return followupType;
	}

	public void setFollowupType(String followupType) {
		this.followupType = followupType;
	}

	public Integer getHukouType() {
		return hukouType;
	}

	public void setHukouType(Integer hukouType) {
		this.hukouType = hukouType;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressCounty() {
		return addressCounty;
	}

	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}

	public String getAddressTown() {
		return addressTown;
	}

	public void setAddressTown(String addressTown) {
		this.addressTown = addressTown;
	}

	public String getAddressVillage() {
		return addressVillage;
	}

	public void setAddressVillage(String addressVillage) {
		this.addressVillage = addressVillage;
	}

	public String getHukouProvince() {
		return hukouProvince;
	}

	public void setHukouProvince(String hukouProvince) {
		this.hukouProvince = hukouProvince;
	}

	public String getHukouCity() {
		return hukouCity;
	}

	public void setHukouCity(String hukouCity) {
		this.hukouCity = hukouCity;
	}

	public String getHukouCounty() {
		return hukouCounty;
	}

	public void setHukouCounty(String hukouCounty) {
		this.hukouCounty = hukouCounty;
	}

	public String getHukouTown() {
		return hukouTown;
	}

	public void setHukouTown(String hukouTown) {
		this.hukouTown = hukouTown;
	}

	public String getHukouVillage() {
		return hukouVillage;
	}

	public void setHukouVillage(String hukouVillage) {
		this.hukouVillage = hukouVillage;
	}

	public Date getMarryDate() {
		return marryDate;
	}

	public void setMarryDate(Date marryDate) {
		this.marryDate = marryDate;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		if (oldLoginIp == null){
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOldLoginDate() {
		if (oldLoginDate == null){
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPregnancyArchiveId() {
		return pregnancyArchiveId;
	}

	public void setPregnancyArchiveId(String pregnancyArchiveId) {
		this.pregnancyArchiveId = pregnancyArchiveId;
	}

	@JsonIgnore
	//@ExcelField(title="拥有角色", align=1, sort=800, fieldType=RoleListType.class)
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}

	public boolean isAdmin(){
		return isAdmin(this.id);
	}

	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}


	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = getUser();
		}
		return currentUser;
	}



	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public String toString() {
		return id;
	}

	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {

		}catch (InvalidSessionException e){

		}
		return null;
	}
	public static User get(String id){
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = SpringContextHolder.getBean(UserDaoR.class).get(new User(id));
			if (user == null){
				return null;
			}
			user.setRoleList(SpringContextHolder.getBean(RoleDaoR.class).findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/*private  UserDaoR userDaoR = SpringContextHolder.getBean(UserDaoR.class);
	private  RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);*/

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
}