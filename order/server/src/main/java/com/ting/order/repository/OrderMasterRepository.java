package com.ting.order.repository;

import com.ting.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午10:36
 *
 */
/*里面不用写方法，直接调用父类的save方法*/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{


}
