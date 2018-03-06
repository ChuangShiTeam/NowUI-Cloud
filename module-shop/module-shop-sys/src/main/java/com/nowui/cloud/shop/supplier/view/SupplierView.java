package com.nowui.cloud.shop.supplier.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 供应商基本信息视图
 *
 * @author lyn
 *
 * 2018-03-06
 */
@Component
@Document(collection = "supplier_info")
public class SupplierView extends BaseView {

    /**
     * 供应商ID
     */
    @KeyId
    @Field
    @NotNull(message = "供应商ID不能为空")
    private String supplierId;
    public static final String SUPPLIER_ID = "supplierId";

    /**
     * 供应商名称
     */
    @Field
    @NotNull(message = "供应商名称不能为空")
    private String supplierName;
    public static final String SUPPLIER_NAME = "supplierName";

    /**
     * 供应商图片
     */
    @Field
    @NotNull(message = "供应商图片不能为空")
    private String supplierSrcFileId;
    public static final String SUPPLIER_SRC_FILE_ID = "supplierSrcFileId";

    /**
     * 联系人
     */
    @Field
    @NotNull(message = "联系人不能为空")
    private String supplierContact;
    public static final String SUPPLIER_CONTACT = "supplierContact";

    /**
     * 手机号码
     */
    @Field
    @NotNull(message = "手机号码不能为空")
    private String supplierPhone;
    public static final String SUPPLIER_PHONE = "supplierPhone";

    /**
     * 电话
     */
    @Field
    @NotNull(message = "电话不能为空")
    private String supplierTel;
    public static final String SUPPLIER_TEL = "supplierTel";

    /**
     * 公司名称
     */
    @Field
    @NotNull(message = "公司名称不能为空")
    private String supplierCompany;
    public static final String SUPPLIER_COMPANY = "supplierCompany";

    /**
     * 电子邮箱
     */
    @Field
    @NotNull(message = "电子邮箱不能为空")
    private String supplierEmail;
    public static final String SUPPLIER_EMAIL = "supplierEmail";

    /**
     * QQ号码
     */
    @Field
    @NotNull(message = "QQ号码不能为空")
    private String supplierQQ;
    public static final String SUPPLIER_Q_Q = "supplierQQ";

    /**
     * WeiXin
     */
    @Field
    @NotNull(message = "WeiXin不能为空")
    private String supplierWeiXin;
    public static final String SUPPLIER_WEI_XIN = "supplierWeiXin";

    /**
     * 供应商所属省份
     */
    @Field
    @NotNull(message = "供应商所属省份不能为空")
    private String supplierProvince;
    public static final String SUPPLIER_PROVINCE = "supplierProvince";

    /**
     * 供应商所属市
     */
    @Field
    @NotNull(message = "供应商所属市不能为空")
    private String supplierCity;
    public static final String SUPPLIER_CITY = "supplierCity";

    /**
     * 供应商所属
     */
    @Field
    @NotNull(message = "供应商所属不能为空")
    private String supplierArea;
    public static final String SUPPLIER_AREA = "supplierArea";

    /**
     * 详细地址
     */
    @Field
    @NotNull(message = "详细地址不能为空")
    private String supplierAddress;
    public static final String SUPPLIER_ADDRESS = "supplierAddress";

    /**
     * 邮政编码
     */
    @Field
    @NotNull(message = "邮政编码不能为空")
    private String supplierZipCode;
    public static final String SUPPLIER_ZIP_CODE = "supplierZipCode";

    /**
     * 经度
     */
    @Field
    @NotNull(message = "经度不能为空")
    private BigDecimal supplierLongitude;
    public static final String SUPPLIER_LONGITUDE = "supplierLongitude";

    /**
     * 纬度
     */
    @Field
    @NotNull(message = "纬度不能为空")
    private BigDecimal supplierLatitude;
    public static final String SUPPLIER_LATITUDE = "supplierLatitude";

