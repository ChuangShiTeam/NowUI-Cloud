package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员背景
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_background_info")
public class MemberBackground extends BaseEntity {

    /**
     * 会员背景编号
     */
    @TableId
    @TableField
    private String memberBackgroundId;

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
     * 会员背景文件编号
     */
    @TableField
    private String memberBackgroundFileId;


    public String getMemberBackgroundId() {
        return memberBackgroundId;
    }
    
    public void setMemberBackgroundId(String memberBackgroundId) {
        this.memberBackgroundId = memberBackgroundId;
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

    public String getMemberBackgroundFileId() {
        return memberBackgroundFileId;
    }
    
    public void setMemberBackgroundFileId(String memberBackgroundFileId) {
        this.memberBackgroundFileId = memberBackgroundFileId;
    }


}