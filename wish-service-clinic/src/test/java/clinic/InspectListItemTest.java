package clinic;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.clinic.InspectListItem;
import com.foundation.service.clinic.biz.IInspectListItemBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-db.xml"})
public class InspectListItemTest {
    @Autowired
    private IInspectListItemBiz inspectListItemBiz;
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
            updateByMapTest();
            getAllTest();
            deleteTest();
            System.out.println("test end....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTest() throws Exception{
        InspectListItem inspectListItem = new InspectListItem();
        inspectListItem.setId(id);
        boolean flag = inspectListItemBiz.save(inspectListItem);
        assertThat(true).isEqualTo(flag);
}

    public void getTest() throws Exception {
        InspectListItem inspectListItem = inspectListItemBiz.queryById(id);
        assertThat(id).isEqualTo(inspectListItem.getId());
    }

    public void updateTest() throws Exception {
        InspectListItem inspectListItem = new InspectListItem();
        inspectListItem.setId(id);
        String name = "test";
        inspectListItem.setInspectName(name);
        boolean update = inspectListItemBiz.update(inspectListItem);
        assertThat(true).isEqualTo(update);
    }

    public void deleteTest() throws Exception {
        boolean deleteReal = inspectListItemBiz.deleteReal(id);
        assertThat(true).isEqualTo(deleteReal);
    }

    public void getAllTest() throws Exception {
        Page<InspectListItem> page = new Page<InspectListItem>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{"24c136a2-6974-411c-b990-4ceab48fddee",
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        page = inspectListItemBiz.queryPage(map, page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = inspectListItemBiz.getCount(null);
        assertThat(count).isNotEqualTo(0);
    }

    public void updateByMapTest() throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("itemIds", Lists.newArrayList("27f530be-fba7-438f-ba41-478350f724a7",
                "68c3972c-83cf-4a78-825a-0975cab13339", id));
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("del_flag", 1);
        map.put("params", params);
        int count = inspectListItemBiz.updateByMap(map);
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
