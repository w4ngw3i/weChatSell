package com.wangwei.dao;

import com.wangwei.dataObj.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by wangwei on 2018/1/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "2566266";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2623723778388");
        orderMaster.setOrderAmount(new BigDecimal(199));
        orderMaster.setBuyerName("黎明");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("18701602711");
        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 2);

        Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid(OPENID, pageRequest);

        Assert.assertNotNull(orderMasters);


    }

}