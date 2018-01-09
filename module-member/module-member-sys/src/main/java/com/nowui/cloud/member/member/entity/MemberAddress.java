package com.nowui.cloud.member.member.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 会员地址
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "member_address_info")
@TableName(value = "member_address_info")
public class MemberAddress extends BaseEntity {

    /**
     * 会员地址编号
     */
    @Id
    @TableId
    @NotNull(message = "会员地址编号不能为空")
    @Length(max = 32, message = "会员地址编号长度超出限制")
    private String memberAddressId;
    public static final String MEMBER_ADDRESS_ID = "memberAddressId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 省
     */
    @Field
    @TableField
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";

    /**
     * 市
     */
    @Field
    @TableField
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";

    /**
     * 区
     */
    @Field
    @TableField
    @NotNull(message = "区不能为空")
    @Length(max = 50, message = "区长度超出限制")
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";

    /**
     * 详细地址
     */
    @Field
    @TableField
    @NotNull(message = "详细地址不能为空")
    @Length(max = 200, message = "详细地址长度超出限制")
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