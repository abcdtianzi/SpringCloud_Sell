package com.ting.order.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午10:52
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完成"),
    CANCEL(2,"取消");

    private int code;
    private String message;
    OrderStatusEnum(int code,String message){
        this.code = code;
        this.message = message;

    }

}
