package com.foundation.service.indicator.biz.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.indicatorSys.*;
import com.foundation.service.common.IndicatorConstants;
import com.foundation.service.common.ScriptEngineInstance;
import com.foundation.service.dist.service.IDistArchiveIndicatorService;
import com.foundation.service.indicator.biz.IIndicatorBiz;
import com.foundation.service.indicator.biz.IIndicatorRuleBiz;
import com.foundation.service.indicator.service.IIndicatorRuleExpItemsService;
import com.foundation.service.indicator.service.IIndicatorRuleGroupService;
import com.foundation.service.indicator.service.IIndicatorRuleItemService;
import com.foundation.service.indicator.service.IIndicatorRuleService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangsen
 * @version V1.0
 * @ClassName: IndicatorRuleBizImpl
 * @Description: @TODO
 * @date 2016/11/21 14:10
 */
@Service
public class IndicatorRuleBizImpl implements IIndicatorRuleBiz {

    private Log logger = LogFactory.getLog(IndicatorRuleBizImpl.class);
    @Autowired
    private IIndicatorBiz indicatorInfoBiz;

    @Autowired
    private IIndicatorRuleService indicatorRuleService;

    @Autowired
    private IDistArchiveIndicatorService indicatorService;

    @Autowired
    private IIndicatorRuleGroupService indicatorRuleGroupService;

    @Autowired
    private IIndicatorRuleItemService indicatorRuleItemService;
    @Autowired
    private IIndicatorRuleExpItemsService indicatorRuleExpItemsService;


    /**
     * @param params          包含规则匹配相关入参的map
     * @param groupRuleParams 规则组的参数项（如：value:sex）
     * @return
     * @Description 获取规则组的参数项（如value:sex）中参数项类型为多选（既item_type=0）的项,及在入参map中的对应的键值对值
     */
    public Map<String, String> matchItemsFromParam(Map<String, String> params, String groupRuleParams) throws Exception {
        Map<String, String> matchedItem = new HashMap<>();//保存匹配的项
        //获取类别为多选值得项，用于摘取入参params
        Map<String, String> itemMap = indicatorRuleItemService.getRuleItemByGroupAndType(IndicatorConstants.BASEDATA_PARAM, IndicatorConstants.BASEDATA_PARAM_TYPE_0);
        for (String param : groupRuleParams.split(IndicatorConstants.SYMBOL_COLON)) {
            if (itemMap.get(param) != null) {
                matchedItem.put(param, params.get(param));
            }
        }
        //这段if判断代码完全是为了兼容老指标规则性别=0即不区分性别，现在不区分性别不需要添加性别项
        if (matchedItem.containsKey("sex") && matchedItem.get("sex").equals("0")) {
            matchedItem.remove("sex");
        }
        return matchedItem;
    }

    /**
     * @Description 获取刻度尺信息
     * @param params
     * @return
     */
/*    public Set<Long> getScaleSet(Map<String,Object> params) throws Exception{
        Set<Long> scale = new TreeSet<>();
        Map<String,String> itemList = matchItemsFromParam(convertParams(params));



        return scale;
    }*/


    /**
     * @param params
     * @return
     * @Description 参数格式转换Object2String
     * （之前定义入参为Map<String,String>,有的调用方位Map<String,Object>这里统一做转换）
     */
    public Map<String, String> convertParams(Map<String, Object> params) {
        Map<String, String> paramMap = new HashMap<>();
        for (String key : params.keySet()) {
            paramMap.put(key, String.valueOf(params.get(key) == null ? "" : params.get(key)));
        }
        return paramMap;
    }


