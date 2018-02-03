package com.nowui.cloud.shop.product.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
@Component
public class ProductRepositoryImpl extends BaseRepositoryImpl<ProductView> implements ProductRepository {

    @Override
    public Integer countForAdmin(String appId, String productName) {
        Criteria criteria = Criteria.where(ProductView.APP_ID).is(appId)
                .and(productName).regex(".*?\\" + productName + ".*")
                .and(ProductView.SYSTEM_STATUS).is(true);

        Integer count = count(criteria);

        return count;
    }

    @Override
    public List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(ProductView.APP_ID).is(appId)
                .and(productName)
                .regex(".*?\\" + productName + ".*")
                .and(ProductView.SYSTEM_STATUS).is(true);

        List<ProductView> productViewList = list(criteria, pageIndex, pageSize);

        return productViewList;
    }
}
