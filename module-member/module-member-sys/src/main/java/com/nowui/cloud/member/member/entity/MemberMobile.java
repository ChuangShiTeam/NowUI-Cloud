package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员手机号码
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_mobile_info")
public class MemberMobile extends BaseEntity {

    /**
     * 会员手机号码编号
     */
    @TableId
    @TableField
    private String memberMobileId;

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
     * 会员手机号码
     */
    @TableField
    private String memberMobile;


    public String getMemberMobileId() {
        return memberMobileId;
    }
    
    public void setMemberMobileId(String memberMobileId) {
        this.memberMobileId = memberMobileId;
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

    public String getMemberMobile() {
        return memberMobile;
    }
    
    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }


}