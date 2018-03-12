package com.nowui.cloud.shop.supplier.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 供应商基本信息服务调用
 *
 * @author lyn
 *
 * 2018-03-06
 */
@Component(value = "supplierRpc")
@FeignClient(name = "module-shop")
public interface SupplierRpc {

}