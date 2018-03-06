package com.nowui.cloud.shop.supplier.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.shop.supplier.entity.Supplier;
import com.nowui.cloud.shop.supplier.view.SupplierView;
import com.nowui.cloud.shop.supplier.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 供应商基本信息管理端控制器
 *
 * @author lyn
 *
 * 2018-03-06
 */
@Api(value = "供应商基本信息", description = "供应商基本信息管理端接口管理")
@RestController
public class SupplierAdminController extends BaseController {

    @Autowired
    private SupplierService supplierService;

    @ApiOperation(value = "供应商基本信息列表")
    @RequestMapping(value = "/supplier/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        SupplierView supplierView = getEntry(SupplierView.class);

        validateRequest(
                supplierView,
                SupplierView.APP_ID,
                SupplierView.SUPPLIER_NAME,
                SupplierView.PAGE_INDEX,
                SupplierView.PAGE_SIZE
        );

        Integer resultTotal = supplierService.countForAdmin(supplierView.getAppId(), supplierView.getSupplierName());
        List<SupplierView> resultList = supplierService.listForAdmin(supplierView.getAppId(), supplierView.getSupplierName(), supplierView.getPageIndex(), supplierView.getPageSize());

        validateResponse(
                SupplierView.SUPPLIER_ID,
                SupplierView.SUPPLIER_NAME,
                SupplierView.SUPPLIER_CONTACT,
                SupplierView.SUPPLIER_PHONE,
                SupplierView.SUPPLIER_COMPANY
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "供应商基本信息信息")
    @RequestMapping(value = "/supplier/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        SupplierView supplierView = getEntry(SupplierView.class);

        validateRequest(
                supplierView,
                SupplierView.APP_ID,
                SupplierView.SUPPLIER_ID
        );

        SupplierView result = supplierService.find(supplierView.getSupplierId());

        validateResponse(
                SupplierView.SUPPLIER_ID,
            	SupplierView.SUPPLIER_NAME,
            	SupplierView.SUPPLIER_SRC_FILE_ID,
            	SupplierView.SUPPLIER_CONTACT,
            	SupplierView.SUPPLIER_PHONE,
            	SupplierView.SUPPLIER_TEL,
            	SupplierView.SUPPLIER_COMPANY,
            	SupplierView.SUPPLIER_EMAIL,
            	SupplierView.SUPPLIER_Q_Q,
            	SupplierView.SUPPLIER_WEI_XIN,
            	SupplierView.SUPPLIER_PROVINCE,
            	SupplierView.SUPPLIER_CITY,
            	SupplierView.SUPPLIER_AREA,
            	SupplierView.SUPPLIER_ADDRESS,
            	SupplierView.SUPPLIER_ZIP_CODE,
            	SupplierView.SUPPLIER_LONGITUDE,
            	SupplierView.SUPPLIER_LATITUDE,
                SupplierView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "供应商基本信息新增")
    @RequestMapping(value = "/supplier/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Supplier supplierEntity = getEntry(Supplier.class);

        String supplierId = Util.getRandomUUID();

        validateRequest(
                supplierEntity,
                Supplier.APP_ID,
                Supplier.SUPPLIER_NAME,
                Supplier.SUPPLIER_SRC_FILE_ID,
                Supplier.SUPPLIER_CONTACT,
                Supplier.SUPPLIER_PHONE,
                Supplier.SUPPLIER_TEL,
                Supplier.SUPPLIER_COMPANY,
                Supplier.SUPPLIER_EMAIL,
                Supplier.SUPPLIER_Q_Q,
                Supplier.SUPPLIER_WEI_XIN,
                Supplier.SUPPLIER_PROVINCE,
                Supplier.SUPPLIER_CITY,
                Supplier.SUPPLIER_AREA,
                Supplier.SUPPLIER_ADDRESS,
                Supplier.SUPPLIER_ZIP_CODE,
                Supplier.SUPPLIER_LONGITUDE,
                Supplier.SUPPLIER_LATITUDE
        );

        Supplier result = supplierService.save(supplierEntity, supplierId, supplierEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            SupplierView supplierView = JSON.parseObject(result.toJSONString(), SupplierView.class);
            supplierService.save(supplierView);
        }

        return renderJson(success);
    }

    @ApiOperation(value = "供应商基本信息修改")
    @RequestMapping(value = "/supplier/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Supplier supplierEntity = getEntry(Supplier.class);

        validateRequest(
                supplierEntity,
                Supplier.SUPPLIER_ID,
                Supplier.APP_ID,
                Supplier.SUPPLIER_NAME,
                Supplier.SUPPLIER_SRC_FILE_ID,
                Supplier.SUPPLIER_CONTACT,
                Supplier.SUPPLIER_PHONE,
                Supplier.SUPPLIER_TEL,
                Supplier.SUPPLIER_COMPANY,
                Supplier.SUPPLIER_EMAIL,
                Supplier.SUPPLIER_Q_Q,
                Supplier.SUPPLIER_WEI_XIN,
                Supplier.SUPPLIER_PROVINCE,
                Supplier.SUPPLIER_CITY,
                Supplier.SUPPLIER_AREA,
                Supplier.SUPPLIER_ADDRESS,
                Supplier.SUPPLIER_ZIP_CODE,
                Supplier.SUPPLIER_LONGITUDE,
                Supplier.SUPPLIER_LATITUDE,
                Supplier.SYSTEM_VERSION
        );

        Supplier result = supplierService.update(supplierEntity, supplierEntity.getSupplierId(), supplierEntity.getSystemRequestUserId(), supplierEntity.getSystemVersion());

        if (result != null) {
            SupplierView supplierView = JSON.parseObject(result.toJSONString(), SupplierView.class);
            supplierService.update(supplierView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "供应商基本信息删除")
    @RequestMapping(value = "/supplier/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Supplier supplierEntity = getEntry(Supplier.class);

        validateRequest(
                supplierEntity,
                Supplier.SUPPLIER_ID,
                Supplier.APP_ID,
                Supplier.SYSTEM_VERSION
        );

        Supplier result = supplierService.delete(supplierEntity.getSupplierId(), supplierEntity.getSystemRequestUserId(), supplierEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            SupplierView supplierView = JSON.parseObject(result.toJSONString(), SupplierView.class);
            supplierService.update(supplierView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "供应商基本信息数据同步")
    @RequestMapping(value = "/supplier/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Supplier> supplierList = supplierService.listByMysql();

        for (Supplier supplier : supplierList) {
            SupplierView supplierView = new SupplierView();
            supplierView.putAll(supplier);

            supplierService.saveOrUpdate(supplierView);
        }

        return renderJson(true);
    }

}