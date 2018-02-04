package com.foundation.service.record;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.foundation.service.dist.biz.IDistArchiveIndicatorBiz;

import java.util.Date;
import java.util.List;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndicatorTests
 * @Description: 档案-指标字典测试类
 * @date 2016/10/13 13:40
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class DistArchiveIndicatorTests {
    @Autowired
    IDistArchiveIndicatorBiz iDistArchiveBiz;

    /**
     * 测试插入
     */
    @Test
    public void  testInsert(){

        DistArchiveIndicator distArchiveIndicator = new DistArchiveIndicator();

        for(int i=0;i<5;i++){
            Date date = new Date();
            distArchiveIndicator.setId(IdGen.uuid());
            distArchiveIndicator.setName("指标" + i);
            distArchiveIndicator.setCode("code" + i);
            distArchiveIndicator.setCreateDate(date);
            distArchiveIndicator.setUpdateDate(date);
            distArchiveIndicator.setUnit("unit" + i);
            distArchiveIndicator.setMaxValue(10F + i);
            distArchiveIndicator.setMinValue(5F+i);
            distArchiveIndicator.setTableName("table"+i);
            distArchiveIndicator.setColumnName("col"+i);
            try {
                iDistArchiveBiz.save(distArchiveIndicator);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 测试更新by Code
     */
    @Test
    public void  testUpdateByCode(){

        DistArchiveIndicator distArchiveIndicator = new DistArchiveIndicator();

            Date date = new Date();
            distArchiveIndicator.setId("f30f03e8-07c4-4ca7-840b-1b952431a6eb");
            distArchiveIndicator.setCode("code1");
            distArchiveIndicator.setSex(1);
            distArchiveIndicator.setName("指标100");
            distArchiveIndicator.setCreateDate(date);
            distArchiveIndicator.setUpdateDate(date);
            distArchiveIndicator.setUnit("unit100");
            distArchiveIndicator.setMaxValue(111F);
            distArchiveIndicator.setMinValue(55F);
            try {
                iDistArchiveBiz.updateByCode(distArchiveIndicator);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    /**
     * 测试查询 by id
     */
    @Test()
    public void testQueryById(){
        try {
            DistArchiveIndicator distArchiveIndicator = iDistArchiveBiz.queryById("00bb1ec5-7011-41c3-a64d-b8860969938a");
            Assert.assertNotNull(distArchiveIndicator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询 by code
     */
    @Test
    public void testQueryByCode(){
        try {
            DistArchiveIndicator distArchiveIndicator = iDistArchiveBiz.queryByCode("code1");
            Assert.assertNotNull(distArchiveIndicator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询 by code
     */
    @Test
    public void testQueryBydCodeAndSex(){
        try {
            DistArchiveIndicator distArchiveIndicator = iDistArchiveBiz.queryByCodeAndSex("abo_rh", 1);
            Assert.assertNotNull(distArchiveIndicator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除by id
     */
    @Test
    public void testDelById(){
        try {
            int num = iDistArchiveBiz.deleteById("f30f03e8-07c4-4ca7-840b-1b952431a6eb");
            Assert.assertEquals("-------------update success!",1,num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除 by code
     */
    @Test
    public void testDeleteByCode(){
        try {
            int num = iDistArchiveBiz.deleteByCodeAndSex("abo_rh", 1);
            Assert.assertEquals("-------------update success!",1,num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询by tablename
     */

    @Test
    public void testQueryByTableName(){
        try {
            List<DistArchiveIndicator> lists = iDistArchiveBiz.queryByTableName("busi_exam_value_w");
            Assert.assertNotNull("-------query  by parentId failed" ,lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
