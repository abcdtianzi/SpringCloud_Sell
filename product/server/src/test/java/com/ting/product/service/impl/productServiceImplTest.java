package com.ting.product.service.impl;

import com.ting.product.SpringCloudProductApplicationTests;
import com.ting.product.common.DecreaseStockInput;
import com.ting.product.dataobject.ProductInfo;
import com.ting.product.dto.CartDTO;
import com.ting.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:15
 */
@Component
public class productServiceImplTest extends SpringCloudProductApplicationTests{

    @Autowired
    private ProductService productService;

    @Test
    public void testFindUpAll() throws Exception {
        Assert.assertTrue(productService.findUpAll().size() > 0);
    }

    @Test
    public void decreaseStock()throws Exception{
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}