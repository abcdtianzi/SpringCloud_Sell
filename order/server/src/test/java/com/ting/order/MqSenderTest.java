package com.ting.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:mq发送测试
 * User: ting
 * Date: 2018-06-11
 * Time: 上午10:40
 */
public class MqSenderTest extends DemoApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now :" +new Date());
    }

    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","fruit","now :" +new Date());
    }
}
