package com.wangwei.dao;

import com.wangwei.dataObj.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangwei on 2018/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("6526662");
        productInfo.setProductName("冰淇淋");
        productInfo.setProductPrice(new BigDecimal(66));
        productInfo.setProductStock(23);
        productInfo.setProductDescription("女人最爱");
        productInfo.setProductIcon("http://xxxxx.jsp");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(3);

        ProductInfo info = productInfoDao.save(productInfo);

        Assert.assertNotNull(info);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> infos = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0, infos.size());
    }

}