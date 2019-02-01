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
public enum ResultEnum {
    LOGIN_ERROR(1,"登录失败"),
    LOGIN_SUCCESS(0,"登录成功"),

    ROLE_ERROR(2,"角色权限不足");
  ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
