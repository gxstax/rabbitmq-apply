package com.ant.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.Data;
//import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.amqp.core.Message;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * <p>
 * 消息队列信息类
 * </p>
 *
 * @author GaoXin
 * @since 2019-10-18 10:18
 */
@Data
public class RabbitMessage<T> implements Serializable {

    private static final long serialVersionUID = -8875630394300519452L;
    /**
     * 消息队列主键信息
     */
    private long deliveryTag;

    private String message;

    private T data;


    public RabbitMessage buildRabbitMessage(final Message message, Class t) {
        this.deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String messageBody = new String(message.getBody(), "UTF-8");
            messageBody = messageBody.replaceAll("\"", "");
            this.message = messageBody;
//            this.message = StringEscapeUtils.unescapeJava(messageBody);
        } catch (UnsupportedEncodingException e) {
            this.message = "";
        }
        this.data = JSON.parseObject(this.message, (Type) t);
        return this;
    }

}
