package smsProvider;

import com.foundation.common.json.JsonUtils;
import com.foundation.api.model.SmsMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/spring/spring-context.xml",
        "classpath:/spring/spring-db.xml", "classpath:/spring-rabbit-provider.xml"
})
public class MQProducerTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired(required = false)
    private AmqpTemplate amqpTopicTemplate;

    @Test
    public void sendDataToQueue() {
        try {
            String queueKey="sms_queue_one_key";//交换机的Key
            Object object="this is fqh message";
            SmsMessage message=new SmsMessage();
            message.setContent("您的订单号为：test，请不要告诉他人，请及时取件");
            String[] mobiles=new String[]{"18612000126"};
            message.setMobiles(mobiles);
            amqpTemplate.convertAndSend(queueKey, JsonUtils.toJson(message));
            Object result=amqpTemplate.convertSendAndReceive(queueKey, object);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void sendTopicDataToQueue() {
        try {
            Object object="this is fqh message_topic";
            String key="sms_queue_topic_key";
            amqpTopicTemplate.convertAndSend(key,object);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}