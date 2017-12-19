package com.nowui.cloud.shop.product.service;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.rpc.ProductRpc;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
public interface ProductService extends ProductRpc {

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

    /**
     * 商品查询
     *
     * @param productId 商品编号
     * @return Product 商品
     */
    Product find(String productId);

    /**
     * 商品查询
     *
     * @param productId 商品编号
     * @param systemStatus 商品编号
     * @return Product 商品
     */
    Product find(String productId, Boolean systemStatus);

    /**
     * 商品新增
     *
     * @param product 商品
     * @param systemCreateUserId 创建人编号
     * @return Boolean 是否成功
     */
    Boolean save(Product product, String systemCreateUserId);

    /**
     * 商品修改
     *
     * @param product 商品
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean update(Product product, String systemUpdateUserId, Integer systemVersion);

    /**
     * 商品删除
     *
     * @param productId 商品编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean delete(String productId, String systemUpdateUserId, Integer systemVersion);
}
