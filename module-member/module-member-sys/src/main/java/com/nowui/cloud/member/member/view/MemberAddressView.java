package com.nowui.cloud.member.member.view;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员地址视图
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component
@Document(collection = "member_address_info")
public class MemberAddressView extends BaseView {

    /**
     * 会员地址编号
     */
    @KeyId
    @Field
    @NotNull(message = "会员地址编号不能为空")
    @Length(max = 32, message = "会员地址编号长度超出限制")
    private String memberAddressId;
    public static final String MEMBER_ADDRESS_ID = "memberAddressId";

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
     * 省
     */
    @Field
    @NotNull(message = "省不能为空")
    @Length(max = 50, message = "省长度超出限制")
    private String memberAddressProvince;
    public static final String MEMBER_ADDRESS_PROVINCE = "memberAddressProvince";

    /**
     * 市
     */
    @Field
    @NotNull(message = "市不能为空")
    @Length(max = 50, message = "市长度超出限制")
    private String memberAddressCity;
    public static final String MEMBER_ADDRESS_CITY = "memberAddressCity";

    /**
     * 区
     */
    @Field
    @NotNull(message = "区不能为空")
    @Length(max = 50, message = "区长度超出限制")
    private String memberAddressArea;
    public static final String MEMBER_ADDRESS_AREA = "memberAddressArea";

    /**
     * 详细地址
     */
    @Field
    @NotNull(message = "详细地址不能为空")
    @Length(max = 200, message = "详细地址长度超出限制")
    private String memberAddressAddress;
    public static final String MEMBER_ADDRESS_ADDRESS = "memberAddressAddress";


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