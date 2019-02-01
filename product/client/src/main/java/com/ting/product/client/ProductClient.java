package com.ting.product.client;

import com.ting.product.common.DecreaseStockInput;
import com.ting.product.common.ProductInfoOutput;
import com.ting.product.config.MultipartSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:接口加注解的方式，伪rpc
 * User: ting
 * Date: 2018-05-23
 * Time: 下午10:35
 */
@FeignClient(name = "PRODUCT",configuration = {MultipartSupportConfig.class},fallback = ProductClient.ProductClientFallback.class)  //server应用名
public interface ProductClient {
    @GetMapping("/msg")   //msg服务方法请求名
    String productMsg();

    //用了RequestBody注解请求方法一定要是post，不能用get，并且这里参数传递要用RequestBody否则报异常
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);

    //测试传文件类型
    @RequestMapping(value = "/product/testMutipartFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String testMutipartFile(@RequestPart(value = "file", required = true)  MultipartFile file);

    //测试传自定义类型
    @PostMapping("/product/testDtoClass")
    public String testDtoClass(@RequestBody  DecreaseStockInput cartDTO);


    @Component
    class ProductClientFallback implements ProductClient{
        @Override
        public String productMsg() {
            return "太拥挤了";
        }

        @Override
        public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {

        }

        @Override
        public String testMutipartFile(@RequestPart(value = "file", required = true) MultipartFile file) {
            return "太拥挤了";
        }

        @Override
        public String testDtoClass(@RequestBody DecreaseStockInput cartDTO) {
            return "太拥挤了";
        }
    }
}
