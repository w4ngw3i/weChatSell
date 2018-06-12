package com.wangwei.dataObj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wangwei.enums.ProductEnum;
import com.wangwei.utils.EnumUtil;
import com.wangwei.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangwei on 2018/1/17.
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    /** 商品名称 */
    private String productName;

    /** '单价' */
    private BigDecimal productPrice;

    /** '库存' */
    private Integer productStock;

    /** '描述' */
    private String productDescription;

    /** '小图' */
    private String productIcon;

    /** 商品状态，0正常1下架 */
    private Integer productStatus = 0;

    /** '类目编号' */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductEnum getProductEnum(){
        return EnumUtil.getByCode(productStatus, ProductEnum.class);
    }
}

