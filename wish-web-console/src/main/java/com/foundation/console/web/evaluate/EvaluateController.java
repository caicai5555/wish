package com.foundation.console.web.evaluate;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.evaluate.biz.IEvaluateCategoryBiz;
import com.foundation.service.evaluate.biz.IEvaluateConclusionContentBiz;
import com.foundation.service.evaluate.biz.IEvaluateInfoBiz;
import com.foundation.service.evaluate.biz.IEvaluateParamBiz;
import com.foundation.service.evaluate.biz.IEvaluateRuleBiz;
import com.foundation.service.evaluate.common.CategoryTypeEnum;
import com.foundation.service.evaluate.common.Constants;
import com.foundation.service.evaluate.common.EvaluateCal;
import com.foundation.service.evaluate.common.EvaluateUtils;
import com.foundation.service.evaluate.common.StatusEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: EvaluateController
 * @Description: 评估管理
 * @author chengchen
 * @date 2016年10月21日 上午10:57:05
 *
 */
@Controller
@RequestMapping(value = "/admin/evaluate")
public class EvaluateController extends BaseController {
	@Autowired
	private IEvaluateInfoBiz evaluateInfoBiz;
	@Autowired
	private IEvaluateParamBiz evaluateParamBiz;
	@Autowired
	private IEvaluateRuleBiz evaluateRuleBiz;
	@Autowired
	private IEvaluateConclusionContentBiz evaluateConclusionContentBiz;
	@Autowired
	private IEvaluateCategoryBiz evaluateCategoryBiz;

