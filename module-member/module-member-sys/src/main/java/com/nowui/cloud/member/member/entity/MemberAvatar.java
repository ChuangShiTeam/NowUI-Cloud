package com.nowui.cloud.member.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 会员头像
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Component

@TableName(value = "member_avatar_info")
public class MemberAvatar extends BaseEntity {

    /**
     * 会员头像编号
     */
    @TableId
    @TableField
    private String memberAvatarId;

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
     * 会员头像文件编号
     */
    @TableField
    private String memberAvatarFileId;


    public String getMemberAvatarId() {
        return memberAvatarId;
    }
    
    public void setMemberAvatarId(String memberAvatarId) {
        this.memberAvatarId = memberAvatarId;
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

    public String getMemberAvatarFileId() {
        return memberAvatarFileId;
    }
    
    public void setMemberAvatarFileId(String memberAvatarFileId) {
        this.memberAvatarFileId = memberAvatarFileId;
    }


}