    /**
     * 应用ID
     */
    @Field
    @NotNull(message = "应用ID不能为空")
    private String appId;
    public static final String APP_ID = "appId";


    public String getSupplierId() {
        return getString(SUPPLIER_ID);
    }

    public void setSupplierId(String supplierId) {
        put(SUPPLIER_ID, supplierId);
    }
    public String getSupplierName() {
        return getString(SUPPLIER_NAME);
    }

    public void setSupplierName(String supplierName) {
        put(SUPPLIER_NAME, supplierName);
    }
    public String getSupplierSrcFileId() {
        return getString(SUPPLIER_SRC_FILE_ID);
    }

    public void setSupplierSrcFileId(String supplierSrcFileId) {
        put(SUPPLIER_SRC_FILE_ID, supplierSrcFileId);
    }
    public String getSupplierContact() {
        return getString(SUPPLIER_CONTACT);
    }

    public void setSupplierContact(String supplierContact) {
        put(SUPPLIER_CONTACT, supplierContact);
    }
    public String getSupplierPhone() {
        return getString(SUPPLIER_PHONE);
    }

    public void setSupplierPhone(String supplierPhone) {
        put(SUPPLIER_PHONE, supplierPhone);
    }
    public String getSupplierTel() {
        return getString(SUPPLIER_TEL);
    }

    public void setSupplierTel(String supplierTel) {
        put(SUPPLIER_TEL, supplierTel);
    }
    public String getSupplierCompany() {
        return getString(SUPPLIER_COMPANY);
    }

    public void setSupplierCompany(String supplierCompany) {
        put(SUPPLIER_COMPANY, supplierCompany);
    }
    public String getSupplierEmail() {
        return getString(SUPPLIER_EMAIL);
    }

    public void setSupplierEmail(String supplierEmail) {
        put(SUPPLIER_EMAIL, supplierEmail);
    }
    public String getSupplierQQ() {
        return getString(SUPPLIER_Q_Q);
    }

    public void setSupplierQQ(String supplierQQ) {
        put(SUPPLIER_Q_Q, supplierQQ);
    }
    public String getSupplierWeiXin() {
        return getString(SUPPLIER_WEI_XIN);
    }

    public void setSupplierWeiXin(String supplierWeiXin) {
        put(SUPPLIER_WEI_XIN, supplierWeiXin);
    }
    public String getSupplierProvince() {
        return getString(SUPPLIER_PROVINCE);
    }

    public void setSupplierProvince(String supplierProvince) {
        put(SUPPLIER_PROVINCE, supplierProvince);
    }
    public String getSupplierCity() {
        return getString(SUPPLIER_CITY);
    }

    public void setSupplierCity(String supplierCity) {
        put(SUPPLIER_CITY, supplierCity);
    }
    public String getSupplierArea() {
        return getString(SUPPLIER_AREA);
    }

    public void setSupplierArea(String supplierArea) {
        put(SUPPLIER_AREA, supplierArea);
    }
    public String getSupplierAddress() {
        return getString(SUPPLIER_ADDRESS);
    }

    public void setSupplierAddress(String supplierAddress) {
        put(SUPPLIER_ADDRESS, supplierAddress);
    }
    public String getSupplierZipCode() {
        return getString(SUPPLIER_ZIP_CODE);
    }

    public void setSupplierZipCode(String supplierZipCode) {
        put(SUPPLIER_ZIP_CODE, supplierZipCode);
    }
    public BigDecimal getSupplierLongitude() {
        return getBigDecimal(SUPPLIER_LONGITUDE);
    }

    public void setSupplierLongitude(BigDecimal supplierLongitude) {
        put(SUPPLIER_LONGITUDE, supplierLongitude);
    }
    public BigDecimal getSupplierLatitude() {
        return getBigDecimal(SUPPLIER_LATITUDE);
    }

    public void setSupplierLatitude(BigDecimal supplierLatitude) {
        put(SUPPLIER_LATITUDE, supplierLatitude);
    }
    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

}