package com.ant.send;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MessageSend
 * @Description MessageSend
 * @Author Ant
 * @Date 2019-06-14 16:21
 * @Version 1.0
 **/
@Component
public class MessageSend {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routekey, String message) {
        CorrelationData correlationData = new CorrelationData("orderID");

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        rabbitTemplate.convertAndSend(exchange, routekey, map, correlationData);
    }
}
