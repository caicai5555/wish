package com.foundation.controller.archive;

import com.foundation.common.BaseController;
import com.foundation.common.bean.ResultModel;
import com.foundation.dao.entity.archive.FamilyArchive;
import com.foundation.service.dist.biz.IFamilyArchiveBiz;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ArchiveController
 * @Description: 档案服务接口
 * @author fqh
 * @date 2016年11月10日
 *
 */
@RestController
@RequestMapping("/archive")
public class ArchiveController extends BaseController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IFamilyArchiveBiz familyArchiveBiz;

	/**
	 * 
	* @Title: getUser 
	* @Description: 评估服务接口
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return ResultModel    返回类型
	* @throws
	 */
	@RequestMapping(value = "/{certNum}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultModel getUserArchive(@PathVariable("certNum") String certNum, HttpServletRequest request){
		logger.info("have into getUserArchive method!");
		ResultModel resultModel=new ResultModel();
		Map<String,Object> resultMap= Maps.newHashMap();
		Map<String,Object> params= Maps.newHashMap();

		params.put("certNum",certNum);
		List<FamilyArchive> archiveList= null;
		try {
			archiveList = familyArchiveBiz.queryList(params);
		} catch (Exception e) {
			resultModel.setTip("error");
			logger.error("查询系统异常",e);
		}
		if(!CollectionUtils.isEmpty(archiveList)){
			logger.info("certNum:"+certNum+"+queryListSize:"+archiveList.size());
			FamilyArchive archive=archiveList.get(0);
			resultMap.put("wifeCertNum",archive.getWifeCertNum());
			resultMap.put("husbandCertNum",archive.getHusbandCertNum());
			resultMap.put("pregnancyArchiveId",archive.getPregnancyArchiveId());
			resultMap.put("provinceCode",archive.getProvinceCode());
		}
		resultModel.setData(resultMap);
		return resultModel;
	}
}
