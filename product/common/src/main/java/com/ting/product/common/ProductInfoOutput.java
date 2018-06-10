package com.ting.product.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:远程调用的ProductInfo公用输出对象，该对象用来封装dao不能用来对外直接暴露数据库属性
 * User: ting
 * Date: 2018-05-15
 * Time: 下午11:03
 */
/*lombok,不用写get Set方法*/
@Data
/*spring data jpa*/
public class ProductInfoOutput {
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;

}
