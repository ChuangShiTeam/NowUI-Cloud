package com.nowui.cloud.shop.wms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购申请单
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Component

@TableName(value = "purchase_order_info")
public class PurchaseOrder extends BaseEntity {

    /**
     * 采购单Id
     */
    @Id
    @TableId
    @NotNull(message = "采购单Id不能为空")
    @Length(max = 32, message = "采购单Id长度超出限制")
    private String purchaseId;
    public static final String PURCHASE_ID = "purchaseId";

    /**
     * 采购单号
     */
    @TableField
    @NotNull(message = "采购单号不能为空")
    @Length(max = 50, message = "采购单号长度超出限制")
    private String purchaseNo;
    public static final String PURCHASE_NO = "purchaseNo";

    /**
     * 采购单名称
     */
    @TableField
    @NotNull(message = "采购单名称不能为空")
    @Length(max = 100, message = "采购单名称长度超出限制")
    private String purchaseName;
    public static final String PURCHASE_NAME = "purchaseName";

    /**
     * 采购类型;
     */
    @TableField
    @NotNull(message = "采购类型;不能为空")
    @Length(max = 50, message = "采购类型;长度超出限制")
    private String purchaseType;
    public static final String PURCHASE_TYPE = "purchaseType";

    /**
     * 采购员Id
     */
    @TableField
    @NotNull(message = "采购员Id不能为空")
    @Length(max = 32, message = "采购员Id长度超出限制")
    private String purchaseUserId;
    public static final String PURCHASE_USER_ID = "purchaseUserId";

    /**
     * 采购员姓名
     */
    @TableField
    @NotNull(message = "采购员姓名不能为空")
    @Length(max = 50, message = "采购员姓名长度超出限制")
    private String purchaseUserName;
    public static final String PURCHASE_USER_NAME = "purchaseUserName";

    /**
     * 品牌供应商Id
     */
    @TableField
    @NotNull(message = "品牌供应商Id不能为空")
    @Length(max = 32, message = "品牌供应商Id长度超出限制")
    private String purchaseSupplierId;
    public static final String PURCHASE_SUPPLIER_ID = "purchaseSupplierId";

    /**
     * 仓库id
     */
    @TableField
    @NotNull(message = "仓库id不能为空")
    @Length(max = 32, message = "仓库id长度超出限制")
    private String warehouseId;
    public static final String WAREHOUSE_ID = "warehouseId";

    /**
     * 订货日期
     */
    @TableField
    @NotNull(message = "订货日期不能为空")
    @Length(max = 0, message = "订货日期长度超出限制")
    private Date purchaseOrderDate;
    public static final String PURCHASE_ORDER_DATE = "purchaseOrderDate";

    /**
     * 采购总金额
     */
    @TableField
    @NotNull(message = "采购总金额不能为空")
    @Length(max = 0, message = "采购总金额长度超出限制")
    private BigDecimal purchaseTotalCost;
    public static final String PURCHASE_TOTAL_COST = "purchaseTotalCost";

    /**
     * 采购状态；待审核，已审核，审核驳回；采购取消；已完成；
     */
    @TableField
    @NotNull(message = "采购状态；待审核，已审核，审核驳回；采购取消；已完成；不能为空")
    @Length(max = 32, message = "采购状态；待审核，已审核，审核驳回；采购取消；已完成；长度超出限制")
    private String purchaseStatus;
    public static final String PURCHASE_STATUS = "purchaseStatus";

    /**
     * 采购备注
     */
    @TableField
    @NotNull(message = "采购备注不能为空")
    @Length(max = 1000, message = "采购备注长度超出限制")
    private String purchaseRemark;
    public static final String PURCHASE_REMARK = "purchaseRemark";

    /**
     * 审核人
     */
    @TableField
    @NotNull(message = "审核人不能为空")
    @Length(max = 32, message = "审核人长度超出限制")
    private String purchaseAuditUserId;
    public static final String PURCHASE_AUDIT_USER_ID = "purchaseAuditUserId";

    /**
     * 审核人姓名
     */
    @TableField
    @NotNull(message = "审核人姓名不能为空")
    @Length(max = 50, message = "审核人姓名长度超出限制")
    private String purchaseAuditUserName;
    public static final String PURCHASE_AUDIT_USER_NAME = "purchaseAuditUserName";

