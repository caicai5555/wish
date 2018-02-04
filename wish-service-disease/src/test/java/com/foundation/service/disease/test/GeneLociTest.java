package com.foundation.service.disease.test;


import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.service.disease.biz.IGeneLociBiz;
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
 * @Description: 基因位点测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class GeneLociTest {

    @Autowired
    IGeneLociBiz geneLociBiz;

    private final String id = "TEST-ID-000001";
    private final String name = "TEST-NAME-000001";

    @Test
        public void testSave(){
        GeneLoci bean = new GeneLoci();
        bean.setId(id);
        bean.setName(name);
        bean.setEnname(name);
        bean.setLociName(name);
        try {
            geneLociBiz.save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        GeneLoci bean = new GeneLoci();
        bean.setId(id);
        bean.setName(name+"update");
        try {
            geneLociBiz.update(bean);

            bean = geneLociBiz.queryById(id);
            assertThat(bean.getName()).isEqualTo(name+"update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryById(){

        try {
            GeneLoci bean= geneLociBiz.queryById(id);
            assertThat(bean).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> params= new HashMap();
        try {
            List<GeneLoci> list = geneLociBiz.queryList(params);
            assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        try {
            geneLociBiz.delete(id);
            GeneLoci bean= geneLociBiz.queryById(id);
            assertThat(bean).isNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryByDiseaseId(){
        try {
            List<GeneLoci> list = geneLociBiz.queryByDiseaseId("6d9b3917-d6e4-4ef8-9935-4b47032e4fee");
            assertThat(list.size()).isGreaterThan(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
