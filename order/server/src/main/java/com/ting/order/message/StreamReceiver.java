package com.ting.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:接收端
 * User: ting
 * Date: 2018-06-11
 * Time: 下午2:02
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    @StreamListener("myMessage")
    @SendTo("myMessage2")
    public String process(Object message){
        log.info("StreamReceiver:{}",message);
        //回应消息到myMessage2
        return "收到";

    }

    @StreamListener("myMessage2")
    public void process2(Object message){
        log.info("StreamReceiver2:{}",message);

    }
}
