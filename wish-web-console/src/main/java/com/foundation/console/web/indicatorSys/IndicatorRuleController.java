package com.foundation.console.web.indicatorSys;

import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.indicatorSys.*;
import com.foundation.service.common.ErrorCode;
import com.foundation.service.common.IndicatorConstants;
import com.foundation.service.common.RespondMsgUtil;
import com.foundation.service.indicator.biz.*;
import com.foundation.service.indicator.service.IIndicatorRuleGroupService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName: IndicatorController
 * @Description: 指标规则
 * @author wangsen
 * @date 2016/11/23 15:08
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/indicatorRule")
public class IndicatorRuleController extends BaseController {

    private Log logger = LogFactory.getLog(IndicatorRuleController.class);

    @Autowired
    IIndicatorBiz indicatorBiz;

    @Autowired
    IIndicatorRuleBiz indicatorRuleBiz;

    @Autowired
    IIndicatorRuleItemBiz indicatorRuleItemBiz;

    @Autowired
    IIndicatorRuleGroupBiz indicatorRuleGroupBiz;

    @Autowired
    IIndicatorRuleExpItemsBiz indicatorRuleExpItemsBiz;

    /**
     * 跳转到指标规则编辑页面
     *
     * @return
     */
    @RequestMapping("/indicatorRulePage")
    public String toIndicatorRulePage(String indicatorId, Model model) {
        try {
            //检查参数
           if(indicatorId==null){
                logger.error("跳转到指标规则编辑页面入参不正确"+",,,,,indicatorId:"+indicatorId);
                return null;
            }
            //查询指标信息
            Indicator indicator = indicatorBiz.queryById(indicatorId);
            IndicatorRule indicatorRuleInfo= indicatorRuleBiz.getIndRuleByIndId(indicatorId);

            //查询指标规则组及指标规则细信息
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("indicatorId",indicatorId);
            List<IndicatorRuleGroupDO> ruleGroupList = indicatorRuleGroupBiz.queryRuleGroupAndRules(params);
            model.addAttribute("ruleGroupList", ruleGroupList);

            if(indicatorRuleInfo==null){
                indicatorRuleInfo = new IndicatorRule();
                indicatorRuleInfo.setIndicatorId(indicatorId);
            }
            indicatorRuleInfo.setIndicatorName(indicator.getName());

            //查询指标颜色值
            Map<String,String> colorMap= indicatorRuleItemBiz.getRuleItemMapByGroup(IndicatorConstants.BASEDATA_COLOR);
            model.addAttribute("colorMap", colorMap);
            model.addAttribute("indicatorRuleInfo", indicatorRuleInfo);
        } catch (Exception e) {
            logger.error("[IndicatorRuleController.toIndicatorRulePage] error:::" + e);
        }

        return "modules/indicatorSys/indicatorRuleList";
    }

    /**
     * 根据指标编号获取指标规则列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/indicatorRules")
    public List indicatorRules(@RequestParam(required = true) String indicatorId) {

        List indicatorRules = null;
        List indicatorRuleGroup = null;

        try {
            //获取规则组
            indicatorRuleGroup = indicatorRuleGroupBiz.getIndRuleGroupByIndId(indicatorId);
            //获取规则
            indicatorRules = indicatorRuleBiz.getIndRulesByIndId(indicatorId);

            indicatorRuleGroup.addAll(indicatorRules);
        } catch (Exception e) {
            logger.error("根据指标id查询指标规则异常" + e);
        }

        return indicatorRuleGroup;
    }

    /**
     * @Description 跳转到添加规则组页面
     * @return
     */
    @RequestMapping("/addRuleGroupPage")
    public String addRuleGroupPage(@RequestParam(required = true) String indicatorId,Model model){
        //检查参数
        if(StringUtils.isEmpty(indicatorId)){
            logger.error("[IndicatorRuleController.addRuleGroupPage],,,跳转到添加指标规则组页面指标id为null");
            return null;
        }
        //查询参数组
        List<IndicatorRuleItems> indicatorRuleItems = null;
        try {

            indicatorRuleItems = indicatorRuleItemBiz.getIndicatorRuleItems();
            model.addAttribute("ruleItems",indicatorRuleItems);
            model.addAttribute("indicatorId",indicatorId);
        } catch (Exception e) {
            logger.error("[IndicatorRuleController.addRuleGroupPage] error:::" + e);
        }
        return "modules/indicatorSys/indicatorRuleGroupAdd";
    }


    /**
     * 获取规则参数项
     *
     * @return
     */
/*    @ResponseBody
    @RequestMapping("/indicatorRuleItems")
    public List<IndicatorRuleItems> getIndicatorRuleItems() {
        List<IndicatorRuleItems> indicatorRuleItems = null;
        try {

            indicatorRuleItems = indicatorRuleItemBiz.getIndicatorRuleItems();
        } catch (Exception e) {
            logger.error("[IndicatorRuleController.getIndicatorRuleItems] error:::" + e);
        }
        return indicatorRuleItems;
    }*/

