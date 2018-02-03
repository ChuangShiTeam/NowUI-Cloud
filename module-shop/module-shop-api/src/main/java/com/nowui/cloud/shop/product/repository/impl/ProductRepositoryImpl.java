package com.nowui.cloud.shop.product.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Component
public class ProductRepositoryImpl extends BaseRepositoryImpl<ProductView> implements ProductRepository {

    @Override
    public Integer countForAdmin(String appId, String productName) {
        return null;
    }

    @Override
    public List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
