package com.ant.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @ClassName MyConfirmCallback
 * @Description MyConfirmCallback
 * @Author Ant
 * @Date 2019-06-14 18:34
 * @Version 1.0
 **/
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback{
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(ack);
        System.out.println(cause);
        System.out.println(correlationData);
    }
}
