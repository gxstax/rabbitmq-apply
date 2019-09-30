package com.ant.rabbitmq.server;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>
 * rabbitmq消息服务类
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 11:37
 */
public interface RabbitMqService {

//    void setMessageCallback(RabbitTemplate.ConfirmCallback callback);

    void send(Object message);

    void send(String routeKey, Object message);

    void send(String exchangeName, String routeKey, Object message);

    void send(String exchangeName, String routeKey, Object message, CorrelationData correlationData);

    void send(String exchangeName, String routeKey, Object message, CorrelationData correlationData, RabbitTemplate.ConfirmCallback callback);
}