	/**
	 * 
	* @Title: index 
	* @Description: 评估列表  
	* @author chengchen
	* @date 2016年11月25日 上午9:58:11 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	// @RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "index", "" })
	public String index( @RequestParam(required = false) String enname,HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> params = Maps.newHashMap();
		Page<EvaluateInfo> pageBounds = new Page<>(request, response);
		try {
			if(StringUtils.isNotBlank(enname)){
				params.put("enname", enname);
			}
			Page<EvaluateInfo> page = evaluateInfoBiz.queryPage(params, pageBounds);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("EvaluateController index method error", e);
		}
		return "modules/evaluate/evaluateList";
	}

	/**
	 * 
	* @Title: delete 
	* @Description: 删除
	* @author chengchen
	* @date 2016年11月25日 上午9:58:24 
	* @param @param id
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		try {
			evaluateInfoBiz.delete(id);
			EvaluateInfo info = evaluateInfoBiz.queryById(id);
			EvaluateCal cal = EvaluateCal.getInstance();
			cal.remove(info);
		} catch (Exception e) {
			logger.error("EvaluateController delete method error", e);
		}
		addMessage(redirectAttributes, "删除成功!");
		return "redirect:" + adminPath + "/evaluate/index";
	}

	/**
	 * 
	* @Title: add 
	* @Description: 评估添加
	* @author chengchen
	* @date 2016年11月25日 上午9:58:34 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/evaluate/evaluateForm";
	}

	/**
	 * 
	* @Title: save 
	* @Description: 保存（暂时不保存文件，待文件系统完成后统一修改） 
	* @author chengchen
	* @date 2016年11月25日 上午9:58:45 
	* @param @param request
	* @param @param response
	* @param @param files
	* @param @param remarks
	* @param @param category
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile[] files, @RequestParam(required = false) String remarks,
			@RequestParam String category, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		StringBuffer resultMsg = new StringBuffer();
		try {
			if (files.length > 0) {
				List<String> msgList = new ArrayList<>();
				StringBuffer msg = new StringBuffer();
				for (MultipartFile file : files) {
					String originalName = file.getOriginalFilename();
					String extName = originalName.substring(originalName.lastIndexOf("."));
					if (!".xml".equals(extName.toLowerCase())) {
						msgList.add(originalName + "，是非法格式，只允许上传xml格式！");
						continue;
					}
					JSONObject validResult = EvaluateUtils.validatorXML(file.getInputStream());
					if (!(boolean) validResult.get(Constants.Common.SUCCESS)) {
						msgList.add(originalName + "，格式不合法！");
						continue;
					}
					// TODO 待文件系统完成后，统一保存文件
					/** 评估信息保存 */
					EvaluateInfo info = new EvaluateInfo();
					info.setEvaluateCategoryId(category);
					info.setStatus(StatusEnum.UPLOAD_SUCCESS.getId());
					info.setCreateBy(currentUser);
					info.setRemarks(remarks);
					evaluateInfoBiz.save(info);
					JSONObject parsingResult = evaluateInfoBiz.parsingXML(info, file.getInputStream());
					if (!(boolean) parsingResult.get(Constants.Common.SUCCESS)) {
						evaluateInfoBiz.deleteReal(info.getId());
						msgList.add(originalName + "，解析失败！，原因：" + parsingResult.get(Constants.Common.MSG) + "!");
						continue;
					}
					/** 解析成功后，更新info中的状态 */
					info.setStatus(StatusEnum.PARSING_SUCCESS.getId());
					if (null != currentUser) {
						info.setUpdateBy(currentUser);
					}
					evaluateInfoBiz.update(info);
				}
				msg.append("上传完成，").append("成功").append(files.length - msgList.size()).append("个");
				if (msgList.size() > 0) {
					msg.append("，").append("失败").append(msgList.size()).append("个。");
				} else {
					msg.append("。");
				}
				resultMsg.append(msg.toString());
				if (msgList.size() > 0) {
					resultMsg.append("<br/>");
					for (String _msg : msgList) {
						resultMsg.append(_msg).append("<br/>");
					}
				}
			} else {
				resultMsg.append("保存失败，附件不能为空！");
			}

		} catch (Exception e) {
			logger.error("[evaluateController.save error]", e);
		}
		addMessage(redirectAttributes, resultMsg.toString());
		return "redirect:" + adminPath + "/evaluate/index";
	}

	/**
	 * 
	* @Title: test 
	* @Description: 测试页面
	* @author chengchen
	* @date 2016年11月25日 上午9:58:56 
	* @param @param id
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "test")
	public String test(@RequestParam String id, Model model) {
		try {
			EvaluateInfo info = evaluateInfoBiz.queryById(id);
			if (null != info) {
				Map<String, Object> params = Maps.newHashMap();
				params.put("evaluateInfoId", info.getId());
				List<EvaluateParam> paramList = evaluateParamBiz.queryList(params);
				if (null != paramList && paramList.size() > 0) {
					model.addAttribute("id", id);
					model.addAttribute("paramList", paramList);
					StringBuffer paramStr = new StringBuffer();
					StringBuffer paramNameStr = new StringBuffer();
					for (int i = 0, size = paramList.size(); i < size; i++) {
						EvaluateParam tmp = paramList.get(i);
						if (null != tmp) {
							paramStr.append(tmp.getEnname());
							paramNameStr.append(tmp.getName());
							if (i != size - 1) {
								paramStr.append(",");
								paramNameStr.append(",");
							}
						}
					}
					model.addAttribute("paramStr", paramStr);
					model.addAttribute("paramNameStr", paramNameStr);
				}

			}
		} catch (Exception e) {
			logger.error("EvaluateController test method error", e);
		}
		return "modules/evaluate/evaluateTest";
	}

	/**
	 * 
	* @Title: testExcute 
	* @Description: 执行评估计算（测试）
	* @author chengchen
	* @date 2016年11月25日 上午9:59:07 
	* @param @param id
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return JSONObject    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "testExcute")
	public JSONObject testExcute(@RequestParam String id, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JSONObject result = new JSONObject();
		result.put(Constants.Common.SUCCESS, false);
		result.put(Constants.Common.MSG, "操作失败，请重试！");
		try {
			EvaluateInfo info = evaluateInfoBiz.queryById(id);
			if (null != info) {
				Map<String, Object> params = Maps.newHashMap();
				params.put("evaluateInfoId", info.getId());
				List<EvaluateParam> paramList = evaluateParamBiz.queryList(params);
				List<EvaluateParam> extraList = Lists.newArrayList();
				List<EvaluateRule> ruleList = evaluateRuleBiz.queryList(params);
				Map<String, Object> data = Maps.newHashMap();
				for (EvaluateParam param : paramList) {
					String requestVal = request.getParameter(param.getEnname());
					if(requestVal.contains("-")){
						String[] requestVals = requestVal.split("-");
						if(requestVals.length!=3){
							return result;
						}
						data.put(param.getEnname(),EvaluateCal.converType(requestVals[0], param.getType()));
						data.put(param.getEnname().substring(2)+"_rangeMin",EvaluateCal.converType(requestVals[1], "double"));
						EvaluateParam p1 = new EvaluateParam();
						p1.setEnname(param.getEnname().substring(2)+"_rangeMin");
						p1.setType("double");
						extraList.add(p1);
						data.put(param.getEnname().substring(2)+"_rangeMax",EvaluateCal.converType(requestVals[2], "double"));
						EvaluateParam p2 = new EvaluateParam();
						p2.setEnname(param.getEnname().substring(2)+"_rangeMax");
						p2.setType("double");
						extraList.add(p2);
					}else{
						data.put(param.getEnname(),EvaluateCal.converType(requestVal, param.getType()));
					}
				}
				EvaluateCal cal = EvaluateCal.getInstance();
				if(extraList.size()>0){
					paramList.addAll(extraList);
				}
				cal.init(info, paramList, ruleList, true, null);
				String resultTag = cal.execute(data, info).toString();
				if (StringUtils.isNotBlank(resultTag) && !Constants.Common.CONCLUSION_DEFAULT.equals(resultTag)) {
					params.put("resultTag", resultTag);
					List<EvaluateConclusionContent> conclusions = evaluateConclusionContentBiz.queryList(params);
					for (EvaluateConclusionContent conclusionContent : conclusions) {
						/** 替换占位符 */
						String conclusion = EvaluateCal.replaceSpecialChar(conclusionContent.getConclusion(), data);
						conclusionContent.setConclusion(conclusion);
					}
					result.put(Constants.Common.SUCCESS, true);
					result.put(Constants.Common.DATA, conclusions);
					result.put(Constants.Common.MSG, "操作成功！！！");
				} else {
					result.put(Constants.Common.SUCCESS, true);
					result.put(Constants.Common.MSG, resultTag);
				}
			}
		} catch (Exception e) {
			logger.error("EvaluateController testExcute method error", e);
		}
		return result;
	}

	/**
	 * 
	* @Title: catgroyList 
	* @Description: 评估分类列表 
	* @author chengchen
	* @date 2016年11月25日 上午9:59:18 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/catgroyList")
	public String catgroyList(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> params = Maps.newHashMap();
		try {
			List<EvaluateCategory> list = Lists.newArrayList();
			List<EvaluateCategory> sourcelist = evaluateCategoryBiz.queryList(params);
			EvaluateCategory.sortList(list, sourcelist, EvaluateCategory.ROOT_ID);// 排序
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("[evaluateController.catgroyList error]", e);
		}
		return "modules/evaluate/evaluateCategoryList";
	}

	/**
	 * 
	* @Title: categoryForm 
	* @Description: 评估表单页面
	* @author chengchen
	* @date 2016年11月25日 上午9:59:29 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @param pid
	* @param @param id
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/categoryForm")
	public String categoryForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) String pid, @RequestParam(required = false) String id) {
		EvaluateCategory category = new EvaluateCategory();
		try {
			if (StringUtils.isNoneBlank(id)) {
				category = evaluateCategoryBiz.queryById(id);
				category.setParent(evaluateCategoryBiz.queryById(category.getPid()));
			}
			if (StringUtils.isNoneBlank(pid)) {
				EvaluateCategory parent = evaluateCategoryBiz.queryById(pid);
				category.setPid(pid);
				category.setParent(parent);
			}
			model.addAttribute("category", category);
		} catch (Exception e) {
			logger.error("[evaluateController.categoryForm error]", e);
		}
		return "modules/evaluate/evaluateCategoryForm";
	}

	/**
	 * 
	* @Title: categoryTreeData 
	* @Description: 评估分类树型数据
	* @author chengchen
	* @date 2016年11月25日 上午9:59:47 
	* @param @param type
	* @param @param request
	* @param @param response
	* @param @return    设定参数 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "categoryTreeData")
	public List<Map<String, Object>> categoryTreeData(@RequestParam(required = false) String type,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = Maps.newHashMap();
		List<Map<String, Object>> mapList = Lists.newArrayList();
		try {
			params.put("type", type);
			List<EvaluateCategory> list = evaluateCategoryBiz.queryList(params);
			for (int i = 0; i < list.size(); i++) {
				EvaluateCategory e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getPid());
				map.put("name", e.getName());
				mapList.add(map);
			}
		} catch (Exception e) {
			logger.error("[evaluateController.treeData error]", e);
		}
		return mapList;
	}

	/**
	 * 
	* @Title: catgroySave 
	* @Description: 评估分类保存
	* @author chengchen
	* @date 2016年11月25日 上午10:00:23 
	* @param @param category
	* @param @param model
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/catgroySave")
	public String catgroySave(EvaluateCategory category, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = getCurrentUser();
		try {
			if (StringUtils.isNotBlank(category.getId())) {
				category.setUpdateBy(currentUser);
				category.setUpdateDate(new Date());
			} else {
				category.setCreateBy(currentUser);
				category.setCreateDate(new Date());
			}
			category.setType(CategoryTypeEnum.EVALUATE.getId());
			if (StringUtils.isBlank(category.getPid())) {
				category.setPid(EvaluateCategory.ROOT_ID);
			}
			if (StringUtils.isNotBlank(category.getId())) {
				evaluateCategoryBiz.update(category);
			} else {
				evaluateCategoryBiz.save(category);
			}
		} catch (Exception e) {
			logger.error("[evaluateController.catgroySave error]", e);
		}
		addMessage(redirectAttributes, "保存成功！");
		return "redirect:" + adminPath + "/evaluate/catgroyList";
	}

	/**
	 * 
	* @Title: categoryDelete 
	* @Description: 评估分类删除
	* @author chengchen
	* @date 2016年11月25日 上午10:00:35 
	* @param @param id
	* @param @param redirectAttributes
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "categoryDelete")
	public String categoryDelete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("evaluateCategoryId", id);
			int count = evaluateInfoBiz.getCount(params);
			if (count > 0) {
				addMessage(redirectAttributes, "删除失败，评估信息在使用");
			} else {
				evaluateCategoryBiz.delete(id);
				addMessage(redirectAttributes, "删除成功");
			}
		} catch (Exception e) {
			logger.error("EvaluateController delete method error", e);
		}
		return "redirect:" + adminPath + "/evaluate/catgroyList";
	}
}
