package com.foundation.console.web.indicatorSys;

import com.foundation.common.bean.Constants;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;
import com.foundation.service.indicator.biz.IIndicatorBiz;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @version V1.0
 * @ClassName: IndicatorController
 * @Description: 指标信息控制类
 * @date 2016/11/25 16:18
 */
@Controller
@RequestMapping(value = "/admin/indicatorInfo")
public class IndicatorController extends BaseController {

    private Log logger = LogFactory.getLog(IndicatorController.class);

    @Autowired
    private IIndicatorBiz indicatorBiz;


    /**
     * @param name
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     * @Description 指标列表
     */
    @RequestMapping("/indicatorPageList")
    public String indicatorList(@RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "1") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                Model model) {
        Map<String, Object> params = new HashMap<String, Object>();//保存条件参数
        try {
            //收集查询信息
            params.put("name", name);
            params.put("parentId", "0");

            //收集分页信息
            Page<IndicatorTreeDO> basePage = new Page<IndicatorTreeDO>(pageNo, pageSize);

            Page<IndicatorTreeDO> pageInfo = indicatorBiz.queryTree(params, basePage);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("[IndicatorController.indicatorList]  error:", e);
        } finally {
            model.addAttribute("params", params);
        }
        return "modules/indicatorSys/indicatorList";
    }

    /**
     * @param parentId
     * @param id
     * @return ModelAndView
     * @throws
     * @Description:指标添加
     */
    @RequestMapping("/indicatorAdd")
    public String indicatorAdd(String parentId, String id, Model model) {


        Indicator indicator = null;
        try {
            if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
                indicator = indicatorBiz.queryById(id);
            } else {
                indicator = new Indicator();
                if (StringUtils.isEmpty(parentId)) {
                    parentId = "0";
                }
                indicator.setParentId(parentId);
            }
        } catch (Exception e) {
            logger.error("[IndicatorController.diseaseIndexAdd]  erro:", e);
        }
        model.addAttribute("indicatorInfo", indicator);
        if ("0".equals(parentId)) {
            return "modules/indicatorSys/indicatorAdd";
        } else {
            return "modules/indicatorSys/indicatorAddChild";
        }
    }

    /**
     * @param indicator
     * @return ModelAndView
     * @throws
     * @Description:保存
     */
    @ResponseBody
    @RequestMapping("/saveIndicator")
    public String save(Indicator indicator) {

        try {

            //如果id为null为新增，反之更新
            Date date = new Date();
            if (StringUtils.isEmpty(indicator.getId())) {
                //判断编码数据库中是否存在
                Map<String, Object> params = new HashedMap();
                params.put("code", indicator.getCode());
                List<Indicator> codeList = indicatorBiz.queryList(params);
                if (null != codeList && codeList.size() > 0) {
                    return "recode";
                }
                //判断名称库中是否存在
                params = new HashedMap();
                params.put("name", indicator.getName());
                List<Indicator> nameList = indicatorBiz.queryList(params);
                if (null != nameList && nameList.size() > 0) {
                    return "rename";
                }

                indicator.setCreateDate(date);
                indicator.setUpdateDate(date);
                indicatorBiz.save(indicator);
            } else {
                indicator.setUpdateDate(date);
                indicatorBiz.update(indicator);
            }
        } catch (Exception e) {
            logger.error("[IndicatorController.save]  erro:", e);
            return "error";
        }
        return "success";
    }

    /**
     * @Description: 删除
     * @param request
     * @param response
     * @return ModelAndView
     * @throws
     */
  /*   @ResponseBody
    @RequestMapping("/delete")
   public String delete(HttpServletRequest request, HttpServletResponse response){
        try {
            String tempId = getReqValByParam("pkId");
            if(StringUtil.isNotEmpty(tempId)){
                DiseaseIndex diseaseIndex = diseaseIndexBiz.getEntityById(Long.parseLong(tempId));
//				diseaseIndex.setIsDel("1");//删除
                diseaseIndexBiz.update(diseaseIndex);
                logger.info("删除成功 tempId = "+tempId);
            }
        }  catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }*/

    /**
     * @Description: 查询指标可用列表（疾病模块通过url获取）
     * @return ModelAndView
     * @throws
     */
    @ResponseBody
    @RequestMapping("/indicatorList")
    public List<Indicator> indicatorList(){
        List<Indicator> list = null;
        try {

            Map<String,Object> map = new HashedMap();
            map.put("delFlag", Constants.DEL_FLAG_NORMAL);
            list = indicatorBiz.queryList(new HashMap());
        } catch (Exception e) {
            logger.error("[IndicatorController.indicatorList] error:------->",e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Description 查询父节点
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getParentIndicators",method= RequestMethod.GET)
    public List<Indicator> getParentIndicators(){
        List<Indicator> parentIndicators = null;
        try {
            Map<String,Object> params = new HashedMap();
            params.put("parentId","0");
            parentIndicators = indicatorBiz.queryList(params);
        } catch (Exception e) {
            logger.error("[IndicatorController.getParentIndicators]  erro:", e);
        }

        return parentIndicators;
    }
}