package com.foundation.service.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foundation.mongo.entity.archive.FamilyArchiveMG;
import com.foundation.mongo.entity.record.ConcIndicatorItem;
import com.foundation.mongo.entity.record.FamilyConcIndicator;
import com.foundation.service.archive.service.IFamilyArchiveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.foundation.service.record.service.IIndicatorRecordService;


/**
 * @author wangsen
 * @ClassName: FamilyArchiveTest
 * @Description: 家庭档案-mongo测试
 * @date 2016/10/28 15:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class FamilyArchiveMongoTest {
    @Autowired
    IFamilyArchiveService familyArchiveService;

    @Autowired
    IIndicatorRecordService indicatorRecordService;

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {

        FamilyArchiveMG bean = new FamilyArchiveMG();

        for (int i = 0; i < 1; i++) {
            bean.setfId("ab");
            bean.setWifeCertNum(i + "00000000000000000");
            bean.setWifeName("wife" + i);
            bean.setHusbandCertNum(i + "22222222222222222");
            bean.setHusbandName("husband" + i);
            try {
                familyArchiveService.saveArvhice(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 测试查询 by Map
     */
    @Test()
    public void testQuesryById() {
        try {
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("husbandName", "husband4");
            FamilyArchiveMG bean = familyArchiveService.queryByParams(queryParams);
            Assert.assertNotNull(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试插入记录归档
     */
    @Test()
    public void testInsetFamilyArchive(){
        FamilyConcIndicator bean = new FamilyConcIndicator();
        bean.setCode("100000112");
        bean.setfId("adfsfsdf455143123121");
        bean.setWifeCertNum("370600000000000000");
        bean.setWifeName("Mery");
        bean.setHusbandCertNum("370611111111111111");
        bean.setHusbandName("Sam");

        List<ConcIndicatorItem> list = new ArrayList<>();
        ConcIndicatorItem item = new ConcIndicatorItem();
        item.setCode("abc");
        item.setName("测试");
        item.setValue("111");

        list.add(item);
        item = new ConcIndicatorItem();
        item.setCode("abcd");
        item.setName("测试2");
        item.setValue("222");
        list.add(item);

        bean.setConcIndicatorItems(list);


        try {
            indicatorRecordService.saveFamilyIndicator(bean,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
