package com.wangwei.service;

import com.wangwei.dataObj.ProductCategory;

import java.util.List;

/**
 * Created by wangwei on 2018/1/17.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
