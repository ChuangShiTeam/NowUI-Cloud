package com.nowui.cloud.shop.product.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.mapper.ProductMapper;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品业务实现
 *
 * @author ZhongYongQiang
 *
 * 2018-02-03
 */
@Service
public class ProductServiceImpl extends SuperServiceImpl<ProductMapper, Product, ProductRepository, ProductView> implements ProductService {

    @Override
    public Integer countForAdmin(String appId, String productName) {
        Criteria criteria = Criteria.where(ProductView.APP_ID).is(appId)
                .and(ProductView.PRODUCT_NAME).regex(".*?" + productName + ".*")
                .and(ProductView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(ProductView.APP_ID).is(appId)
                .and(ProductView.PRODUCT_NAME).regex(".*?" + productName + ".*")
                .and(ProductView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, ProductView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<ProductView> productViewList = list(query, sort, pageIndex, pageSize);

        return productViewList;
    }

}