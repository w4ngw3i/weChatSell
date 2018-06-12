package com.wangwei.service.impl;

import com.wangwei.dao.SellerInfoDao;
import com.wangwei.dataObj.SellerInfo;
import com.wangwei.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:51
 */
@Component
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return sellerInfoDao.findByOpenid(openId);
    }
}
