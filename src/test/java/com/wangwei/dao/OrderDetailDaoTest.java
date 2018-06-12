package com.wangwei.dao;

import com.wangwei.dataObj.OrderDetail;
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
 * Created by wangwei on 2018/1/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;


    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("855292529");
        orderDetail.setOrderId("98626202602802");
        orderDetail.setProductIcon("http://oss.aliyuncs.com/dface/5323fc7920f318b2590000e9/0.jpg");
        orderDetail.setProductId("123445678");
        orderDetail.setProductPrice(new BigDecimal(45));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductName("胡辣椒炒毛肚");
        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOrderId() throws Exception {

        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId("25225552");

        Assert.assertNotEquals(0, orderDetails.size());
    }


}