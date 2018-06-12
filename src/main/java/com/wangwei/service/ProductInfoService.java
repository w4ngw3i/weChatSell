package com.wangwei.service;

import com.wangwei.dataObj.ProductInfo;
import com.wangwei.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wangwei on 2018/1/17.
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在售商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(PageRequest pageRequest);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);


    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
