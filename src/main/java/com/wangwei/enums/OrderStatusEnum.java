package com.wangwei.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created by wangwei on 2018/1/19.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISHED(1, "完成"),
    CANCEL(2, "已取消")
    ;

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
