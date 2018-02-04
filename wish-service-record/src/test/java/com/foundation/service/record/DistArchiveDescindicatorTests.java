package com.foundation.service.record;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import com.foundation.service.dist.biz.IDistArchiveDescindicatorBiz;
import com.foundation.service.dist.service.IDistArchiveDescindicatorService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
public class DistArchiveDescindicatorTests {
    @Autowired
    IDistArchiveDescindicatorBiz distArchiveDescindicatorBiz;
    @Autowired
    IDistArchiveDescindicatorService distArchiveDescindicatorService;

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {

        DistArchiveDescindicator bean = new DistArchiveDescindicator();

        for (int i = 0; i < 5; i++) {
            Date date = new Date();
            bean.setId(IdGen.uuid());
            bean.setCode("code" + i);
            bean.setName("name" + i);
            bean.setCreateDate(date);
            bean.setUpdateDate(date);
            bean.setRemark("remark" + i);
            try {
                distArchiveDescindicatorBiz.save(bean);
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

        DistArchiveDescindicator bean = new DistArchiveDescindicator();

        Date date = new Date();
        bean.setId("d68db92e-f49f-4f42-91d0-c78e92b2266d");
        bean.setCode("code110");
        bean.setName("name110");
        bean.setCreateDate(date);
        bean.setUpdateDate(date);
        bean.setRemark("remark110");
        bean.setArchiveFlag(0);
        try {
            distArchiveDescindicatorBiz.updateById(bean);
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
            DistArchiveDescindicator bean = distArchiveDescindicatorBiz.queryById("0dd3b9df-a38b-4d98-92a8-b6cd35a98104");
            Assert.assertNotNull(bean);
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
            distArchiveDescindicatorBiz.deleteIndexById("0dd3b9df-a38b-4d98-92a8-b6cd35a98104");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){

        try {
            List list =  distArchiveDescindicatorBiz.queryList(new HashMap());
            Assertions.assertThat(list.size()).isGreaterThan(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test(){
        try {
            List list= distArchiveDescindicatorService.queryByDiseaseId("7ffe364d-7fb5-4c3e-adc4-d5df5b720227");
            Assertions.assertThat(list.size()).isGreaterThan(0);
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
