package questionnaire;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;
import com.foundation.service.questionnaire.biz.IRepstQuestionsCtgrBiz;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-db.xml"})
public class RepstQuestionsCtgrTest {
    @Autowired
    private IRepstQuestionsCtgrBiz repstQuestionsCtgrBiz;
    private String id = IdGen.uuid();

    @Test
    public void crudTest(){
        try {
            System.out.println("crud begin.....RepstQuestionsCtgr");
            saveTest();
            saveListTest();
            getTest();
            updateTest();
            countTest();
            getAllTest();
            detailTest();
            detailPageTest();
            deleteTest();
            System.out.println("crud end....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTest() throws Exception{
        RepstQuestionsCtgr entity = new RepstQuestionsCtgr();
        entity.setId(id);
        boolean flag = repstQuestionsCtgrBiz.save(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void saveListTest() throws Exception{
        List<RepstQuestionsCtgr> list = new ArrayList<>();
        RepstQuestionsCtgr entity = new RepstQuestionsCtgr();
        entity.setId(IdGen.uuid());
        list.add(entity);
        RepstQuestionsCtgr quest = new RepstQuestionsCtgr();
        quest.setId(IdGen.uuid());
        list.add(quest);
        boolean flag = repstQuestionsCtgrBiz.save(list);
        assertThat(true).isEqualTo(flag);
    }

    public void getTest() throws Exception {
        RepstQuestionsCtgr entity = repstQuestionsCtgrBiz.queryById(id);
        assertThat(id).isEqualTo(entity.getId());
    }

    public void updateTest() throws Exception {
        RepstQuestionsCtgr entity = new RepstQuestionsCtgr();
        entity.setId(id);
        entity.setOrderId(12);
        boolean flag = repstQuestionsCtgrBiz.update(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void deleteTest() throws Exception {
        boolean flag = repstQuestionsCtgrBiz.deleteReal(id);
        assertThat(true).isEqualTo(flag);
    }

    public void getAllTest() throws Exception {
        Page<RepstQuestionsCtgr> page = new Page<RepstQuestionsCtgr>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        page = repstQuestionsCtgrBiz.queryPage(map, page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = repstQuestionsCtgrBiz.getCount(null);
        assertThat(count).isNotEqualTo(0);
    }

    public void detailTest() throws Exception {
        System.out.println("aaaaa");
        RepstQuestionsCtgr entity = repstQuestionsCtgrBiz.queryDetailById("316a9a1e-068b-4b8a-a648-c728c71090c3");
        assertThat(entity.getId()).isNotEmpty();
    }

    public void detailPageTest() throws Exception {
        Page<RepstQuestionsCtgr> page = new Page<>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{
                "316a9a1e-068b-4b8a-a648-c728c71090c3",
                id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        repstQuestionsCtgrBiz.queryDetailPage(map, page);
        assertThat(page.getList().size()).isNotEqualTo(0);
    }


    @Test
    public void userTest(){
        String username = "dev";
        String password = "dev";
        try {
            String encrypt = ConfigTools.encrypt(password);
            System.out.println(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
