package com.wangwei.service.impl;

import com.wangwei.dataObj.SellerInfo;
import com.wangwei.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenId() {

        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId("woutwoutwotjw");

        Assert.assertNotNull(sellerInfo);
    }
}