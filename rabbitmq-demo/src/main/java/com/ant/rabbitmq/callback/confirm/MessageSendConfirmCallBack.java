package com.ant.rabbitmq.callback.confirm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息发送确认回调
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 13:04
 */

public class MessageSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    /**
     * <p>
     * 功能描述
     * </p>
     *
     * @param correlationData 回调信息
     * @param ack 是否成功
     * @param cause 失败原因
     * @return void
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData.getId();
        System.out.println("id---------->" + id);
        System.out.println("是否发送成功：" +ack);
        System.out.println(cause);
    }
}
