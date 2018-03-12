package com.nowui.cloud.sns.forum.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛用户关注
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "forum_user_follow_info")
public class ForumUserFollow extends BaseEntity {

    /**
     * 论坛用户关注编号
     */
	@Id
    @TableId
    @NotNull(message = "论坛用户关注编号不能为空")
    @Length(max = 32, message = "论坛用户关注编号长度超出限制")
    private String forumUserFollowId;
    public static final String FORUM_USER_FOLLOW_ID = "forumUserFollowId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 关注论坛的会员编号
     */
    @TableField
    @NotNull(message = "关注论坛的会员编号不能为空")
    @Length(max = 32, message = "关注论坛的会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 关注论坛的用户的头像文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "关注论坛的用户头像文件路径不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 关注论坛的用户的昵称
     */
    @TableField(exist = false)
    @NotNull(message = "关注论坛的用户的昵称")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 关注论坛的用户的会员签名
     */
    @TableField(exist = false)
    @NotNull(message = "关注论坛的用户的会员签名不能为空")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";

    /**
     * 论坛版主(id)
     */
    @TableField(exist = false)
    @NotNull(message = "论坛版主(id)不能为空")
    private String forumModeratorMemberId;
    public static final String FORUM_MODERATOR_MEMBER_ID = "forumModeratorMemberId";
    
    /**
     * 版主的头像文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "版主的头像文件路径")
    private String forumModeratorUserAvatarFilePath;
    public static final String FORUM_MODERATOR_AVATAR_FILE_PATH = "forumModeratorUserAvatarFilePath";
    
    /**
     * 版主的昵称
     */
    @TableField(exist = false)
    @NotNull(message = "版主的昵称")
    private String forumModeratorUserNickName;
    public static final String FORUM_MODERATOR_USER_NICKNAME = "forumModeratorUserNickName";
    
    /**
     * 版主的会员签名
     */
    @TableField(exist = false)
    @NotNull(message = "版主的会员签名不能为空")
    private String forumModeratorMemberSignature;
    public static final String FORUM_MODERATOR_MEMBER_SIGNATURE = "forumModeratorMemberSignature";
    
    /**
     * 论坛编号
     */
    @TableField
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";
    
    /**
     * 用户论坛是否置顶
     */
    @TableField
    @NotNull(message = "论坛是否置顶不能为空")
    @Length(max = 1, message = "论坛是否置顶长度超出限制")
    private Boolean forumUserFollowIsTop;
    public static final String FORUM_USER_FOLLOW_IS_TOP = "forumUserFollowIsTop";
    
    /**
     * 论坛编号列表
     */
    public static final String FORUM_ID_LSIT = "forumIdList";
    
    public String getForumUserFollowId() {
        return getString(FORUM_USER_FOLLOW_ID);
    }
    
    public void setForumUserFollowId(String forumUserFollowId) {
        put(FORUM_USER_FOLLOW_ID, forumUserFollowId);
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

	public String getUserAvatarFilePath() {
		return getString(USER_AVATAR_FILE_PATH);
	}

	public void setUserAvatarFilePath(String userAvatarFilePath) {
		put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
	}

	public String getUserNickName() {
		return getString(USER_NICKNAME);
	}

	public void setUserNickName(String userNickName) {
		put(USER_NICKNAME, userNickName);
	}

	public String getMemberSignature() {
		return getString(MEMBER_SIGNATURE);
	}

	public void setMemberSignature(String memberSignature) {
		put(MEMBER_SIGNATURE, memberSignature);
	}
	
	public String getForumModerator() {
        return getString(FORUM_MODERATOR_MEMBER_ID);
    }
    
    public void setForumModerator(String forumModerator) {
        put(FORUM_MODERATOR_MEMBER_ID, forumModerator);
    }
    
    public String getForumModeratorUserAvatarFilePath() {
        return getString(FORUM_MODERATOR_AVATAR_FILE_PATH);
    }
    
    public void setForumModeratorUserAvatarFilePath(String forumModeratorUserAvatarFilePath) {
        put(FORUM_MODERATOR_AVATAR_FILE_PATH, forumModeratorUserAvatarFilePath);
    }
    
    public String getForumModeratorUserNickName() {
        return getString(FORUM_MODERATOR_USER_NICKNAME);
    }
    
    public void setForumModeratorUserNickName(String forumModeratorUserNickName) {
        put(FORUM_MODERATOR_USER_NICKNAME, forumModeratorUserNickName);
    }
    
    public String getForumModeratorMemberSignature() {
        return getString(FORUM_MODERATOR_MEMBER_SIGNATURE);
    }
    
    public void setForumModeratorMemberSignature(String forumModeratorMemberSignature) {
        put(FORUM_MODERATOR_MEMBER_SIGNATURE, forumModeratorMemberSignature);
    }


    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public Boolean getForumUserFollowIsTop() {
        return getBoolean(FORUM_USER_FOLLOW_IS_TOP);
    }
    
    public void setForumUserFollowIsTop(Boolean forumUserFollowIsTop) {
        put(FORUM_USER_FOLLOW_IS_TOP, forumUserFollowIsTop);
    }

}