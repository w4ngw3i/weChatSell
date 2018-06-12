package com.wangwei.controller;

import com.wangwei.dataObj.ProductCategory;
import com.wangwei.dataObj.ProductInfo;
import com.wangwei.service.CategoryService;
import com.wangwei.service.ProductInfoService;
import com.wangwei.utils.ResultVoUtil;
import com.wangwei.vo.ProductInfoVo;
import com.wangwei.vo.ProductVo;
import com.wangwei.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangwei on 2018/1/17.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    @Cacheable(cacheNames = "product", key = "123")
    public ResultVo list(){
        // 1.查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2.查询商品类目（一次性查询）
//            List<Integer> categoryTypeList = new ArrayList();
//            // 1。传统方法
//            for (ProductInfo productInfo :productInfoList
//                 ) {
//                categoryTypeList.add(productInfo.getCategoryType());
//
//            }
            //2.精简方法（java8，lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //数据拼装
        List<ProductVo> productVoList = new ArrayList<>();

        for (ProductCategory productCategory: productCategoryList) {

            ProductVo productVo = new ProductVo();

            productVo.setCategoryName(productCategory.getCategoryName());

            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();

            for (ProductInfo productinfo: productInfoList) {
                if (productinfo.getCategoryType().equals(productCategory.getCategoryType())){

                    ProductInfoVo productInfoVo = new ProductInfoVo();

                    BeanUtils.copyProperties(productinfo, productInfoVo);

                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }
}
