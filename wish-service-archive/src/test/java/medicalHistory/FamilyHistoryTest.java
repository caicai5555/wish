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
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.service.medicalHistory.biz.IFamilyHistoryBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class FamilyHistoryTest {
	@Autowired
	private IFamilyHistoryBiz familyHistoryBiz;

	@Test
	public void saveTest() {
		FamilyHistory history = new FamilyHistory();
		history.setUserId("111");
		history.setRelativeCode("1");
		history.setDiseaseAge(80);
		history.setDiseaseCode("1");
		history.setDiseaseName("测试");
		history.setGeneticFlag(1);
		try {
			boolean flag = familyHistoryBiz.save(history);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			FamilyHistory history = familyHistoryBiz.queryById("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
			assertThat("111").isEqualTo(history.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		FamilyHistory history = new FamilyHistory();
		history.setId("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
		history.setUserId("222");
		try {
			familyHistoryBiz.update(history);
			FamilyHistory update = familyHistoryBiz.queryById("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
			assertThat("222").isEqualTo(update.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<FamilyHistory> pageBounds = new Page<FamilyHistory>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<FamilyHistory> page = familyHistoryBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = familyHistoryBiz.getCount(params);
            assertThat(1L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			familyHistoryBiz.delete("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
            FamilyHistory history = familyHistoryBiz.queryById("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
            assertThat(1).isEqualTo(history.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
			familyHistoryBiz.deleteReal("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
            FamilyHistory history = familyHistoryBiz.queryById("e3b971b0-7f10-4ca1-b1ca-fdf9b81a1f05");
            assertThat(history).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
