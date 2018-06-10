package com.ting.order.repository;

import com.ting.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午10:39
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

}
