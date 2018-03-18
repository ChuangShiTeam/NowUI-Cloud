package com.nowui.cloud.sns.forum.view;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 论坛用户关注视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "forum_user_follow_info")
public class ForumUserFollowView extends BaseView {

    /**
     * 论坛用户关注编号
     */
    @KeyId
    @Field
    @NotNull(message = "论坛用户关注编号不能为空")
    private String forumUserFollowId;
    public static final String FORUM_USER_FOLLOW_ID = "forumUserFollowId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 关注论坛的会员编号
     */
    @Field
    @NotNull(message = "关注论坛的会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 关注论坛的用户的头像
     */
    @Field
    @NotNull(message = "关注论坛的头像")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 关注论坛的用户的昵称
     */
    @Field
    @NotNull(message = "关注论坛的用户的昵称")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 关注论坛的会员签名
     */
    @Field
    @NotNull(message = "关注论坛的会员签名")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    
    /**
     * 论坛版主(id)
     */
    @Field
    @NotNull(message = "论坛版主(id)不能为空")
    private String forumModeratorMemberId;
    public static final String FORUM_MODERATOR_MEMBER_ID = "forumModeratorMemberId";
    
    /**
     * 版主的头像文件路径
     */
    @Field
    @NotNull(message = "版主的头像文件路径")
    private String forumModeratorUserAvatarFilePath;
    public static final String FORUM_MODERATOR_AVATAR_FILE_PATH = "forumModeratorUserAvatarFilePath";
    
    /**
     * 版主的昵称
     */
    @Field
    @NotNull(message = "版主的昵称")
    private String forumModeratorUserNickName;
    public static final String FORUM_MODERATOR_USER_NICKNAME = "forumModeratorUserNickName";
    
    /**
     * 版主的会员签名
     */
    @Field
    @NotNull(message = "版主的会员签名不能为空")
    private String forumModeratorMemberSignature;
    public static final String FORUM_MODERATOR_MEMBER_SIGNATURE = "forumModeratorMemberSignature";
    
    /**
     * 论坛编号
     */
    @Field
    @NotNull(message = "论坛编号不能为空")
    private String forumId;
    public static final String FORUM_ID = "forumId";
    
    
    
    /**
     * 是否置顶
     */
    @Field
    @NotNull(message = "是否置顶不能为空")
    private Boolean forumUserFollowIsTop;
    public static final String FORUM_USER_FOLLOW_IS_TOP = "forumUserFollowIsTop";
    
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getForumId() {
		return forumId;
	}
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
	public String getForumUserFollowId() {
		return forumUserFollowId;
	}
	public void setForumUserFollowId(String forumUserFollowId) {
		this.forumUserFollowId = forumUserFollowId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public String getMemberSignature() {
		return memberSignature;
	}
	public void setMemberSignature(String memberSignature) {
		this.memberSignature = memberSignature;
	}
	public String getForumModeratorMemberId() {
		return forumModeratorMemberId;
	}
	public void setForumModeratorMemberId(String forumModeratorMemberId) {
		this.forumModeratorMemberId = forumModeratorMemberId;
	}
	public String getForumModeratorUserAvatarFilePath() {
		return forumModeratorUserAvatarFilePath;
	}
	public void setForumModeratorUserAvatarFilePath(String forumModeratorUserAvatarFilePath) {
		this.forumModeratorUserAvatarFilePath = forumModeratorUserAvatarFilePath;
	}
	public String getForumModeratorUserNickName() {
		return forumModeratorUserNickName;
	}
	public void setForumModeratorUserNickName(String forumModeratorUserNickName) {
		this.forumModeratorUserNickName = forumModeratorUserNickName;
	}
	public String getForumModeratorMemberSignature() {
		return forumModeratorMemberSignature;
	}
	public void setForumModeratorMemberSignature(String forumModeratorMemberSignature) {
		this.forumModeratorMemberSignature = forumModeratorMemberSignature;
	}
	public Boolean getForumUserFollowIsTop() {
		return forumUserFollowIsTop;
	}
	public void setForumUserFollowIsTop(Boolean forumUserFollowIsTop) {
		this.forumUserFollowIsTop = forumUserFollowIsTop;
	}
	


    

}