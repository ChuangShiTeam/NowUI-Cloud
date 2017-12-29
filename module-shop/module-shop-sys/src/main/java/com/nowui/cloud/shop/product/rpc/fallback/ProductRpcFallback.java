package com.nowui.cloud.shop.product.rpc.fallback;

import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.rpc.ProductRpc;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
@Component(value = "productRpcFallback")
public class ProductRpcFallback implements ProductRpc {

    @Override
    public Product find(String productId) {
        return null;
    }
}
