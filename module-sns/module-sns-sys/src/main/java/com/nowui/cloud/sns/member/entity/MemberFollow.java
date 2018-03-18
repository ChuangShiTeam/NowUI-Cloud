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
@TableName(value = "sns_member_follow_info")
public class MemberFollow extends BaseEntity {

    /**
     * 会员关注编号
     */
    @Id
    @TableId
    @NotNull(message = "会员关注编号不能为空")
    @Length(max = 32, message = "会员关注编号长度超出限制")
    private String snsMemberFollowId;
    public static final String SNS_MEMBER_FOLLOW_ID = "snsMemberFollowId";

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

	public String getSnsMemberFollowId() {
		return snsMemberFollowId;
	}

	public void setSnsMemberFollowId(String snsMemberFollowId) {
		this.snsMemberFollowId = snsMemberFollowId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowMemberId() {
		return followMemberId;
	}

	public void setFollowMemberId(String followMemberId) {
		this.followMemberId = followMemberId;
	}

	public String getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(String followUserId) {
		this.followUserId = followUserId;
	}


}