    /**
     * 添加规则组
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addIndicatorGroup")
    public String addIndicatorGroup(IndicatorRuleGroup indicatorRuleGroup) {
        String msg = "";
        try {
            //查询数据库此分组是否存在
            if (StringUtils.isNotEmpty(indicatorRuleGroup.getItemCodes())&& indicatorRuleGroup.getIndicatorId()!=null) {
                IndicatorRuleGroup ruleGroup = indicatorRuleGroupBiz.getIndRuleGroupByParams(indicatorRuleGroup.getIndicatorId(), indicatorRuleGroup.getItemCodes());
                //如果数据库存在，则返回信息，如果不存在插入数据
                if (ruleGroup == null) {
                    indicatorRuleGroupBiz.saveIndicatorRuleGroup(indicatorRuleGroup);
                    msg = RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
                } else {
                    msg = RespondMsgUtil.respondMsg(ErrorCode.DATA_EXISTED, null);
                }
            } else {
                msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, indicatorRuleGroup);
                logger.error("addIndicatorGroup入参不正确，指标id:"+ indicatorRuleGroup.getIndicatorId()+"参数项："+ indicatorRuleGroup.getItemCodes());
            }
        } catch (Exception e) {
            logger.error("指标规则编辑-添加规则组异常：" + e);
            msg = RespondMsgUtil.respondMsg(ErrorCode.INSERT_FAILED, null);
        }
        return msg;
    }

    /**
     * 删除规则组
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delIndRuleGroup")
    public String delIndRuleGroup(String groupId) {
        String msg = "";
        if (groupId == null) {
            msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, null);
        } else {
            try {
                indicatorRuleBiz.delIndRules(groupId);
                msg = RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
            } catch (Exception e) {
                logger.error("删除指标规则组异常" + e);
                msg = RespondMsgUtil.respondMsg(ErrorCode.DELETE_FAILED, null);
            }
        }
        return msg;
    }


    //-------------------------------------------------------------

    /**
     * 跳转到规则添加页面
     *
     * @param itemCodes
     * @return
     */
    @RequestMapping("/ruleAddPage")
    public String ruleAddPage(String itemCodes, String indicatorId, String groupId, Model model) {
        try {
            if (StringUtils.isNotEmpty(itemCodes) && indicatorId != null && groupId != null) {
                //查询规则编辑项
                String[] itemsCodes = itemCodes.split(IndicatorConstants.SYMBOL_COLON);
                List<IndicatorRuleItems> iIndicatorRuleItemses = indicatorRuleItemBiz.getRuleItemsByCodes(itemsCodes);
                //查询指标颜色值
                List<Map<String,Object>> colorList= indicatorRuleItemBiz.getRuleItemByGroup(IndicatorConstants.BASEDATA_COLOR);

                model.addAttribute("ruleItemses", iIndicatorRuleItemses);
                model.addAttribute("indicatorId", indicatorId);
                model.addAttribute("groupId", groupId);
                model.addAttribute("colors",colorList);

            } else {
                logger.error("Class: IndicatorController;跳转到规则添加页面入参不正确，itemCodes:" + itemCodes
                        + "----- indicatorId=" + indicatorId + "-----groupId=" + groupId);
            }
        } catch (Exception e) {
            logger.error("加载规则编辑页面异常：" + e);
        }

        return "modules/indicatorSys/ruleAddPage";
    }

    /**
     * 跳转到规则编辑页面
     *
     * @param indruleId
     * @return
     */
    @RequestMapping("/ruleEditPage")
    public String ruleEditPage(String indruleId,Model model) {
        try {
            if (indruleId != null) {
                //查询规则编辑项
                List<IndicatorRuleExpItemsDO> IndicatorruleExpItems = indicatorRuleExpItemsBiz.getRuleExpItemsRuleId(indruleId);
                //查询规则信息
                IndicatorRule indicatorRule = indicatorRuleBiz.getIndRuleById(indruleId);
                //查询指标颜色值
                List<Map<String,Object>> colorList= indicatorRuleItemBiz.getRuleItemByGroup(IndicatorConstants.BASEDATA_COLOR);

                model.addAttribute("indicatorRule",indicatorRule);
                model.addAttribute("ruleItems", IndicatorruleExpItems);
                model.addAttribute("colors",colorList);
            } else {
                logger.error("Class: IndicatorController;跳转到规则编辑页面入参不正确，indruleId:" + indruleId);
            }
        } catch (Exception e) {
            logger.error("加载规则编辑页面异常：" + e);
        }

        return "modules/indicatorSys/ruleEditPage";
    }


