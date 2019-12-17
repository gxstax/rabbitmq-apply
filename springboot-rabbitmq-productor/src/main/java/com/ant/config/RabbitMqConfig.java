package com.ant.config;

import com.ant.callback.MyConfirmCallback;
import com.ant.callback.MyReturnCallback;
import com.ant.converter.MyMessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName RabbitMqConfig
 * @Description RabbitMqConfig
 * @Author Ant
 * @Date 2019-06-14 16:08
 * @Version 1.0
 **/
@Configuration
//@ComponentScan("com.ant")
public class RabbitMqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        // 创建一个连接工厂
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // 设置rabbitmq 服务端所在地址
//               connectionFactory.setHost("192.168.1.13");
//        // 设置端口号
//        connectionFactory.setPort(5672);
//        // 设置连接用户名
//        connectionFactory.setUsername("ant");
//        // 设置连接密码
//        connectionFactory.setPassword("370828");
//        // 设置VirtualHost
//        connectionFactory.setVirtualHost("/");



        // 设置rabbitmq 服务端所在地址
        connectionFactory.setHost("49.235.129.28");

        // 设置端口号，连接用户名， VirtualHost等
        connectionFactory.setPort(30007);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        // 开启发送发确认模式
        connectionFactory.setPublisherConfirms(true);
        // 开启失败回调
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 设置消息确认（到交换机）
//        rabbitTemplate.setConfirmCallback(confirmCallback());
//        // 设置失败回调
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setReturnCallback(returnCallback());
        // 设置消息解析器
//        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.declareExchange(directExchange());
//        admin.declareExchange(topicExchange());
//        admin.declareExchange(batDirectExchange());
//        return admin;
//    }

    /**
     * 定义一个交换机
     * @Param
     * @return
     **/
//    @Bean
//    public TopicExchange topicExchange() {
//        // 生命一个备用交换机
////        Map<String, Object> map = new HashMap<>();
////        map.put("alternate-exchange", "batExchange");
//    }

//    /**
//     * 备用交换机
//     * @Param []
//     * @return org.springframework.amqp.core.DirectExchange
//     **/
//    @Bean
//    public TopicExchange batDirectExchange() {
//        return new TopicExchange("batExchange", false, false);
//    }
//
//
//    @Bean
//    public DirectExchange directExchange() {
//        DirectExchange directExchange = new DirectExchange("deadExchange", true, false);
//        return directExchange;
//    }
//
//    @Bean
//    public Queue topicQueue() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("x-dead-letter-exchange", "deadExchange");
//        map.put("x-dead-letter-routing-key", "deadKey");
//        Queue queue = new Queue("antQueue", true, false, false, map);
//        return queue;
//    }
//
//    @Bean
//    public Queue queue() {
//        Queue queue = new Queue("deadQueue", true, false, false);
//        return queue;
//    }
//
//    @Bean
//    public Binding topicBinding() {
//        Binding binding = BindingBuilder.bind(topicQueue()).to(topicExchange()).with("debug.order.B");
//        return binding;
//    }
//
//    @Bean
//    public Binding binding() {
//        Binding binding = BindingBuilder.bind(queue()).to(directExchange()).with("deadKey");
//        return binding;
//    }
//
//    @Bean
//    public MyReturnCallback returnCallback () {
//        return new MyReturnCallback();
//    }
//
//    @Bean
//    public MyConfirmCallback confirmCallback () {
//        return new MyConfirmCallback();
//    }
//
    @Bean
    public MyMessageConverter messageConverter() {
        return new MyMessageConverter();
    }
}
