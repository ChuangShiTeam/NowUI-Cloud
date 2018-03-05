package com.nowui.cloud.shop.wms.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.shop.wms.entity.Warehouse;
import com.nowui.cloud.shop.wms.view.WarehouseView;

import java.util.List;

/**
 * 仓库信息管理业务接口
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
public interface WarehouseService extends SuperService<Warehouse, WarehouseView> {

    /**
     * 仓库信息管理统计
     *
     * @param appId 应用编号
     * @param warehouseName 仓库名称
     * @return Integer 仓库信息管理统计
     */
    Integer countForAdmin(String appId, String warehouseName);

    /**
     * 仓库信息管理列表
     *
     * @param appId 应用编号
     * @param warehouseName 仓库名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Warehouse> 仓库信息管理列表
     */
    List<WarehouseView> listForAdmin(String appId, String warehouseName, Integer pageIndex, Integer pageSize);

}