    /**
     * 审核时间
     */
    @TableField
    @NotNull(message = "审核时间不能为空")
    @Length(max = 0, message = "审核时间长度超出限制")
    private Date purchaseAuditTime;
    public static final String PURCHASE_AUDIT_TIME = "purchaseAuditTime";

    /**
     * 应用ID
     */
    @TableField
    @NotNull(message = "应用ID不能为空")
    @Length(max = 32, message = "应用ID长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";


    public String getPurchaseId() {
        return getString(PURCHASE_ID);
    }
    
    public void setPurchaseId(String purchaseId) {
        put(PURCHASE_ID, purchaseId);
    }

    public String getPurchaseNo() {
        return getString(PURCHASE_NO);
    }
    
    public void setPurchaseNo(String purchaseNo) {
        put(PURCHASE_NO, purchaseNo);
    }

    public String getPurchaseName() {
        return getString(PURCHASE_NAME);
    }
    
    public void setPurchaseName(String purchaseName) {
        put(PURCHASE_NAME, purchaseName);
    }

    public String getPurchaseType() {
        return getString(PURCHASE_TYPE);
    }
    
    public void setPurchaseType(String purchaseType) {
        put(PURCHASE_TYPE, purchaseType);
    }

    public String getPurchaseUserId() {
        return getString(PURCHASE_USER_ID);
    }
    
    public void setPurchaseUserId(String purchaseUserId) {
        put(PURCHASE_USER_ID, purchaseUserId);
    }

    public String getPurchaseUserName() {
        return getString(PURCHASE_USER_NAME);
    }
    
    public void setPurchaseUserName(String purchaseUserName) {
        put(PURCHASE_USER_NAME, purchaseUserName);
    }

    public String getPurchaseSupplierId() {
        return getString(PURCHASE_SUPPLIER_ID);
    }
    
    public void setPurchaseSupplierId(String purchaseSupplierId) {
        put(PURCHASE_SUPPLIER_ID, purchaseSupplierId);
    }

    public String getWarehouseId() {
        return getString(WAREHOUSE_ID);
    }
    
    public void setWarehouseId(String warehouseId) {
        put(WAREHOUSE_ID, warehouseId);
    }

    public Date getPurchaseOrderDate() {
        return getDate(PURCHASE_ORDER_DATE);
    }
    
    public void setPurchaseOrderDate(Date purchaseOrderDate) {
        put(PURCHASE_ORDER_DATE, purchaseOrderDate);
    }

    public BigDecimal getPurchaseTotalCost() {
        return getBigDecimal(PURCHASE_TOTAL_COST);
    }
    
    public void setPurchaseTotalCost(BigDecimal purchaseTotalCost) {
        put(PURCHASE_TOTAL_COST, purchaseTotalCost);
    }

    public String getPurchaseStatus() {
        return getString(PURCHASE_STATUS);
    }
    
    public void setPurchaseStatus(String purchaseStatus) {
        put(PURCHASE_STATUS, purchaseStatus);
    }

    public String getPurchaseRemark() {
        return getString(PURCHASE_REMARK);
    }
    
    public void setPurchaseRemark(String purchaseRemark) {
        put(PURCHASE_REMARK, purchaseRemark);
    }

    public String getPurchaseAuditUserId() {
        return getString(PURCHASE_AUDIT_USER_ID);
    }
    
    public void setPurchaseAuditUserId(String purchaseAuditUserId) {
        put(PURCHASE_AUDIT_USER_ID, purchaseAuditUserId);
    }

    public String getPurchaseAuditUserName() {
        return getString(PURCHASE_AUDIT_USER_NAME);
    }
    
    public void setPurchaseAuditUserName(String purchaseAuditUserName) {
        put(PURCHASE_AUDIT_USER_NAME, purchaseAuditUserName);
    }

    public Date getPurchaseAuditTime() {
        return getDate(PURCHASE_AUDIT_TIME);
    }
    
    public void setPurchaseAuditTime(Date purchaseAuditTime) {
        put(PURCHASE_AUDIT_TIME, purchaseAuditTime);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }


}