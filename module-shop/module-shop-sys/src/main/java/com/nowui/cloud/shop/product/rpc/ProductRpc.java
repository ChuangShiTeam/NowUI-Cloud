package com.nowui.cloud.shop.product.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
@Component(value = "productRpc")
@FeignClient(name = "module-shop")
public interface ProductRpc {

}