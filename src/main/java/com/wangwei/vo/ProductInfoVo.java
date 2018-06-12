package com.wangwei.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 * Created by wangwei on 2018/1/18.
 */
@Data
public class ProductInfoVo implements Serializable{

    private static final long serialVersionUID = -6649639930208386983L;
    @JsonProperty("id")
    private String productId;

    /** 商品名称 */
    @JsonProperty("name")
    private String productName;

    /** '单价' */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /** '描述' */
    @JsonProperty("description")
    private String productDescription;

    /** '小图' */
    @JsonProperty("icon")
    private String productIcon;
}
