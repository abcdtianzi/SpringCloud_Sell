package com.ting.product.controller;

import com.ting.product.common.ProductInfoOutput;
import com.ting.product.dataobject.ProductCategory;
import com.ting.product.dataobject.ProductInfo;
import com.ting.product.dto.CartDTO;
import com.ting.product.service.CategoryService;
import com.ting.product.service.ProductService;
import com.ting.product.utils.ResultVoUtil;
import com.ting.product.vo.ProductInfoVo;
import com.ting.product.vo.ProductVo;
import com.ting.product.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-15
 * Time: 下午10:41
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询所有在架的商品
     * 2.查询类目type列表
     * 3.查询类目
     * 4。构造数据
     */
    @RequestMapping("/list")
    public ResultVo list() {
        //查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //数据库查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //构造数据


        List<ProductVo> productVoList = new ArrayList<ProductVo>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());


            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (Objects.equals(productInfo.getCategoryType(), productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //直接通过copyProperties方法构造vo
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);


    }

    /*根据商品id列表获取商品信息， 这是给订单服务用的！！*/
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
        return productService.findList(productIdList);
    }


    /*减库存*/
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList){
         productService.decreaseStock(cartDTOList);
    }


}
