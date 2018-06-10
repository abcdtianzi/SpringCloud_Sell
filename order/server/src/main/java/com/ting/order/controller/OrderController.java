package com.ting.order.controller;

import com.ting.order.converter.OrderForm2OrderDTO;
import com.ting.order.dto.OrderDTO;
import com.ting.order.enums.ResultEnum;
import com.ting.order.exception.OrderException;
import com.ting.order.form.OrderForm;
import com.ting.order.service.OrderService;
import com.ting.order.utils.ResultVoUtil;
import com.ting.order.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-20
 * Time: 下午2:40
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        /*校验不通过*/
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        /*orderForm -> orderDTO*/
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY.getCode());
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String,String>map = new HashMap<String, String>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtil.success(map);
    }

}
