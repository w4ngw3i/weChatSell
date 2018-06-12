package com.wangwei.handler;

import com.wangwei.exception.SellException;
import com.wangwei.utils.ResultVoUtil;
import com.wangwei.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午11:44
 */
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handleSellException(SellException e){

        return ResultVoUtil.error(e.getCode(), e.getMessage());

    }
}
