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
import com.foundation.dao.entity.medicalHistory.DiseaseHistory;
import com.foundation.service.medicalHistory.biz.IDiseaseHistoryBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseHistoryTest {
	@Autowired
	private IDiseaseHistoryBiz diseaseHistoryBiz;

	@Test
	public void saveTest() {
		DiseaseHistory history = new DiseaseHistory();
		history.setUserId("111");
		history.setVisitTime(new Date());
		history.setHospital("hospital");
		history.setDiseaseName("疾病名称");
		try {
			boolean flag = diseaseHistoryBiz.save(history);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			DiseaseHistory history = diseaseHistoryBiz.queryById("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
			assertThat("111").isEqualTo(history.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		DiseaseHistory history = new DiseaseHistory();
		history.setId("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
		history.setUserId("222");
		try {
			diseaseHistoryBiz.update(history);
			DiseaseHistory update = diseaseHistoryBiz.queryById("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<DiseaseHistory> pageBounds = new Page<DiseaseHistory>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<DiseaseHistory> page = diseaseHistoryBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = diseaseHistoryBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
            diseaseHistoryBiz.delete("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
            DiseaseHistory history = diseaseHistoryBiz.queryById("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
            assertThat(1).isEqualTo(history.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
            diseaseHistoryBiz.deleteReal("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
            DiseaseHistory history = diseaseHistoryBiz.queryById("e21c25d2-67b7-408e-9626-a2a112bb1aa1");
            assertThat(history).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
