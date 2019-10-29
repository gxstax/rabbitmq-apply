package com.ant.consume;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 下单队列消费
 * </p>
 *
 * @author GaoXin
 * @since 2019-10-16 19:17
 */
@Component
public class PayOrderQueueReceive {





    @RabbitListener(queues = "heytea.processor.member.queue.activity.order.send.coupons", containerFactory = "simpleRabbitListenerContainerFactory")
    public void getPointsQueueMessage(Message message, Channel channel) throws IOException {
        try {

            if (true) {
                // 查询订单信息和会员信息
//
                System.out.println(new String(message.getBody(), "UTF-8"));

                // TODO .... 具体业务
                // 消息确认
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                /**
                 * 消息退回 (批量)
                 * 参数说明：
                 *  消息唯一标志
                 *  是否批量退回 false 否 true 是
                 *  是否退回消息队列 true：是 false：否
                 */
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            }
        } catch (Exception e) {
            // 发生异常进行消息退回
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);


        }
    }


}
