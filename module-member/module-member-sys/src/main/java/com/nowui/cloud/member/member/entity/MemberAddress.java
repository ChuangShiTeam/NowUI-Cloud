package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员地址
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_address_info")
public class MemberAddress extends BaseEntity {

    /**
     * 会员地址编号
     */
    @TableId
    @TableField
    private String memberAddressId;

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
     * 省
     */
    @TableField
    private String memberAddressProvince;

    /**
     * 市
     */
    @TableField
    private String memberAddressCity;

    /**
     * 区
     */
    @TableField
    private String memberAddressArea;

    /**
     * 详细地址
     */
    @TableField
    private String memberAddressAddress;


    public String getMemberAddressId() {
        return memberAddressId;
    }
    
    public void setMemberAddressId(String memberAddressId) {
        this.memberAddressId = memberAddressId;
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

    public String getMemberAddressProvince() {
        return memberAddressProvince;
    }
    
    public void setMemberAddressProvince(String memberAddressProvince) {
        this.memberAddressProvince = memberAddressProvince;
    }

    public String getMemberAddressCity() {
        return memberAddressCity;
    }
    
    public void setMemberAddressCity(String memberAddressCity) {
        this.memberAddressCity = memberAddressCity;
    }

    public String getMemberAddressArea() {
        return memberAddressArea;
    }
    
    public void setMemberAddressArea(String memberAddressArea) {
        this.memberAddressArea = memberAddressArea;
    }

    public String getMemberAddressAddress() {
        return memberAddressAddress;
    }
    
    public void setMemberAddressAddress(String memberAddressAddress) {
        this.memberAddressAddress = memberAddressAddress;
    }


}