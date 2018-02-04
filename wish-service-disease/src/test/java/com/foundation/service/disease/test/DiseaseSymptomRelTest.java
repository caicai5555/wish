package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.DiseaseSymptomRel;
import com.foundation.service.disease.service.IDiseaseSymptomRelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IndicatorTest
 * @Description: 疾病-疾病与症状信息关系服务测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseSymptomRelTest {

    @Autowired
    IDiseaseSymptomRelService service;

    private final String id = "TEST-ID-00000";
    private final String one = "TEST-ONE-00000";
    private final String two = "TEST-TWO-00000";
    private final String three = "TEST-THREE-00000";

    @Test
    public void testSave(){
        DiseaseSymptomRel bean = new DiseaseSymptomRel();
        bean.setId(id);
        bean.setDiseaseId(one);
        bean.setSymptomId(two);
        bean.setCategoryId(three);
        try {
            service.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchSave(){
        List<DiseaseSymptomRel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DiseaseSymptomRel bean = new DiseaseSymptomRel();
            bean.setId(id+i);
            bean.setDiseaseId(one);
            bean.setSymptomId(two+i);
            bean.setCategoryId(three);
            list.add(bean);
        }

        try {
            service.batchSave(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            service.deleteByDiseaseId(one);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