    /**
     * @param paramMap 入参Map，包含指标code,value及其他附加信息
     * @return
     * @throws Exception
     * @Description 指标值相关参数匹配相应指标规则相关信息
     */
    @Override
    public IndicatorRuleInfo getIndRuleResult(Map<String, Object> paramMap) throws Exception {
        if (paramMap.get(IndicatorConstants.INDICATOR_CODE) == null || paramMap.get(IndicatorConstants.VALUE) == null) {
            logger.error(IndicatorRuleBizImpl.class + "必选入参未传值！！！indexCode:" + paramMap.get(IndicatorConstants.INDICATOR_CODE)
                    + "-----value:" + paramMap.get(IndicatorConstants.VALUE));
            return null;
        }
        IndicatorRuleInfo ruleInfo = null;
        IndicatorRule indicatorRule = null;

        Map<String, String> params = convertParams(paramMap);//统一类型转换Object2String

        //获取指标信息
        Map<String, Object> indParams = new HashMap<>();
        indParams.put("code", params.get(IndicatorConstants.INDICATOR_CODE));
        List<Indicator> list = indicatorInfoBiz.queryList(indParams);
        if (null == list && list.size() == 0) {
            logger.error("指标规则匹配getIndRuleResult()入参指标编码未查询到指标记录，传入参数:" + params.toString());
            return null;
        }
        Indicator indicatorInfo = list.get(0);

        //查询指标规则及组相关信息，调用匹配方法
        Map<String, Object> sparam = new HashMap<String, Object>();
        sparam.put("code", params.get(IndicatorConstants.INDICATOR_CODE));
        Map<String, IndicatorRuleGroupDO> ruleGroupDO = toIndicatorRuleGroupMap(indicatorRuleGroupService.getIndGroupAndRules(sparam));
        if (ruleGroupDO != null && ruleGroupDO.size() > 0) {
            indicatorRule = matchRule(ruleGroupDO, params);
        }

        if (indicatorRule != null) {
            ruleInfo = new IndicatorRuleInfo();
            String groupId = indicatorRule.getGroupId();//获取匹配成功或匹配过的规则组的ID
            //传入参数匹配的相关规则区间值(执行脚本后的具体数值)
   /*         List<IndicatorRuleIntervalDO> ruleIntervalDO = getIndRuleInterval(ruleGroupDO.get(groupId).getRuleIds()
                    , params, ruleGroupDO.get(groupId).getItemCodes());
            //获取刻度尺
            Set<Double> scaleSet = getScaleSet(ruleIntervalDO);*/
            //拷贝基础参数
            BeanUtils.copyProperties(ruleInfo, indicatorRule);//如果要优化，则手动set

            //设置附加参数
            ruleInfo.setMsg(indicatorRule.getConclusion());//这里为什么msg要设置，是因为这里定义的“提示建议”变量名与返回要求的实体bean字段不一致
            /*ruleInfo.setIntervalValue(JSONObject.toJSONString(ruleIntervalDO));*/
            ruleInfo.setIndicatorId(indicatorInfo.getId());
            ruleInfo.setIndicatorName(indicatorInfo.getName());
            ruleInfo.setIndicatorType(params.get(IndicatorConstants.INDICATOR_CODE));
            ruleInfo.setIsSaveArchives(indicatorInfo.getArchiveFlag());
            ruleInfo.setValue(params.get(IndicatorConstants.VALUE));
/*            ruleInfo.setScaleList((TreeSet<Double>) scaleSet);
            if (scaleSet != null && scaleSet.size() > 0) {
                ruleInfo.setMinVal(((TreeSet<Double>) scaleSet).first());
                ruleInfo.setMaxVal(((TreeSet<Double>) scaleSet).last());
            }*/

            //判断如果匹配返回的规则bean这几个字段为空说明有表达式进行匹配过但是没匹配合适的，这里设置相关默认值
            if (indicatorRule.getValueTab() == null && indicatorRule.getColor() == null) {
                ruleInfo.setDefaultMin(ruleGroupDO.get(groupId).getIndicatorRules().get(0).getDefaultMin());
                ruleInfo.setDefaultMax(ruleGroupDO.get(groupId).getIndicatorRules().get(0).getDefaultMax());
                ruleInfo.setUnit(ruleGroupDO.get(groupId).getIndicatorRules().get(0).getUnit());
            }

            //################### need modify下期，会为3种返回结果类添加表示字段，这期先将查询不到结果返回null,而不是返回默认值
            if (-1L != Double.valueOf(params.get(IndicatorConstants.VALUE)) && indicatorRule.getId() == null) {
                return null;
            }

        } else {
            logger.error("指标规则匹配----未匹配到指标规则，getIndRuleResult()传入参数:" + params.toString());
            return null;
        }

        //logger.error("指标匹配结果------------------------------："+ JSONObject.toJSONString(ruleInfo));
        return ruleInfo;
    }

