package com.ting.order.service.impl;

import com.ting.order.dataobject.OrderDetail;
import com.ting.order.dataobject.OrderMaster;
import com.ting.order.dto.OrderDTO;
import com.ting.order.enums.OrderStatusEnum;
import com.ting.order.enums.PayStatusEnum;
import com.ting.order.repository.OrderDetailRepository;
import com.ting.order.repository.OrderMasterRepository;
import com.ting.order.service.OrderService;
import com.ting.order.utils.KeyUtil;
import com.ting.product.client.ProductClient;
import com.ting.product.common.DecreaseStockInput;
import com.ting.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午11:25
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /*调用远程服务接口*/
    @Autowired
    private ProductClient productClient;

    //创建订单，dto为数据传输对象
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        //查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        //远程调用
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);


        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for(ProductInfoOutput productInfo:productInfoList){
                if(Objects.equals(productInfo.getProductId(),orderDetail.getProductId())){
                    //单价*数量
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);//累计
                     BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);


        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        //属性copy
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //总金额
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
