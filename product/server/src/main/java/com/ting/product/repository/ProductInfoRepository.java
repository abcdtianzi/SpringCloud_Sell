package com.ting.product.repository;

import com.ting.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-15
 * Time: 下午11:34
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {//第二个参数为主键类型
    //查询所有在架的商品
    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
