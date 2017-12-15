package com.nowui.cloud.shop.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer adminCount(String appId, String productName) {
        Integer count = productMapper.selectCount(
                new EntityWrapper<Product>()
                        .eq("appId", appId)
                        .like("productName", productName)
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
        );
        return productList;
    }

    @Override
    public Product find(String productId) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    @Override
    public Boolean save(Product product, String systemCreateUserId) {
        product.setSystemCreateUserId(systemCreateUserId);
        product.setSystemUpdateUserId(systemCreateUserId);

        Boolean success = productMapper.insert(product) != 0;
        return success;
    }

    @Override
    public Boolean update(Product product) {
        Boolean success = productMapper.updateById(product) != 0;
        return success;
    }

    @Override
    public Boolean delete(String productId) {
        Boolean success = productMapper.deleteById(productId) != 0;
        return success;
    }

}