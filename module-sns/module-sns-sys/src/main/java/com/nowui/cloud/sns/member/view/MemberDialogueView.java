package com.nowui.cloud.sns.member.view;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

/**
 * 会员对话视图
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
@Document(collection = "member_dialogue_info")
public class MemberDialogueView extends BaseView {

    /**
     * 会员对话编号
     */
    @KeyId
    @Field
    private String memberDialogueId;
    public static final String MEMBER_DIALOGUE_ID = "memberDialogueId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 发起会员编号
     */
    @Field
    private String initiateMemberId;
    public static final String INITIATE_MEMBER_ID = "initiateMemberId";

    /**
     * 发起用户编号
     */
    @Field
    private String initiateUserId;
    public static final String INITIATE_USER_ID = "initiateUserId";
    
    /**
     * 发起用户头像图片路径
     */
    @Field
    private String initiateUserAvatarPath;
    public static final String INITIATE_USER_AVATAR_PATH = "initiateUserAvatarPath";
    
    /**
     * 发起用户昵称
     */
    @Field
    private String initiateUserNickName;
    public static final String INITIATE_USER_NICK_NAME = "initiateUserNickName";

    /**
     * 响应会员编号
     */
    @Field
    private String respondMemberId;
    public static final String RESPOND_MEMBER_ID = "respondMemberId";

    /**
     * 响应用户编号
     */
    @Field
    private String respondUserId;
    public static final String RESPOND_USER_ID = "respondUserId";
    
    /**
     * 响应用户头像图片路径
     */
    @Field
    private String respondUserAvatarPath;
    public static final String RESPOND_USER_AVATAR_PATH = "respondUserAvatarPath";
    
    /**
     * 响应用户昵称
     */
    @Field
    private String respondUserNickName;
    public static final String RESPOND_USER_NICK_NAME = "respondUserNickName";
    
	public String getMemberDialogueId() {
		return memberDialogueId;
	}
	public void setMemberDialogueId(String memberDialogueId) {
		this.memberDialogueId = memberDialogueId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getInitiateMemberId() {
		return initiateMemberId;
	}
	public void setInitiateMemberId(String initiateMemberId) {
		this.initiateMemberId = initiateMemberId;
	}
	public String getInitiateUserId() {
		return initiateUserId;
	}
	public void setInitiateUserId(String initiateUserId) {
		this.initiateUserId = initiateUserId;
	}
	public String getInitiateUserAvatarPath() {
		return initiateUserAvatarPath;
	}
	public void setInitiateUserAvatarPath(String initiateUserAvatarPath) {
		this.initiateUserAvatarPath = initiateUserAvatarPath;
	}
	public String getInitiateUserNickName() {
		return initiateUserNickName;
	}
	public void setInitiateUserNickName(String initiateUserNickName) {
		this.initiateUserNickName = initiateUserNickName;
	}
	public String getRespondMemberId() {
		return respondMemberId;
	}
	public void setRespondMemberId(String respondMemberId) {
		this.respondMemberId = respondMemberId;
	}
	public String getRespondUserId() {
		return respondUserId;
	}
	public void setRespondUserId(String respondUserId) {
		this.respondUserId = respondUserId;
	}
	public String getRespondUserAvatarPath() {
		return respondUserAvatarPath;
	}
	public void setRespondUserAvatarPath(String respondUserAvatarPath) {
		this.respondUserAvatarPath = respondUserAvatarPath;
	}
	public String getRespondUserNickName() {
		return respondUserNickName;
	}
	public void setRespondUserNickName(String respondUserNickName) {
		this.respondUserNickName = respondUserNickName;
	}

    
}