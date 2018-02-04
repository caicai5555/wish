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
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.service.evaluate.biz.IEvaluateCategoryBiz;
import com.foundation.service.evaluate.common.CategoryTypeEnum;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class EvaluateCategoryTest {
	@Autowired
	private IEvaluateCategoryBiz evaluateCategoryBiz;

	@Test
	public void saveTest() {
		EvaluateCategory category = new EvaluateCategory();
		category.setId(IdGen.uuid());
		category.setName("分类1");
		category.setType(CategoryTypeEnum.CONCLUSION.getId());
		try {
			boolean flag = evaluateCategoryBiz.save(category);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			EvaluateCategory param = evaluateCategoryBiz.queryById("8036b512-9a89-44f3-b0e2-a52ab72b0c98");
			assertThat("分类1").isEqualTo(param.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		EvaluateCategory param = new EvaluateCategory();
		param.setId("8036b512-9a89-44f3-b0e2-a52ab72b0c98");
		param.setName("分类Update");
		try {
			evaluateCategoryBiz.update(param);
			EvaluateCategory update = evaluateCategoryBiz.queryById("8036b512-9a89-44f3-b0e2-a52ab72b0c98");
			assertThat("分类Update").isEqualTo(update.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void getCountTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        int count = evaluateCategoryBiz.getCount(params);
			assertThat(1L).isEqualTo(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryListTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("pid", "7cd686c2-28e0-4fef-ad5e-abed84fa9c36");
	        List<EvaluateCategory> list = evaluateCategoryBiz.queryList(params);
			assertThat(1L).isEqualTo(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
