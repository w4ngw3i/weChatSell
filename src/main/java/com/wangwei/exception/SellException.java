package com.wangwei.exception;

import com.wangwei.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by wangwei on 2018/1/19.
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
