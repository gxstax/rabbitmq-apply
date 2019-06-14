package com.ant.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName RabbitMqConfig
 * @Description RabbitMqConfig
 * @Author Ant
 * @Date 2019-06-14 16:08
 * @Version 1.0
 **/
@Configuration
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        // 创建一个连接工厂
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // 设置rabbitmq 服务端所在地址
        connectionFactory.setHost("192.168.1.13");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接用户名
        connectionFactory.setUsername("ant");
        // 设置连接密码
        connectionFactory.setPassword("370828");
        // 设置VirtualHost
        connectionFactory.setVirtualHost("/");

        return connectionFactory;
    }
}
