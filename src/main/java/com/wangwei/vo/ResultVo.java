package com.wangwei.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * http请求返回最外层对象
 * Created by wangwei on 2018/1/17.
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 6656253736460108122L;

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;
}
