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
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.common.MedicalHistoryConstants;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryBiz;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryDetailBiz;
import com.foundation.service.usercenter.DictUtils;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: SurgeryHistoryController 
* @Description:  手术史管理
* @author chengchen 
* @date 2016年11月24日 下午1:44:10 
*
 */
@Controller
@RequestMapping(value = "/admin/medicalHistory/surgeryHistory")
public class SurgeryHistoryController extends BaseController {
	@Autowired
	private ISurgeryHistoryBiz surgeryHistoryBiz;
	@Autowired
	private ISurgeryHistoryDetailBiz surgeryHistoryDetailBiz;

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
		Page<SurgeryHistory> pageBounds = new Page<>(request, response);
		try {
			Page<SurgeryHistory> page = surgeryHistoryBiz.queryPage(params, pageBounds);
			if (null != page.getList() && page.getList().size() > 0) {
				for (SurgeryHistory tmp : page.getList()) {
					List<SurgeryHistoryDetail> details = surgeryHistoryDetailBiz.queryList(tmp.getId());
					if (null != details && details.size() > 0) {
						StringBuffer nameStr = new StringBuffer();
						for (int i = 0, size = details.size(); i < size; i++) {
							nameStr.append(details.get(i).getSurgeryName());
							if (i != size - 1) {
								nameStr.append("，");
							}
						}
						tmp.setSurgeryNameStr(nameStr.toString());
					}
				}
			}
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SurgeryHistoryController index method error", e);
		}
		return "modules/medicalHistory/surgeryHistoryList";
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
			surgeryHistoryBiz.delete(id);
		} catch (Exception e) {
			logger.error("SurgeryHistoryController delete method error", e);
		}
		addMessage(redirectAttributes, "删除成功!");
		return "redirect:" + adminPath + "/medicalHistory/surgeryHistory/index";
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
	public String form(SurgeryHistory history, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history = surgeryHistoryBiz.queryById(history.getId());
			}
			List<SurgeryHistoryDetail> details = surgeryHistoryDetailBiz.queryList(history.getId());
			if (null != details && details.size() > 0) {
				for (SurgeryHistoryDetail tmp : details) {
					if (MedicalHistoryConstants.Common.OTHER.equals(tmp.getSurgeryCode())) {
						history.setOtherContent(tmp.getSurgeryName());
					}
					history.getSurgeryCodes().add(tmp.getSurgeryCode());
				}
			}
		} catch (Exception e) {
			logger.error("SurgeryHistoryController form method error", e);
		}
		model.addAttribute("history", history);
		return "modules/medicalHistory/surgeryHistoryForm";
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
	public String save(SurgeryHistory history, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history.setUpdateBy(currentUser);
				history.setUpdateDate(new Date());
				// 先清空所有明细数据
				surgeryHistoryDetailBiz.deleteByHistoryId(history.getId());
			} else {
				history.setCreateBy(currentUser);
				history.setCreateDate(new Date());
			}
			if (StringUtils.isNotBlank(history.getId())) {
				surgeryHistoryBiz.update(history);
			} else {
				surgeryHistoryBiz.save(history);
			}
			if (null != history.getSurgeryCodes() && history.getSurgeryCodes().size() > 0) {
				for (String code : history.getSurgeryCodes()) {
					String name = DictUtils.getDictLabel(code, MedicalHistoryConstants.Dict.DICT_SURGERY_TYPE, "");
					if (StringUtils.isBlank(name)) {
						continue;
					}
					SurgeryHistoryDetail detail = new SurgeryHistoryDetail();
					detail.setUserId(currentUser.getId());
					detail.setSurgeryHistoryId(history.getId());
					detail.setSurgeryCode(code);
					// 如果选择了其他，则获取文本框内容
					if (MedicalHistoryConstants.Common.OTHER.equals(code)) {
						detail.setSurgeryName(history.getOtherContent());
					} else {
						detail.setSurgeryName(name);
					}
					surgeryHistoryDetailBiz.save(detail);
				}
			}
		} catch (Exception e) {
			logger.error("[SurgeryHistoryController.save error]", e);
		}
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + adminPath + "/medicalHistory/surgeryHistory/index";
	}
}
