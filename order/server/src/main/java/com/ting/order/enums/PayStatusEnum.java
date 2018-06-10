package com.ting.order.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午10:56
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private int code;
    private String message;
    PayStatusEnum(int code,String message){
        this.code = code;
        this.message = message;

    }
}
