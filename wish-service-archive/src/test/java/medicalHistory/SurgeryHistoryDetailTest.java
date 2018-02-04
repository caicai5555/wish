package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryDetailBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class SurgeryHistoryDetailTest {
	@Autowired
	private ISurgeryHistoryDetailBiz surgeryHistoryDetailBiz;

	@Test
	public void saveTest() {
		SurgeryHistoryDetail detail = new SurgeryHistoryDetail();
		detail.setUserId("111");
		detail.setSurgeryHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
		detail.setSurgeryCode("1");
		detail.setSurgeryName("测试");
		try {
			boolean flag = surgeryHistoryDetailBiz.save(detail);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			SurgeryHistoryDetail detail = surgeryHistoryDetailBiz.queryById("9d365327-4333-49ea-b758-455ceaf1c74b");
			assertThat("111").isEqualTo(detail.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		SurgeryHistoryDetail detail = new SurgeryHistoryDetail();
		detail.setId("9d365327-4333-49ea-b758-455ceaf1c74b");
		detail.setUserId("222");
		try {
			surgeryHistoryDetailBiz.update(detail);
			SurgeryHistoryDetail update = surgeryHistoryDetailBiz.queryById("9d365327-4333-49ea-b758-455ceaf1c74b");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<SurgeryHistoryDetail> pageBounds = new Page<SurgeryHistoryDetail>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<SurgeryHistoryDetail> page = surgeryHistoryDetailBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = surgeryHistoryDetailBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			surgeryHistoryDetailBiz.deleteByHistoryId("05a48740-963b-4863-b0a7-9512dc676951");
            Map<String, Object> params = Maps.newHashMap();
            int count = surgeryHistoryDetailBiz.getCount(params);
            assertThat(0).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			surgeryHistoryDetailBiz.deleteReal("e8a57f30-7995-46fc-9086-032cbc682386");
			SurgeryHistoryDetail detail = surgeryHistoryDetailBiz.queryById("e8a57f30-7995-46fc-9086-032cbc682386");
            assertThat(detail).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
