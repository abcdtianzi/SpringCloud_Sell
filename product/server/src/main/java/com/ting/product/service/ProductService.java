package com.ting.product.service;

import com.ting.product.common.ProductInfoOutput;
import com.ting.product.dataobject.ProductInfo;
import com.ting.product.dto.CartDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:05
 */
public interface ProductService {
    /*查询所有在架商品*/
    List<ProductInfo> findUpAll();

    /*查询商品列表*/
    List<ProductInfoOutput> findList(List<String> productIdList);


    /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);
}
