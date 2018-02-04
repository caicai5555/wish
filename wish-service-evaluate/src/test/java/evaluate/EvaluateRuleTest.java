package evaluate;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.service.evaluate.biz.IEvaluateRuleBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class EvaluateRuleTest {
	@Autowired
	private IEvaluateRuleBiz evaluateRuleBiz;

	@Test
	public void saveTest() {
		EvaluateRule rule = new EvaluateRule();
		rule.setId(IdGen.uuid());
		rule.setEvaluateInfoId("11111");
		rule.setName("规则");
		rule.setRule("规则表达式");
		try {
			boolean flag = evaluateRuleBiz.save(rule);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try{
			EvaluateRule rule = evaluateRuleBiz.queryById("e5cc27f8-5fb9-4666-8379-a6915b43b31f");
			assertThat("规则2").isEqualTo(rule.getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		EvaluateRule rule = new EvaluateRule();
		rule.setId("e5cc27f8-5fb9-4666-8379-a6915b43b31f");
		rule.setName("规则Update");
		try{
			evaluateRuleBiz.update(rule);
			EvaluateRule update = evaluateRuleBiz.queryById("e5cc27f8-5fb9-4666-8379-a6915b43b31f");
			assertThat("规则Update").isEqualTo(update.getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<EvaluateRule> pageBounds = new Page<EvaluateRule>(1, 1);
            Map<String, Object> params = Maps.newHashMap();
	        Page<EvaluateRule> page = evaluateRuleBiz.queryPage(params, pageBounds);
			assertThat(1L).isEqualTo(page.getList().size());
			assertThat(2L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getCountTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        int count = evaluateRuleBiz.getCount(params);
			assertThat(2L).isEqualTo(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryListTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        List<EvaluateRule> list = evaluateRuleBiz.queryList(params);
			assertThat(2L).isEqualTo(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
