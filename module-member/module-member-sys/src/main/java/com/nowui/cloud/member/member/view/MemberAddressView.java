package com.nowui.cloud.member.member.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 会员地址	视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_address_info")
public class MemberAddressView extends BaseView{
    /**
     * 会员地址编号
     */
    @KeyId
    @Field
    private String memberAddressId;
    public static final String MEMBER_ADDRESS_ID = "memberAddressId";

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
     * 省
     */
    @Field
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";

    /**
     * 市
     */
    @Field
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";

    /**
     * 区
     */
    @Field
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";

    /**
     * 详细地址
     */
    @Field
    private String memberAddressAddress;
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";


    public String getMemberAddressId() {
        return getString(MEMBER_ADDRESS_ID);
    }

    public void setMemberAddressId(String memberAddressId) {
        put(MEMBER_ADDRESS_ID, memberAddressId);
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

    public String getMemberAddressProvince() {
        return getString(MEMBER_ADDRESS_PROVINCE);
    }

    public void setMemberAddressProvince(String memberAddressProvince) {
        put(MEMBER_ADDRESS_PROVINCE, memberAddressProvince);
    }

    public String getMemberAddressCity() {
        return getString(MEMBER_ADDRESS_CITY);
    }

    public void setMemberAddressCity(String memberAddressCity) {
        put(MEMBER_ADDRESS_CITY, memberAddressCity);
    }

    public String getMemberAddressArea() {
        return getString(MEMBER_ADDRESS_AREA);
    }

    public void setMemberAddressArea(String memberAddressArea) {
        put(MEMBER_ADDRESS_AREA, memberAddressArea);
    }

    public String getMemberAddressAddress() {
        return getString(MEMBER_ADDRESS_ADDRESS);
    }

    public void setMemberAddressAddress(String memberAddressAddress) {
        put(MEMBER_ADDRESS_ADDRESS, memberAddressAddress);
    }


}