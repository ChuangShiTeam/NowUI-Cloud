package com.nowui.cloud.shop.wms.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 仓库信息管理视图
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Component
@Document(collection = "warehouse_info")
public class WarehouseView extends BaseView {

    /**
     * 仓库Id
     */
    @KeyId
    @Field
    @NotNull(message = "仓库Id不能为空")
    private String warehouseId;
    public static final String WAREHOUSE_ID = "warehouseId";

    /**
     * 仓库名称
     */
    @Field
    @NotNull(message = "仓库名称不能为空")
    private String warehouseName;
    public static final String WAREHOUSE_NAME = "warehouseName";

    /**
     * 省份
     */
    @Field
    @NotNull(message = "省份不能为空")
    private String warehouseProvince;
    public static final String WAREHOUSE_PROVINCE = "warehouseProvince";

    /**
     * 市
     */
    @Field
    @NotNull(message = "市不能为空")
    private String warehouseCity;
    public static final String WAREHOUSE_CITY = "warehouseCity";

    /**
     * 区
     */
    @Field
    @NotNull(message = "区不能为空")
    private String warehouseArea;
    public static final String WAREHOUSE_AREA = "warehouseArea";

    /**
     * 仓库地址
     */
    @Field
    @NotNull(message = "仓库地址不能为空")
    private String warehouseAddress;
    public static final String WAREHOUSE_ADDRESS = "warehouseAddress";

    /**
     * 供应商Id
     */
    @Field
    @NotNull(message = "供应商Id不能为空")
    private String warehouseSupplierId;
    public static final String WAREHOUSE_SUPPLIER_ID = "warehouseSupplierId";

    /**
     * 应用ID
     */
    @Field
    @NotNull(message = "应用ID不能为空")
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