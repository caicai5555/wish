package com.foundation.console.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Dict;
import com.foundation.service.usercenter.service.impl.DictServiceImpl;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foundation.console.common.BaseController;

/**
 * 字典Controller
 * @author fqh
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "/admin/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictServiceImpl dictService;

	/**
	 * 会在此controller每个方法执行前被执行,用户获取字段数据
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public Dict get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			Map<String,Object> params = Maps.newHashMap();
			params.put("id",id);
			return dictService.get(params);
		}else{
			return new Dict();
		}
	}

	/**
	 * 字段列表
	 * @param dict
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = {"list", ""})//list或者“”都可以请求到list method
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		//类型列表
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
		//数据列表
        Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict);
        model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	/**
	 * 字典表单
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	/**
	 * 字典保存
	 * @param dict
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dict)){
			return form(dict, model);
		}
		dictService.save(dict);
		addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}

	/**
	 * 字典删除
	 * @param dict
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		dictService.delete(dict);
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="+dict.getType();
	}
	
/*	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i=0; i<list.size(); i++){
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required=false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}*/

}
