package medicalHistory;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.service.medicalHistory.biz.IDrugHistoryBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DrugHistoryTest {
	@Autowired
	private IDrugHistoryBiz drugHistoryBiz;

	@Test
	public void saveTest() {
		DrugHistory history = new DrugHistory();
		history.setUserId("111");
		try {
			boolean flag = drugHistoryBiz.save(history);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			DrugHistory history = drugHistoryBiz.queryById("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
			assertThat("111").isEqualTo(history.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		DrugHistory history = new DrugHistory();
		history.setId("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
		history.setUserId("222");
		try {
			drugHistoryBiz.update(history);
			DrugHistory update = drugHistoryBiz.queryById("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<DrugHistory> pageBounds = new Page<DrugHistory>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<DrugHistory> page = drugHistoryBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = drugHistoryBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			drugHistoryBiz.delete("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
            DrugHistory history = drugHistoryBiz.queryById("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
            assertThat(1).isEqualTo(history.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			drugHistoryBiz.deleteReal("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
            DrugHistory history = drugHistoryBiz.queryById("2bcd104c-d8d1-4bf2-9ce7-ec2422fede63");
            assertThat(history).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
