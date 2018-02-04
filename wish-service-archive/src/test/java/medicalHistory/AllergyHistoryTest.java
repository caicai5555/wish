package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class AllergyHistoryTest {
	@Autowired
	private IAllergyHistoryBiz allergyHistoryBiz;

	@Test
	public void saveTest() {
		AllergyHistory history = new AllergyHistory();
		history.setUserId("111");
		try {
			boolean flag = allergyHistoryBiz.save(history);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			AllergyHistory history = allergyHistoryBiz.queryById("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
			assertThat("111").isEqualTo(history.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		AllergyHistory history = new AllergyHistory();
		history.setId("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
		history.setUserId("222");
		try {
			allergyHistoryBiz.update(history);
			AllergyHistory update = allergyHistoryBiz.queryById("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<AllergyHistory> pageBounds = new Page<AllergyHistory>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<AllergyHistory> page = allergyHistoryBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = allergyHistoryBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
            allergyHistoryBiz.delete("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
            AllergyHistory history = allergyHistoryBiz.queryById("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
            assertThat(1).isEqualTo(history.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
            allergyHistoryBiz.deleteReal("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
            AllergyHistory history = allergyHistoryBiz.queryById("9b4c451b-5f94-40e7-8812-81ec37ab2e6d");
            assertThat(history).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
