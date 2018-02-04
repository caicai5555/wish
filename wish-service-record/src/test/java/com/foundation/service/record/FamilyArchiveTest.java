package com.foundation.service.record;

import com.foundation.dao.entity.archive.FamilyArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.foundation.service.dist.biz.IFamilyArchiveBiz;

/**
 * @author wangsen
 * @ClassName: FamilyArchiveTest
 * @Description: 家庭字典档案-服务测试
 * @date 2016/10/20 15:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class FamilyArchiveTest {
    @Autowired
    IFamilyArchiveBiz familyArchiveBiz;

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {

        FamilyArchive bean = new FamilyArchive();

        for (int i = 0; i < 5; i++) {
            bean.preInsert();
                                bean.setWifeCertNum(i + "00000000000000000");
            bean.setWifeName("wife" + i);
            bean.setHusbandCertNum(i + "22222222222222222");
            bean.setHusbandName("husband" + i);
            bean.setServiceZoneCode("s00" + i);
            bean.setServiceZone("service" + i);

            try {
                familyArchiveBiz.save(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 测试更新by Id
     */
    @Test
    public void testUpdateById() {

        FamilyArchive bean = new FamilyArchive();
        bean.preUpdate();
        bean.setId("4aa8b020-e637-46c6-a183-a2b23f377ca8");
        bean.setWifeCertNum("00000000000001111");
        bean.setWifeName("wifeeeee");
        bean.setHusbandCertNum("22222222222223333");
        bean.setHusbandName("husbandddd");
        bean.setServiceZoneCode("s00111");
        bean.setServiceZone("service1111");
        try {
            familyArchiveBiz.updateById(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试查询 by id
     */
    @Test()
    public void testQueryById() {
        try {
            FamilyArchive bean = familyArchiveBiz.queryById("4aa8b020-e637-46c6-a183-a2b23f377ca8");
            Assert.assertNotNull(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除by id
     */
    @Test
    public void testDelById() {
        try {
            familyArchiveBiz.deleteIndexById("4aa8b020-e637-46c6-a183-a2b23f377ca8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
