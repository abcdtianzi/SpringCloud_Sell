package com.ting.order.vo;

import lombok.Data;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:33
 */
@Data
public class ResultVo<T> {
    private Integer Code;
    private String msg;
    private T data;
}
