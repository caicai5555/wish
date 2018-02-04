package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;
import com.foundation.service.disease.service.IDiseaseAppraisalConclusionRelService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: IndicatorTest
 * @Description: 疾病-疾病与评估结论关联服务测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseAppraisalConclusionRelTest {

    @Autowired
    IDiseaseAppraisalConclusionRelService service;

    private final String id = "TEST-ID-00000";
    private final String one = "TEST-ONE-00000";
    private final String two = "TEST-TWO-00000";

    @Test
    public void testSave(){
        DiseaseAppraisalConclusionRel bean = new DiseaseAppraisalConclusionRel();
        bean.setId(id);
        bean.setDiseaseId(one);
        bean.setAppraisalConclusionId(two);
        try {
            service.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatchSave(){
        List<DiseaseAppraisalConclusionRel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DiseaseAppraisalConclusionRel bean = new DiseaseAppraisalConclusionRel();
            bean.setId(id+i);
            bean.setDiseaseId(one);
            bean.setAppraisalConclusionId(two+i);
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
    @Test
    public void testQueryList(){
        try {
            List list = service.queryList(new HashMap());
            Assertions.assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
