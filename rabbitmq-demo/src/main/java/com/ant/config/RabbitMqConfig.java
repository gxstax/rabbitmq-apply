package com.ant.config;

import com.ant.rabbitmq.converter.MyMessageConverter;
import com.ant.rabbitmq.converter.MyMessageHandle;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

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

//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(exchange, false, false);
//    }
//
//    @Bean
//    public Queue topicQueue() {
//        return new Queue(queue, true, false, false);
//    }
//
//    @Bean
//    public Binding topicBinding() {
//        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("debug.order.B");
//    }






    @Bean
    public DirectExchange deadTopicExchange() {
        return new DirectExchange("deadExchange");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Map deadExchangeConfigMap() {
        Map<String, Object> map = new HashMap<>(2);
        // 绑定死信交换机
        map.put("x-dead-letter-exchange", "deadExchange");

        return map;
    }

    @Bean
    public Queue orderQueue(Map deadExchangeConfigMap) {
//        Map map = deadExchangeConfigMap();
//        // 定义绑定的死信交换机
//        Map<String, Object> map = new HashMap<>(2);
//        // 绑定死信交换机
//        map.put("x-dead-letter-exchange", "deadExchange");
        // 重定向路由键
        deadExchangeConfigMap.put("x-dead-letter-routing-key", "deadQueue");
        /**
         * durable:是否持久化
         * exclusive: 是否是排他队列
         * autoDelete：是否自动删除
         **/
        return new Queue("orderQueue", true, false, false, deadExchangeConfigMap);
    }

    @Bean
    public Queue orderQueue1(Map deadExchangeConfigMap) {
//        Map map = deadExchangeConfigMap();
//        Map<String, Object> map = new HashMap<>(2);
//        // 绑定死信交换机
//        map.put("x-dead-letter-exchange", "deadExchange");
        // 重定向路由键
        deadExchangeConfigMap.put("x-dead-letter-routing-key", "deadQueue2");
        // 定义绑定的死信交换机
        /**
         * durable:是否持久化
         * exclusive: 是否是排他队列
         * autoDelete：是否自动删除
         **/
        return new Queue("orderQueue1", true, false, false, deadExchangeConfigMap);
    }

    @Bean
    public Queue deadQueue() {
        Map<String, Object> map = new HashMap<>();
        return new Queue("deadQueue", true, false, false, map);
    }

    @Bean
    public Queue deadQueue2() {
        Map<String, Object> map = new HashMap<>();
        return new Queue("deadQueue2", true, false, false, map);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(deadQueue()).to(deadTopicExchange()).with("deadQueue");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(deadQueue2()).to(deadTopicExchange()).with("deadQueue2");
    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();

        // 设置连接工厂
//        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        // 设置消息确认模式（自动、手动、不确认）
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息预取条数
        simpleRabbitListenerContainerFactory.setPrefetchCount(50);

        configurer.configure(simpleRabbitListenerContainerFactory, connectionFactory);

        simpleRabbitListenerContainerFactory.setMessageConverter(new MyMessageConverter());


        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public SimpleMessageListenerContainer simpleRabbitListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();

        // 设置连接工厂
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        // 设置消息确认模式（自动、手动、不确认）
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息预取条数
        simpleMessageListenerContainer.setPrefetchCount(50);

        MessageListenerAdapter adapter = new MessageListenerAdapter(new MyMessageHandle());

        adapter.setDefaultListenerMethod("onMessage");

        adapter.setMessageConverter(new MyMessageConverter());

        simpleMessageListenerContainer.setMessageListener(adapter);


        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();


        return simpleMessageListenerContainer;
    }


    @Bean
    MyMessageConverter myMessageConverter(){
        MyMessageConverter messageConverter = new MyMessageConverter();
        return messageConverter;
    }


}
