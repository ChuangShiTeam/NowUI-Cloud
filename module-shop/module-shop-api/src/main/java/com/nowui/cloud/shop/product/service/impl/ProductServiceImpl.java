package com.nowui.cloud.shop.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer adminCount(String appId, String productName) {
        Integer count = productMapper.selectCount(
                new EntityWrapper<Product>()
                        .eq("appId", appId)
                        .like("productName", productName)
                        .eq("systemStatus", true)
        );
        return count;
    }

    @Override
    public List<Product> adminList(String appId, String productName, Integer pageIndex, Integer pageSize) {
        List<Product> productList = productMapper.selectPage(
                new Page<Product>(pageIndex, pageSize),
                new EntityWrapper<Product>()
                        .eq("appId", appId)
                        .like("productName", productName)
                        .eq("systemStatus", true)
                        .orderDesc(Arrays.asList("systemCreateTime"))
        );
        return productList;
    }

}