package com.foundation.console.web.medicalHistory;

import java.util.Date;
import java.util.List;
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
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.common.MedicalHistoryConstants;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryBiz;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryDetailBiz;
import com.foundation.service.usercenter.DictUtils;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: AllergyHistoryController 
* @Description:  过敏史管理
* @author chengchen 
* @date 2016年11月24日 下午1:44:10 
*
 */
@Controller
@RequestMapping(value = "/admin/medicalHistory/allergyHistory")
public class AllergyHistoryController extends BaseController {
	@Autowired
	private IAllergyHistoryBiz allergyHistoryBiz;
	@Autowired
	private IAllergyHistoryDetailBiz allergyHistoryDetailBiz;

	/**
	 * 
	* @Title: index 
	* @Description: 分页列表
	* @author chengchen
	* @date 2016年11月24日 下午1:44:00 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	// @RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "index", "" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> params = Maps.newHashMap();
		Page<AllergyHistory> pageBounds = new Page<>(request, response);
		try {
			Page<AllergyHistory> page = allergyHistoryBiz.queryPage(params, pageBounds);
			if (null != page.getList() && page.getList().size() > 0) {
				for (AllergyHistory tmp : page.getList()) {
					List<AllergyHistoryDetail> details = allergyHistoryDetailBiz.queryList(tmp.getId());
					if (null != details && details.size() > 0) {
						StringBuffer nameStr = new StringBuffer();
						for (int i = 0, size = details.size(); i < size; i++) {
							nameStr.append(details.get(i).getAllergyName());
							if (i != size - 1) {
								nameStr.append("，");
							}
						}
						tmp.setAllergyNameStr(nameStr.toString());
					}
				}
			}
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("AllergyHistoryController index method error", e);
		}
		return "modules/medicalHistory/allergyHistoryList";
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
			allergyHistoryBiz.delete(id);
		} catch (Exception e) {
			logger.error("AllergyHistoryController delete method error", e);
		}
		addMessage(redirectAttributes, "删除成功!");
		return "redirect:" + adminPath + "/medicalHistory/allergyHistory/index";
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
	public String form(AllergyHistory history, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history = allergyHistoryBiz.queryById(history.getId());
			}
			List<AllergyHistoryDetail> details = allergyHistoryDetailBiz.queryList(history.getId());
			if (null != details && details.size() > 0) {
				for (AllergyHistoryDetail tmp : details) {
					if (MedicalHistoryConstants.Common.OTHER.equals(tmp.getAllergyCode())) {
						history.setOtherContent(tmp.getAllergyName());
					}
					history.getAllergyCodes().add(tmp.getAllergyCode());
				}
			}
		} catch (Exception e) {
			logger.error("AllergyHistoryController form method error", e);
		}
		model.addAttribute("history", history);
		return "modules/medicalHistory/allergyHistoryForm";
	}

	/**
	 * 
	* @Title: save 
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
	public String save(AllergyHistory history, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history.setUpdateBy(currentUser);
				history.setUpdateDate(new Date());
				// 先清空所有明细数据
				allergyHistoryDetailBiz.deleteByHistoryId(history.getId());
			} else {
				history.setCreateBy(currentUser);
				history.setCreateDate(new Date());
			}
			if (StringUtils.isNotBlank(history.getId())) {
				allergyHistoryBiz.update(history);
			} else {
				allergyHistoryBiz.save(history);
			}
			if (null != history.getAllergyCodes() && history.getAllergyCodes().size() > 0) {
				for (String code : history.getAllergyCodes()) {
					String name = DictUtils.getDictLabel(code, MedicalHistoryConstants.Dict.DICT_ALLERGY_TYPE, "");
					if (StringUtils.isBlank(name)) {
						continue;
					}
					AllergyHistoryDetail detail = new AllergyHistoryDetail();
					detail.setUserId(currentUser.getId());
					detail.setAllergyHistoryId(history.getId());
					detail.setAllergyCode(code);
					// 如果选择了其他，则获取文本框内容
					if (MedicalHistoryConstants.Common.OTHER.equals(code)) {
						detail.setAllergyName(history.getOtherContent());
					} else {
						detail.setAllergyName(name);
					}
					allergyHistoryDetailBiz.save(detail);
				}
			}
		} catch (Exception e) {
			logger.error("[AllergyHistoryController.save error]", e);
		}
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + adminPath + "/medicalHistory/allergyHistory/index";
	}
}
