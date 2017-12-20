package com.nowui.cloud.shop.product.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.rpc.ProductRpc;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
public interface ProductService extends BaseService<Product>, ProductRpc {

    /**
     * 商品计数
     *
     * @param appId 应用编号
     * @param productName 商品名称
     * @return Integer 商品数量
     */
    Integer adminCount(String appId, String productName);

    /**
     * 商品列表
     *
     * @param appId 应用编号
     * @param productName 商品名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Product> 商品列表
     */
    List<Product> adminList(String appId, String productName, Integer m, Integer n);
}
