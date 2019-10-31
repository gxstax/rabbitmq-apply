package com;

import com.ant.config.RabbitMqConfig;
import com.ant.send.MsgSend;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-09-29 15:43
 */
@SpringBootApplication
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);

//        AnnotationConfigApplicationContext context
//                = new AnnotationConfigApplicationContext();
//        context.register(RabbitMqConfig.class);
//        context.refresh();
//
//
//        Queue orderQueue = (Queue) context.getBean("orderQueue");
//        Queue orderQueue_2 = (Queue) context.getBean("orderQueue");
//        Queue orderQueue2 = (Queue) context.getBean("orderQueue1");
//        Map map1 = (Map) context.getBean("deadExchangeConfigMap");
//        Map map2 = (Map) context.getBean("deadExchangeConfigMap");
//
//
//        System.out.println(map1.hashCode());
//        System.out.println(map2.hashCode());
//
//
//        System.out.println("-----------------");
//        System.out.println(orderQueue.getArguments().hashCode());
//        System.out.println(orderQueue_2.getArguments().hashCode());
////        System.out.println(testQueue.getArguments().hashCode());
//
//        System.out.println("-------------------");
//
//        System.out.println(orderQueue.getArguments().get("x-dead-letter-exchange"));
//        System.out.println(orderQueue2.getArguments().get("x-dead-letter-exchange"));
//
//        System.out.println(orderQueue.getArguments().get("x-dead-letter-routing-key"));
//        System.out.println(orderQueue2.getArguments().get("x-dead-letter-routing-key"));

    }
}
