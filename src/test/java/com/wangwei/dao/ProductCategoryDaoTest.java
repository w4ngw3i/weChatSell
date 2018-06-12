package com.wangwei.dao;

import com.wangwei.dataObj.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangwei on 2018/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findOne(1);
        System.out.println(productCategory.toString());

    }


    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryId(2);
        productCategory.setCategoryName("热销榜");
        productCategory.setCategoryType(1);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void updateTest2(){
        ProductCategory productCategory = productCategoryDao.findOne(2);
        productCategory.setCategoryType(8);
        productCategoryDao.save(productCategory);
    }


    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(0, 3, 8);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());

    }

}