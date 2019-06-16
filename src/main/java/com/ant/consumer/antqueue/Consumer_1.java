package com.ant.consumer.antqueue;

import com.ant.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Consumer
 * @Description Consumer
 * @Author Ant
 * @Date 2019-06-14 11:19
 * @Version 1.0
 **/
public class Consumer_1 {
    static int i = 0;

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = ConnectionUtil.getConnection();

        // 建立信道
        final Channel channel = connection.createChannel();

        // 定义队列
        channel.queueDeclare(ConnectionUtil.QUENU_NAME, true, false, false, null);

        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            long start = System.currentTimeMillis();
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                i++;
//                System.out.println(new String(body, "UTF-8"));
                // 这里我们进行消息确认
//                System.out.println("消息消费成功！！！");
                // 第二个参数是设置是否批量确认

                if(i%2500==0 || i==1200000) {
                    channel.basicAck(envelope.getDeliveryTag(), true);
                    System.out.println("耗时："+ (System.currentTimeMillis() -start) + "ms");
                }
            }
        };

        // 消息预取（设置这个消息不能设置自动确认，要设置成手动确认）
        channel.basicQos(2500);

        // 开始消费，这里是手动确认，手动确认要防止消息堆积问题
//        channel.basicConsume(ConnectionUtil.QUENU_NAME, deliverCallback);
        // 这里的第二个参数true，如果设置，则进行消息确认
        channel.basicConsume(ConnectionUtil.QUENU_NAME, false, deliverCallback);
    }
}
