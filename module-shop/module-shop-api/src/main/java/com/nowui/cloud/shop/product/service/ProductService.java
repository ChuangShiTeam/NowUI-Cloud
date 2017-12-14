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
     * @param 应用编号
     * @param 商品名称
     * @return 商品计数
     */
    Integer adminCount(String appId, String productName);

    /**
     * 商品列表
     *
     * @param 应用编号
     * @param 商品名称
     * @param 从m条开始
     * @param 取n条数据
     * @return 商品列表
     */
    List<Product> adminList(String appId, String productName, Integer m, Integer n);

    /**
     * 商品查询
     *
     * @param 商品编号
     * @return 商品
     */
    Product find(String productId);

    /**
     * 商品新增
     *
     * @param 商品
     * @return 是否成功
     */
    Boolean save(Product product);

    /**
     * 商品修改
     *
     * @param 商品
     * @return 是否成功
     */
    Boolean update(Product product);

    /**
     * 商品删除
     *
     * @param 商品编号
     * @return 是否成功
     */
    Boolean delete(String productId);
}
