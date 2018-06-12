package com.wangwei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther wangwei
 * @Date 2018/4/20 下午12:10
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入微信授权。。。。");
        log.info("code={}", code);

       String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx34a8ace156ba32bb&secret=376ce78657e511ac7af89ddf8c551307&code="+ code +"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(url, String.class);

        log.info("info={}", response);

    }
}
