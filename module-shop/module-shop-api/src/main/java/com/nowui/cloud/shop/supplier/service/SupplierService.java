package com.nowui.cloud.shop.supplier.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.shop.supplier.entity.Supplier;
import com.nowui.cloud.shop.supplier.view.SupplierView;

import java.util.List;

/**
 * 供应商基本信息业务接口
 *
 * @author lyn
 *
 * 2018-03-06
 */
public interface SupplierService extends SuperService<Supplier, SupplierView> {

    /**
     * 供应商基本信息统计
     *
     * @param appId 应用编号
     * @param supplierName 供应商名称
     * @return Integer 供应商基本信息统计
     */
    Integer countForAdmin(String appId, String supplierName);

    /**
     * 供应商基本信息列表
     *
     * @param appId 应用编号
     * @param supplierName 供应商名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Supplier> 供应商基本信息列表
     */
    List<SupplierView> listForAdmin(String appId, String supplierName, Integer pageIndex, Integer pageSize);

}