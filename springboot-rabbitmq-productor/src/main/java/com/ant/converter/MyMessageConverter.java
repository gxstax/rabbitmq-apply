package com.ant.converter;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

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
        messageProperties.setContentType("text/json");
        Message message = new Message(JSON.toJSONBytes(obj), messageProperties);
//        System.out.println("toMessage");
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return null;
    }
}
