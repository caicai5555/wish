package com.foundation.service.indicatorSys.test;


import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;
import com.foundation.service.indicator.service.IIndicatorRuleGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorRuleTest
 * @Description: 指标规则组测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class IndicatorRuleGroupTest {
	@Autowired
	private IIndicatorRuleGroupService indicatorRuleGroupService;

	private final String id = "TEST-ID-000001";
	private final String groupId = "TEST-GROUPID-000001";
	private final String indicatorId = "TEST-INDICATORID-000001";
	private final String indicatorCode = "TEST-INDICATORCODE-000001";
	private final String itemCodes = "value";
	@Test
	public void saveTest() {
		IndicatorRuleGroup bean = new IndicatorRuleGroup();
		bean.setId(id);
		bean.setItemCodes(itemCodes);
		bean.setItemNames("参数值");
		bean.setIndicatorCode(indicatorCode);
		bean.setIndicatorId(indicatorId);
		bean.setDelFlag(0);

		try {
			indicatorRuleGroupService.saveIndicatorRuleGroup(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getIndRuleGroupByParamsTest(){
		try{
			IndicatorRuleGroup bean = indicatorRuleGroupService.getIndRuleGroupByParams(indicatorId,itemCodes);
			assertThat(bean).isNotNull();
		}catch(Exception e){
		    e.printStackTrace();
		}

	}

	@Test
	public void getIndRuleGroupByIndIdTest(){
		try{
			List<IndicatorRuleGroup> list = indicatorRuleGroupService.getIndRuleGroupByIndId(indicatorId);
			assertThat(list).isNotNull();
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

	@Test
	public void delIndRuleGroupByIdTest(){
		try{
			indicatorRuleGroupService.delIndRuleGroupById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void getIndRuleAndGroupTest(){
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("indicatorCode","bloodSugar");
			List<IndicatorRuleGroupDO> indRuleAndGroup = indicatorRuleGroupService.getIndGroupAndRules(params);
			assertThat(indRuleAndGroup).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
