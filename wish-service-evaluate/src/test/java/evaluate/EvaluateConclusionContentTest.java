package evaluate;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.service.evaluate.biz.IEvaluateConclusionContentBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class EvaluateConclusionContentTest {
	@Autowired
	private IEvaluateConclusionContentBiz evaluateConclusionContentBiz;

	@Test
	public void saveTest() {
		EvaluateConclusionContent conclusion = new EvaluateConclusionContent();
		conclusion.setId(IdGen.uuid());
		conclusion.setEvaluateInfoId("11111");
		conclusion.setEvaluateRuleId("2222");
		conclusion.setName("表述描述");
		conclusion.setEnname("ddd");
		conclusion.setConclusion("结论");
		try {
			boolean flag = evaluateConclusionContentBiz.save(conclusion);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			EvaluateConclusionContent param = evaluateConclusionContentBiz.queryById("ada97a47-ecee-4b43-a654-638a3e19dcc2");
			assertThat("表述描述").isEqualTo(param.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		EvaluateConclusionContent param = new EvaluateConclusionContent();
		param.setId("ada97a47-ecee-4b43-a654-638a3e19dcc2");
		param.setName("表述描述Update");
		try {
			evaluateConclusionContentBiz.update(param);
			EvaluateConclusionContent update = evaluateConclusionContentBiz.queryById("ada97a47-ecee-4b43-a654-638a3e19dcc2");
			assertThat("表述描述Update").isEqualTo(update.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        int count = evaluateConclusionContentBiz.getCount(params);
			assertThat(1L).isEqualTo(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryListTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        List<EvaluateConclusionContent> list = evaluateConclusionContentBiz.queryList(params);
			assertThat(1L).isEqualTo(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
