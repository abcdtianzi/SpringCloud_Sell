package com.ting.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ting.order.utils.JsonUtil;
import com.ting.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-11
 * Time: 下午7:15
 */
@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("ProductInfo"),
            exchange = @Exchange("ProductInfoExchange")
    ))
    public void process(String productInfo) {
        //String->productInfoOutput
        List<ProductInfoOutput> productInfoOutputList = JsonUtil.fromJson(productInfo
                , new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从product队列消息收到的对象:{}", productInfoOutputList);

        //消息存储到redis,本地docker中
        for(ProductInfoOutput productInfoOutput:productInfoOutputList){
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId())
                    ,String.valueOf(productInfoOutput.getProductStock()));
        }


    }
}