    /**
     * 添加指标规则
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addIndicatorRule")
    public String addIndicatorRule(@RequestBody IndicatorRuleVO indicatorRuleVO) {
        String msg = "";
        //判断主参数是否为空
        try {
            if (null != indicatorRuleVO && indicatorRuleVO.getIndicatorId() != null && indicatorRuleVO.getGroupId() != null && indicatorRuleVO.getRuleExpItems().size() > 0) {
                indicatorRuleBiz.addIndicatorRule(indicatorRuleVO);
                msg = RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
            } else {
                msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, null);
                logger.error("添加指标规则入参不正确，ruleParam:" + indicatorRuleVO);
            }
        } catch (Exception e) {
            logger.error("添加指标规则及其表达式子项时异常，ruleParam:" + indicatorRuleVO +"\r\n异常为：" + e);
            return msg = RespondMsgUtil.respondMsg(ErrorCode.INSERT_FAILED, null);
        }
        return msg;
    }

    /**
     * 更新指标规则
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateIndicatorRule")
    public String updateIndicatorRule(@RequestBody IndicatorRuleVO indicatorRuleVO) {
        String msg = "";
        //判断主参数是否为空
        try {
            if (null != indicatorRuleVO && indicatorRuleVO.getRuleExpItems().size() > 0) {
                indicatorRuleBiz.editIndicatorRule(indicatorRuleVO);
                msg = RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
            } else {
                msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, null);
                logger.error("添加指标规则入参不正确，ruleParam:" + indicatorRuleVO);
            }
        } catch (Exception e) {
            logger.error("添加指标规则及其表达式子项时异常，e:" + e);
            return msg = RespondMsgUtil.respondMsg(ErrorCode.UPDATE_FAILED, null);
        }
        return msg;
    }

    /**
     * 删除指标规则
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRule")
    public String delIndicatorRule(String id) {

        String msg = "";
        if (id == null) {
            msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, null);
        } else {
            try {
                indicatorRuleBiz.delIndRule(id);
            } catch (Exception e) {
                logger.error("删除指标规则异常" + e);
                msg = RespondMsgUtil.respondMsg(ErrorCode.DELETE_FAILED, null);
            }
        }
        return msg;

    }

    /**
     * 更新指标规则附加信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateIndRuleAppendInfo")
    public String updateIndRuleAppendInfo(IndicatorRule indRule) {
        String msg = "";
        //判断主参数是否为空
        try {
            if(indRule.getIndicatorId()!=null){
                indicatorRuleBiz.updateRuleAppendInfo(indRule);
                msg = RespondMsgUtil.respondMsg(ErrorCode.SUCCESS, null);
            } else {
                msg = RespondMsgUtil.respondMsg(ErrorCode.PARAM_FORMAT, null);
                logger.error("添加指标规则入参不正确，indRule:" + indRule);
            }
        } catch (Exception e) {
            logger.error("更新指标规则附加信息时异常，ruleParam:" + e);
            return msg = RespondMsgUtil.respondMsg(ErrorCode.UPDATE_FAILED, null);
        }
        return msg;
    }




    //-----------------------------------------------------测试
    /**
     * 测试指标规则匹配
     */
    @ResponseBody
    @RequestMapping("/testIndRule")
    public IndicatorRuleInfo testMatchRule() {
        IndicatorRuleInfo ruleInfo =null;
        Map<String,Object> params = new HashMap<>();
        params.put("indicatorCode","test"); //指标编码，必选
        params.put("sex","2");//性别
        params.put("value","3");//比对值，必选
        params.put("age","20");//比对值，必选

        params.put("indicatorCode","TG"); //指标编码，必选
        params.put("sex","2");//性别：0,1,2，必选
        params.put("value","4");//比对值，必选


        //测试体重
        params.put("indicatorCode","weight"); //指标编码，必选
        params.put("value","60");//比对值，必选
        params.put("height","60");

        //测试腰围
        params.put("indicatorCode","waistline"); //指标编码，必选
        params.put("value","64");//比对值，必选
        params.put("height","182");

        try {
            ruleInfo= indicatorRuleBiz.getIndRuleResult(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleInfo;
    }

    /**
     * 测试指标规则匹配2,入参从url传
     */
    @ResponseBody
    @RequestMapping("/testRule")
    public IndicatorRuleInfo testRule(HttpServletRequest request) {
        IndicatorRuleInfo ruleInfo =null;

        Map params =getParameterMap(request);

        try {
            ruleInfo= indicatorRuleBiz.getIndRuleResult(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruleInfo;
    }
    @Autowired
    public IIndicatorRuleGroupService indicatorRuleGroupService;
    @ResponseBody
    @RequestMapping("/testRuleGroupVO")
    public Map<String,IndicatorRuleGroupDO> test1() throws Exception{
        return null /*indicatorRuleGroupService.getIndRuleAndGroup("1459934433569")*/;

    }

    @ResponseBody
    @RequestMapping("/testRuleIntervalVO")
    public List<IndicatorRuleIntervalDO> test2() throws Exception{
        Map<String,String> params = new HashMap<>();
        params.put("indicatorCode","RBC"); //指标编码，必选
        params.put("sex","2");//性别：0,1,2，必选
        params.put("value","4");//比对值，必选

        List<Long> ids = new ArrayList<>();
        ids.add(1459934433635L);
        ids.add(1459934433636L);
        ids.add(1459934433637L);
        ids.add(1459934433638L);
        ids.add(1459934433639L);
        ids.add(1459934433640L);
       // return iIndicatorRuleBiz.getIndRuleInterval(ids, params);
        return null;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}