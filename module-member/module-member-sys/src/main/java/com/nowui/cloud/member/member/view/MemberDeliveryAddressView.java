package com.nowui.cloud.member.member.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 用户收货地址	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_delivery_address")
public class MemberDeliveryAddressView extends BaseView {

    /**
     * 会员收货地址编号
     */
    @KeyId
    @Field
    private String memberDeliveryAddressId;
    public static final String MEMBER_DELIVERY_ADDRESS_ID = "memberDeliveryAddressId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 姓名
     */
    @Field
    private String memberDeliveryAddressName;
    public static final String MEMBER_DELIVERY_ADDRESS_NAME = "memberDeliveryAddressName";

    /**
     * 手机号
     */
    @Field
    private String memberDeliveryAddressPhone;
    public static final String MEMBER_DELIVERY_ADDRESS_PHONE = "memberDeliveryAddressPhone";

    /**
     * 省
     */
    @Field
    private String memberDeliveryAddressProvince;
    public static final String MEMBER_DELIVERY_ADDRESS_PROVINCE = "memberDeliveryAddressProvince";

    /**
     * 市
     */
    @Field
    private String memberDeliveryAddressCity;
    public static final String MEMBER_DELIVERY_ADDRESS_CITY = "memberDeliveryAddressCity";

    /**
     * 区
     */
    @Field
    private String memberDeliveryAddressArea;
    public static final String MEMBER_DELIVERY_ADDRESS_AREA = "memberDeliveryAddressArea";

    /**
     * 详细地址
     */
    @Field
    private String memberDeliveryAddressAddress;
    public static final String MEMBER_DELIVERY_ADDRESS_ADDRESS = "memberDeliveryAddressAddress";

    /**
     * 邮政编码
     */
    @Field
    private String memberDeliveryAddressPostcode;
    public static final String MEMBER_DELIVERY_ADDRESS_POSTCODE = "memberDeliveryAddressPostcode";

    /**
     * 是否默认收货地址
     */
    @Field
    private Boolean memberDeliveryAddressIsDefault;
    public static final String MEMBER_DELIVERY_ADDRESS_IS_DEFAULT = "memberDeliveryAddressIsDefault";


    public String getMemberDeliveryAddressId() {
        return getString(MEMBER_DELIVERY_ADDRESS_ID);
    }

    public void setMemberDeliveryAddressId(String memberDeliveryAddressId) {
        put(MEMBER_DELIVERY_ADDRESS_ID, memberDeliveryAddressId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getMemberDeliveryAddressName() {
        return getString(MEMBER_DELIVERY_ADDRESS_NAME);
    }

    public void setMemberDeliveryAddressName(String memberDeliveryAddressName) {
        put(MEMBER_DELIVERY_ADDRESS_NAME, memberDeliveryAddressName);
    }

    public String getMemberDeliveryAddressPhone() {
        return getString(MEMBER_DELIVERY_ADDRESS_PHONE);
    }

    public void setMemberDeliveryAddressPhone(String memberDeliveryAddressPhone) {
        put(MEMBER_DELIVERY_ADDRESS_PHONE, memberDeliveryAddressPhone);
    }

    public String getMemberDeliveryAddressProvince() {
        return getString(MEMBER_DELIVERY_ADDRESS_PROVINCE);
    }

    public void setMemberDeliveryAddressProvince(String memberDeliveryAddressProvince) {
        put(MEMBER_DELIVERY_ADDRESS_PROVINCE, memberDeliveryAddressProvince);
    }

    public String getMemberDeliveryAddressCity() {
        return getString(MEMBER_DELIVERY_ADDRESS_CITY);
    }

    public void setMemberDeliveryAddressCity(String memberDeliveryAddressCity) {
        put(MEMBER_DELIVERY_ADDRESS_CITY, memberDeliveryAddressCity);
    }

    public String getMemberDeliveryAddressArea() {
        return getString(MEMBER_DELIVERY_ADDRESS_AREA);
    }

    public void setMemberDeliveryAddressArea(String memberDeliveryAddressArea) {
        put(MEMBER_DELIVERY_ADDRESS_AREA, memberDeliveryAddressArea);
    }

    public String getMemberDeliveryAddressAddress() {
        return getString(MEMBER_DELIVERY_ADDRESS_ADDRESS);
    }

    public void setMemberDeliveryAddressAddress(String memberDeliveryAddressAddress) {
        put(MEMBER_DELIVERY_ADDRESS_ADDRESS, memberDeliveryAddressAddress);
    }

    public String getMemberDeliveryAddressPostcode() {
        return getString(MEMBER_DELIVERY_ADDRESS_POSTCODE);
    }

    public void setMemberDeliveryAddressPostcode(String memberDeliveryAddressPostcode) {
        put(MEMBER_DELIVERY_ADDRESS_POSTCODE, memberDeliveryAddressPostcode);
    }

    public Boolean getMemberDeliveryAddressIsDefault() {
        return getBoolean(MEMBER_DELIVERY_ADDRESS_IS_DEFAULT);
    }

    public void setMemberDeliveryAddressIsDefault(Boolean memberDeliveryAddressIsDefault) {
        put(MEMBER_DELIVERY_ADDRESS_IS_DEFAULT, memberDeliveryAddressIsDefault);
    }


}