package com.ant.rabbitmq.callback.returns;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-30 09:12
 */
public class MessageReturnCallBack implements RabbitTemplate.ReturnCallback {

    /**
     * <p>
     * 功能描述
     * </p>
     *
     * @param message 发送消息 + 消息配置信息
     * @param replyCode 状态码
     * @param replyText 失败信息
     * @param exchange 交换机名称
     * @param routingKey 路由键名称
     * @return void
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message);
        System.out.println(replyCode);
        System.out.println(replyText);
        System.out.println(exchange);
        System.out.println(routingKey);
    }
}
