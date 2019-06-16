package com.ant.accept;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName MessageAccept
 * @Description MessageAccept
 * @Author Ant
 * @Date 2019-06-14 16:50
 * @Version 1.0
 **/
@Component
public class MessageAccept {

//    @RabbitListener(queues = "antQueue", containerFactory = "simpleRabbitListenerContainerFactory")
//    public void get(Message message, Channel channel) throws IOException {
//        System.out.println("消费者1：" + new String(message.getBody(), "UTF-8"));
//        // 设置预取消息条数
//        channel.basicQos(2500);
//        if(placeOrder()) {
//            // 消息确认
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }else {
//            // 消息退回(批量退回)
//            // 最后一个参数是是否退回到队列中去
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            // 消息退回（单条退回）
////            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//            System.out.println("消息退回");
//        }
//    }
//
//    public boolean placeOrder() {
//        return true;
//    }

    @RabbitListener(queues = "antQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void get2(Message message, Channel channel) throws IOException {
        System.out.println("消费者2：" + new String(message.getBody(), "UTF-8"));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
        System.out.println("antQueue 消息被退回");
    }

    @RabbitListener(queues = "deadQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void getDead(Message message, Channel channel) throws IOException {
        System.out.println("deadQueue消费：" + new String(message.getBody(), "UTF-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
