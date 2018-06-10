package com.ting.order.service;

import com.ting.order.dto.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午11:24
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
}
