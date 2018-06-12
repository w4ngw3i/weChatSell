package com.wangwei.dao;

import com.wangwei.dataObj.SellerInfo;
import com.wangwei.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void findByOpenid() {

        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("woutwoutwotjw");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void save() {

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("woutwoutwotjw");

        SellerInfo result = sellerInfoDao.save(sellerInfo);

        Assert.assertNotNull(result);

    }
}