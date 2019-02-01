package com.ting.order.controller;

import com.ting.product.client.ProductClient;
import com.ting.product.common.DecreaseStockInput;
import com.ting.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:测试feign调用远程服务
 * User: ting
 * Date: 2018-05-22
 * Time: 下午9:58
 */
@RestController
@Slf4j
public class FeignClientController {

    @Autowired
    private ProductClient productClient;


//    http://127.0.0.1:8085/getFeignProductMsg
    @GetMapping("/getFeignProductMsg")
    public String getProductMsg() {
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022","157875227953464068"));
        log.info("response={}", productInfoList);
        return "ok";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022",2);
        productClient.decreaseStock(Arrays.asList(cartDTO));
        return "ok";
    }

    //测试传文件类型
    @PostMapping("/testMutipartFile")
    public String testMutipartFile(MultipartFile file){
        productClient.testMutipartFile(file);
        return "ok";
    }

    //测试传自定义类型
    @GetMapping("/testDtoClass")
    public String testDtoClass(){
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022",2);
        productClient.testDtoClass(cartDTO);
        return "ok";
    }



}
