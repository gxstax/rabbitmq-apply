package com.ant.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectionUtil
 * @Description ConnectionUtil
 * @Author Ant
 * @Date 2019-06-02 15:57
 * @Version 1.0
 **/
public class ConnectionUtil {

    public static final String QUENU_NAME = "antQueue";
    public static final String QUENU_NAME_1 = "antQueue1";
    public static final String QUENU_NAME_2 = "antQueue2";

    public static final String ROUTE_KEY = "debug.ant";
    public static final String ROUTE_KEY_1 = "info.ant";
    public static final String ROUTE_KEY_2 = "error.ant";

    public static final String TOPIC = "debug.*.B";
    public static final String TOPIC_1 = "error.#";
    public static final String TOPIC_2 = "*.email.*";



    public static final String TOPIC_HEY_TEA_PAY = "Heytea.service.member.*.payOrderQueue";
    public static final String TOPIC_HEY_TEA_REFUND = "Heytea.service.member.*.refundOrderQueue";





    public static final String EXCHANGE_NAME = "heytea.service.member.order.exchange";

    /**
     * 获取rabbitmq连接
     * @Param []
     * @return com.rabbitmq.client.Connection
     **/
    public static Connection getConnection() throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置rabbitmq 服务端所在地址
        connectionFactory.setHost("192.168.1.13");

        // 设置端口号，连接用户名， VirtualHost等
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ant");
        connectionFactory.setPassword("370828");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }

    /**
     * 获取rabbitmq连接
     * @Param []
     * @return com.rabbitmq.client.Connection
     **/
    public static Connection getHeyteaConnection() throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置rabbitmq 服务端所在地址
        connectionFactory.setHost("49.235.129.28");

        // 设置端口号，连接用户名， VirtualHost等
        connectionFactory.setPort(30007);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }
}
