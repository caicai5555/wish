package com.foundation.service.record;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveConclusion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.foundation.service.dist.biz.IDistArchiveConclusionBiz;

import java.util.Date;

/**
 * @author wangsen
 * @ClassName: DistArchiveConclusionTests
 * @Description: 档案-档案项字典测试类
 * @date 2016/10/13 13:40
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class DistArchiveConclusionTests {
    @Autowired
    IDistArchiveConclusionBiz iDistArchiveConclusionBiz;

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {

        DistArchiveConclusion distArchiveConclusion = new DistArchiveConclusion();

        for (int i = 0; i < 5; i++) {
            Date date = new Date();
            distArchiveConclusion.setId(IdGen.uuid());
            distArchiveConclusion.setCode("code" + i);
            distArchiveConclusion.setName("name" + i);
            distArchiveConclusion.setCreateDate(date);
            distArchiveConclusion.setUpdateDate(date);
            distArchiveConclusion.setRemark("remark" + i);
            try {
                iDistArchiveConclusionBiz.save(distArchiveConclusion);
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

        DistArchiveConclusion distArchiveConclusion = new DistArchiveConclusion();

        Date date = new Date();
        distArchiveConclusion.setId("d68db92e-f49f-4f42-91d0-c78e92b2266d");
        distArchiveConclusion.setCode("code110");
        distArchiveConclusion.setName("name110");
        distArchiveConclusion.setCreateDate(date);
        distArchiveConclusion.setUpdateDate(date);
        distArchiveConclusion.setRemark("remark110");
        distArchiveConclusion.setArchiveFlag(0);
        try {
            iDistArchiveConclusionBiz.updateById(distArchiveConclusion);
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
            DistArchiveConclusion distArchiveConclusion = iDistArchiveConclusionBiz.queryById("f74257dc-b0e0-4efd-af0a-976b33a4fa59");
            Assert.assertNotNull(distArchiveConclusion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除by id
     */
    @Test
    public void testDelIndexById() {
        try {
                iDistArchiveConclusionBiz.deleteIndexById("f74257dc-b0e0-4efd-af0a-976b33a4fa59");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除 by code
     */
/*    @Test
    public void testDeleteByCode(){
        try {
            int num = iDistArchiveConclusionBiz.deleteByIndexCode("code2");
            Assert.assertEquals("-------------update success!",1,num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 测试查询by parent id
     */
/*
    @Test
    public void testQueryIndexByParentId(){
        try {
            List<DistArchiveIndex> lists = iDistArchiveIndexBiz.queryByParentId("");
            Assert.assertNotNull("-------query index by parentId failed" ,lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


}
