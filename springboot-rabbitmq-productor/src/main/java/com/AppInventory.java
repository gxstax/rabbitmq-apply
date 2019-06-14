package com;

import com.ant.config.RabbitMqConfig;
import com.ant.send.MessageSend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName AppInventory
 * @Description AppInventory
 * @Author Ant
 * @Date 2019-06-14 16:19
 * @Version 1.0
 **/
@SpringBootApplication
public class AppInventory {
    public static void main(String[] args) {
        SpringApplication.run(AppInventory.class);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
//        MessageSend messageSend = context.getBean(MessageSend.class);
//        messageSend.sendMessage("exchange", "debug.order.B", "hh1234");
//        context.close();
    }
}
