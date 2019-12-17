package com.ant.producer;

import com.ant.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2019-10-22 10:33
 */
public class HeyteaProducter {

//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "heytea.service.member.points.payOrderQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "Heytea.service.member.experience.payOrderQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "Heytea.service.member.coupon.payOrderQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "Heytea.service.member.points.refundOrderQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "Heytea.service.member.experience.refundOrderQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "Heytea.service.member.coupon.refundOrderQueue";


    private static final String QUEUE_NAME1_POINTS_PAY_ORDER = "heytea.service.member.experience.payOrderDeadQueue";
//    private static final String QUEUE_NAME1_POINTS_PAY_ORDER1 = "orderQueue1";


    private static final String EXCHANGE_NAME = "";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接
        Connection connection = ConnectionUtil.getHeyteaConnection();

        // 创建一个信道
        Channel channel = connection.createChannel();

//        channel.queueDelete(QUEUE_NAME1_POINTS_PAY_ORDER);

//        channel.queueDeclare(QUEUE_NAME1_POINTS_PAY_ORDER, true, false, false, null);

        // 定义一个交换机
//        channel.exchangeDelete(ConnectionUtil.EXCHANGE_NAME);
//        channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        // 绑定交换机与队列
//        channel.queueBind(QUEUE_NAME1_POINTS_PAY_ORDER, ConnectionUtil.EXCHANGE_NAME, "ant");
//        channel.queueBind(QUEUE_NAME1_POINTS_PAY_ORDER1, ConnectionUtil.EXCHANGE_NAME, "order2");
//        channel.queueBind(ConnectionUtil.QUENU_NAME_1, ConnectionUtil.EXCHANGE_NAME, ConnectionUtil.TOPIC_1);
//        channel.queueBind(ConnectionUtil.QUENU_NAME_2, ConnectionUtil.EXCHANGE_NAME, ConnectionUtil.TOPIC_2);

        // 参数信息
        // 1：队列名称
        // 2：队列是否需要持久化（注意不同于消息持久化）
        // 3：是否是单一队列，就是仅仅对可以当前连接连接到（排他队列），一般都是false
        // 4：自动删除，当没有任何消费者消费，则自动删除
        // 5：队列消息配置项（最大容量，寿命）这个后面补充
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);


//        String[] strings = new String[]{"error", "debug", "info"};
//        String[] strings1 = new String[]{"user", "order", "email"};
//        String[] strings2 = new String[]{"A", "B", "C"};
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    String topic = strings[i]+"."+strings1[j]+"."+strings2[k];
//                    // 发布一条消息
//                    channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, topic,null, topic.getBytes());
//                }
//            }
//        }

//        for (int i = 0; i < 1200000; i++) {
//            // 发布一条消息
//            channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, "debug.order.B",null, ("message"+i).getBytes());
//        }

        for (int i = 0; i < 1; i++) {
//            Map messMap = new HashMap<>(1);
//            messMap.put("orderNo", "655101201811191556408027");
            channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, "ant" ,null, ("{'orderNo':'655101201811191556408027'}").getBytes());
        }


        // 关闭信道
        channel.close();

        // 关闭连接
        connection.close();
    }
}
