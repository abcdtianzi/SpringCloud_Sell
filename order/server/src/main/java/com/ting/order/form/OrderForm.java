package com.ting.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:前端传输的数据，加入了校验
 * User: ting
 * Date: 2018-05-20
 * Time: 下午2:42
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "phone必填")
    private String phone;

    @NotEmpty(message = "address必填")
    private String address;

    @NotEmpty(message = "openId必填")
    private String openId;

    @NotEmpty(message = "购物车必填")
    private String items;

}
