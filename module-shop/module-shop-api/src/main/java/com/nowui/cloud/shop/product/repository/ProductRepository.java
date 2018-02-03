package com.nowui.cloud.shop.product.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.stereotype.Component;

/**
 * 商品数据访问组件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
public interface ProductRepository extends BaseRepository<ProductView> {


}