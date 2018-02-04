package mongo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.dao.IMongoBaseDao;

import mongo.entity.UserTest;
import mongo.entity.UserTestSub;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MongoTest {
	@Autowired
	private IMongoBaseDao mongobaseDao;

	@Test
	public void saveTest() {
		Map<String, Object> _sub = new HashMap<>();
		_sub.put("value", 1.7);
		_sub.put("valueTab", 1);
		_sub.put("msg", "测试spring-mongo");
		Map<String, Object> indicator = new HashMap<>();
		indicator.put("heart", _sub);
		UserTestSub subUser = new UserTestSub();
		subUser.setSubId(123);
		subUser.setSubName("subName");
		subUser.setSubIndicator(indicator);
		UserTest user = new UserTest();
		user.setId(3);
		user.setName("cc");
		Map<String,UserTestSub> map = new HashMap<>();
		map.put("indicator", subUser);
		user.setItem(map);
		mongobaseDao.save(user);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getByIdTest(){
		UserTest test = mongobaseDao.queryById(1, UserTest.class);
		assertThat(1).isEqualTo(((Map<String,Object>)test.getItem().get("indicator").getSubIndicator().get("heart")).get("valueTab"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void queryOneTest(){
		Map<String,Object> params = new HashMap<>();
		params.put("item.indicator.subId", 123);
		UserTest test = mongobaseDao.queryOne(params, UserTest.class);
		assertThat(1).isEqualTo(((Map<String,Object>)test.getItem().get("indicator").getSubIndicator().get("heart")).get("valueTab"));
	}
	
	@Test
	public void queryCountTest(){
		Map<String,Object> params = new HashMap<>();
		params.put("name", "cc");
		long count = mongobaseDao.queryCount(params, UserTest.class);
		assertThat(0L).isLessThan(count);
	}
	
	@SuppressWarnings("unchecked")
	//@Test
	public void queryAllTest(){
		List<UserTest> all = mongobaseDao.queryAll(UserTest.class);
		assertThat(0L).isLessThan(all.size());
		assertThat(1).isEqualTo(((Map<String,Object>)all.get(0).getItem().get("indicator").getSubIndicator().get("heart")).get("valueTab"));
	}
	@Test
	public void queryListTest(){
		Map<String,Object> params = new HashMap<>();
		params.put("item.indicator.subIndicator.heart.valueTab", 1);
		List<UserTest> list = mongobaseDao.queryList(params, UserTest.class);
		assertThat(0L).isLessThan(list.size());
	}
	
	@Test
	public void queryPageTest(){
		Map<String,Object> params = new HashMap<>();
		params.put("item.indicator.subIndicator.heart.valueTab", 1);
		Page<UserTest> page = mongobaseDao.queryPage(1, 5, params, UserTest.class);
		assertThat(0L).isLessThan(page.getCount());
	}
	
	@Test
	public void updateTest(){
		Map<String,Object> whereParams = new HashMap<>();
		whereParams.put("name", "cc");
		
		Map<String,Object> updateParams = new HashMap<>();
		
		Map<String, Object> _sub = new HashMap<>();
		_sub.put("value", 80);
		_sub.put("valueTab", 15);
		_sub.put("msg", "新增");
		updateParams.put("item.indicator.subIndicator.height", _sub);
		mongobaseDao.update(whereParams, updateParams, UserTest.class);
	}
	
	@Test
	public void deleteReallyTest(){
		mongobaseDao.deleteReally(2, UserTest.class);
	}
	
	@Test
	public void deleteLogicalTest(){
		mongobaseDao.deleteLogical(1, UserTest.class);
	}

	@Test
	public void getFieldsTest(){
		HashMap<String, Object> params = Maps.newHashMap();
		List<String> fields = Arrays.asList("item.indicator.subId");
		List<Map> users = mongobaseDao.queryFieldsList(params, fields, Map.class, "userTest");
		System.out.println(users);
		UserTest user = mongobaseDao.queryFieldsOne(params, fields, UserTest.class, null);
		System.out.println(user);
	}
}
