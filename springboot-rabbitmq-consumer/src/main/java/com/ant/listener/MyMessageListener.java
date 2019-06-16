package com.ant.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @ClassName MyMessageListener
 * @Description MyMessageListener
 * @Author Ant
 * @Date 2019-06-15 10:39
 * @Version 1.0
 **/
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }
}
