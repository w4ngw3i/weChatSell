package com.wangwei.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.wangwei.dto.OrderDTO;

/**
 * @Auther wangwei
 * @Date 2018/4/21 下午4:57
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
