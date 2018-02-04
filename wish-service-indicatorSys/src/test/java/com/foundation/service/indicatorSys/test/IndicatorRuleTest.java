package com.foundation.service.indicatorSys.test;


import com.foundation.dao.entity.indicatorSys.IndicatorRule;
import com.foundation.service.indicator.service.IIndicatorRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorRuleTest
 * @Description: 指标规则测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class IndicatorRuleTest {
	@Autowired
	private IIndicatorRuleService indicatorRuleService;

	private final String id = "TEST-ID-000001";
	private final String groupId = "TEST-GROUPID-000001";
	private final String indicatorId = "TEST-INDICATORID-000001";
	private final String indicatorCode = "TEST-INDICATORCODE-000001";

	@Test
	public void saveTest() {
		IndicatorRule indicatorRule = new IndicatorRule();
		indicatorRule.setId(id);
		indicatorRule.setGroupId(groupId);
		indicatorRule.setIndicatorCode(indicatorCode);
		indicatorRule.setIndicatorId(indicatorId);

		try {
			indicatorRuleService.saveIndicatorRule(indicatorRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest(){
		try{
			IndicatorRule indicatorRule = new IndicatorRule();
			indicatorRule.setId(id);
			indicatorRule.setSuggest("suggetst");
			indicatorRule.setIndicatorId(indicatorId);
			indicatorRuleService.updateIndicatorRule(indicatorRule);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void queryById(){
		try{
			IndicatorRule indicatorRule = indicatorRuleService.getIndRuleById(id);
			assertThat(indicatorRule).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void queryByIndicatorId(){
		try{
			List<IndicatorRule> list =indicatorRuleService.getIndRulesByIndId(indicatorId);
			assertThat(list).isNotNull();
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

	@Test
	public void delIndRuleByIdTest(){
		try{
			indicatorRuleService.delIndRuleById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void delIndRulesByGroupIdTest(){
		try{
			indicatorRuleService.delIndRulesByGroupId(groupId);
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

}
