package com.foundation.service.record;

import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.record.*;
import com.foundation.service.archive.service.IArchiveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.foundation.service.record.service.impl.IndicatorRecordService;

import java.util.*;

/**
 * @author wangsen
 * @ClassName: ArchiveMongoTest
 * @Description: mongo档案测试类
 * @date 2016/10/19 10:37
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml",
        "classpath:spring-db.xml"
})
public class RecordMongoTest {

    @Autowired
    IndicatorRecordService iIndicatorRecordService;

    @Autowired
    IArchiveService archiveService;


    private final String userId = "TEST0000001";
    private final String certNum = "TEST37061219880602";


    /**--------描述性指标记录测试----*/
    /**
     * 测试保存
     */
    @Test
    public void testSaveDescIndicator() {
        boolean rtn = false;

        DescIndicator bean = new DescIndicator();
        bean.setCode("height");
        bean.setValue("120");
        bean.setUserId(userId);
        bean.setCertNum(certNum);

        try {
            rtn = iIndicatorRecordService.saveRecord(bean);
            Assert.assertTrue(rtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询
     */
    @Test
    public void testQueryListDescIndicator() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        try {
            List<DescIndicator> list = iIndicatorRecordService.queryList(params, DescIndicator.class);
            Assert.assertNotNull(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**--------结论性指标记录测试----*/
    /**
     * 测试保存
     */
    @Test
    public void testSaveConcIndicator() {
        boolean rtn = false;

        ConcIndicator bean = new ConcIndicator();
        bean.setCode("OGTT");
        bean.setValue("40");
        bean.setUserId(userId);
        bean.setCertNum(certNum);
        bean.setSource("testa");
        bean.setEvent("testa");

        try {
            rtn = iIndicatorRecordService.saveRecord(bean);
            Assert.assertTrue(rtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询
     */
    @Test
    public void testQueryListConccIndicator() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        try {
            List<ConcIndicator> list = iIndicatorRecordService.queryList(params, ConcIndicator.class);
            Assert.assertNotNull(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**--------指标记录测试----*/
    /**
     * 测试保存
     */
    @Test
    public void testSaveIndicator() {
        boolean rtn = false;

        Indicator indicator = new Indicator();

        indicator.setUserId(userId);
        indicator.setCertNum(certNum);
        indicator.setCode("bloodSugarbbb");
        indicator.setValue("40");
        indicator.setColor("#FF0000");
        indicator.setConclusion("偏高");
        indicator.setSuggest("多吃");
        indicator.setMaxVal(10D);
        indicator.setMinVal(5D);
        indicator.setDefaultMax(20D);
        indicator.setDefaultMin(1D);
        indicator.setRangeMinVal(5D);//
        indicator.setRangeMaxVal(10D);
        indicator.setIntervalValue("[{\\\"conclusion\\\":\\\"偏高\\\",\\\"value\\\":[\\\"11.00\\\",\\\"27.00\\\"],\\\"valueTab\\\":0},{\\\"conclusion\\\":\\\"亚健康\\\",\\\"value\\\":[\\\"11.00\\\",\\\"7.80\\\"],\\\"valueTab\\\":3},{\\\"conclusion\\\":\\\"正常\\\",\\\"value\\\":[\\\"3.90\\\",\\\"7.80\\\"],\\\"valueTab\\\":1},{\\\"conclusion\\\":\\\"亚健康\\\",\\\"value\\\":[\\\"0.00\\\",\\\"3.90\\\"],\\\"valueTab\\\":3}]");
        indicator.setScaleList(new TreeSet<Double>(Arrays.asList(0D, 3.9D, 7.8D, 27D, 11D)));


        try {
            rtn = iIndicatorRecordService.saveIndicator(indicator, true);
//                        rtn = iIndicatorRecordService.saveRecord(indicator);
            Assert.assertTrue(rtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询
     */
    @Test
    public void testQueryListIndicator() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        try {
            List<Indicator> list = iIndicatorRecordService.queryList(params, Indicator.class);
            Assert.assertNotNull(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * -----------测试Archive
     */
    @Test
    public void testSaveArchive() {
        DescIndicator descIndicator = new DescIndicator();
        descIndicator.setCertNum(certNum);
        descIndicator.setCode("height");
        descIndicator.setValue("120");
        descIndicator.setUserId("2");


        try {
            boolean b = iIndicatorRecordService.saveIndicator(descIndicator, true);
            Assert.assertTrue(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试档案查询by user id
    @Test
    public void testQueryArchiveByUserID() {
        try {
            Archive bean = archiveService.queryByUserId(userId);
            Assert.assertNotNull(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试多条规则插入
    @Test
    public void testSaveIndicators() {
        Map<String, BaseRecordEntity> map= new HashMap<>();
        for (int i = 0; i < 15; i++) {

            DescIndicator descIndicator = new DescIndicator();
            descIndicator.setUserId(userId);
            descIndicator.setCertNum(certNum);
            descIndicator.setCode("height" + i);
            descIndicator.setValue("120" + i);

            map.put("aaa" + i, descIndicator);


            Indicator indicator = new Indicator();

            indicator.setUserId(userId);
            indicator.setCertNum(certNum);
            indicator.setCode("bloodSugard" + i);
            indicator.setValue("40" + i);
            indicator.setColor("#FF0000");
            map.put("bbb"+i, indicator);

            ConcIndicator concIndicator = new ConcIndicator();
            concIndicator.setUserId(userId);
            concIndicator.setCertNum(certNum);
            concIndicator.setCode("1012001_" + i);
            concIndicator.setValue("40" + i);
            concIndicator.setSource("testa");
            concIndicator.setEvent("testa");

            List<ConcIndicatorItem> list = new ArrayList<>();
            ConcIndicatorItem item = new ConcIndicatorItem();
            item.setCode("abc");
            item.setName("测试");
            item.setValue("111");

            list.add(item);
            item = new ConcIndicatorItem();
            item.setCode("abcd");
            item.setName("测试2");
            item.setValue("222");
            list.add(item);
            concIndicator.setConcIndicatorItems(list);


            map.put("ccc" + i, concIndicator);
        }
        try {
            iIndicatorRecordService.saveIndicators(map, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
