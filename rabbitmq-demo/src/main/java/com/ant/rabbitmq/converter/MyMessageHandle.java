package com.ant.rabbitmq.converter;

import com.ant.rabbitmq.RabbitMessage;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-11-14 10:17
 */
public class MyMessageHandle {

    public void onMessage(RabbitMessage rabbitMessage) {
        System.out.println(rabbitMessage);

    }
}
