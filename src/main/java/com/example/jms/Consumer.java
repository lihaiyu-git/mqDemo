package com.example.jms;

import com.example.entity.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Map;

@Component
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(Message message) throws JMSException{
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;
            System.out.println("--------text test-------"+tm.getText());
        }

        if(message instanceof MapMessage){
            MapMessage map = (MapMessage) message;
            System.out.println("--------map test-------"+map.getString("name"));
        }

        if(message instanceof ObjectMessage){
            User user = (User) ((ObjectMessage)message).getObject();
            System.out.println("--------object test-------"+user.getName());
        }

        if (message instanceof BytesMessage) {
            byte[] b = new byte[1024];
            int len = -1;
            BytesMessage bm = (BytesMessage) message;
            while ((len = bm.readBytes(b)) != -1)
            {
                System.out.println("-------byte test-----"+new String(b, 0, len));
            }
        }
    }

//    @JmsListener(destination = "mytest.queue")
//    public void receiveMap(Message message) throws JMSException{
//        if(message instanceof MapMessage){
//            MapMessage map = (MapMessage) message;
//            System.out.println(map.getString("name"));
//        }
//    }
//
//    @JmsListener(destination = "mytest.queue")
//    public void receiveObject(Message message) throws JMSException {
//        if(message instanceof ObjectMessage){
//            User user = (User) ((ObjectMessage)message).getObject();
//            System.out.println("--------object test-------"+user.getName());
//        }
//    }
//
//    @JmsListener(destination = "mytest.queue")
//    public void receiveBytes(Message message) throws JMSException {
//        if (message instanceof BytesMessage) {
//            byte[] b = new byte[1024];
//            int len = -1;
//            BytesMessage bm = (BytesMessage) message;
//            while ((len = bm.readBytes(b)) != -1)
//            {
//                System.out.println("-------byte-----"+new String(b, 0, len));
//            }
//        }
//    }


}
