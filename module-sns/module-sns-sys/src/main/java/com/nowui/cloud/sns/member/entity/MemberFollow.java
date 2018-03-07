package com.nowui.cloud.sns.member.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 会员关注
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@TableName(value = "member_follow_info")
public class MemberFollow extends BaseEntity {

    /**
     * 会员关注编号
     */
    @Id
    @TableId
    @NotNull(message = "会员关注编号不能为空")
    @Length(max = 32, message = "会员关注编号长度超出限制")
    private String memberFollowId;
    public static final String MEMBER_FOLLOW_ID = "memberFollowId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 关注会员编号
     */
    @TableField
    @NotNull(message = "关注会员编号不能为空")
    @Length(max = 32, message = "关注会员编号长度超出限制")
    private String followMemberId;
    public static final String FOLLOW_MEMBER_ID = "followMemberId";

    /**
     * 关注用户编号
     */
    @TableField
    @NotNull(message = "关注用户编号不能为空")
    @Length(max = 32, message = "关注用户编号长度超出限制")
    private String followUserId;
    public static final String FOLLOW_USER_ID = "followUserId";
    
    public static final String MEMBER_IS_FOLLOW = "memberIsFollow";

    public static final String MEMBER_IS_SELF = "memberIsSelf";

    public String getMemberFollowId() {
        return getString(MEMBER_FOLLOW_ID);
    }

    public void setMemberFollowId(String memberFollowId) {
        put(MEMBER_FOLLOW_ID, memberFollowId);
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
    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }
    public String getFollowMemberId() {
        return getString(FOLLOW_MEMBER_ID);
    }

    public void setFollowMemberId(String followMemberId) {
        put(FOLLOW_MEMBER_ID, followMemberId);
    }
    public String getFollowUserId() {
        return getString(FOLLOW_USER_ID);
    }

    public void setFollowUserId(String followUserId) {
        put(FOLLOW_USER_ID, followUserId);
    }

}