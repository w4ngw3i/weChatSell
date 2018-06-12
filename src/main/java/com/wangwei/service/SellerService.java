package com.wangwei.service;

import com.wangwei.dataObj.SellerInfo;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:50
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openId
     * @return
     */
    SellerInfo findSellerInfoByOpenId(String openId);
}
