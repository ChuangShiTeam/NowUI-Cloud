package com.nowui.cloud.shop.wms.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.shop.wms.entity.Warehouse;
import com.nowui.cloud.shop.wms.view.WarehouseView;
import com.nowui.cloud.shop.wms.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 仓库信息管理管理端控制器
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Api(value = "仓库信息管理", description = "仓库信息管理管理端接口管理")
@RestController
public class WarehouseAdminController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "仓库信息管理列表")
    @RequestMapping(value = "/warehouse/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        WarehouseView warehouseView = getEntry(WarehouseView.class);

        validateRequest(
                warehouseView,
                WarehouseView.APP_ID,
                WarehouseView.WAREHOUSE_NAME,
                WarehouseView.PAGE_INDEX,
                WarehouseView.PAGE_SIZE
        );

        Integer resultTotal = warehouseService.countForAdmin(warehouseView.getAppId(), warehouseView.getWarehouseName());
        List<WarehouseView> resultList = warehouseService.listForAdmin(warehouseView.getAppId(), warehouseView.getWarehouseName(), warehouseView.getPageIndex(), warehouseView.getPageSize());

        validateResponse(
                WarehouseView.WAREHOUSE_ID,
                WarehouseView.WAREHOUSE_NAME,
                WarehouseView.WAREHOUSE_PROVINCE,
                WarehouseView.WAREHOUSE_CITY,
                WarehouseView.WAREHOUSE_AREA,
                WarehouseView.WAREHOUSE_ADDRESS
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "仓库信息管理信息")
    @RequestMapping(value = "/warehouse/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        WarehouseView warehouseView = getEntry(WarehouseView.class);

        validateRequest(
                warehouseView,
                WarehouseView.APP_ID,
                WarehouseView.WAREHOUSE_ID
        );

        WarehouseView result = warehouseService.find(warehouseView.getWarehouseId());

        validateResponse(
                WarehouseView.WAREHOUSE_ID,
            	WarehouseView.WAREHOUSE_NAME,
            	WarehouseView.WAREHOUSE_PROVINCE,
            	WarehouseView.WAREHOUSE_CITY,
            	WarehouseView.WAREHOUSE_AREA,
            	WarehouseView.WAREHOUSE_ADDRESS,
            	WarehouseView.WAREHOUSE_SUPPLIER_ID,
                WarehouseView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "仓库信息管理新增")
    @RequestMapping(value = "/warehouse/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Warehouse warehouseEntity = getEntry(Warehouse.class);

        String warehouseId = Util.getRandomUUID();

        validateRequest(
                warehouseEntity,
                Warehouse.APP_ID,
                Warehouse.WAREHOUSE_NAME,
                Warehouse.WAREHOUSE_PROVINCE,
                Warehouse.WAREHOUSE_CITY,
                Warehouse.WAREHOUSE_AREA,
                Warehouse.WAREHOUSE_ADDRESS,
                Warehouse.WAREHOUSE_SUPPLIER_ID
        );

        Warehouse result = warehouseService.save(warehouseEntity, warehouseId, warehouseEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            WarehouseView warehouseView = JSON.parseObject(result.toJSONString(), WarehouseView.class);
            warehouseService.save(warehouseView);
        }

        return renderJson(success);
    }

    @ApiOperation(value = "仓库信息管理修改")
    @RequestMapping(value = "/warehouse/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Warehouse warehouseEntity = getEntry(Warehouse.class);

        validateRequest(
                warehouseEntity,
                Warehouse.WAREHOUSE_ID,
                Warehouse.APP_ID,
                Warehouse.WAREHOUSE_NAME,
                Warehouse.WAREHOUSE_PROVINCE,
                Warehouse.WAREHOUSE_CITY,
                Warehouse.WAREHOUSE_AREA,
                Warehouse.WAREHOUSE_ADDRESS,
                Warehouse.WAREHOUSE_SUPPLIER_ID,
                Warehouse.SYSTEM_VERSION
        );

        Warehouse result = warehouseService.update(warehouseEntity, warehouseEntity.getWarehouseId(), warehouseEntity.getSystemRequestUserId(), warehouseEntity.getSystemVersion());

        if (result != null) {
            WarehouseView warehouseView = JSON.parseObject(result.toJSONString(), WarehouseView.class);
            warehouseService.update(warehouseView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "仓库信息管理删除")
    @RequestMapping(value = "/warehouse/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Warehouse warehouseEntity = getEntry(Warehouse.class);

        validateRequest(
                warehouseEntity,
                Warehouse.WAREHOUSE_ID,
                Warehouse.APP_ID,
                Warehouse.SYSTEM_VERSION
        );

        Warehouse result = warehouseService.delete(warehouseEntity.getWarehouseId(), warehouseEntity.getSystemRequestUserId(), warehouseEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            WarehouseView warehouseView = JSON.parseObject(result.toJSONString(), WarehouseView.class);
            warehouseService.update(warehouseView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "仓库信息管理数据同步")
    @RequestMapping(value = "/warehouse/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Warehouse> warehouseList = warehouseService.listByMysql();

        for (Warehouse warehouse : warehouseList) {
            WarehouseView warehouseView = new WarehouseView();
            warehouseView.putAll(warehouse);

            warehouseService.update(warehouseView);
        }

        return renderJson(true);
    }

}