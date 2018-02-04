package com.foundation.console.web.disease;

import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.service.disease.biz.IDiseaseCategoryBiz;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DiseaseCategoryController
 * @Description: 疾病-疾病分类（~科）
 * @author wangsen
 * @date 2016/12/1 17:33
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/diseaseCategory")
public class DiseaseCategoryController extends BaseController {
	private Log logger = LogFactory.getLog(DiseaseCategoryController.class);
	@Autowired
	IDiseaseCategoryBiz diseaseCategoryBiz;

	/**
	 * @Description 获取一级疾病分类列表
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/categorys")
	public List<DiseaseCategory> categorys(HttpServletRequest request, Model model) {
		Map<String,Object> params = new HashedMap();
		List<DiseaseCategory> diseaseCategories = null;
		try {
			//如果isParent==0,则只查询父数据
			if(null != request.getParameter("isParent")){
				params.put("isParent",request.getParameter("isParent"));//设置查询条件,0:为顶级,1:为子集
			}
			if(null != request.getParameter("parentId")){
				params.put("parentId",request.getParameter("parentId"));
			}

			diseaseCategories = diseaseCategoryBiz.queryList(params);

		} catch (Exception e) {
			logger.error("[DiseaseCategoryController.categorys]----> erro:", e);
		}
		return diseaseCategories;
	}
}
