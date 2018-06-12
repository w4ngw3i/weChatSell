package com.wangwei.utils;

import java.util.Random;

/**
 * Created by wangwei on 2018/1/19.
 */
public class KeyUtil {

    public static synchronized String genUniqueKey(){

        Random random = new Random();

        int number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
