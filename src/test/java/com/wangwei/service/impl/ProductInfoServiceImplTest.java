package com.wangwei.service.impl;

import com.wangwei.dataObj.ProductInfo;
import com.wangwei.enums.ProductEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangwei on 2018/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productInfoService.findOne("123445678");
        Assert.assertEquals("123445678", productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);

        Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);

        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("95292682600");
        productInfo.setProductName("辣子鸡");
        productInfo.setProductPrice(new BigDecimal(99));
        productInfo.setProductStock(32);
        productInfo.setProductDescription("名家真传");
        productInfo.setProductIcon("http://laziji.jsp");
        productInfo.setProductStatus(ProductEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo info = productInfoService.save(productInfo);

        Assert.assertNotNull(info);
    }

    @Test
    public void onSale(){

        ProductInfo productInfo = productInfoService.onSale("6526662");

        Assert.assertTrue("【修改商品状态 上架】", productInfo != null);

    }

    @Test
    public void offSale(){

        ProductInfo productInfo = productInfoService.offSale("6526662");

        Assert.assertTrue("【修改商品状态 下架】", productInfo != null);

    }

}