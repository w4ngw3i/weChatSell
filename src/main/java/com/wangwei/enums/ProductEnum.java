package com.wangwei.enums;

import lombok.Getter;

/**
 * Created by wangwei on 2018/1/17.
 */
@Getter
public enum  ProductEnum implements CodeEnum{

    UP(0, "在售"),

    DOWN(1, "已下架");


    private Integer code;

    private String msg;

    ProductEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
