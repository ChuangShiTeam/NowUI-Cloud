package com.nowui.cloud.shop.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Integer adminCount(String appId, String productName) {
        Integer count = count(
                new BaseWrapper<Product>()
                        .eq(Product.APP_ID, appId)
                        .like(Product.PRODUCT_NAME, productName)
                        .eq(Product.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Product> adminList(String appId, String productName, Integer m, Integer n) {
        List<Product> productList = list(
                new BaseWrapper<Product>()
                        .setSqlSelect("imageId")
                        .eq(Product.APP_ID, appId)
                        .like(Product.PRODUCT_NAME, productName)
                        .eq(Product.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Product.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        for(Product product: productList) {
            //image list
            //product
        }

        return productList;
    }

}