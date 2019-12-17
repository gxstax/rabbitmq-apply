package com.ant.rabbitmq.converter;

import com.alibaba.fastjson.JSON;
import com.ant.rabbitmq.PayOrderDTO;
import com.ant.rabbitmq.RabbitMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cache.annotation.Cacheable;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName MyMessageConverter
 * @Description MyMessageConverter
 * @Author Ant
 * @Date 2019-06-14 19:42
 * @Version 1.0
 **/
public class MyMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object obj, MessageProperties messageProperties) throws MessageConversionException {
        // 解决消息队列解析为二进制字节码--消费者1：123,34,107,101,121,49,34,58,34,118,97,108,117,101,49,34,125
        messageProperties.setContentType("text/xml");
        Message message = new Message(JSON.toJSONBytes(obj), messageProperties);
//        System.out.println("toMessage");
        return message;
    }

    @Override
    public Object fromMessage(Message message) {
        System.out.println("到了接收消息解析器");

        RabbitMessage<PayOrderDTO> rabbitMessage = new RabbitMessage().buildRabbitMessage(message, PayOrderDTO.class);
        String message2 = null;
        try {
            message2 = new String("{'orderNo':'sssssssiiidjjkk'}".getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new Message(JSON.toJSONBytes(rabbitMessage.getData()), null).getBody();
    }
}
