import com.foundation.cache.redis.JedisTemplate;
import com.foundation.cache.utils.RedisUtils;
import com.foundation.dao.entity.sys.User;
import com.foundation.common.io.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fanqinghui on 2016/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/spring-context.xml",
		"classpath:/spring/spring-db.xml",
		"classpath:/spring/spring-shiro.xml"
})
public class TestUserCache {

	@Test
	public void testUserAdmin(){
		JedisTemplate template= RedisUtils.getTemplate();
		String cacheKeyName="shiro_cache_userCache";
		String key="id_1";
		User admin= (User) SerializeUtil.toObject(template.hget(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key)));
		System.out.println(admin);
	}
}
