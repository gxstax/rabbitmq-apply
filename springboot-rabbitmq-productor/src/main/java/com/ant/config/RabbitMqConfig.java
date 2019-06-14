package com.ant.config;

import com.ant.callback.MyConfirmCallback;
import com.ant.callback.MyReturnCallback;
import com.ant.converter.MyMessageConverter;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan("com.ant")
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

        // 开启发送发确认模式
        connectionFactory.setPublisherConfirms(true);
        // 开启失败回调
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MyReturnCallback returnCallback,
                                         MyConfirmCallback confirmCallback, MyMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // 设置消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);

        rabbitTemplate.setMandatory(true);
        // 设置失败回调
        rabbitTemplate.setReturnCallback(returnCallback);

        // 设置消息解析器
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    /**
     * 定义一个交换机
     * @Param
     * @return
     **/
    @Bean
    public TopicExchange directExchange() {
        // 生命一个备用交换机
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange", "batExchange");
        return new TopicExchange("exchange", false, false, map);
    }

    /**
     * 备用交换机
     * @Param []
     * @return org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public TopicExchange batDirectExchange() {
        return new TopicExchange("batExchange", false, false);
    }

    @Bean
    public MyReturnCallback returnCallback () {
        return new MyReturnCallback();
    }

    @Bean
    public MyConfirmCallback confirmCallback () {
        return new MyConfirmCallback();
    }

    @Bean
    public MyMessageConverter messageConverter() {
        return new MyMessageConverter();
    }
}
