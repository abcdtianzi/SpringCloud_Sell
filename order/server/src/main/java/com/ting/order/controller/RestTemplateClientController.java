package com.ting.order.controller;

import com.mysql.jdbc.LoadBalancedConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-22
 * Time: 下午9:58
 */
@RestController
@Slf4j
public class RestTemplateClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplateConfigs;

//    http://10.0.0.8:8081/getRestTemplateProductMsg
    @GetMapping("/getRestTemplateProductMsg")
    public String getProductMsg() {
        //调用商品服务
        //第一种方式，用RestTemplate,要写死ip，维护性差,restTemplate第二个参数为返回类型
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //第二种方式，负载均衡，适用于请求多台服务器,loadBalancerClient通过Eureka注册中心注册应用获取服务地址，在通过RestTemplate发送消息
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
//        String response = restTemplate.getForObject(url, String.class);


        //第三种方式,利用@LoadBalanced，restTemplate可直接使用应用名字
        String response = restTemplateConfigs.getForObject("http://PRODUCT/msg",String.class);


        log.info("response={}", response);
        return response;
    }
}
