package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryDetailBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class AllergyHistoryDetailTest {
	@Autowired
	private IAllergyHistoryDetailBiz allergyHistoryDetailBiz;

	@Test
	public void saveTest() {
		AllergyHistoryDetail detail = new AllergyHistoryDetail();
		detail.setUserId("111");
		detail.setAllergyHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
		detail.setAllergyCode("1");
		detail.setAllergyName("测试");
		try {
			boolean flag = allergyHistoryDetailBiz.save(detail);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			AllergyHistoryDetail detail = allergyHistoryDetailBiz.queryById("c9c65c73-9f3c-4647-856c-f4ee0299ad12");
			assertThat("111").isEqualTo(detail.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		AllergyHistoryDetail detail = new AllergyHistoryDetail();
		detail.setId("c9c65c73-9f3c-4647-856c-f4ee0299ad12");
		detail.setUserId("222");
		try {
			allergyHistoryDetailBiz.update(detail);
			AllergyHistoryDetail update = allergyHistoryDetailBiz.queryById("c9c65c73-9f3c-4647-856c-f4ee0299ad12");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<AllergyHistoryDetail> pageBounds = new Page<AllergyHistoryDetail>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<AllergyHistoryDetail> page = allergyHistoryDetailBiz.queryPage(params, pageBounds);
			assertThat(2L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = allergyHistoryDetailBiz.getCount(params);
            assertThat(2L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			allergyHistoryDetailBiz.deleteByHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
            Map<String, Object> params = Maps.newHashMap();
            int count = allergyHistoryDetailBiz.getCount(params);
            assertThat(1).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			allergyHistoryDetailBiz.deleteReal("c9c65c73-9f3c-4647-856c-f4ee0299ad12");
			AllergyHistoryDetail detail = allergyHistoryDetailBiz.queryById("c9c65c73-9f3c-4647-856c-f4ee0299ad12");
            assertThat(detail).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
