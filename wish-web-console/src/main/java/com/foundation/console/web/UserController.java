package com.foundation.console.web;

import com.foundation.common.beanvalidator.BeanValidators;
import com.foundation.common.config.Global;
import com.foundation.common.date.DateUtils;
import com.foundation.common.excel.ExportExcel;
import com.foundation.common.excel.ImportExcel;
import com.foundation.common.persistence.Page;
import com.foundation.common.security.PwdSha1Util;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.sys.Office;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.mongo.entity.record.DescIndicator;
import com.foundation.service.archive.service.IArchiveService;
import com.foundation.service.record.service.impl.IndicatorRecordService;
import com.foundation.service.usercenter.UserUtils;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 * @author fqh
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "/admin/sys/user")
public class UserController extends BaseController {

	@Autowired
	private IUserBiz userBiz;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return userBiz.getUser(id);
		}else{
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	/**
	 * @Title: 档案列表
	 * @Description: TODO
	 * @author chunyangli
	 * @date 2016/10/13 16:32
	 * @param
	 * @return
	 * @throws
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"perInfoInfo", ""})
	public String perInfoIndex(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = userBiz.findUser(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		return "modules/sys/perInfoIndex";
	}

	/**
	 * @Title: 
	 * @Description: TODO
	 * @author chunyangli
	 * @date 2016/10/13 18:51
	 * @param     
	 * @return    
	 * @throws 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"perInfoList", ""})
	public String perInfoList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = userBiz.findUser(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		return "modules/sys/perInfoList";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = userBiz.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/userList";
	}
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = userBiz.findUser(new Page<User>(request, response), user);
		return page;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", userBiz.findAllRole());
		return "modules/sys/userForm";
	}


	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form2")
	public ModelAndView form2(User user, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("modules/sys/userForm");
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles", userBiz.findAllRole());
		return modelAndView;
	}

	@Autowired
	private IArchiveService archiveService;
	@Autowired
	private IndicatorRecordService indicatorRecordService;
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "perInfoForm")
	public String perInfoForm(User user, Model model) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(null!=user){
			params.put("id",user.getId());
			params.put("DBKEY",user.getDBKEY());
		}

		User user1 = userBiz.getUserInfoById(params);

		if(null!=user1 && StringUtils.isNotBlank(user1.getId())){

			try {//查询mongo
				Archive archive = archiveService.queryByUserId(user1.getId());
				if(null!=archive && null!=archive.getDescIndicator() && archive.getDescIndicator().size()>0){
					Map<String,DescIndicator> map = archive.getDescIndicator();
					/*if(null!=map.get("birthday")){
						user1.setBirthDay(StringUtils.isBlank(map.get("birthday").getValue())?"":map.get("birthday").getValue());
					}*/
					if(null!=map.get("sex")){
						String sexV=map.get("sex").getValue();
						if(StringUtils.isNotBlank(sexV)){
							user1.setSex(Integer.valueOf(sexV));
							user1.setSexCode(StringUtils.isBlank(map.get("sex").getCode())?"":map.get("sex").getCode());
						}
					}
					if(null!=map.get("married")){
						user1.setMarrayType(StringUtils.isBlank(map.get("married").getValue()) ?"":map.get("married").getValue());
						user1.setMarrayCode(StringUtils.isBlank(map.get("married").getCode())?"":map.get("married").getCode());
					}
					if(null!=map.get("height")){
						user1.setHeight(StringUtils.isBlank(map.get("height").getValue())?"":map.get("height").getValue());
					}
					if(null!=map.get("pal")){
						user1.setPhysicalCoefficient(StringUtils.isBlank(map.get("pal").getValue())?"":map.get("pal").getValue());
					}
					if(null!=map.get("job")){
						user1.setProfession(StringUtils.isBlank(map.get("job").getValue())?"":map.get("job").getValue());
						user1.setProfessionCode(StringUtils.isBlank(map.get("job").getCode())?"":map.get("job").getCode());
					}
					if(null!=map.get("blood")){
						user1.setBloodType(StringUtils.isBlank(map.get("blood").getValue())?"":map.get("blood").getValue());
						user1.setBloodCode(StringUtils.isBlank(map.get("blood").getCode())?"":map.get("blood").getCode());
					}

				}
			} catch (Exception e) {
				logger.error("perInfoForm is error:",e);
			}
		}
		model.addAttribute("user", user1);
		model.addAttribute("Global", new Global());
		model.addAttribute("allRoles", userBiz.findAllRole());
		return "modules/sys/perInfo";
	}



	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(PwdSha1Util.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		List<Role> roleResult=userBiz.findAllRole();
		for (Role r : roleResult){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		userBiz.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			userBiz.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据"+ DateUtils.getNowTime("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = userBiz.findUser(new Page<User>(request, response, -1), user);
    		new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					if ("true".equals(checkLoginName("", user.getLoginName()))){
						user.setPassword(PwdSha1Util.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						userBiz.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && userBiz.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			currentUser.setCardNo(user.getCardNo());
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setAddress(user.getAddress());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			userBiz.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "perInfo")
	public String personalInfo(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			currentUser.setCardNo(user.getCardNo());
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setAddress(user.getAddress());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			userBiz.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/perInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = getCurrentUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if (PwdSha1Util.validatePassword(oldPassword, user.getPassword())){
				userBiz.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = userBiz.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "updatePerInfo")
	public Map<String,Object> updatePerInfo(HttpServletRequest request, HttpServletResponse response) {
		User user = getCurrentUser();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(null==user ){
			resultMap.put("result","session已过期，请重新登录");
		}
		Map<String,Object> params = new HashMap<String,Object>();
		String dbType = request.getParameter("dbType");
		String id=request.getParameter("id");
		User userNow = new User();

		if(StringUtils.isNotBlank(dbType)){
			if(dbType.equals("mongo")){
				Map<String,BaseRecordEntity> mapVo = new HashMap<String,BaseRecordEntity>();
				String birthday = request.getParameter("birthday");
				String sex = request.getParameter("sex");
				String married = request.getParameter("married");
				String height = request.getParameter("height");
				String pal = request.getParameter("pal");
				String job = request.getParameter("job");
				String blood = request.getParameter("blood");

				Archive archive = null;
				try {
					userNow = userBiz.getUser(id);
					archive = archiveService.queryByUserId(id);
				} catch (Exception e) {
					logger.error("=>updatePerInfo=>archiveService.queryByUserId is error ",e);
				}
				if(null!=archive && null!=archive.getDescIndicator() && archive.getDescIndicator().size()>0){
					Map<String,DescIndicator> map = archive.getDescIndicator();
					if(null!=map.get("birthday")){
						map.get("birthday").setValue(birthday);
						mapVo.put("birthday",map.get("birthday"));
					}
					if(null!=map.get("sex")){
						map.get("sex").setValue(sex);
						mapVo.put("sex",map.get("sex"));
					}
					if(null!=map.get("married")){
						map.get("married").setValue(married);
						mapVo.put("married",map.get("married"));
					}
					if(null!=map.get("height")){
						map.get("height").setValue(height);
						mapVo.put("height",map.get("height"));
					}
					if(null!=map.get("pal")){
						map.get("pal").setValue(pal);
						mapVo.put("pal",map.get("pal"));
					}
					if(null!=map.get("job")){
						map.get("job").setValue(job);
						mapVo.put("job",map.get("job"));
					}
					if(null!=map.get("blood")){
						map.get("blood").setValue(blood);
						mapVo.put("blood",map.get("blood"));
					}
				}else{

					DescIndicator d1 = getMapVo( "出生年月", birthday,  "birthday",  userNow,  id);
					mapVo.put("birthday",d1);

					DescIndicator d2 = getMapVo( "性别", sex,  "sex",  userNow,  id);
					mapVo.put("sex",d2);

					DescIndicator d3 = getMapVo( "婚姻状况", married,  "married",  userNow,  id);
					mapVo.put("married",d3);

					DescIndicator d4 = getMapVo( "身高", height,  "height",  userNow,  id);
					mapVo.put("height",d4);

					DescIndicator d5 = getMapVo( "体力系数", pal,  "pal",  userNow,  id);
					mapVo.put("pal",d5);

					DescIndicator d6 = getMapVo( "职业", job,  "job",  userNow,  id);
					mapVo.put("job",d6);
					DescIndicator d7 = getMapVo( "血型", blood,  "blood",  userNow,  id);
					mapVo.put("blood",d7);



				}
				try {
					indicatorRecordService.saveIndicators(mapVo, true);
				} catch (Exception e) {
					resultMap.put("result","保存出错");
					logger.error("lcytest is error==",e);
				}


				resultMap.put("result","基本信息保存成功。");
			}else if(dbType.equals("mysql")){

				String cardNo=request.getParameter("cardNo");
				String email=request.getParameter("email");
				String phone=request.getParameter("phone");
				String mobile=request.getParameter("mobile");
				String addressProvince=request.getParameter("addressProvince");
				String addressCity=request.getParameter("addressCity");
				String addressCounty=request.getParameter("addressCounty");
				String addressTown=request.getParameter("addressTown");
				String addressVillage=request.getParameter("addressVillage");
				String hukouProvince=request.getParameter("hukouProvince");
				String hukouCity=request.getParameter("hukouCity");
				String hukouCounty=request.getParameter("hukouCounty");
				String hukouTown=request.getParameter("hukouTown");
				String hukouVillage=request.getParameter("hukouVillage");
				String name = request.getParameter("name");
				String updateBy=user.getId();

				params.put("name",name);
				params.put("cardNo",cardNo);
				params.put("email",email);
				params.put("phone",phone);
				params.put("mobile",mobile);
				params.put("addressProvince",addressProvince);
				params.put("addressCity",addressCity);
				params.put("addressCounty",addressCounty);
				params.put("addressTown",addressTown);
				params.put("addressVillage",addressVillage);
				params.put("hukouProvince",hukouProvince);
				params.put("hukouCity",hukouCity);
				params.put("hukouCounty",hukouCounty);
				params.put("hukouTown",hukouTown);
				params.put("hukouVillage",hukouVillage);
				params.put("updateBy",updateBy);
				params.put("id",id);
				params.put("DBKEY",user.getDBKEY());
				try {
					int flat = userBiz.updatePerInfo(params);
					logger.info("flat============"+flat);
					resultMap.put("result","账户信息保存成功。");
				} catch (Exception e){
					resultMap.put("result","操作失败，更新出现异常："+e.getMessage());
				}
			}else{
				resultMap.put("result","操作失败，数据库类型不正确");
			}
		}else{
			resultMap.put("result","操作失败，数据库类型不正确");
		}
		return resultMap;
	}

	/**
	 * 根据参数拼装mongo数据
	 * @param name
	 * @param value
	 * @param code
	 * @param userNow
	 * @param id
	 * @return
	 */
	public DescIndicator getMapVo(String name,String value,String code,User userNow,String userId){
		Map<String,DescIndicator> map = Maps.newHashMap();
		DescIndicator d = new DescIndicator();
		d.setValue(value);
		d.setName(name);
		d.setUserId(userId);
		d.setCertNum(null==userNow||StringUtils.isBlank(userNow.getId())?"":userNow.getId());
		d.setCode(code);
		return d;
	}




	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "lcytest")
	public Map<String,Object> lcytest(HttpServletRequest request, HttpServletResponse response) {
		User user = getCurrentUser();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(null==user ){
			resultMap.put("result","session已过期，请重新登录");
		}
		Map<String,Object> params = new HashMap<String,Object>();

		Map<String,BaseRecordEntity> mapVo = new HashMap<String,BaseRecordEntity>();
		for(int i=0;i<7;i++){
			DescIndicator d = new DescIndicator();
			d.setUserId("ea698032-3bbc-45a0-bc93-320383e0b65f");
			d.setCertNum("111111");
			d.setUpdateTime(new Date());
			d.setEvent("lcy test");
			d.setSource("console web");
			String key = "";
			if(i==0){
				d.setName("birthday");
				key = d.getName();
				d.setName("生日");
				d.setValue("1988-04-22");
			}else if(i==1){
				d.setName("sex");
				key = d.getName();
				d.setName("性别");
				d.setValue("男");
			}else if(i==2){
				d.setName("married");
				key = d.getName();
				d.setName("婚姻状况");
				d.setValue("已婚");
			}else if(i==3){
				d.setName("height");
				key = d.getName();
				d.setName("身高");
				d.setValue("173");
			}else if(i==4){
				d.setName("pal");
				key = d.getName();
				d.setName("体力系数");
				d.setValue("888");

			}else if(i==5){
				d.setName("job");
				key = d.getName();
				d.setName("职业");
				d.setValue("美国总统");
			}else if(i==6){
				d.setName("blood");
				key = d.getName();
				d.setName("血型");
				d.setValue("B型");
			}
			d.setCode(key);
			mapVo.put(key,d);
		}
		try {
			indicatorRecordService.saveIndicators(mapVo, true);
		} catch (Exception e) {
			resultMap.put("result","保存出错");
			logger.error("lcytest is error==",e);
		}

		return resultMap;
	}
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
