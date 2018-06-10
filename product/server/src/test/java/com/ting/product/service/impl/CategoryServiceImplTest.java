package com.ting.product.service.impl;

import com.ting.product.SpringCloudProductApplicationTests;
import com.ting.product.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:27
 */
public class CategoryServiceImplTest extends SpringCloudProductApplicationTests{

    @Autowired
    CategoryService categoryService;

    @Test
    public void testFindByCategoryTypeIn() throws Exception {
        Assert.assertTrue(categoryService.findByCategoryTypeIn(Arrays.asList(11,12)).size() > 0);

    }
}