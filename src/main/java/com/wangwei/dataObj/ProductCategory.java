package com.wangwei.dataObj;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by wangwei on 2018/1/16.
 */
@Entity
@DynamicUpdate//用于动态修改数据库更新时间
@Data //用于get set tostring生成
public class ProductCategory {
    /**
     *类目id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * '类目编号'
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
