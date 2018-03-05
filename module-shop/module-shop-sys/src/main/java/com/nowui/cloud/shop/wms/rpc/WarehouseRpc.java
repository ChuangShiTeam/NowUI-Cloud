package com.nowui.cloud.shop.wms.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 仓库信息管理服务调用
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Component(value = "warehouseRpc")
@FeignClient(name = "module-shop")
public interface WarehouseRpc {

}