    /**
     * @Description list转Map兼容之前数据结构
     * @param indGroupAndRules
     * @return
     */
    private Map<String,IndicatorRuleGroupDO> toIndicatorRuleGroupMap(List<IndicatorRuleGroupDO> indGroupAndRules) {
        if(null ==indGroupAndRules || indGroupAndRules.size()==0){
            return null;
        }
        Map<String,IndicatorRuleGroupDO> map= new HashedMap();
        for(IndicatorRuleGroupDO bean: indGroupAndRules){
            map.put(bean.getRuleGroupId(),bean);
        }
        return map;
    }


    /**
     * @param ruleIntervalVO 规则组内规则列表
     * @return
     * @Description 获取刻度尺
     */
    private Set<Double> getScaleSet(List<IndicatorRuleIntervalDO> ruleIntervalVO) {
        Set<Double> scaleSet = new TreeSet<>();
        for (IndicatorRuleIntervalDO ruleInterval : ruleIntervalVO) {
            for (String v : ruleInterval.getValue())
                scaleSet.add(Double.valueOf(v));
        }
        return scaleSet;
    }

    /**
     * @param indicatorRules 传入指标查询的相关指标规则
     * @param params         入参map
     * @return
     * @Description 通过js脚本匹配规则
     * 处理逻辑：
     * 1、传入的规则列表已经是按照表达式占位符个数倒序排序的；
     * 2、循环规则列表，替换表达式项，如果都可从map中get到则执行，执行结果为true则直接返回匹配的规则实体；
     * 3、未匹配到则继续第2步，如果有执行匹配，但未匹配到合适的（说明入参适合此规则组规则），则新建规则实体设置实体的parentId(即：规则组Id)返回；
     * 4、循环结束如果未有合适的规则组，则返回null；
     */
    private IndicatorRule matchRule(Map<String, IndicatorRuleGroupDO> indicatorRules, Map<String, String> params) {
        IndicatorRule indRuleInfo = null;
        Object result = false;
        String mapKey = null;
        String expression = null;

        //是否占位符->参数匹配过
        boolean matchedFlag = false;

        //正则分割填充项用于在map中获取key->value
        Pattern pattern = Pattern.compile(IndicatorConstants.MATCH_EXPRESSION);
        Matcher matcher = null;

        //-----------------------（待补充，看心情就补充下吧，相应场景会提高命中的），入参除去indexCode外所有参数升序排序，
        // ＂:＂间隔拼与IndicatorRuleGroupVO.ruleParams比较，如果相等则直接匹配此表达式规则组下的规则，
        // 匹配与否返回此组表达式相关信息，若未找到相关组则执行如下循环（倒序遍历匹配）

        try {
            //获得脚本引擎对象
            ScriptEngine engine = ScriptEngineInstance.getInstance();
            //-----------------------按指标表达式占位符格式倒序遍历执行
            OUTER:
            //匹配规则开始
            //外层循环
            for (Map.Entry<String, IndicatorRuleGroupDO> indRuleGroup : indicatorRules.entrySet()) {
                matchedFlag = false;
                //内层指标规则列表循环
                for (IndicatorRule indRule : indRuleGroup.getValue().getIndicatorRules()) {
                    expression = indRule.getExpression();
                    matcher = pattern.matcher(expression);
                    while (matcher.find()) {
                        mapKey = matcher.group(1);
                        //Set<String> keySet = new HashSet<String>();//这里可以将matche的匹配项放入set中去重
                        //keySet.add(mapKey);

                        //如果有占位符Map中未get到，直接跳出这组指标规则循环继续下一组
                        if (StringUtils.isEmpty(params.get(mapKey))) {
                            continue OUTER;
                        }
                        expression = expression.replace(mapKey, params.get(mapKey));
                    }

                    //表达式中的占位符Map中都可以在找到替换则执行表达式
                    result = engine.eval(expression);

                    //执行结果，匹配成功返回匹配到的表达式对象，如果未匹配成功则设置匹配状态为true,说明匹配过
                    if ((boolean) result) {
                        logger.error("匹配的表达式:::::" + expression);
                        return indRule;
                    } else {
                        matchedFlag = true;
                    }
                }
                //如果在此组中匹配过了，但是没有匹配到,new对象设置基本信息
                if (matchedFlag) {
                    indRuleInfo = new IndicatorRule();

                    indRuleInfo.setGroupId(indRuleGroup.getValue().getRuleGroupId());

                    return indRuleInfo;
                }

            }
        } catch (ScriptException e) {
            logger.error("脚本引擎执行规则匹配异常，执行表达式：" + expression + "异常信息:" + e);

        } catch (Exception e) {
            logger.error("规则匹配过程异常：" + e);
        }
        return null;
    }

