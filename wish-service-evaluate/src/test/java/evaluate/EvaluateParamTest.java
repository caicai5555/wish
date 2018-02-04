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
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.service.evaluate.biz.IEvaluateParamBiz;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class EvaluateParamTest {
	@Autowired
	private IEvaluateParamBiz evaluateParamBiz;

	@Test
	public void saveTest() {
		EvaluateParam param = new EvaluateParam();
		param.setId(IdGen.uuid());
		param.setEvaluateInfoId("11111");
		param.setName("参数");
		param.setEnname("param");
		param.setType("string");
		try {
			boolean flag = evaluateParamBiz.save(param);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			EvaluateParam param = evaluateParamBiz.queryById("ad2abd9e-9241-42cc-b41e-2fe04a9e4c5a");
			assertThat("param2").isEqualTo(param.getEnname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		EvaluateParam param = new EvaluateParam();
		param.setId("ad2abd9e-9241-42cc-b41e-2fe04a9e4c5a");
		param.setName("参数Update");
		try {
			evaluateParamBiz.update(param);
			EvaluateParam update = evaluateParamBiz.queryById("ad2abd9e-9241-42cc-b41e-2fe04a9e4c5a");
			assertThat("参数Update").isEqualTo(update.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<EvaluateParam> pageBounds = new Page<EvaluateParam>(1, 1);
            Map<String, Object> params = Maps.newHashMap();
	        Page<EvaluateParam> page = evaluateParamBiz.queryPage(params, pageBounds);
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
	        int count = evaluateParamBiz.getCount(params);
			assertThat(2L).isEqualTo(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryListTest(){
		try {
            Map<String, Object> params = Maps.newHashMap();
	        List<EvaluateParam> list = evaluateParamBiz.queryList(params);
			assertThat(2L).isEqualTo(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
