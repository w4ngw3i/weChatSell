package com.wangwei.form;

import lombok.Data;

/**
 * @Auther wangwei
 * @Date 2018/4/22 下午9:13
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * '类目编号'
     */
    private Integer categoryType;
}
