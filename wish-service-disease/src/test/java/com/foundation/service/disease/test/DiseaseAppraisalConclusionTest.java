package com.foundation.service.disease.test;


import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;
import com.foundation.service.disease.biz.IDiseaseAppraisalConclusionBiz;
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
 * @ClassName: IndicatorTest
 * @Description: 指标-评估结论（新描述性指标）测试类
 * @author wangsen
 * @date 2016/11/24 15:36
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml","classpath:spring-db.xml" })
public class DiseaseAppraisalConclusionTest {

    @Autowired
    IDiseaseAppraisalConclusionBiz dacBiz;

    private final String id = "TEST-ID-000001";
    private final String name = "TEST-NAME-000001";
    @Test
    public void testQueryByid(){

        try {
            DiseaseAppraisalConclusion bean= dacBiz.queryById("1461724803910");
            assertThat(bean).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> params= new HashMap();
        params.put("parentId","1461724803910");
        try {
            List<DiseaseAppraisalConclusion> list = dacBiz.queryList(params);
            assertThat(0).isLessThan(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryPageTest(){
        try {
            Page<DiseaseAppraisalConclusion> pageBounds = new Page<DiseaseAppraisalConclusion>(1, 2);
            Map<String, Object> params = Maps.newHashMap();
            Page<DiseaseAppraisalConclusion> page = dacBiz.queryPage(params, pageBounds);
            assertThat(2L).isEqualTo(page.getList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave(){
        DiseaseAppraisalConclusion disease = new DiseaseAppraisalConclusion();
        disease.setId(id);
        disease.setName(name);
        try {
            dacBiz.save(disease);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testUpdate(){
        DiseaseAppraisalConclusion disease = new DiseaseAppraisalConclusion();
        disease.setId(id);
        disease.setName(name+"update");
        try {
            dacBiz.update(disease);

            disease = dacBiz.queryById(id);
            assertThat(disease.getId()).isEqualTo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
