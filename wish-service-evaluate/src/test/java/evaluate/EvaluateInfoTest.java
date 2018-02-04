package evaluate;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.service.evaluate.biz.IEvaluateInfoBiz;
import com.foundation.service.evaluate.common.EvaluateUtils;
import com.foundation.service.evaluate.common.StatusEnum;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.RuleModel;
import com.google.common.collect.Maps;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class EvaluateInfoTest {
	@Autowired
	private IEvaluateInfoBiz evaluateInfoBiz;

	@Test
	public void saveTest() {
		EvaluateInfo info = new EvaluateInfo();
		//info.setName("ceshi2");
		//info.setEnName("enceshi2");
		//info.setFileName("文件名2");
		info.setFilePath("文件路径");
		info.setEvaluateCategoryId("7cd686c2-28e0-4fef-ad5e-abed84fa9c36");
		//info.setStatus(1);
		//info.setMode(1);
		//info.setTimes(1L);
		//info.setCreateBy(new User("createBy"));
		//info.setCreateDate(new Date());
		//info.setUpdateBy(new User("updateBy"));
		//info.setUpdateDate(new Date());
		//info.setDelFlag(0);
		//info.setDelDate(new Date());
		//info.setRemarks("备注！！！");
		try {
			boolean flag = evaluateInfoBiz.save(info);
			assertThat(true).isEqualTo(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryByIdTest(){
		try {
			EvaluateInfo info = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
			assertThat("ceshi").isEqualTo(info.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTest(){
		EvaluateInfo info = new EvaluateInfo();
		info.setId("2b466cad8156-4590-b427-07a4b970a6ea");
		info.setName("ceshiUpdate2");
		try {
			evaluateInfoBiz.update(info);
			EvaluateInfo update = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
			assertThat("ceshiUpdate2").isEqualTo(update.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryPageTest(){
		try {
	        Page<EvaluateInfo> pageBounds = new Page<EvaluateInfo>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
	        Page<EvaluateInfo> page = evaluateInfoBiz.queryPage(params, pageBounds);
			assertThat(5L).isEqualTo(page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCountTest(){
		try{
            Map<String, Object> params = Maps.newHashMap();
            int count = evaluateInfoBiz.getCount(params);
            assertThat(5L).isEqualTo(count);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void startTest(){
		try{
            evaluateInfoBiz.start("2b466cad8156-4590-b427-07a4b970a6ea");
            EvaluateInfo info = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
            assertThat(StatusEnum.RUNNING.getId()).isEqualTo(info.getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void completeTest(){
		try{
            evaluateInfoBiz.complete("2b466cad8156-4590-b427-07a4b970a6ea");
            EvaluateInfo info = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
            assertThat(3L).isEqualTo(info.getTimes());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
            evaluateInfoBiz.delete("2b466cad8156-4590-b427-07a4b970a6ea");
            EvaluateInfo info = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
            assertThat(1).isEqualTo(info.getDelFlag());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteRealTest(){
		try{
            evaluateInfoBiz.deleteReal("2b466cad8156-4590-b427-07a4b970a6ea");
            EvaluateInfo info = evaluateInfoBiz.queryById("2b466cad8156-4590-b427-07a4b970a6ea");
            assertThat(info).isNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void parsingXMLTest(){
		try{
			EvaluateInfo info = new EvaluateInfo();
			evaluateInfoBiz.save(info);
			InputStream inputStream = EvaluateInfoTest.class.getResourceAsStream("/1011002.xml");
			JSONObject result = evaluateInfoBiz.parsingXML(info,inputStream);
			System.out.println(result);
            //assertThat("OGTT").isEqualTo(info.getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseXmlTest(){
		String path = "/evalaue_test.xml";
		InputStream inputStream = EvaluateInfoTest.class.getResourceAsStream(path);
		try {
			RuleModel ruleModel = (RuleModel) EvaluateUtils.xml2Obj(null, inputStream);
			Map<String,Object> ruleResult = EvaluateUtils.getParsingRuleResult(ruleModel,1);
			Map<String, String> parsingResult = EvaluateUtils.getParsingResult(ruleModel);
			System.out.println(ruleResult);
			System.out.println(parsingResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
