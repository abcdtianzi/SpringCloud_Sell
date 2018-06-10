package com.ting.product.exception;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-09
 * Time: 下午3:40
 */
public class ProductException extends RuntimeException {

    private Integer code;
    public ProductException (Integer code,String message){
        super(message);
        this.code = code;
    }

    public ProductException (Integer code){
        this.code = code;
    }
}
