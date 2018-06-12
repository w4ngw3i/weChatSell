package com.wangwei.service.impl;

import com.wangwei.dataObj.OrderDetail;
import com.wangwei.dto.OrderDTO;
import com.wangwei.enums.OrderStatusEnum;
import com.wangwei.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangwei on 2018/1/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "2525266";

    private final String ORDER_ID = "26252526268";


    
    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("夏娃");
        orderDTO.setBuyerPhone("132552538711");
        orderDTO.setBuyerAddress("本体");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> cartDTOList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123445678");
        orderDetail1.setProductQuantity(3);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("12626262");
        orderDetail2.setProductQuantity(6);

        cartDTOList.add(orderDetail1);
        cartDTOList.add(orderDetail2);

        orderDTO.setOrderDetailList(cartDTOList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("]创建订单] result = {}", result);

    }

    @Test
    public void findOne() throws Exception {

        OrderDTO orderDTO = orderService.findOne("1516328592719935696");

        Assert.assertEquals("1516328592719935696", orderDTO.getOrderId());
    }

    @Test
    public void findList() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 2);

        Page<OrderDTO> result = orderService.findList(BUYER_OPENID, pageRequest);

        Assert.assertNotNull(result);
    }


    @Test
    public void findList2() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 2);

        Page<OrderDTO> result = orderService.findList(pageRequest);

        Assert.assertNotNull(result);
    }


    @Test
    public void cancel() throws Exception {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        OrderDTO cancel = orderService.cancel(orderDTO);

        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancel.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        OrderDTO cancel = orderService.finish(orderDTO);

        Assert.assertEquals(OrderStatusEnum.FINISHED.
                getCode(), cancel.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        OrderDTO cancel = orderService.paid(orderDTO);

        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), cancel.getPayStatus());
    }

}