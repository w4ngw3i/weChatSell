package com.wangwei.controller;

import com.lly835.bestpay.model.PayResponse;
import com.wangwei.dto.OrderDTO;
import com.wangwei.enums.ResultEnum;
import com.wangwei.exception.SellException;
import com.wangwei.service.OrderService;
import com.wangwei.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @Auther wangwei
 * @Date 2018/4/21 下午4:48
 */
@Controller
//@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping()
    public ModelAndView index(@RequestParam("openid") String openid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                      Map<String, Object> map){
        log.info("openid={}", openid);

        String url = "";

        try {
            url = URLDecoder.decode(returnUrl, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //1 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        //2 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", url);
        return new ModelAndView("pay/create");
    }




    @GetMapping("create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                Map<String, Object> map){

        //1 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
        throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        //2 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create");

        }

/**
 * 微信异步通知
 * @param notifyData
 */
@PostMapping("/notify")
public ModelAndView notify(@RequestBody String notifyData){

        payService.notify();

        //返回给微信处理结果
        return new ModelAndView("pay/success");

        }
        }
