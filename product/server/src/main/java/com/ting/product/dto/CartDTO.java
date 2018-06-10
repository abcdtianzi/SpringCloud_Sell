package com.ting.product.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-09
 * Time: 下午3:29
 */
@Data
public class CartDTO {
    /*商品id*/
    private String productId;


    /*商品数量*/
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
