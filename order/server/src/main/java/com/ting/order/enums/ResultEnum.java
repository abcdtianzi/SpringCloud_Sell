package com.ting.order.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-20
 * Time: 下午11:37
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    CART_EMPTY(2,"购物车为空");

    private Integer code;
    private String message;

    ResultEnum(Integer code , String message){
        this.code = code;
        this.message = message;
    }
}
