package com.nowui.cloud.member.member.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 会员默认头像
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_default_avatar_info")
public class MemberDefaultAvatar extends BaseEntity {

    /**
     * 会员默认头像编号
     */
    @TableId
    @TableField
    private String memberDefaultAvatarId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 头像文件编号
     */
    @TableField
    private String memberDefaultAvatarFileId;

    public String getMemberDefaultAvatarId() {
        return memberDefaultAvatarId;
    }
    
    public void setMemberDefaultAvatarId(String memberDefaultAvatarId) {
        this.memberDefaultAvatarId = memberDefaultAvatarId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMemberDefaultAvatarFileId() {
        return memberDefaultAvatarFileId;
    }

    public void setMemberDefaultAvatarFileId(String memberDefaultAvatarFileId) {
        this.memberDefaultAvatarFileId = memberDefaultAvatarFileId;
    }

}