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
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.common.MedicalHistoryConstants;
import com.foundation.service.medicalHistory.biz.IDrugHistoryBiz;
import com.foundation.service.medicalHistory.biz.IDrugHistoryDetailBiz;
import com.foundation.service.usercenter.DictUtils;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: DrugHistoryController 
* @Description: 用药史管理
* @author chengchen 
* @date 2016年11月25日 下午3:15:21 
*
 */
@Controller
@RequestMapping(value = "/admin/medicalHistory/drugHistory")
public class DrugHistoryController extends BaseController {
	@Autowired
	private IDrugHistoryBiz drugHistoryBiz;
	@Autowired
	private IDrugHistoryDetailBiz drugHistoryDetailBiz;

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
		Page<DrugHistory> pageBounds = new Page<>(request, response);
		try {
			Page<DrugHistory> page = drugHistoryBiz.queryPage(params, pageBounds);
			if (null != page.getList() && page.getList().size() > 0) {
				for (DrugHistory tmp : page.getList()) {
					List<DrugHistoryDetail> details = drugHistoryDetailBiz.queryList(tmp.getId());
					if (null != details && details.size() > 0) {
						StringBuffer nameStr = new StringBuffer();
						for (int i = 0, size = details.size(); i < size; i++) {
							nameStr.append(details.get(i).getDrugName());
							if (i != size - 1) {
								nameStr.append("，");
							}
						}
						tmp.setDrugNameStr(nameStr.toString());
					}
				}
			}
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("DrugHistoryController index method error", e);
		}
		return "modules/medicalHistory/drugHistoryList";
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
			drugHistoryBiz.delete(id);
		} catch (Exception e) {
			logger.error("DrugHistoryController delete method error", e);
		}
		addMessage(redirectAttributes, "删除成功!");
		return "redirect:" + adminPath + "/medicalHistory/drugHistory/index";
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
	public String form(DrugHistory history, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history = drugHistoryBiz.queryById(history.getId());
			}
			List<DrugHistoryDetail> details = drugHistoryDetailBiz.queryList(history.getId());
			if (null != details && details.size() > 0) {
				for (DrugHistoryDetail tmp : details) {
					if (MedicalHistoryConstants.Common.OTHER.equals(tmp.getDrugCode())) {
						history.setOtherContent(tmp.getDrugName());
					}
					history.getDrugCodes().add(tmp.getDrugCode());
				}
			}
		} catch (Exception e) {
			logger.error("DrugHistoryController form method error", e);
		}
		model.addAttribute("history", history);
		return "modules/medicalHistory/drugHistoryForm";
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
	public String save(DrugHistory history, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		try {
			if (StringUtils.isNotBlank(history.getId())) {
				history.setUpdateBy(currentUser);
				history.setUpdateDate(new Date());
				// 先清空所有明细数据
				drugHistoryDetailBiz.deleteByHistoryId(history.getId());
			} else {
				history.setCreateBy(currentUser);
				history.setCreateDate(new Date());
			}
			if (StringUtils.isNotBlank(history.getId())) {
				drugHistoryBiz.update(history);
			} else {
				drugHistoryBiz.save(history);
			}
			if (null != history.getDrugCodes() && history.getDrugCodes().size() > 0) {
				for (String code : history.getDrugCodes()) {
					String name = DictUtils.getDictLabel(code, MedicalHistoryConstants.Dict.DICT_DRUG_TYPE, "");
					if (StringUtils.isBlank(name)) {
						continue;
					}
					DrugHistoryDetail detail = new DrugHistoryDetail();
					detail.setUserId(currentUser.getId());
					detail.setDrugHistoryId(history.getId());
					detail.setDrugCode(code);
					// 如果选择了其他，则获取文本框内容
					if (MedicalHistoryConstants.Common.OTHER.equals(code)) {
						detail.setDrugName(history.getOtherContent());
					} else {
						detail.setDrugName(name);
					}
					drugHistoryDetailBiz.save(detail);
				}
			}
		} catch (Exception e) {
			logger.error("[DrugHistoryController.save error]", e);
		}
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + adminPath + "/medicalHistory/drugHistory/index";
	}


}
