package com.ting.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ting.product.client"})  //这块注意要加，否则调用不到jar包里的client
@SpringCloudApplication
@ComponentScan(basePackages = "com.ting") //如果不加的话服务降级没法调用到
@EnableHystrixDashboard
public class SpringCloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOrderApplication.class, args);
	}
}