    /**
     * @param ruleIds         规则组内规则id组
     * @param params          初始的入参Map
     * @param groupRuleParams 规则组规则编码字串（如value:sex）
     * @return
     * @Description 获取指标规则匹配后的相关区间值，并格式化区间值（含表达式）
     */
    @Override
    public List<IndicatorRuleIntervalDO> getIndRuleInterval(List<String> ruleIds, Map<String, String> params, String groupRuleParams) throws Exception {
        Map<String, String> innerItem = matchItemsFromParam(params, groupRuleParams);
        List<IndicatorRuleIntervalDO> ruleIntervalVO = indicatorRuleService.getIndRuleInterval(ruleIds, innerItem);

        //正则分割填充项用于在map中获取key->value
        Pattern pattern = Pattern.compile(IndicatorConstants.MATCH_EXPRESSION);
        Matcher matcher = null;
        String mapKey = null;
        Object result = null;//表达式计算结果
        Set<String> temValue = null;//记录计算后中间结果

        //获得脚本引擎对象
        ScriptEngine engine = ScriptEngineInstance.getInstance();
        //计算最终区间值
        for (IndicatorRuleIntervalDO ruleInterval : ruleIntervalVO) {
            temValue = new TreeSet<>();
            for (String val : ruleInterval.getValue()) {
                matcher = pattern.matcher(val);
                //如果值为表达式则替换占位符执行获取结果,如果不是直接放入List
                if (matcher.groupCount() > 0) {
                    while (matcher.find()) {
                        mapKey = matcher.group(1);
                        val = val.replace(mapKey, params.get(mapKey));
                    }
                    //表达式中的占位符Map中都可以在找到替换则执行表达式
                    result = engine.eval(IndicatorConstants.REGEXP_FIXED2.replace("$exp", val));
                    temValue.add(String.valueOf(result));
                } else {
                    temValue.add(val);
                }
                ruleInterval.setValue(temValue);
            }
        }
        return ruleIntervalVO;
    }

    @Override
    public List<IndicatorRule> getIndRulesByIndId(String indicatorId) throws Exception {
        return indicatorRuleService.getIndRulesByIndId(indicatorId);
    }

