package com.wangwei.dao;

import com.wangwei.dataObj.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:35
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
