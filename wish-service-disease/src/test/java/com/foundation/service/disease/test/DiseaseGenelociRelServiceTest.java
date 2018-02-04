package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.DiseaseGenelociRel;
import com.foundation.service.disease.service.IDiseaseGenelociRelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IndicatorTest
 * @Description: 疾病-疾病与基因位点关系服务测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseGenelociRelServiceTest {

    @Autowired
    IDiseaseGenelociRelService service;

    private final String id = "TEST-ID-00000";
    private final String one = "TEST-ONE-00000";
    private final String two = "TEST-TWO-00000";

    @Test
    public void testSave(){
        DiseaseGenelociRel bean = new DiseaseGenelociRel();
        bean.setId(id);
        bean.setDiseaseId(one);
        bean.setGeneLociId(two);
        try {
            service.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchSave(){
        List<DiseaseGenelociRel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DiseaseGenelociRel bean = new DiseaseGenelociRel();
            bean.setId(id+i);
            bean.setDiseaseId(one);
            bean.setGeneLociId(two+i);
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
