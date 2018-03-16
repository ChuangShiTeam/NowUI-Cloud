package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员收货地址
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_delivery_address")
public class MemberDeliveryAddress extends BaseEntity {

    /**
     * 会员收货地址编号
     */
    @TableId
    @TableField
    private String memberDeliveryAddressId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 会员编号
     */
    @TableField
    private String memberId;

    /**
     * 姓名
     */
    @TableField
    private String memberDeliveryAddressName;

    /**
     * 手机号
     */
    @TableField
    private String memberDeliveryAddressPhone;

    /**
     * 省
     */
    @TableField
    private String memberDeliveryAddressProvince;

    /**
     * 市
     */
    @TableField
    private String memberDeliveryAddressCity;

    /**
     * 区
     */
    @TableField
    private String memberDeliveryAddressArea;

    /**
     * 详细地址
     */
    @TableField
    private String memberDeliveryAddressAddress;

    /**
     * 邮政编码
     */
    @TableField
    private String memberDeliveryAddressPostcode;

    /**
     * 是否默认收货地址
     */
    @TableField
    private Boolean memberDeliveryAddressIsDefault;


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