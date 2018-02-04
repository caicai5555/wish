package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.Symptom;
import com.foundation.service.disease.biz.ISymptomBiz;
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
 * @ClassName: IndicatorTest
 * @Description: 指标信息测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class SymptomTest {

    @Autowired
    ISymptomBiz iSymptomBiz;

    private final String id = "TEST-ID-000001";
    private final String name = "TEST-NAME-000001";

    @Test
    public void testSave(){
        Symptom bean = new Symptom();
        bean.setId(id);
        bean.setName(name);
        bean.setIntensity(name);
        bean.setPhenomenon(name);
        try {
            iSymptomBiz.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        Symptom bean = new Symptom();
        bean.setId(id);
        bean.setName(name+"update");
        try {
            iSymptomBiz.update(bean);

            bean = iSymptomBiz.queryById(id);
            assertThat(bean.getName()).isEqualTo(name+"update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryById(){

        try {
            Symptom bean= iSymptomBiz.queryById(id);
            assertThat(bean).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> params= new HashMap();
        try {
            List<Symptom> list = iSymptomBiz.queryList(params);
            assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            iSymptomBiz.delete(id);
            Symptom bean= iSymptomBiz.queryById(id);
            assertThat(bean).isNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testQueryByDiseaseid(){
        try {
            List list = iSymptomBiz.queryByDiseaseId("7ffe364d-7fb5-4c3e-adc4-d5df5b720227");
            assertThat(list.size()).isGreaterThan(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