    @Override
    public IndicatorRule getIndRuleByIndId(String indicatorId) throws Exception {
        return indicatorRuleService.getIndRuleByIndId(indicatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delIndRules(String groupId) throws Exception {
        indicatorRuleGroupService.delIndRuleGroupById(groupId);
        indicatorRuleService.delIndRulesByGroupId(groupId);
    }

    @Override
    public void delIndRule(String id) throws Exception {
        indicatorRuleService.delIndRuleById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addIndicatorRule(IndicatorRuleVO indicatorRuleVO) throws Exception {

        //生成指标规则主键
        String id = IdGen.uuid();
        //保存设置规则主键后的指标规则表达式项
        List<IndicatorRuleExpItems> ruleExpItems = new ArrayList<>();

        //拼接规则表达式
        StringBuffer expression = new StringBuffer();
        for (IndicatorRuleExpItems ruleItem : indicatorRuleVO.getRuleExpItems()) {
            expression.append(ruleItem.getItemCode()).append(ruleItem.getItemOpt()).append(ruleItem.getItemValue()).append(IndicatorConstants.SYMBOL_AND);
            //设置表达式项的规则ID
            ruleItem.setIndruleId(id);
            //设置表达式项的主键Id
            ruleItem.setId(IdGen.uuid());
            ruleExpItems.add(ruleItem);
        }
        expression.delete(expression.length() - 2, expression.length());
        //创建指标规则类
        IndicatorRule indsRule = new IndicatorRule();
        BeanUtils.copyProperties(indsRule, indicatorRuleVO);
        indsRule.setId(id);
        indsRule.setExpression(expression.toString());
        indsRule.setItemCount(calItemCount(expression.toString()));
        indsRule.setColor(indicatorRuleVO.getColor());
        indsRule.setConclusion(indicatorRuleVO.getConclusion());
        indsRule.setSuggest(indicatorRuleVO.getSuggest());
        indsRule.setValueTab(indicatorRuleVO.getValueTab());
        indsRule.setIndicatorId(indicatorRuleVO.getIndicatorId());
        indsRule.setGroupId(indicatorRuleVO.getGroupId());
        //保存指标规则
        indicatorRuleService.saveIndicatorRule(indsRule);

        //保存指标表达式项
        indicatorRuleExpItemsService.saveIndRuleExpItems(ruleExpItems);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editIndicatorRule(IndicatorRuleVO indicatorRuleVO) throws Exception {
        try{
            //设置指标规则相关属性用于更新
            IndicatorRule indicatorRule = new IndicatorRule();
            BeanUtils.copyProperties(indicatorRule, indicatorRuleVO);
            indicatorRule.setId(indicatorRuleVO.getIndruleId());

            //拼接规则表达式,生成新表达式
            StringBuffer expression = new StringBuffer();
            for (IndicatorRuleExpItems ruleItem : indicatorRuleVO.getRuleExpItems()) {
                expression.append(ruleItem.getItemCode()).append(ruleItem.getItemOpt()).append(ruleItem.getItemValue()).append(IndicatorConstants.SYMBOL_AND);
            }
            expression.delete(expression.length() - 2, expression.length());
            indicatorRule.setExpression(expression.toString());
            indicatorRule.setItemCount(calItemCount(expression.toString()));
            //更新规则
            indicatorRuleService.updateIndicatorRule(indicatorRule);
            //更新规则表达式项
            indicatorRuleExpItemsService.updateIndRuleExpItems(indicatorRuleVO.getRuleExpItems());
        }catch(Exception e){
            logger.error("更新表达式异常" + e);
        }

    }


    /**
     * @return
     * @throws Exception
     * @Description 计算表达式中占位符个数
     */
    Integer calItemCount(String exp) throws Exception {
        Pattern pattern = Pattern.compile(IndicatorConstants.MATCH_EXPRESSION);
        Set<String> itemSet = new TreeSet<>();
        Matcher matcher = null;
        try {
            matcher = pattern.matcher(exp);
            while (matcher.find()) {
                itemSet.add(matcher.group(1));
            }
        } catch (Exception e) {

        }
        return itemSet.size();
    }


    @Override
    public IndicatorRule getIndRuleById(String id) throws Exception {
        return indicatorRuleService.getIndRuleById(id);
    }

    @Override
    public void updateRuleAppendInfo(IndicatorRule indRule) throws Exception {
        indicatorRuleService.updateIndicatorRule(indRule);
    }

}
