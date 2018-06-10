package com.ting.product.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:远程调用的cartDTO对象，该对象用来封装dao不能用来直接对外暴露数据库属性
 * User: ting
 * Date: 2018-06-09
 * Time: 下午3:29
 */
@Data
public class DecreaseStockInput {
    /*商品id*/
    private String productId;


    /*商品数量*/
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
