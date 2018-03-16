package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员昵称
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_nick_name_info")
public class MemberNickName extends BaseEntity {

    /**
     * 会员昵称编号
     */
    @TableId
    @TableField
    private String memberNickNameId;

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
     * 会员昵称
     */
    @TableField
    private String memberNickName;


    public String getMemberNickNameId() {
        return memberNickNameId;
    }
    
    public void setMemberNickNameId(String memberNickNameId) {
        this.memberNickNameId = memberNickNameId;
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

    public String getMemberNickName() {
        return memberNickName;
    }
    
    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }


}