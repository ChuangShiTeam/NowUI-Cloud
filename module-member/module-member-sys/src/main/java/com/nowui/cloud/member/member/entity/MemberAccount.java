package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员账号
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_account_info")
public class MemberAccount extends BaseEntity {

    /**
     * 会员账号编号
     */
    @TableId
    @TableField
    private String memberAccountId;

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
     * 会员账号
     */
    @TableField
    private String memberAccount;


    public String getMemberAccountId() {
        return memberAccountId;
    }
    
    public void setMemberAccountId(String memberAccountId) {
        this.memberAccountId = memberAccountId;
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

    public String getMemberAccount() {
        return memberAccount;
    }
    
    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }


}