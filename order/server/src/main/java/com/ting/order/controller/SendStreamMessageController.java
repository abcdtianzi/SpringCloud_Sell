package com.ting.order.controller;

import com.ting.order.dto.OrderDTO;
import com.ting.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-11
 * Time: 下午2:06
 */
@RestController
public class SendStreamMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        String message = "stream now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }
    @GetMapping("/sendObject")
    public void process2(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }



}
