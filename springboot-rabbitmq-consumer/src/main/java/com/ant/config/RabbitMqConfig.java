package com.ant.config;

import com.ant.listener.MyMessageListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
//        // 设置连接工厂
//        messageListenerContainer.setConnectionFactory(connectionFactory());
//        // 设置消息监听
//        messageListenerContainer.setMessageListener(messageListener());
//        // 设置队列
//        messageListenerContainer.addQueueNames("antQueue");
//        // 设置
//        messageListenerContainer.addQueues();
//        //
//        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        return messageListenerContainer;
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        // 设置连接工厂
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
        // 设置消息确认模式（自动、手动、不确认）
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息预取条数
        simpleRabbitListenerContainerFactory.setPrefetchCount(2500);

        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MyMessageListener messageListener() {
        return new MyMessageListener();
    }

}
