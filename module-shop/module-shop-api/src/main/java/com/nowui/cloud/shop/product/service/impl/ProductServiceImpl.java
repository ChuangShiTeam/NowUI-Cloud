package com.nowui.cloud.shop.product.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Service
public class ProductServiceImpl extends SuperServiceImpl<ProductMapper, Product, ProductRepository, ProductView> implements ProductService {

    @Override
    public Integer countForAdmin(String appId, String productName) {
        long count = repository.count();

        return new Long(count).intValue();
    }

    @Override
    public List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize) {
        List<ProductView> productViewList = repository.findAll();

        return productViewList;
    }

}