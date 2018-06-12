package com.wangwei;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangwei on 2018/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //使用@Slf4j注解可以直接使用log变量
//    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test(){
/*        logger.error("error");
        logger.debug("debug");
        logger.info("info"); */
        log.error("error");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        String name = "wangwei";

        String password = "root";

        System.out.println("name:"+name+", password"+password);

        log.info("name:{}, password:{}"+name, password);


    }
}
