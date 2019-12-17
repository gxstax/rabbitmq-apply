package com.ant.rabbitmq;

import lombok.Data;

/**
 * <p>
 * 下单信息对象
 * </p>
 *
 * @author GaoXin
 * @since 2019-10-18 10:33
 */
@Data
public class PayOrderDTO {
    /**
     * 订单ID
     */
    private String orderNo;
}
