package com.foundation.service.indicatorSys.test;


import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItems;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;
import com.foundation.service.indicator.service.IIndicatorRuleExpItemsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorRuleTest
 * @Description: 指标规则表达式项测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class IndicatorRuleExpItemsTest {
	@Autowired
	private IIndicatorRuleExpItemsService indicatorRuleExpItemsService;

	private final String id = "TEST-ID-00000";
	private final String indruleId = "TEST-INDICATORID-000001";
	private final String ruleGroupId = "TEST-GROUPID-000001";
	private final String itemCode = "value";

	@Test
	public void saveIndRuleExpItemsTest() {
		try{
			List<IndicatorRuleExpItems> ruleExpItems = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				IndicatorRuleExpItems ruleExpItem = new IndicatorRuleExpItems();
				ruleExpItem.setId(id+i);
				ruleExpItem.setIndruleId(indruleId);
				ruleExpItem.setItemCode(itemCode+i);
				ruleExpItems.add(ruleExpItem);
			}

			indicatorRuleExpItemsService.saveIndRuleExpItems(ruleExpItems);
		}catch(Exception e){
		    e.printStackTrace();
		}

	}

	@Test
	public void updateIndRuleExpItemsTest(){
		try{
			List<IndicatorRuleExpItems> ruleExpItems = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				IndicatorRuleExpItems ruleExpItem = new IndicatorRuleExpItems();
				ruleExpItem.setId(id+i);
				ruleExpItem.setItemOpt(">");
				ruleExpItem.setItemValue("1"+i);
				ruleExpItems.add(ruleExpItem);
			}

			indicatorRuleExpItemsService.updateIndRuleExpItems(ruleExpItems);
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

	@Test
	public void deleteByIndruleIdTest(){
		try{
			indicatorRuleExpItemsService.deleteByIndruleId(indruleId);
		}catch(Exception e){
		    e.printStackTrace();
		}
	}

	@Test
	public void deleteByruleGroupIdTest(){
		try{
			indicatorRuleExpItemsService.deleteByRuleGroupId(ruleGroupId);
		}catch(Exception e){
		    e.printStackTrace();
		}
	}


	@Test
	public void getRuleExpItemsRuleIdTest(){
		try{
			List<IndicatorRuleExpItemsDO> list = indicatorRuleExpItemsService.getRuleExpItemsRuleId("1458007941833");
			assertThat(list).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
