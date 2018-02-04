package com.foundation.console.web.archive;

import com.foundation.common.persistence.Page;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.sys.User;
import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.record.ConcIndicator;
import com.foundation.mongo.entity.record.DescIndicator;
import com.foundation.mongo.entity.record.Indicator;
import com.foundation.service.archive.biz.impl.ArchiveBizImpl;
import com.foundation.service.dist.biz.impl.DistArchiveConclusionBizImpl;
import com.foundation.service.dist.biz.impl.DistArchiveDescindicatorBizImpl;
import com.foundation.service.dist.biz.impl.DistArchiveIndicatorBizImpl;
import com.foundation.service.record.biz.IIndicatorRecordBiz;
import com.foundation.service.usercenter.service.impl.SystemServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ArchiveController
 * @Description: 用户档案控制类
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/24 20:40 
 */
 
@Controller
@RequestMapping(value="/admin/archive")
public class ArchiveController extends BaseController {
	@Autowired
	private SystemServiceImpl systemService;

	@Autowired
	ArchiveBizImpl archiveBizImpl;
	@Autowired
	IIndicatorRecordBiz indicatorRecordBiz;

	@Autowired
	DistArchiveIndicatorBizImpl indicatorBizImpl;
	@Autowired
	DistArchiveDescindicatorBizImpl descindicatorBizImpl;
	@Autowired
	DistArchiveConclusionBizImpl conclusionBizImpl;

	/**
	 * @Description: 个人用户跳转到档案-指标项页面
	 * @param userId
	 * @return
	 */
	//@RequiresPermissions("sys:area:view")
	@RequestMapping(value="indicatorsPage")
	public String indicatorsPage(String userId,Model model) {
		if(StringUtils.isEmpty(userId)){
			logger.error("ArchiveController.indicators-------->param：userId" + userId);
			return "";
		}
		try {
			//获取用户信息
			User user = systemService.getUser(userId);
			model.addAttribute("user",user);
			Map map = new HashMap();
			map.put("height1","aaaaaaaaaaa");
			model.addAttribute("map", map);
			//Map<String,String> indicators = indicatorBizImpl.queryList();
			//指标字典项
			//model.addAttribute("indicator", indicators);
			//描述性字典项
			model.addAttribute("descIndicator", "");
			//结论性字典项
			model.addAttribute("concIndicator", "");
			model.addAttribute("userId", 2/*userId*/);

		} catch (Exception e) {
			logger.error("#######ArchiveController.indicatorsPage error", e);
		}
		return "modules/archive/archiveIndicatorList";
	}

	/**
	 * @Description: ajax获取档案-指标项
	 * @param userId
	 * @param indicatorType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("records")
	public List records(String userId,String indicatorType){
		List list = new ArrayList();
		if(StringUtils.isEmpty(userId)&& StringUtils.isEmpty(indicatorType)){
			logger.error("#######ArchiveController.records.params is null");
			return list;
		}

		//查询用户档案信息
		try {
			//String testUserId = "2";
			Archive archive =archiveBizImpl.queryByUserId(userId);
			//根据请求类型访问相关实体
			switch (indicatorType){
				case "indicator":
					list = new ArrayList(archive.getIndicator().values());
					break;
				case "descIndicator":
					list = new ArrayList(archive.getDescIndicator().values());
					break;
				case "concIndicator":
					list = new ArrayList(archive.getConcIndicator().values());
					break;
				default:
					list = null;
			}
		} catch (Exception e) {
			logger.error("##########ArchiveController.records error", e);
		}
		return list;
	}
	//------------------------子页面跳转
	/**
	 * @Description: 跳转至用户指标项页面
	 * @param model
	 * @return
	 */
	@RequestMapping("indicatorPage")
	public String indicatorPage(String userId,String indicatorCode,Model model){
		if(StringUtils.isEmpty(userId)|| StringUtils.isEmpty(indicatorCode)){
			logger.error("#######ArchiveController.indicatorPage.params is null");
			return "";
		}
		model.addAttribute("userId",userId);
		model.addAttribute("indicatorCode",indicatorCode);

		return "modules/archive/indicatorList";
	}
	/**
	 * @Description: 跳转至用户描述性指标项页面
	 * @param model
	 * @return
	 */
	@RequestMapping("descIndicatorPage")
	public String descIndicatorPage(String userId,String indicatorCode,Model model){
		if(StringUtils.isEmpty(userId)|| StringUtils.isEmpty(indicatorCode)){
			logger.error("#######ArchiveController.descIndicatorPage.params is null");
			return "";
		}
		model.addAttribute("userId",userId);
		model.addAttribute("indicatorCode",indicatorCode);

		return "modules/archive/descIndicatorList";
	}
	/**
	 * @Description: 跳转至用户结论性指标项页面
	 * @param model
	 * @return
	 */
	@RequestMapping("concIndicatorPage")
	public String concIndicatorPage(String userId,String indicatorCode,Model model){
		if(StringUtils.isEmpty(userId)|| StringUtils.isEmpty(indicatorCode)){
			logger.error("#######ArchiveController.concIndicatorPage.params is null");
			return "";
		}
		model.addAttribute("userId",userId);
		model.addAttribute("indicatorCode",indicatorCode);
		return "modules/archive/concIndicatorList";
	}

	//------------------------子页面ajax获取数据

	/**
	 * @Description: ajax-查询指定用户指标项记录
	 * @param userId
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("indicatorList")
	public Page indicatorRecords(String userId, String code,Integer page,Integer rows){
		Map queryParams = new HashMap();
		queryParams.put("userId",userId);
		//queryParams.put("code",code);
		Page pageInfo = null;
		try {
			pageInfo = indicatorRecordBiz.queryPageList(page,rows,queryParams, Indicator.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	/**
	 * @Description: ajax-查询指定用户描述性指标项记录
	 * @param userId
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("descIndicatorList")
	public Page descIndicatorRecords(String userId, String code,Integer page,Integer rows){
		Map queryParams = new HashMap();
		queryParams.put("userId",userId);
		//queryParams.put("code",code);
		Page pageInfo = null;
		try {
			pageInfo = indicatorRecordBiz.queryPageList(page,rows,queryParams, DescIndicator.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	/**
	 * @Description: ajax-查询指定用户结论性性指标项记录
	 * @param userId
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("concIndicatorList")
	public Page concIndicatorRecords(String userId, String code,Integer page,Integer rows){
		Map queryParams = new HashMap();
		queryParams.put("userId",userId);
		//queryParams.put("code",code);
		Page pageInfo = null;
		try {
			pageInfo = indicatorRecordBiz.queryPageList(page,rows,queryParams, ConcIndicator.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}
}
