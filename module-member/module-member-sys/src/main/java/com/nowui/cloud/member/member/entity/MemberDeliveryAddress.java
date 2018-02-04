package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员收货地址
 *
 * @author xinqing
 *
 * 2018-01-14
 */
@Component
@TableName(value = "member_delivery_address")
public class MemberDeliveryAddress extends BaseEntity {

    /**
     * 会员收货地址编号
     */
    @Id
    @TableId
    @NotNull(message = "会员收货地址编号不能为空")
    @Length(max = 32, message = "会员收货地址编号长度超出限制")
    private String memberDeliveryAddressId;
    public static final String MEMBER_DELIVERY_ADDRESS_ID = "memberDeliveryAddressId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 姓名
     */
    @TableField
    @NotNull(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度超出限制")
    private String memberDeliveryAddressName;
    public static final String MEMBER_DELIVERY_ADDRESS_NAME = "memberDeliveryAddressName";

    /**
     * 手机号
     */
    @TableField
    @NotNull(message = "手机号不能为空")
    @Length(max = 20, message = "手机号长度超出限制")
    private String memberDeliveryAddressPhone;
    public static final String MEMBER_DELIVERY_ADDRESS_PHONE = "memberDeliveryAddressPhone";

    /**
     * 省
     */
    @TableField
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String memberDeliveryAddressProvince;
    public static final String MEMBER_DELIVERY_ADDRESS_PROVINCE = "memberDeliveryAddressProvince";

    /**
     * 市
     */
    @TableField
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String memberDeliveryAddressCity;
    public static final String MEMBER_DELIVERY_ADDRESS_CITY = "memberDeliveryAddressCity";

    /**
     * 区
     */
    @TableField
    @NotNull(message = "区不能为空")
    @Length(max = 50, message = "区长度超出限制")
    private String memberDeliveryAddressArea;
    public static final String MEMBER_DELIVERY_ADDRESS_AREA = "memberDeliveryAddressArea";

    /**
     * 详细地址
     */
    @TableField
    @NotNull(message = "详细地址不能为空")
    @Length(max = 200, message = "详细地址长度超出限制")
    private String memberDeliveryAddressAddress;
    public static final String MEMBER_DELIVERY_ADDRESS_ADDRESS = "memberDeliveryAddressAddress";

    /**
     * 邮政编码
     */
    @TableField
    @NotNull(message = "邮政编码不能为空")
    @Length(max = 10, message = "邮政编码长度超出限制")
    private String memberDeliveryAddressPostcode;
    public static final String MEMBER_DELIVERY_ADDRESS_POSTCODE = "memberDeliveryAddressPostcode";

    /**
     * 是否默认收货地址
     */
    @TableField
    @NotNull(message = "是否默认收货地址不能为空")
    @Length(max = 1, message = "是否默认收货地址长度超出限制")
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