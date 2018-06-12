package com.wangwei.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Auther wangwei
 * @Date 2018/4/21 下午5:24
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
