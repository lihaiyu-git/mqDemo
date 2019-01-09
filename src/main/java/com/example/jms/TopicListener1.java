package com.example.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener1 {
//    @JmsListener(destination = "mytest.queue")
//    public void receive(String text){
//        System.out.println("TopicListener: consumer-a 收到一条信息: " + text);
//    }
}
