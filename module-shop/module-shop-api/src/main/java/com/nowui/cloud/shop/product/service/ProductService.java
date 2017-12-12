package com.nowui.cloud.shop.product.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.shop.product.entity.Product;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
public interface ProductService extends BaseService {

    /**
     * 商品总数
     *
     * @param appId
     * @param productName
     * @return count
     */
    Integer adminCount(String appId, String productName);

    /**
     * 商品列表
     *
     * @param appId
     * @param productName
     * @param m
     * @param n
     * @return List<Product>
     */
    List<Product> adminList(String appId, String productName, int m, int n);
}
