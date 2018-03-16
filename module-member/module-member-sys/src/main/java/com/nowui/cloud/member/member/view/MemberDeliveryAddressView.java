package com.nowui.cloud.member.member.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员收货地址视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_delivery_address")
public class MemberDeliveryAddressView extends BaseView {

    /**
     * 会员收货地址编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员收货地址编号不能为空")
    @Length(max = 32, message = "会员收货地址编号长度超出限制")
    private String memberDeliveryAddressId;
    public static final String MEMBER_DELIVERY_ADDRESS_ID = "memberDeliveryAddressId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 姓名
     */
    @Field
    @NotNull(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度超出限制")
    private String memberDeliveryAddressName;
    public static final String MEMBER_DELIVERY_ADDRESS_NAME = "memberDeliveryAddressName";

    /**
     * 手机号
     */
    @Field
    @NotNull(message = "手机号不能为空")
    @Length(max = 20, message = "手机号长度超出限制")
    private String memberDeliveryAddressPhone;
    public static final String MEMBER_DELIVERY_ADDRESS_PHONE = "memberDeliveryAddressPhone";

    /**
     * 省
     */
    @Field
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String memberDeliveryAddressProvince;
    public static final String MEMBER_DELIVERY_ADDRESS_PROVINCE = "memberDeliveryAddressProvince";

    /**
     * 市
     */
    @Field
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String memberDeliveryAddressCity;
    public static final String MEMBER_DELIVERY_ADDRESS_CITY = "memberDeliveryAddressCity";

    /**
     * 区
     */
    @Field
    @NotNull(message = "区不能为空")
    @Length(max = 50, message = "区长度超出限制")
    private String memberDeliveryAddressArea;
    public static final String MEMBER_DELIVERY_ADDRESS_AREA = "memberDeliveryAddressArea";

    /**
     * 详细地址
     */
    @Field
    @NotNull(message = "详细地址不能为空")
    @Length(max = 200, message = "详细地址长度超出限制")
    private String memberDeliveryAddressAddress;
    public static final String MEMBER_DELIVERY_ADDRESS_ADDRESS = "memberDeliveryAddressAddress";

    /**
     * 邮政编码
     */
    @Field
    @NotNull(message = "邮政编码不能为空")
    @Length(max = 10, message = "邮政编码长度超出限制")
    private String memberDeliveryAddressPostcode;
    public static final String MEMBER_DELIVERY_ADDRESS_POSTCODE = "memberDeliveryAddressPostcode";

    /**
     * 是否默认收货地址
     */
    @Field
    @NotNull(message = "是否默认收货地址不能为空")
    @Digits(integer = 1, fraction = 0, message = "是否默认收货地址长度超出限制")
    private Boolean memberDeliveryAddressIsDefault;
    public static final String MEMBER_DELIVERY_ADDRESS_IS_DEFAULT = "memberDeliveryAddressIsDefault";


    public String getMemberDeliveryAddressId() {
        return memberDeliveryAddressId;
    }

    public void setMemberDeliveryAddressId(String memberDeliveryAddressId) {
        this.memberDeliveryAddressId = memberDeliveryAddressId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberDeliveryAddressName() {
        return memberDeliveryAddressName;
    }

    public void setMemberDeliveryAddressName(String memberDeliveryAddressName) {
        this.memberDeliveryAddressName = memberDeliveryAddressName;
    }
    
    public String getMemberDeliveryAddressPhone() {
        return memberDeliveryAddressPhone;
    }

    public void setMemberDeliveryAddressPhone(String memberDeliveryAddressPhone) {
        this.memberDeliveryAddressPhone = memberDeliveryAddressPhone;
    }
    
    public String getMemberDeliveryAddressProvince() {
        return memberDeliveryAddressProvince;
    }

    public void setMemberDeliveryAddressProvince(String memberDeliveryAddressProvince) {
        this.memberDeliveryAddressProvince = memberDeliveryAddressProvince;
    }
    
    public String getMemberDeliveryAddressCity() {
        return memberDeliveryAddressCity;
    }

    public void setMemberDeliveryAddressCity(String memberDeliveryAddressCity) {
        this.memberDeliveryAddressCity = memberDeliveryAddressCity;
    }
    
    public String getMemberDeliveryAddressArea() {
        return memberDeliveryAddressArea;
    }

    public void setMemberDeliveryAddressArea(String memberDeliveryAddressArea) {
        this.memberDeliveryAddressArea = memberDeliveryAddressArea;
    }
    
    public String getMemberDeliveryAddressAddress() {
        return memberDeliveryAddressAddress;
    }

    public void setMemberDeliveryAddressAddress(String memberDeliveryAddressAddress) {
        this.memberDeliveryAddressAddress = memberDeliveryAddressAddress;
    }
    
    public String getMemberDeliveryAddressPostcode() {
        return memberDeliveryAddressPostcode;
    }

    public void setMemberDeliveryAddressPostcode(String memberDeliveryAddressPostcode) {
        this.memberDeliveryAddressPostcode = memberDeliveryAddressPostcode;
    }
    
    public Boolean getMemberDeliveryAddressIsDefault() {
        return memberDeliveryAddressIsDefault;
    }

    public void setMemberDeliveryAddressIsDefault(Boolean memberDeliveryAddressIsDefault) {
        this.memberDeliveryAddressIsDefault = memberDeliveryAddressIsDefault;
    }
    

}