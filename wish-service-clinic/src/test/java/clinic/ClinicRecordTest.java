package clinic;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.service.clinic.biz.IClinicRecordBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-db.xml"})
public class ClinicRecordTest {
    @Autowired
    private IClinicRecordBiz clinicRecordBiz;
    private String id = IdGen.uuid();
//    private String id = "6c444f83-c3f2-4437-b3a0-f00641116d8d";

    @Test
    public void crudTest(){
        try {
            System.out.println("test begin....");
            saveTest();
            getTest();
            updateTest();
            countTest();
            getAllTest();
            deleteTest();
            System.out.println("test end....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTest() throws Exception{
        ClinicRecord record = new ClinicRecord();
        record.setId(id);
        boolean flag = clinicRecordBiz.save(record);
        assertThat(true).isEqualTo(flag);
}

    public void getTest() throws Exception {
        ClinicRecord record = clinicRecordBiz.queryById(id);
        assertThat(id).isEqualTo(record.getId());
    }

    public void updateTest() throws Exception {
        ClinicRecord record = new ClinicRecord();
        record.setId(id);
        Date date = new Date();
        record.setInspectDate(date);
        boolean flag = clinicRecordBiz.update(record);
        assertThat(true).isEqualTo(flag);
    }

    public void deleteTest() throws Exception {
        boolean flag = clinicRecordBiz.deleteReal(id);
        assertThat(true).isEqualTo(flag);
    }

    public void getAllTest() throws Exception {
        Page<ClinicRecord> page = new Page<ClinicRecord>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{"24c136a2-6974-411c-b990-4ceab48fddee",
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("inspect_date", "desc");
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        page = clinicRecordBiz.queryPage(map, page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = clinicRecordBiz.getCount(null);
        assertThat(count).isNotEqualTo(0);
    }

    @Test
    public void userTest(){
        String username = "root";
        String password = "root";
        try {
            String encrypt = ConfigTools.encrypt(password);
            System.out.println(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
