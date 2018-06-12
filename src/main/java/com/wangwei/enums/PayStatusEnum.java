package com.wangwei.enums;

import lombok.Getter;

/**
 * Created by wangwei on 2018/1/19.
 */
@Getter
public enum  PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;


    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
