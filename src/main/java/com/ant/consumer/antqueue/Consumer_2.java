package com.ant.consumer.antqueue;

import com.ant.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Consumer
 * @Description Consumer
 * @Author Ant
 * @Date 2019-06-14 11:19
 * @Version 1.0
 **/
public class Consumer_2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = ConnectionUtil.getConnection();

        // 建立信道
        final Channel channel = connection.createChannel();

        // 定义队列
        channel.queueDeclare(ConnectionUtil.QUENU_NAME, true, false, false, null);

        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, "UTF-8"));
                // 这里我们进行消息确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 消息预取（设置这个消息不能设置自动确认，要设置成手动确认）
        channel.basicQos(1);

        // 开始消费，这里是手动确认，手动确认要防止消息堆积问题
//        channel.basicConsume(ConnectionUtil.QUENU_NAME, deliverCallback);
        // 这里的第二个参数true，如果设置，则进行消息确认
        channel.basicConsume(ConnectionUtil.QUENU_NAME, false, deliverCallback);
    }
}
