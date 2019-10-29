package com.ant.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 * rabbitmq配置类
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 11:16
 */
@Configuration
@ComponentScan("com.ant")
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String address;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.config.exchange.points.pointsExchange}")
    private String exchange;

    @Value("${rabbitmq.config.queue.points.pointsQueue}")
    private String queue;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        // 是否开启消息确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 开启失败回调
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange, false, false);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(queue, true, false, false);
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("debug.order.B");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        // 设置连接工厂
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        // 设置消息确认模式（自动、手动、不确认）
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息预取条数
        simpleRabbitListenerContainerFactory.setPrefetchCount(2500);

        return simpleRabbitListenerContainerFactory;
    }


}
