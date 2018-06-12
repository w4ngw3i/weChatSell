package com.wangwei.service.impl;

import com.wangwei.converter.OrderMaster2OrderDTOConverter;
import com.wangwei.dao.OrderDetailDao;
import com.wangwei.dao.OrderMasterDao;
import com.wangwei.dataObj.OrderDetail;
import com.wangwei.dataObj.OrderMaster;
import com.wangwei.dataObj.ProductInfo;
import com.wangwei.dto.CartDTO;
import com.wangwei.dto.OrderDTO;
import com.wangwei.enums.OrderStatusEnum;
import com.wangwei.enums.PayStatusEnum;
import com.wangwei.enums.ResultEnum;
import com.wangwei.exception.SellException;
import com.wangwei.service.OrderService;
import com.wangwei.service.PayService;
import com.wangwei.service.ProductInfoService;
import com.wangwei.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangwei on 2018/1/19.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        List<CartDTO> cartDTOList = new ArrayList<>();

        /** 查询商品 数量 单价 */
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){

            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());

            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }

            /** 计算总价 */
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            /** 订单详情入库 */
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());

            cartDTOList.add(cartDTO);
        }

        /** 写入订单数据库（orderMaster和orderDetail） */
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        /** 减库存 */
//        List<CartDTO> cartDTOList = new ArrayList<>();
        productInfoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);

        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);

        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXISTS);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

    }


    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();

        /** 判断订单状态 */
        if (!(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))){
            log.error("【取消订单】订单状态不正确 orderId={}, orderStatus={}"+orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /** 修改订单状态 */
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if (result == null){
            log.error("【取消订单】取消失败 orderMaster={}"+orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        /** 返回库存 */
        List<CartDTO> cartDTOList = new ArrayList<>();

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        if (orderDetailList != null){
            for (OrderDetail orderDetail: orderDetailList){
                CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
                cartDTOList.add(cartDTO);
            }
        }
        productInfoService.increaseStock(cartDTOList);

        /** java lamda写法 */
/*        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());*/
        /** 如果已支付，需退款 */

        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){

            payService.refund(orderDTO);

        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        /** 判断订单状态 */
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完成订单】 订单状态不正确 orderStatus={}, orderId={}", orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /** 修改订单状态 */
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());

        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster resultUpdate = orderMasterDao.save(orderMaster);

        if (resultUpdate == null){
            log.error("【完成订单】更新失败 orderMaster={}"+orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        /** 判断订单状态 */
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付】 订单状态不正确 orderStatus={}, orderId={}", orderDTO.getOrderStatus(), orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /** 判断支付状态 */
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】 订单支付状态不正确 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        /** 修改支付状态 */
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster resultUpdate = orderMasterDao.save(orderMaster);

        if (resultUpdate == null){
            log.error("【订单支付】更新失败 orderMaster={}"+orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
