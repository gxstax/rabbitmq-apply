package com.ant.consume;


import com.alibaba.fastjson.JSON;
import com.ant.rabbitmq.PayOrderDTO;
import com.ant.rabbitmq.RabbitMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    @RabbitListener(queues = "antQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void getPointsQueueMessage(Message message, RabbitMessage<PayOrderDTO> message2, Channel channel) throws IOException {
        try {

            if (true) {
                // 查询订单信息和会员信息
//
                System.out.println("消费到的消息为->" + message2);

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


//    @RabbitListener(queues = "orderQueue", containerFactory = "simpleRabbitListenerContainerFactory")
//    public void orderQueue(Message message, Channel channel) throws IOException {
//        try {
//
//            if (true) {
//                // 查询订单信息和会员信息
////
//                System.out.println(new String(message.getBody(), "UTF-8"));
//
//                // TODO .... 具体业务
//                // 消息确认
////                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//
//                System.out.println("oerderQueue 消息退回");
//            } else {
//                /**
//                 * 消息退回 (批量)
//                 * 参数说明：
//                 *  消息唯一标志
//                 *  是否批量退回 false 否 true 是
//                 *  是否退回消息队列 true：是 false：否
//                 */
////                channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//
//            }
//        } catch (Exception e) {
//            // 发生异常进行消息退回
////            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//
//
//        }
//    }

//    @RabbitListener(queues = "orderQueue1", containerFactory = "simpleRabbitListenerContainerFactory")
//    public void orderQueue1(Message message, Channel channel) throws IOException {
//        try {
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//
//            System.out.println("oerderQueue1 消息退回");
//
//        } catch (Exception e) {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
//    }




//    @RabbitListener(queues = "deadQueue", containerFactory = "simpleRabbitListenerContainerFactory")
//    public void deadQueue(Message message, Channel channel) throws IOException {
//        try {
//            System.out.println("死信队列deadQueue消费到了消息：" + new String(message.getBody(), "UTF-8"));
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            // 发生异常进行消息退回
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
//
//        }
//    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "antAnnotationQueue", durable = "true"),
            exchange = @Exchange(value = "antAnnotationExchange", type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "antAnnotationKey"))
    public void deadQueue(Message message, Channel channel) throws IOException {
        try {
            System.out.println("死信队列deadQueue消费到了消息：" + new String(message.getBody(), "UTF-8"));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 发生异常进行消息退回
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);

        }
    }

}
