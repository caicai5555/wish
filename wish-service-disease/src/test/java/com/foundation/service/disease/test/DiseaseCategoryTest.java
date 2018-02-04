package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.DiseaseCategory;
import com.foundation.service.disease.biz.IDiseaseCategoryBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName: IndicatorTest
 * @Description: 指标类别测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseCategoryTest {

    @Autowired
    IDiseaseCategoryBiz diseaseCategoryBiz;

    private final String id = "TEST-ID-000001";
    private final String name = "TEST-NAME-000001";

    @Test
    public void testQueryByid(){

        try {
            DiseaseCategory bean= diseaseCategoryBiz.queryById("1450948605780");
            assertThat(bean).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> params= new HashMap();
        params.put("parentId","0");
        try {
            List<DiseaseCategory> list = diseaseCategoryBiz.queryList(params);
            assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave(){
        DiseaseCategory bean = new DiseaseCategory();
        bean.setId(id);
        bean.setParentId("0");
        bean.setName("name");
        bean.setCreateDate(new Date());
        try {
            diseaseCategoryBiz.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testUpdate(){
        DiseaseCategory bean = new DiseaseCategory();
        bean.setId(id);
        bean.setParentId("0");
        bean.setName("name111");
        try {
            diseaseCategoryBiz.update(bean);

            bean = diseaseCategoryBiz.queryById("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
