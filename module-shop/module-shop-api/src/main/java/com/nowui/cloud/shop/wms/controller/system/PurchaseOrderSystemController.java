package com.nowui.cloud.shop.wms.controller.system;

import com.nowui.cloud.shop.wms.rpc.PurchaseOrderRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采购申请单系统端控制器
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Api(value = "采购申请单", description = "采购申请单系统端接口管理")
@RestController
public class PurchaseOrderSystemController implements PurchaseOrderRpc {

}