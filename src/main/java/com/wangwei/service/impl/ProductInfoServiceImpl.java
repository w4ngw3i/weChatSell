package com.wangwei.service.impl;

import com.wangwei.dao.ProductInfoDao;
import com.wangwei.dataObj.ProductInfo;
import com.wangwei.dto.CartDTO;
import com.wangwei.enums.ProductEnum;
import com.wangwei.enums.ResultEnum;
import com.wangwei.exception.SellException;
import com.wangwei.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by wangwei on 2018/1/17.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(PageRequest pageRequest) {
        return productInfoDao.findAll(pageRequest);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO: cartDTOList){

            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());

            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }

            Integer stock = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(stock);

            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO: cartDTOList){

            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());

            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }

            int stock = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (stock < 0){
                throw new SellException(ResultEnum.DECREASE_STOCK_ERROR);
            }

            productInfo.setProductStock(stock);

            productInfoDao.save(productInfo);
        }

    }

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productInfoDao.findOne(productId);

        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
        }

        if (productInfo.getProductEnum() == ProductEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态
        productInfo.setProductStatus(ProductEnum.UP.getCode());

        return productInfoDao.save(productInfo);

    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoDao.findOne(productId);

        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
        }

        if (productInfo.getProductEnum() == ProductEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态
        productInfo.setProductStatus(ProductEnum.DOWN.getCode());

        return productInfoDao.save(productInfo);
    }
}
