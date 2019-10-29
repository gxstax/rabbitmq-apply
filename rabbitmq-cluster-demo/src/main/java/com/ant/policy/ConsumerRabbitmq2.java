package com.ant.policy;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConsumerRabbitmq1
 * @Description ConsumerRabbitmq1
 * @Author Ant
 * @Date 2019-06-16 14:13
 * @Version 1.0
 **/
public class ConsumerRabbitmq2 {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("ant");
        connectionFactory.setPassword("370828");
        connectionFactory.setHost("192.168.1.15");

        // 超时配置
        connectionFactory.setChannelRpcTimeout(100000);
        connectionFactory.setConnectionTimeout(100000);
        connectionFactory.setHandshakeTimeout(100000);
        connectionFactory.setShutdownTimeout(100000);
        return connectionFactory.newConnection();
    }


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        // 设置参数
        Map<String , Object> map = new HashMap<>();
        map.put("x-ha-policy", "nodes");
        map.put("x-ha-nodes", "[rabbit@rabbitmq1]");

        channel.queueDeclare("rabbitmq2_queue", true, false, false, null);
//        channel.queueDelete("rabbitmq1_queue");
//        channel.queueDelete("rabbitmq2_queue");

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, "UTF-8"));
            }
        };

        channel.basicConsume("rabbitmq2_queue", true, defaultConsumer);
    }
}
