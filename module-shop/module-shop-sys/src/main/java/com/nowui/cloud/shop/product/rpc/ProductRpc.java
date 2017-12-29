package com.nowui.cloud.shop.product.rpc;

import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.rpc.fallback.ProductRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhongYongQiang
 */
@Component(value = "productRpc")
@FeignClient(name = "module-shop", fallback = ProductRpcFallback.class)
public interface ProductRpc {

    /**
     * 商品查找
     *
     * @param productId 商品编号
     * @return Product 商品
     */
    @RequestMapping(value = "/product/system/find", method = RequestMethod.POST)
    Product find(@RequestParam(value = "productId", required = true) String productId);

}