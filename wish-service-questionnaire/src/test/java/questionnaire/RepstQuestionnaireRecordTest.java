package questionnaire;

import com.alibaba.druid.filter.config.ConfigTools;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireRecord;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireRecordBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-db.xml"})
public class RepstQuestionnaireRecordTest {
    @Autowired
    private IRepstQuestionnaireRecordBiz repstQuestionnaireRecordBiz;
    private String id = IdGen.uuid();

    @Test
    public void crudTest(){
        try {
            System.out.println("crud begin...");
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
        RepstQuestionnaireRecord entity = new RepstQuestionnaireRecord();
        entity.setId(id);
        String questionnaireId = "6def6fdb-4b20-43a6-b608-b62bfc9d0609";
        entity.setQuestionnaireId(questionnaireId);
        List<RepstUserQuestion> list = Lists.newArrayList();
        RepstUserQuestion userQuestion = new RepstUserQuestion();
        userQuestion.setId(IdGen.uuid());
        userQuestion.setRecordId(id);
        userQuestion.setQuestionnaireId(questionnaireId);
        userQuestion.setQuestionId("0b9be963-163c-42ff-a637-9bee8385bb86");
        userQuestion.setId("00fde4da-5492-45c1-b182-dc0f8c46278f");
        list.add(userQuestion);
        entity.setRepstUserQuestionList(list);
        boolean flag = repstQuestionnaireRecordBiz.save(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void saveListTest() throws Exception{
        List<RepstQuestionnaireRecord> list = new ArrayList<>();
        RepstQuestionnaireRecord entity = new RepstQuestionnaireRecord();
        entity.setId(IdGen.uuid());
        entity.setQuestionnaireId("questId_1");
        list.add(entity);
        RepstQuestionnaireRecord quest = new RepstQuestionnaireRecord();
        quest.setId(IdGen.uuid());
        quest.setQuestionnaireId("questId_2");
        list.add(quest);
        boolean flag = repstQuestionnaireRecordBiz.save(list);
        assertThat(true).isEqualTo(flag);
    }

    public void getTest() throws Exception {
        RepstQuestionnaireRecord entity = repstQuestionnaireRecordBiz.queryById(id);
        assertThat(id).isEqualTo(entity.getId());
    }

    public void updateTest() throws Exception {
        RepstQuestionnaireRecord entity = new RepstQuestionnaireRecord();
        entity.setId(id);
        Date date = new Date();
        entity.setAnswerStartTime(date);
        boolean flag = repstQuestionnaireRecordBiz.update(entity);
        assertThat(true).isEqualTo(flag);
    }

    public void deleteTest() throws Exception {
        boolean flag = repstQuestionnaireRecordBiz.deleteReal(id);
        assertThat(true).isEqualTo(flag);
    }

    public void getAllTest() throws Exception {
        Page<RepstQuestionnaireRecord> page = new Page<RepstQuestionnaireRecord>(1, 2);
        HashMap<String, Object> map = Maps.newHashMap();
        ArrayList<Object> list = Lists.newArrayList(new Object[]{
                                        "68dd2b91-e7f6-4c89-bdc2-ba574c1c5f09",
                                        "6c444f83-c3f2-4437-b3a0-f00641116d8d",
                                        id});
        map.put("ids", list);
        LinkedHashMap<Object, Object> orderMap = Maps.newLinkedHashMap();
        orderMap.put("id", "asc");
        map.put("orders", orderMap);
        page = repstQuestionnaireRecordBiz.queryPage(map, page);
        assertThat(page.getCount()).isNotEqualTo(0);
    }

    public void countTest() throws Exception {
        int count = repstQuestionnaireRecordBiz.getCount(null);
        assertThat(count).isNotEqualTo(0);
    }


    public void detailTest() throws Exception {
        String id2 = "9a7bf1fd-0d88-48df-b4b1-5cb69cb8e65c";
        RepstQuestionnaireRecord entity = repstQuestionnaireRecordBiz.queryDetailById("9a7bf1fd-0d88-48df-b4b1-5cb69cb8e65c");
        assertThat(entity.getRepstUserQuestionList().size()).isNotEqualTo(0);
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
