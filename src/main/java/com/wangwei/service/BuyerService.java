package com.wangwei.service;

import com.wangwei.dto.OrderDTO;

/**
 * Created by wangwei on 2018/1/21.
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);
}
