package com.ant.accept;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName MessageAccept
 * @Description MessageAccept
 * @Author Ant
 * @Date 2019-06-14 16:50
 * @Version 1.0
 **/
@Component
public class MessageAccept {

    @RabbitListener(queues = "antQueue")
    public void get(String message) {
        System.out.println("消费者1：" + message);
    }

    @RabbitListener(queues = "antQueue")
    public void get(Message message) throws UnsupportedEncodingException {
        System.out.println("消费者2：" + new String(message.getBody(), "UTF-8"));
    }
}
