package com.ant.controller;

import com.ant.send.MsgSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description OrderController
 * @Author Ant
 * @Date 2019-06-14 16:23
 * @Version 1.0
 **/
@RestController
public class OrderController {

    @Autowired
    MsgSend msgSend;

    @RequestMapping("/order")
    public Object order(String exchange, String routeKey, String message) {
        msgSend.sendMessage(exchange, routeKey, message);
        return "下单成功！！！";
    }
}
