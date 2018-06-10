package com.ting.product.client;

import com.ting.product.common.DecreaseStockInput;
import com.ting.product.common.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:接口加注解的方式，伪rpc
 * User: ting
 * Date: 2018-05-23
 * Time: 下午10:35
 */
@FeignClient(name = "PRODUCT")  //server应用名
public interface ProductClient {
    @GetMapping("/msg")   //msg服务方法请求名
    String productMsg();

    //用了RequestBody注解请求方法一定要是post，不能用get，并且这里参数传递要用RequestBody否则报异常
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);
}
