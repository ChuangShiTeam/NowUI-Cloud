package com.nowui.cloud.shop.product.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.shop.product.entity.Product;

import java.util.List;

/**
 * @author ZhongYongQiang
 */
public interface ProductService extends BaseService {

    /**
     * admin list
     *
     * @param
     * @return
     */
    List<Product> adminList();
}
