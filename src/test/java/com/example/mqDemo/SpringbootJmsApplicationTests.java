package com.example.mqDemo;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.jms.Consumer;
import com.example.jms.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQStreamMessage;
import org.json.JSONArray;
import org.json.JSONString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTests {
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;
    @Autowired
    JmsTemplate jms;
    @Autowired
    Topic topic;
    @Test
    public void contextLoads() throws JMSException{
        Destination destination = new ActiveMQQueue("mytest.queue");

        //String
        producer.sendMessage(destination, "String test");

        //map
        Map<String,Object> map = new HashMap<>();
        map.put("name","mq map test1");
        producer.sendMap(destination,map);

        //object
        User user = new User();
        user.setAge(18);
        user.setName("lihaiyu");

        producer.sendObject(destination,user);

        //bytes
        jms.send(destination,new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bytesMessage = session.createBytesMessage();
                bytesMessage.writeBytes("BytesMessage类型消息".getBytes());
                return bytesMessage;
            }
        });

//        jms.send(destination,new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                User user = new User();
//                user.setAge(18);
//                user.setName("lihaiyu");
//
//                ObjectMessage objectMessage = session.createObjectMessage(user);
//                return objectMessage;
//            }
//        });


    }


}
