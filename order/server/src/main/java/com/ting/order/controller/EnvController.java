package com.ting.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-10
 * Time: 下午5:41
 */
@RestController
@RefreshScope
public class EnvController {
    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print() {
        return env;
    }

}
