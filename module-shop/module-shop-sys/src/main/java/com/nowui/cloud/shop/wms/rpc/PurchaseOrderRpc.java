package com.nowui.cloud.shop.wms.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 采购申请单服务调用
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Component(value = "purchaseOrderRpc")
@FeignClient(name = "module-shop")
public interface PurchaseOrderRpc {

}