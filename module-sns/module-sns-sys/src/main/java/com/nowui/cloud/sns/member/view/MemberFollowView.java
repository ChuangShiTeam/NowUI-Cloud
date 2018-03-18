package com.nowui.cloud.sns.member.view;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 关注会员信息视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "sns_member_follow_info")
public class MemberFollowView extends BaseView {

    /**
     * 会员关注编号
     */
    @KeyId
    @Field
    private String snsMemberFollowId;
    public static final String SNS_MEMBER_FOLLOW_ID = "snsMemberFollowId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @Field
    private String userId;
    public static final String USER_ID = "userId";
    
    /**
     * 用户头像图片路径
     */
    @Field
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 用户昵称
     */
    @Field
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";

    /**
     * 被关注会员编号
     */
    @Field
    private String followMemberId;
    public static final String FOLLOW_MEMBER_ID = "followMemberId";

    /**
     * 被关注用户编号
     */
    @Field
    private String followUserId;
    public static final String FOLLOW_USER_ID = "followUserId";

    /**
     * 被关注用户头像图片路径
     */
    @Field
    private String followUserAvatarFilePath;
    public static final String FOLLOW_USER_AVATAR_FILE_PATH = "followUserAvatarFilePath";
    
    /**
     * 被关注用户昵称
     */
    @Field
    private String followUserNickName;
    public static final String FOLLOW_USER_NICK_NAME= "followUserNickName";
    
    /**
     * 会员是否关注
     */
    public static final String MEMBER_IS_FOLLOW = "memberIsFollow";
    
    /**
     * 是否会员自己
     */
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

	public String getUserAvatarFilePath() {
		return userAvatarFilePath;
	}

	public void setUserAvatarFilePath(String userAvatarFilePath) {
		this.userAvatarFilePath = userAvatarFilePath;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
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

	public String getFollowUserAvatarFilePath() {
		return followUserAvatarFilePath;
	}

	public void setFollowUserAvatarFilePath(String followUserAvatarFilePath) {
		this.followUserAvatarFilePath = followUserAvatarFilePath;
	}

	public String getFollowUserNickName() {
		return followUserNickName;
	}

	public void setFollowUserNickName(String followUserNickName) {
		this.followUserNickName = followUserNickName;
	}

    
}