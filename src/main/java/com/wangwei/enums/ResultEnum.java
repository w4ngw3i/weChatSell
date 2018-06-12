package com.wangwei.enums;

import lombok.Getter;

/**
 * Created by wangwei on 2018/1/19.
 */
@Getter
public enum  ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数错误"),

    PRODUCT_NOT_EXISTS(10, "商品不存在"),

    DECREASE_STOCK_ERROR(11, "库存不足"),

    ORDER_NOT_EXISTS(12, "订单不存在"),

    ORDERDETAIL_NOT_EXISTS(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_PAY_STATUS_ERROR(16, "订单支付状态不正确"),

    CART_EMPTY(17, "购物车为空"),

    ORDER_OWNER_ERROR(18, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(19, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINISH_SUCCESS(23, "订单完结成功"),

    PRODUCT_STATUS_ERROR(23, "商品状态错误"),

    PRODUCT_STATUS_UPDATE_SUCCESS(24, "商品状态修改成功"),

    PRODUCT_UPDATE_SUCCESS(25, "商品修改成功"),

    PRODUCT_CATEGORY_UPDATE_SUCCESS(26, "商品种类修改成功")
    ;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
