package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员密码
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_password_info")
public class MemberPassword extends BaseEntity {

    /**
     * 会员密码编号
     */
    @TableId
    @TableField
    private String memberPasswordId;

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
     * 会员密码
     */
    @TableField
    private String memberPassword;


    public String getMemberPasswordId() {
        return memberPasswordId;
    }
    
    public void setMemberPasswordId(String memberPasswordId) {
        this.memberPasswordId = memberPasswordId;
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

    public String getMemberPassword() {
        return memberPassword;
    }
    
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }


}