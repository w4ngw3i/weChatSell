package com.wangwei.utils;

import com.wangwei.enums.CodeEnum;

/**
 * @Auther wangwei
 * @Date 2018/4/22 上午11:06
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){

        for (T each : enumClass.getEnumConstants()) {

            if (code.equals(each.getCode())){
                return each;
            }
        }

        return null;
    }

}
