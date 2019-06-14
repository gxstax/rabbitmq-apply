package com.ant.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyReturnCallback
 * @Description MyReturnCallback
 * @Author Ant
 * @Date 2019-06-14 18:31
 * @Version 1.0
 **/
public class MyReturnCallback implements RabbitTemplate.ReturnCallback{

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息信息：" +message);// 自己发送的消息+配置信息
        System.out.println("状态码：" + replyCode);// 状态码
        System.out.println("状态信息：" + replyText);//状态信息
        System.out.println("交换机：" + exchange);// 交换机
        System.out.println("路由键值：" + routingKey);
    }
}
