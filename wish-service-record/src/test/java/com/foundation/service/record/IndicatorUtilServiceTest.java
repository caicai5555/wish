package com.foundation.service.record;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.foundation.service.common.IndicatorType;
import com.foundation.service.dist.service.IDistArchiveUtilService;

/**
 * @author wangsen
 * @ClassName: DistArchiveUtilServiceTest
 * @Description: 字典共用服务测试类
 * @date 2016/10/28 9:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class IndicatorUtilServiceTest {
    @Autowired
    IDistArchiveUtilService distArchiveUtilService;

    /**
     * 测试根据编码获取指标类型
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
    	IndicatorType type =distArchiveUtilService.getIndicatorType("code0");

        System.out.println("-------------------------------"+type);
    }

}
