package com.foundation.service.indicatorSys.test;


import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;
import com.foundation.service.indicator.service.IIndicatorService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorTest
 * @Description: 指标信息测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml",
        "classpath:spring-mongodb.xml",
        "classpath:spring-db.xml" })
public class IndicatorTest {
	@Autowired
	private IIndicatorService indicatorService;

	private final String id = "TEST-ID-000001";
	private final String code = "TEST-CODE-000001";

	@Test
	public void saveTest() {
		Indicator bean = new Indicator();
		bean.setId(id);
		bean.setCode(code);
		bean.setName("name");
		bean.setRemark("remark");
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		try {
			boolean flag = indicatorService.save(bean);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryByIdTest(){
		try {
			Indicator bean = indicatorService.queryById(id);
			assertThat(bean).isNotNull();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest(){
		Indicator bean = new Indicator();
		bean.setId(id);
		bean.setName("namename");
		try {
			indicatorService.update(bean);
			Indicator bean2 = indicatorService.queryById(id);
			assertThat("namename").isEqualTo(bean2.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryPageTest(){
		try {
			Page<Indicator> pageBounds = new Page<Indicator>(1, 2);
			Map<String, Object> params = Maps.newHashMap();
			Page<Indicator> page = indicatorService.queryPage(params, pageBounds);
			assertThat(2L).isEqualTo(page.getList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryCategoryPageTest(){
		Page<Indicator> pageBounds = new Page<Indicator>(1, 5);
		Map<String, Object> params = Maps.newHashMap();
		params.put("name","血");
		Page<Indicator> page = null;
		try {
			page = indicatorService.queryCategoryPage(params, pageBounds);
			assertThat(2L).isEqualTo(page.getList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryListTest(){
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("parentId","0");
			List<Indicator> lists = indicatorService.queryList(params);
			assertThat(lists).isNotNull();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getCountTest(){
		try{
			Map<String, Object> params = Maps.newHashMap();
			int count = indicatorService.getCount(params);
			assertThat(1).isLessThan(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryTreeTest(){
		try{
			Map<String, Object> params = new HashMap<String, Object>();//保存条件参数
			//收集分页信息
			Page<IndicatorTreeDO> basePage = new Page<IndicatorTreeDO>(1,10);
			//params.put("name","血");

			params.put("pageNo",basePage.getPageNo());
			params.put("pageSize",basePage.getPageSize());

			List<IndicatorTreeDO> pageList = indicatorService.queryTree(params);
			System.out.println(basePage.setList(pageList));
			assertThat(pageList).isNotNull();
		}catch(Exception e){
		    e.printStackTrace();
		}

	}

	@Test
	public void testQueryByDiseaseId(){
		try {
			List list = indicatorService.queryByDiseaseId("8c3f9412-d535-4799-82fb-25e7dba1e3a3");
			assertThat(list.size()).isGreaterThan(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
