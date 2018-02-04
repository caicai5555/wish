package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.service.medicalHistory.biz.IDrugHistoryDetailBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DrugHistoryDetailTest {
	@Autowired
	private IDrugHistoryDetailBiz drugHistoryDetailBiz;

	@Test
	public void saveTest() {
		DrugHistoryDetail detail = new DrugHistoryDetail();
		detail.setUserId("111");
		detail.setDrugHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
		detail.setDrugCode("1");
		detail.setDrugName("测试");
		try {
			boolean flag = drugHistoryDetailBiz.save(detail);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			DrugHistoryDetail detail = drugHistoryDetailBiz.queryById("6ff133c9-1fb0-4d34-9066-7a804fedd58f");
			assertThat("111").isEqualTo(detail.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		DrugHistoryDetail detail = new DrugHistoryDetail();
		detail.setId("6ff133c9-1fb0-4d34-9066-7a804fedd58f");
		detail.setUserId("222");
		try {
			drugHistoryDetailBiz.update(detail);
			DrugHistoryDetail update = drugHistoryDetailBiz.queryById("6ff133c9-1fb0-4d34-9066-7a804fedd58f");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<DrugHistoryDetail> pageBounds = new Page<DrugHistoryDetail>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<DrugHistoryDetail> page = drugHistoryDetailBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = drugHistoryDetailBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			drugHistoryDetailBiz.deleteByHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
            Map<String, Object> params = Maps.newHashMap();
            int count = drugHistoryDetailBiz.getCount(params);
            assertThat(0).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			drugHistoryDetailBiz.deleteReal("ae841165-7bce-4562-9390-8729e300250a");
			DrugHistoryDetail detail = drugHistoryDetailBiz.queryById("ae841165-7bce-4562-9390-8729e300250a");
            assertThat(detail).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
