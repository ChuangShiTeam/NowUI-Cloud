package com.nowui.cloud.shop.wms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 仓库信息管理
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Component

@TableName(value = "warehouse_info")
public class Warehouse extends BaseEntity {

    /**
     * 仓库Id
     */
    @Id
    @TableId
    @NotNull(message = "仓库Id不能为空")
    @Length(max = 32, message = "仓库Id长度超出限制")
    private String warehouseId;
    public static final String WAREHOUSE_ID = "warehouseId";

    /**
     * 仓库名称
     */
    @TableField
    @NotNull(message = "仓库名称不能为空")
    @Length(max = 50, message = "仓库名称长度超出限制")
    private String warehouseName;
    public static final String WAREHOUSE_NAME = "warehouseName";

    /**
     * 省份
     */
    @TableField
    @NotNull(message = "省份不能为空")
    @Length(max = 32, message = "省份长度超出限制")
    private String warehouseProvince;
    public static final String WAREHOUSE_PROVINCE = "warehouseProvince";

    /**
     * 市
     */
    @TableField
    @NotNull(message = "市不能为空")
    @Length(max = 32, message = "市长度超出限制")
    private String warehouseCity;
    public static final String WAREHOUSE_CITY = "warehouseCity";

    /**
     * 区
     */
    @TableField
    @NotNull(message = "区不能为空")
    @Length(max = 32, message = "区长度超出限制")
    private String warehouseArea;
    public static final String WAREHOUSE_AREA = "warehouseArea";

    /**
     * 仓库地址
     */
    @TableField
    @NotNull(message = "仓库地址不能为空")
    @Length(max = 500, message = "仓库地址长度超出限制")
    private String warehouseAddress;
    public static final String WAREHOUSE_ADDRESS = "warehouseAddress";

    /**
     * 供应商Id
     */
    @TableField
    @NotNull(message = "供应商Id不能为空")
    @Length(max = 32, message = "供应商Id长度超出限制")
    private String warehouseSupplierId;
    public static final String WAREHOUSE_SUPPLIER_ID = "warehouseSupplierId";

    /**
     * 应用ID
     */
    @TableField
    @NotNull(message = "应用ID不能为空")
    @Length(max = 32, message = "应用ID长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";


    public String getWarehouseId() {
        return getString(WAREHOUSE_ID);
    }
    
    public void setWarehouseId(String warehouseId) {
        put(WAREHOUSE_ID, warehouseId);
    }

    public String getWarehouseName() {
        return getString(WAREHOUSE_NAME);
    }
    
    public void setWarehouseName(String warehouseName) {
        put(WAREHOUSE_NAME, warehouseName);
    }

    public String getWarehouseProvince() {
        return getString(WAREHOUSE_PROVINCE);
    }
    
    public void setWarehouseProvince(String warehouseProvince) {
        put(WAREHOUSE_PROVINCE, warehouseProvince);
    }

    public String getWarehouseCity() {
        return getString(WAREHOUSE_CITY);
    }
    
    public void setWarehouseCity(String warehouseCity) {
        put(WAREHOUSE_CITY, warehouseCity);
    }

    public String getWarehouseArea() {
        return getString(WAREHOUSE_AREA);
    }
    
    public void setWarehouseArea(String warehouseArea) {
        put(WAREHOUSE_AREA, warehouseArea);
    }

    public String getWarehouseAddress() {
        return getString(WAREHOUSE_ADDRESS);
    }
    
    public void setWarehouseAddress(String warehouseAddress) {
        put(WAREHOUSE_ADDRESS, warehouseAddress);
    }

    public String getWarehouseSupplierId() {
        return getString(WAREHOUSE_SUPPLIER_ID);
    }
    
    public void setWarehouseSupplierId(String warehouseSupplierId) {
        put(WAREHOUSE_SUPPLIER_ID, warehouseSupplierId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }


}