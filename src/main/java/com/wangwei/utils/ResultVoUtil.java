package com.wangwei.utils;

import com.wangwei.vo.ResultVo;

/**
 * Created by wangwei on 2018/1/18.
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){

        ResultVo resultVo = new ResultVo();

        resultVo.setCode(0);

        resultVo.setMsg("成功");

        resultVo.setData(object);

        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code, String msg){

        ResultVo resultVo = new ResultVo();

        resultVo.setCode(code);

        resultVo.setMsg(msg);

        return resultVo;
    }
}
