package com.ant.send;

import com.ant.rabbitmq.callback.confirm.MessageSendConfirmCallBack;
import com.ant.rabbitmq.server.RabbitMqService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 15:28
 */
@Component
public class MsgSend {

    @Autowired
    private RabbitMqService rabbitMqService;

    public void sendMessage(String exchange, String routekey, String message) {
        CorrelationData correlationData = new CorrelationData("orderID");

//        Map<String, String> map = new HashMap<>();
////        map.put("key1", "value1");
        rabbitMqService.send(exchange, routekey, new Message(message.getBytes(), new MessageProperties()),
                correlationData, new MessageSendConfirmCallBack());

//        rabbitTemplate.convertAndSend(exchange, routekey, message, correlationData);
    }
}
