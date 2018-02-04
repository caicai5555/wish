package com.foundation.service.disease.test;


import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.disease.Disease;
import com.foundation.service.disease.biz.IDiseaseBiz;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorInfoTest
 * @Description: 指标信息测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseTest {

    @Autowired
    IDiseaseBiz diseaseBiz;

    private final String id = "TEST-ID-000001";

    @Test
    public void testQueryByid(){

        try {
            Disease bean= diseaseBiz.queryById("1");
            assertThat(bean).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> params= new HashMap();
        params.put("pid","1");
        try {
            List<Disease> list = diseaseBiz.queryList(params);
            assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryPageTest(){
        try {
            Page<Disease> pageBounds = new Page<Disease>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
            Page<Disease> page = diseaseBiz.queryPage(params, pageBounds);
            assertThat(2L).isEqualTo(page.getList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave(){
        Disease disease = new Disease();
        disease.setId(id);
        disease.setInfo("test");
        disease.setEnglishName("en_test");
        try {
            diseaseBiz.save(disease);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testUpdate(){
        Disease disease = new Disease();
        disease.setId("1");
        disease.setInfo("test");
        disease.setEnglishName("en_test");
        try {
            diseaseBiz.update(disease);

            disease = diseaseBiz.queryById("1");
            assertThat(disease.getInfo()).isEqualTo("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
