package com.ting.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ting.order.dataobject.OrderDetail;
import com.ting.order.dto.OrderDTO;
import com.ting.order.enums.ResultEnum;
import com.ting.order.exception.OrderException;
import com.ting.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-20
 * Time: 下午11:41
 */
@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());

        //反序列化
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode());
        }
        orderDTO.setOrderDetailList(orderDetailList);


        return orderDTO;

    }
}
