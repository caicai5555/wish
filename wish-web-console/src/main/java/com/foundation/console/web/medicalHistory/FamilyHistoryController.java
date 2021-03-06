package com.foundation.console.web.medicalHistory;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foundation.common.persistence.Page;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.medicalHistory.biz.IFamilyHistoryBiz;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: FamilyHistoryController 
* @Description: 家族病史管理
* @author chengchen 
* @date 2016年11月25日 上午9:54:26 
*
 */
@Controller
@RequestMapping(value = "/admin/medicalHistory/familyHistory")
public class FamilyHistoryController extends BaseController {
	@Autowired
	private IFamilyHistoryBiz familyHistoryBiz;

	/**
	 * 
	* @Title: index 
	* @Description: 分页列表
	* @author chengchen
	* @date 2016年11月25日 上午10:33:06 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = { "index", "" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> params = Maps.newHashMap();
		Page<FamilyHistory> pageBounds = new Page<>(request, response);
		try {
			Page<FamilyHistory> page = familyHistoryBiz.queryPage(params, pageBounds);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("FamilyHistoryController index method error", e);
		}
		return "modules/medicalHistory/familyHistoryList";
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除
	* @author chengchen
	* @date 2016年11月24日 下午4:39:58 
	* @param @param id
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		try {
			familyHistoryBiz.delete(id);
		} catch (Exception e) {
			logger.error("FamilyHistoryController delete method error", e);
		}
		addMessage(redirectAttributes, "删除成功!");
		return "redirect:" + adminPath + "/medicalHistory/familyHistory/index";
	}

	/**
	 * 
	* @Title: form 
	* @Description: 添加/修改页面
	* @author chengchen
	* @date 2016年11月24日 下午4:40:10 
	* @param @param history
	* @param @param model
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "form")
	public String form(FamilyHistory history, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history = familyHistoryBiz.queryById(history.getId());
			}
		} catch (Exception e) {
			logger.error("FamilyHistoryController form method error", e);
		}
		model.addAttribute("history", history);
		return "modules/medicalHistory/familyHistoryForm";
	}

	/**
	 * 
	* @Title: catgroySave 
	* @Description: 保存动作
	* @author chengchen
	* @date 2016年11月24日 下午4:40:27 
	* @param @param history
	* @param @param model
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/save")
	public String save(FamilyHistory history, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history.setUpdateBy(currentUser);
				history.setUpdateDate(new Date());
			} else {
				history.setCreateBy(currentUser);
				history.setCreateDate(new Date());
			}
			if (StringUtils.isNotBlank(history.getId())) {
				familyHistoryBiz.update(history);
			} else {
				familyHistoryBiz.save(history);
			}
		} catch (Exception e) {
			logger.error("[FamilyHistoryController.save error]", e);
		}
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + adminPath + "/medicalHistory/familyHistory/index";
	}
}
