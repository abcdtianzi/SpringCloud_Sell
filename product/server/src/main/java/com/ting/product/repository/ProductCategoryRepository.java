package com.ting.product.repository;

import com.ting.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-15
 * Time: 下午11:34
 */

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {//第二个参数为主键类型
    //in后缀为in(List)
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
