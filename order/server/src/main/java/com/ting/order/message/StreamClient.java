package com.ting.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-11
 * Time: 下午2:00
 */
public interface StreamClient {
    String INPUT = "myMessage";
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();

    String INPUT2 = "myMessage2";
    @Input(StreamClient.INPUT2)
    SubscribableChannel input2();

    @Output(StreamClient.INPUT2)
    MessageChannel output2();
}
