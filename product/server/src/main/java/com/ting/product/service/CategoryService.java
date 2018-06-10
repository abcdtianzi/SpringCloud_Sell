package com.ting.product.service;

import com.ting.product.dataobject.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:24
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
