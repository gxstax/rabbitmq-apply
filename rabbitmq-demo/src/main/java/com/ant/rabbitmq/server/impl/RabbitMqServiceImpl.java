package com.ant.rabbitmq.server.impl;

import com.ant.rabbitmq.callback.returns.MessageReturnCallBack;
import com.ant.rabbitmq.server.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 11:38
 */
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(new MessageReturnCallBack());
    }

    @Override
    public void send(Object message) {
        rabbitTemplate.convertAndSend(message);
    }

    @Override
    public void send(String routeKey, Object message) {
        rabbitTemplate.convertAndSend(routeKey, message);
    }

    @Override
    public void send(String exchangeName, String routeKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routeKey, message);
    }

    @Override
    public void send(String exchangeName, String routeKey, Object message, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(exchangeName, routeKey, message, correlationData);
    }

    @Override
    public void send(String exchangeName, String routeKey, Object message, CorrelationData correlationData, RabbitTemplate.ConfirmCallback callback) {
        rabbitTemplate.setConfirmCallback(callback);
        rabbitTemplate.convertAndSend(exchangeName, routeKey, message, correlationData);
    }
}
