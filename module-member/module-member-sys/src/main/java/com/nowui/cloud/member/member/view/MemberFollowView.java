package com.nowui.cloud.member.member.view;

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
@Document(collection = "member_follow_info")
public class MemberFollowView extends BaseView {

    /**
     * 会员关注编号
     */
    @KeyId
    @Field
    private String memberFollowId;
    public static final String MEMBER_FOLLOW_ID = "memberFollowId";

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

    public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
    }

    public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
    }

    public String getUserNickName() {
        return getString(USER_NICK_NAME);
    }

    public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
    }

    public String getFollowUserAvatarFilePath() {
        return getString(FOLLOW_USER_AVATAR_FILE_PATH);
    }

    public void setFollowUserAvatarFilePath(String followUserAvatarFilePath) {
        put(FOLLOW_USER_AVATAR_FILE_PATH, followUserAvatarFilePath);
    }

    public String getFollowUserNickName() {
        return getString(FOLLOW_USER_NICK_NAME);
    }

    public void setFollowUserNickName(String followUserNickName) {
        put(FOLLOW_USER_NICK_NAME, followUserNickName);
    }
}