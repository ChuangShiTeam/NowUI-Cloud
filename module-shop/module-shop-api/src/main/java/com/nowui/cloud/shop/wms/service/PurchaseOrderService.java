package com.nowui.cloud.shop.wms.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.shop.wms.entity.PurchaseOrder;
import com.nowui.cloud.shop.wms.view.PurchaseOrderView;

import java.util.List;

/**
 * 采购申请单业务接口
 *
 * @author lyn
 *
 * 2018-03-05
 */
public interface PurchaseOrderService extends SuperService<PurchaseOrder, PurchaseOrderView> {

    /**
     * 采购申请单统计
     *
     * @param appId 应用编号
     * @param purchaseNo 采购单号
     * @param purchaseName 采购单名称
     * @param purchaseType 采购类型;
     * @param purchaseUserName 采购员姓名
     * @return Integer 采购申请单统计
     */
    Integer countForAdmin(String appId, String purchaseNo, String purchaseName, String purchaseType, String purchaseUserName);

    /**
     * 采购申请单列表
     *
     * @param appId 应用编号
     * @param purchaseNo 采购单号
     * @param purchaseName 采购单名称
     * @param purchaseType 采购类型;
     * @param purchaseUserName 采购员姓名
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<PurchaseOrder> 采购申请单列表
     */
    List<PurchaseOrderView> listForAdmin(String appId, String purchaseNo, String purchaseName, String purchaseType, String purchaseUserName, Integer pageIndex, Integer pageSize);

}