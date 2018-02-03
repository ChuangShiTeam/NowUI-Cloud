package com.nowui.cloud.shop.product.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品数据访问组件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
public interface ProductRepository extends BaseRepository<ProductView> {

//    /**
//     * 商品计数
//     *
//     * @param appId 应用编号
//     * @param productName 商品名称
//     * @return Integer 商品数量
//     */
//    Integer countForAdmin(String appId, String productName);
//
//    /**
//     * 商品列表
//     *
//     * @param appId 应用编号
//     * @param productName 商品名称
//     * @param pageIndex 页码
//     * @param pageSize 每页个数
//     * @return List<Product> 商品列表
//     */
//    List<ProductView> listForAdmin(String appId, String productName, Integer pageIndex, Integer pageSize);

}