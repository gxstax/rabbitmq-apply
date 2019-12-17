package com.ant.consumer;

import com.ant.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-10-22 14:24
 */
public class HeyteaConsumer {
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "heytea.service.member.points.payOrderDeadQueue";
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "heytea.processor.member.queue.activity.order.send.coupons";
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "Heytea.service.member.coupon.payOrderQueue";
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "Heytea.service.member.points.refundOrderQueue";
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "Heytea.service.member.experience.refundOrderQueue";
//    private static final String QUEUE_NAMEC_POINTS_PAY_ORDER = "Heytea.service.member.coupon.refundOrderQueue";

//    private static final String QUEUE_NAME_POINTS_PAY_ORDER = "antQueue";
    private static final String QUEUE_NAME_POINTS_PAY_ORDER = "center_message_public_queue_new";


    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = ConnectionUtil.getHeyteaConnection();

        // 建立信道
        final Channel channel = connection.createChannel();
        channel.basicQos(10, false);


        // 定义队列
//        channel.queueDeclare(QUEUE_NAMEC_POINTS_PAY_ORDER, true, false, false, null);

        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("deliveryTag:" + envelope.getDeliveryTag());
                System.out.println(new String(body, "UTF-8"));
                System.out.println("isRedeliver:" + envelope.isRedeliver());
                System.out.println("consumerTag:" + consumerTag);
                System.out.println("properties" + properties);

//                channel.basicAck(envelope.getDeliveryTag(), true);


            }
        };

        // 开始消费，这里是手动确认，手动确认要防止消息堆积问题
//        channel.basicConsume(ConnectionUtil.QUENU_NAME, deliverCallback);
        // 这里的第二个参数true，如果设置，则进行消息确认
        channel.basicConsume(QUEUE_NAME_POINTS_PAY_ORDER, false, deliverCallback);



        channel.close();
        connection.close();
    }
}
