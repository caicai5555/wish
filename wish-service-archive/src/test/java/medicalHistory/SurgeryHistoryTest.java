package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class SurgeryHistoryTest {
	@Autowired
	private ISurgeryHistoryBiz surgeryHistoryBiz;

	@Test
	public void saveTest() {
		SurgeryHistory history = new SurgeryHistory();
		history.setUserId("111");
		try {
			boolean flag = surgeryHistoryBiz.save(history);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			SurgeryHistory history = surgeryHistoryBiz.queryById("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
			assertThat("111").isEqualTo(history.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		SurgeryHistory history = new SurgeryHistory();
		history.setId("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
		history.setUserId("222");
		try {
			surgeryHistoryBiz.update(history);
			SurgeryHistory update = surgeryHistoryBiz.queryById("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<SurgeryHistory> pageBounds = new Page<SurgeryHistory>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<SurgeryHistory> page = surgeryHistoryBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = surgeryHistoryBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			surgeryHistoryBiz.delete("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
            SurgeryHistory history = surgeryHistoryBiz.queryById("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
            assertThat(1).isEqualTo(history.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			surgeryHistoryBiz.deleteReal("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
            SurgeryHistory history = surgeryHistoryBiz.queryById("e3faf1e6-489f-46e2-93e1-1b5f3394548d");
            assertThat(history).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
