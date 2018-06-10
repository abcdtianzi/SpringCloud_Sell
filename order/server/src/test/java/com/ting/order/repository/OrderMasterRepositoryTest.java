package com.ting.order.repository;

import com.ting.order.DemoApplicationTests;
import com.ting.order.dataobject.OrderDetail;
import com.ting.order.dataobject.OrderMaster;
import com.ting.order.enums.OrderStatusEnum;
import com.ting.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午10:47
 */
@Component
public class OrderMasterRepositoryTest extends DemoApplicationTests{
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() throws Exception{
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        Assert.assertTrue(orderMasterRepository.save(orderMaster) != null);
    }


}