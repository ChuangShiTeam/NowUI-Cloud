package com.nowui.cloud.shop.product.service.impl;

import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> adminList() {
        List<Product> productList = new ArrayList<Product>();
        return productList;
    }

}