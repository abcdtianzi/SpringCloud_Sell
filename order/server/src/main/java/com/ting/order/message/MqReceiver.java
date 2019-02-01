package com.ting.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:接收mq消息
 * User: ting
 * Date: 2018-06-11
 * Time: 上午10:36
 */
@Slf4j
@Component
public class MqReceiver {
    //1 . 需要到控制面板添加队列 @RabbitListener(queues = "myQueue")
    //2。自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3。自动创建队列，绑定Exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MqReceiver:{}",message);
    }

    /*数码供应商 接收消息*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerOrder"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String message){
        log.info("Computer Receiver:{}",message);
    }

    /*水果供应商 接收消息*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message){
        log.info("fruit Receiver:{}",message);
    }

}
