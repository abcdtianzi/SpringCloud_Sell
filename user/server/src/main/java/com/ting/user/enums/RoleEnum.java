package com.ting.user.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-09
 * Time: 下午3:42
 */
@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(0,"卖家")
    ;


    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
