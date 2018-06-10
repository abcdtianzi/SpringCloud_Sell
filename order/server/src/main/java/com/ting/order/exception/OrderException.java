package com.ting.order.exception;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-20
 * Time: 下午11:34
 */
public class OrderException extends RuntimeException{
    private  Integer code;

    public OrderException (Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException (Integer code){
        this.code = code;
    }
}
