package com.ting.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PipedReader;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2019-01-26
 * Time: 下午2:59
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixContorller {

    @Autowired
    private RestTemplate restTemplateConfigs;


    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        return restTemplateConfigs.postForObject("http://PRODUCT/product/listForOrder", Arrays.asList("157875196366160022", "157875227953464068"), String.class);
    }

    //超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    //熔断配置
    //下面的配置意思是每5次调用，如果错误率达到60%以上将会熔断10s，10s后进入半开状态，再次请求如果还失败将继续熔断10s，重新计时
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //错误阈值
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //熔断持续时间，10s
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //错误达到熔断的百分比
//    })
    @HystrixCommand
    @GetMapping("/getProductInfoList2")
    public String getProductInfoList2(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        return restTemplateConfigs.postForObject("http://PRODUCT/product/listForOrder", Arrays.asList("157875196366160022", "157875227953464068"), String.class);
    }

    private String fallback() {
        return "太拥挤了，请稍后再试";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤";
    }

}
