package com.foundation.service.indicatorSys.test;


import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.service.common.IndicatorConstants;
import com.foundation.service.indicator.service.IIndicatorRuleItemService;
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
 * @ClassName: IndicatorRuleItemTest
 * @Description: 指标规则字典测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class IndicatorRuleItemTest {
	@Autowired
	private IIndicatorRuleItemService indicatorRuleItemService;


	@Test
	public void getIndicatorRuleItemsTest() {
		try{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("itemGroup", IndicatorConstants.BASEDATA_PARAM);
			List<IndicatorRuleItems> list = indicatorRuleItemService.getIndicatorRuleItems(params);
			assertThat(list).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@Test
	public void getRuleItemsByCodesTest(){
		try{
			String[] codes = new String[]{"sex","age"};
			List<IndicatorRuleItems> list = indicatorRuleItemService.getRuleItemsByCodes(codes);
			assertThat(2L).isEqualTo(list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void getRuleItemByGroupTest(){
		try{
			List<Map<String, Object>> list = indicatorRuleItemService.getRuleItemByGroup("sex");
			assertThat(list).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Test
	public void getRuleItemMapByGroupTest(){
		try{
			Map<String, String> map= indicatorRuleItemService.getRuleItemMapByGroup("indColor");
			assertThat(map).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@Test
	public void getRuleItemByGroupAndTypeTest(){
		try{
			Map<String, String> map=indicatorRuleItemService.getRuleItemByGroupAndType("param","1");
			assertThat(map).isNotNull();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
