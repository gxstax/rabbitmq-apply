package com.ant.rabbitmq.callback.confirm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * <p>
 * 积分操作消息发送确认消息回调
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 14:37
 */
public class PointsMsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}
