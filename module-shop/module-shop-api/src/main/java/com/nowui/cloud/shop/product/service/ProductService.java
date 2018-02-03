package com.nowui.cloud.shop.product.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.view.ProductView;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
public interface ProductService extends SuperService<Product, ProductView> {

    /**
     * 商品计数
     *
     * @param appId 应用编号
     * @param productName 商品名称
     * @return Integer 商品数量
     */
    Integer countForAdmin(String appId, String productName);

    /**
     * 商品列表
     *
     * @param appId 应用编号
     * @param productName 商品名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Product> 商品列表
     */
    List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize);
}
