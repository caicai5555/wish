package questionnaire;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgr;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireCtgrBiz;
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
public class RepstQuestionnaireCtgrTest {
    @Autowired
    private IRepstQuestionnaireCtgrBiz repstQuestionnaireCtgrBiz;
    private String id = IdGen.uuid();

    @Test
    public void crudTest(){
        try {
            System.out.println("crud begin.....RepstQuestionnaireCtgr");
            saveTest();
            saveListTest();
            getTest();
            updateTest();
            countTest();
            getAllTest();
            deleteTest();
            detailTest();
            System.out.println("crud end....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTest() throws Exception{
        RepstQuestionnaireCtgr entity = new RepstQuestionnaireCtgr();
        entity.setId(id);
        boolean flag = repstQuestionnaireCtgrBiz.save(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void saveListTest() throws Exception{
        List<RepstQuestionnaireCtgr> list = new ArrayList<>();
        RepstQuestionnaireCtgr entity = new RepstQuestionnaireCtgr();
        entity.setId(IdGen.uuid());
        list.add(entity);
        RepstQuestionnaireCtgr quest = new RepstQuestionnaireCtgr();
        quest.setId(IdGen.uuid());
        list.add(quest);
        boolean flag = repstQuestionnaireCtgrBiz.save(list);
        assertThat(true).isEqualTo(flag);
    }

    public void getTest() throws Exception {
        RepstQuestionnaireCtgr entity = repstQuestionnaireCtgrBiz.queryById(id);
        assertThat(id).isEqualTo(entity.getId());
    }

    public void updateTest() throws Exception {
        RepstQuestionnaireCtgr entity = new RepstQuestionnaireCtgr();
        entity.setId(id);
        entity.setOrderId(123);
        boolean flag = repstQuestionnaireCtgrBiz.update(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void deleteTest() throws Exception {
        boolean flag = repstQuestionnaireCtgrBiz.deleteReal(id);
        assertThat(true).isEqualTo(flag);
    }

    public void getAllTest() throws Exception {
        Page<RepstQuestionnaireCtgr> page = new Page<RepstQuestionnaireCtgr>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        page = repstQuestionnaireCtgrBiz.queryPage(map, page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = repstQuestionnaireCtgrBiz.getCount(null);
        assertThat(count).isNotEqualTo(0);
    }

    public void detailTest() throws Exception {
        String id = "e1d04674-52a4-43ca-9725-a53f3a9d44a5";
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
