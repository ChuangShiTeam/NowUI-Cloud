package com.nowui.cloud.shop.product.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
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

    @Override
    public Product find(String productId) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    @Override
    public Product find(String productId, Boolean systemStatus) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    @Override
    public Boolean save(Product product, String systemCreateUserId) {
        product.setSystemCreateUserId(systemCreateUserId);
        product.setSystemCreateTime(new Date());
        product.setSystemUpdateUserId(systemCreateUserId);
        product.setSystemUpdateTime(new Date());
        product.setSystemVersion(0);
        product.setSystemStatus(true);

        Boolean success = productMapper.insert(product) != 0;
        return success;
    }

    @Override
    public Boolean update(Product product, String systemUpdateUserId, Integer systemVersion) {
        product.setSystemUpdateUserId(systemUpdateUserId);
        product.setSystemUpdateTime(new Date());
        product.setSystemVersion(systemVersion + 1);

        Boolean success = productMapper.update(
                product,
                new EntityWrapper<Product>()
                        .eq("productId", product.getProductId())
                        .eq("systemVersion", systemVersion)
                        .eq("systemStatus", true)
        ) != 0;
        return success;
    }

    @Override
    public Boolean delete(String productId, String systemUpdateUserId, Integer systemVersion) {
        Product product = new Product();
        product.setSystemUpdateUserId(systemUpdateUserId);
        product.setSystemUpdateTime(new Date());
        product.setSystemVersion(systemVersion + 1);
        product.setSystemStatus(false);

        Boolean success = productMapper.update(
                product,
                new EntityWrapper<Product>()
                        .eq("productId", productId)
                        .eq("systemVersion", systemVersion)
                        .eq("systemStatus", true)
        ) != 0;
        return success;
    }

}