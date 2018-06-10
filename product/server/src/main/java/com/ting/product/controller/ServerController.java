package com.ting.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-22
 * Time: 下午9:52
 */
@RestController
public class ServerController {
    @GetMapping("/msg")
    public String msg(){
        return "this is Product Msg";
    }
}
