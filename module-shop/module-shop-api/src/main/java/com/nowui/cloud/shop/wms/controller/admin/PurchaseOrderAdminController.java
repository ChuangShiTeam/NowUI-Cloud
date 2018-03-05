package com.nowui.cloud.shop.wms.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.shop.wms.entity.PurchaseOrder;
import com.nowui.cloud.shop.wms.view.PurchaseOrderView;
import com.nowui.cloud.shop.wms.service.PurchaseOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 采购申请单管理端控制器
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Api(value = "采购申请单", description = "采购申请单管理端接口管理")
@RestController
public class PurchaseOrderAdminController extends BaseController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @ApiOperation(value = "采购申请单列表")
    @RequestMapping(value = "/purchase/order/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        PurchaseOrderView purchaseOrderView = getEntry(PurchaseOrderView.class);

        validateRequest(
                purchaseOrderView,
                PurchaseOrderView.APP_ID,
                PurchaseOrderView.PURCHASE_NO,
                PurchaseOrderView.PURCHASE_NAME,
                PurchaseOrderView.PURCHASE_TYPE,
                PurchaseOrderView.PURCHASE_USER_NAME,
                PurchaseOrderView.PAGE_INDEX,
                PurchaseOrderView.PAGE_SIZE
        );

        Integer resultTotal = purchaseOrderService.countForAdmin(purchaseOrderView.getAppId(), purchaseOrderView.getPurchaseNo(), purchaseOrderView.getPurchaseName(), purchaseOrderView.getPurchaseType(), purchaseOrderView.getPurchaseUserName());
        List<PurchaseOrderView> resultList = purchaseOrderService.listForAdmin(purchaseOrderView.getAppId(), purchaseOrderView.getPurchaseNo(), purchaseOrderView.getPurchaseName(), purchaseOrderView.getPurchaseType(), purchaseOrderView.getPurchaseUserName(), purchaseOrderView.getPageIndex(), purchaseOrderView.getPageSize());

        validateResponse(
                PurchaseOrderView.PURCHASE_ID,
                PurchaseOrderView.PURCHASE_NO,
                PurchaseOrderView.PURCHASE_NAME,
                PurchaseOrderView.PURCHASE_TYPE,
                PurchaseOrderView.PURCHASE_USER_NAME,
                PurchaseOrderView.PURCHASE_ORDER_DATE,
                PurchaseOrderView.PURCHASE_TOTAL_COST,
                PurchaseOrderView.PURCHASE_STATUS,
                PurchaseOrderView.PURCHASE_AUDIT_USER_NAME,
                PurchaseOrderView.PURCHASE_AUDIT_TIME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "采购申请单信息")
    @RequestMapping(value = "/purchase/order/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        PurchaseOrderView purchaseOrderView = getEntry(PurchaseOrderView.class);

        validateRequest(
                purchaseOrderView,
                PurchaseOrderView.APP_ID,
                PurchaseOrderView.PURCHASE_ID
        );

        PurchaseOrderView result = purchaseOrderService.find(purchaseOrderView.getPurchaseId());

        validateResponse(
                PurchaseOrderView.PURCHASE_ID,
            	PurchaseOrderView.PURCHASE_NO,
            	PurchaseOrderView.PURCHASE_NAME,
            	PurchaseOrderView.PURCHASE_TYPE,
            	PurchaseOrderView.PURCHASE_USER_ID,
            	PurchaseOrderView.PURCHASE_USER_NAME,
            	PurchaseOrderView.PURCHASE_SUPPLIER_ID,
            	PurchaseOrderView.WAREHOUSE_ID,
            	PurchaseOrderView.PURCHASE_ORDER_DATE,
            	PurchaseOrderView.PURCHASE_TOTAL_COST,
            	PurchaseOrderView.PURCHASE_STATUS,
            	PurchaseOrderView.PURCHASE_REMARK,
            	PurchaseOrderView.PURCHASE_AUDIT_USER_ID,
            	PurchaseOrderView.PURCHASE_AUDIT_USER_NAME,
            	PurchaseOrderView.PURCHASE_AUDIT_TIME,
                PurchaseOrderView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "采购申请单新增")
    @RequestMapping(value = "/purchase/order/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        PurchaseOrder purchaseOrderEntity = getEntry(PurchaseOrder.class);

        String purchaseOrderId = Util.getRandomUUID();

        validateRequest(
                purchaseOrderEntity,
                PurchaseOrder.APP_ID,
                PurchaseOrder.PURCHASE_NO,
                PurchaseOrder.PURCHASE_NAME,
                PurchaseOrder.PURCHASE_TYPE,
                PurchaseOrder.PURCHASE_USER_ID,
                PurchaseOrder.PURCHASE_USER_NAME,
                PurchaseOrder.PURCHASE_SUPPLIER_ID,
                PurchaseOrder.WAREHOUSE_ID,
                PurchaseOrder.PURCHASE_ORDER_DATE,
                PurchaseOrder.PURCHASE_TOTAL_COST,
                PurchaseOrder.PURCHASE_STATUS,
                PurchaseOrder.PURCHASE_REMARK,
                PurchaseOrder.PURCHASE_AUDIT_USER_ID,
                PurchaseOrder.PURCHASE_AUDIT_USER_NAME,
                PurchaseOrder.PURCHASE_AUDIT_TIME
        );

        PurchaseOrder result = purchaseOrderService.save(purchaseOrderEntity, purchaseOrderId, purchaseOrderEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            PurchaseOrderView purchaseOrderView = JSON.parseObject(result.toJSONString(), PurchaseOrderView.class);
            purchaseOrderService.save(purchaseOrderView);
        }

        return renderJson(success);
    }

    @ApiOperation(value = "采购申请单修改")
    @RequestMapping(value = "/purchase/order/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        PurchaseOrder purchaseOrderEntity = getEntry(PurchaseOrder.class);

        validateRequest(
                purchaseOrderEntity,
                PurchaseOrder.PURCHASE_ID,
                PurchaseOrder.APP_ID,
                PurchaseOrder.PURCHASE_NO,
                PurchaseOrder.PURCHASE_NAME,
                PurchaseOrder.PURCHASE_TYPE,
                PurchaseOrder.PURCHASE_USER_ID,
                PurchaseOrder.PURCHASE_USER_NAME,
                PurchaseOrder.PURCHASE_SUPPLIER_ID,
                PurchaseOrder.WAREHOUSE_ID,
                PurchaseOrder.PURCHASE_ORDER_DATE,
                PurchaseOrder.PURCHASE_TOTAL_COST,
                PurchaseOrder.PURCHASE_STATUS,
                PurchaseOrder.PURCHASE_REMARK,
                PurchaseOrder.PURCHASE_AUDIT_USER_ID,
                PurchaseOrder.PURCHASE_AUDIT_USER_NAME,
                PurchaseOrder.PURCHASE_AUDIT_TIME,
                PurchaseOrder.SYSTEM_VERSION
        );

        PurchaseOrder result = purchaseOrderService.update(purchaseOrderEntity, purchaseOrderEntity.getPurchaseId(), purchaseOrderEntity.getSystemRequestUserId(), purchaseOrderEntity.getSystemVersion());

        if (result != null) {
            PurchaseOrderView purchaseOrderView = JSON.parseObject(result.toJSONString(), PurchaseOrderView.class);
            purchaseOrderService.update(purchaseOrderView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "采购申请单删除")
    @RequestMapping(value = "/purchase/order/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        PurchaseOrder purchaseOrderEntity = getEntry(PurchaseOrder.class);

        validateRequest(
                purchaseOrderEntity,
                PurchaseOrder.PURCHASE_ID,
                PurchaseOrder.APP_ID,
                PurchaseOrder.SYSTEM_VERSION
        );

        PurchaseOrder result = purchaseOrderService.delete(purchaseOrderEntity.getPurchaseId(), purchaseOrderEntity.getSystemRequestUserId(), purchaseOrderEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            PurchaseOrderView purchaseOrderView = JSON.parseObject(result.toJSONString(), PurchaseOrderView.class);
            purchaseOrderService.update(purchaseOrderView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "采购申请单数据同步")
    @RequestMapping(value = "/purchase/order/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.listByMysql();

        for (PurchaseOrder purchaseOrder : purchaseOrderList) {
            PurchaseOrderView purchaseOrderView = new PurchaseOrderView();
            purchaseOrderView.putAll(purchaseOrder);

            purchaseOrderService.update(purchaseOrderView);
        }

        return renderJson(true);
    }

}