package clinic;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.service.clinic.biz.IInspectItemBiz;
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
public class InspectItemTest {
    @Autowired
    private IInspectItemBiz inspectItemBiz;
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
        InspectItem inspectItem = new InspectItem();
        inspectItem.setId(id);
        boolean flag = inspectItemBiz.save(inspectItem);
        assertThat(true).isEqualTo(flag);
}

    public void getTest() throws Exception {
        InspectItem inspectItem = inspectItemBiz.queryById(id);
        assertThat(id).isEqualTo(inspectItem.getId());
    }

    public void updateTest() throws Exception {
        InspectItem inspectItem = new InspectItem();
        inspectItem.setId(id);
        Date date = new Date();
        inspectItem.setCreateDate(date);
        boolean update = inspectItemBiz.update(inspectItem);
        assertThat(true).isEqualTo(update);
    }

    public void deleteTest() throws Exception {
        boolean deleteReal = inspectItemBiz.deleteReal(id);
        assertThat(true).isEqualTo(deleteReal);
    }

    public void getAllTest() throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{"27f530be-fba7-438f-ba41-478350f724a7",
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("create_date", "desc");
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        Page<InspectItem> page = new Page<>(1,2);
        HashMap<String, Object> params = Maps.newHashMap();
        page = inspectItemBiz.queryPage(params, page);
        System.out.println(page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = inspectItemBiz.getCount(null);
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
