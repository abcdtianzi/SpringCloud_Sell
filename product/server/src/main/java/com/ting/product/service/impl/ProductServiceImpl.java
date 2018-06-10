package com.ting.product.service.impl;

import com.ting.product.common.ProductInfoOutput;
import com.ting.product.dataobject.ProductInfo;
import com.ting.product.dto.CartDTO;
import com.ting.product.enums.ProductStatusEnum;
import com.ting.product.enums.ResultEnum;
import com.ting.product.exception.ProductException;
import com.ting.product.repository.ProductInfoRepository;
import com.ting.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-16
 * Time: 下午11:07
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoList;
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        //ProductInfo转ProductInfoOutput
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
//            Optional的isPresent代表有值
            if (!productInfoOptional.isPresent()) {
                //商品不存在抛异常
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                //商品库存不足
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR.getCode(), ResultEnum.PRODUCT_STOCK_ERROR.getMessage());
            }
            //获取到的剩余值重新赋值给库存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

        }
    }


}
