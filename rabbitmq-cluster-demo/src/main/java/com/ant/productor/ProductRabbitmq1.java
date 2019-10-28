package com.ant.productor;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConsumerRabbitmq1
 * @Description ConsumerRabbitmq1
 * @Author Ant
 * @Date 2019-06-16 14:13
 * @Version 1.0
 **/
public class ProductRabbitmq1 {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("ant");
        connectionFactory.setPassword("370828");
        connectionFactory.setHost("192.168.1.14");

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

        channel.queueDeclare("rabbitmq1_queue", true, false, false, null);
        channel.basicPublish("", "rabbitmq1_queue", null, "hello1".getBytes());
        channel.basicPublish("", "rabbitmq2_queue", null, "hello2".getBytes());

        channel.close();
        connection.close();
    }
}
