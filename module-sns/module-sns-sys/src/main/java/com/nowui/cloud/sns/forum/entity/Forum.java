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

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 论坛信息
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "forum_info")
public class Forum extends BaseEntity {

    /**
     * 论坛编号
     */
	@Id
    @TableId
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 论坛名称
     */
    @TableField
    @NotNull(message = "论坛名称不能为空")
    @Length(max = 25, message = "论坛名称长度超出限制")
    private String forumName;
    public static final String FORUM_NAME = "forumName";
    
    /**
     * 版主(用户编号)
     */
    @TableField
    @NotNull(message = "版主(用户编号)不能为空")
    @Length(max = 32, message = "版主(用户编号)长度超出限制")
    private String forumModeratorUserId;
    public static final String FORUM_MODERATOR_USER_ID = "forumModeratorUserId";
    
    /**
     * 版主(会员编号)
     */
    @TableField
    @NotNull(message = "版主(会员编号)不能为空")
    @Length(max = 32, message = "版主(会员编号)长度超出限制")
    private String forumModeratorMemberId;
    public static final String FORUM_MODERATOR_MEMBER_ID = "forumModeratorMemberId";
    
    /**
     * 请求者的会员编号(用于查看论坛列表等类似接口)
     */
    @TableField(exist = false)
    @NotNull(message = "请求者的会员编号不能为空")
    @Length(max = 32, message = "请求者的会员编号长度超出限制")
    private String requestMemberId;
    public static final String REQUEST_MEMBER_ID = "requestMemberId";
    
    /**
     * 版主头像文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "版主头像文件路径不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 版主昵称
     */
    @TableField(exist = false)
    @NotNull(message = "版主昵称")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 版主会员签名
     */
    @TableField(exist = false)
    @NotNull(message = "版主的会员签名")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    
    /**
     * 论坛多媒体文件Id
     */
    @TableField
    @NotNull(message = "论坛多媒体文件Id不能为空")
    private String forumMediaId;
    public static final String FORUM_MEDIA_ID = "forumMediaId";
    
    /**
     * 论坛多媒体文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "论坛多媒体文件路径不能为空")
    private String forumMediaFilePath;
    public static final String FORUM_MEDIA_FILE_PATH = "forumMediaFilePath";

    /**
     * 论坛多媒体类型
     */
    @TableField
    @NotNull(message = "论坛多媒体类型不能为空")
    @Length(max = 25, message = "论坛多媒体类型长度超出限制")
    private String forumMediaType;
    public static final String FORUM_MEDIA_TYPE = "forumMediaType";

    /**
     * 论坛背景文件Id
     */
    @TableField
    @NotNull(message = "论坛背景文件Id不能为空")
    private String forumBackgroundMediaId;
    public static final String FORUM_BACKGROUND_MEDIA_ID = "forumBackgroundMediaId";
    
    /**
     * 论坛背景文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "论坛背景文件路径不能为空")
    private String forumBackgroundMediaFilePath;
    public static final String FORUM_BACKGROUND_MEDIA_FILE_PATH = "forumBackgroundMediaFilePath";

    /**
     * 论坛背景类型
     */
    @TableField
    @NotNull(message = "论坛背景类型不能为空")
    @Length(max = 32, message = "论坛背景类型长度超出限制")
    private String forumBackgroundMediaType;
    public static final String FORUM_BACKGROUND_MEDIA_TYPE = "forumBackgroundMediaType";

    /**
     * 位置
     */
    @TableField
    @NotNull(message = "位置不能为空")
    @Length(max = 200, message = "位置长度超出限制")
    private String forumLocation;
    public static final String FORUM_LOCATION = "forumLocation";

    /**
     * 论坛是否置顶
     */
    @TableField
    @NotNull(message = "论坛是否置顶不能为空")
    @Length(max = 1, message = "论坛是否置顶长度超出限制")
    private Boolean forumIsTop;
    public static final String FORUM_IS_TOP = "forumIsTop";

    /**
     * 论坛置顶级别
     */
    @TableField
    private Integer forumTopLevel;
    public static final String FORUM_TOP_LEVEL = "forumTopLevel";

    /**
     * 论坛置顶结束时间
     */
    @TableField
    private Date forumTopEndTime;
    public static final String FORUM_TOP_END_TIME = "forumTopEndTime";

    /**
     * 是否有效
     */
    @TableField
    @NotNull(message = "是否有效不能为空")
    private Boolean forumIsActive;
    public static final String FORUM_IS_ACTIVE = "forumIsActive";

    /**
     * 是否推荐
     */
    @TableField
    @NotNull(message = "是否推荐不能为空")
    private Boolean forumIsRecommend;
    public static final String FORUM_IS_RECOMAND = "forumIsRecommend";
    
    /**
     * 审核状态
     */
    @TableField
    @NotNull(message = "审核状态不能为空")
    @Length(max = 25, message = "审核状态长度超出限制")
    private String forumAuditStatus;
    public static final String FORUM_AUDIT_STATUS = "forumAuditStatus";
    
    /**
     * 审核内容
     */
    @TableField
    @Length(max = 500, message = "是否推荐长度超出限制")
    private String forumAuditContent;
    public static final String FORUM_AUDIT_CONTENT = "forumAuditContent";
    
    /**
     * 论坛简介
     */
    @TableField
    @NotNull(message = "论坛简介不能为空")
    @Length(max = 255, message = "论坛简介长度超出限制")
    private String forumDescription;
    public static final String FORUM_DESCRIPTION = "forumDescription";
    
    /**
     * 论坛排序
     */
    @TableField
    @NotNull(message = "论坛排序不能为空")
    @Length(max = 11, message = "论坛排序长度超出限制")
    private Integer forumSort;
    public static final String FORUM_SORT = "forumSort";
    
    /**
     * 会员是否关注此论坛
     */
    public static final String MEMBER_IS_FOLLOW_FORUM = "memberIsFollowForum";

    /**
     * 论坛的当日目前话题数量
     */
    public static final String FORUM_TODAY_TOPIC_COUNT = "forumTodayTopicCount";
    
    /**
     * 论坛的用户关注数量
     */
    public static final String FORUM_USER_FOLLOW_COUNT = "forumUserFollowCount";
   
    /**
     * 论坛编号列表
     */
    public static final String FORUM_ID_LIST = "forumIdList";
    
    /**
     * 论坛用户关注列表
     */
    public static final String FORUM_USER_FOLLOW_LIST = "forumUserFollowList";
    
    /**
     * 论坛用户关注列表
     */
    public static final String FORUM_USER_IS_MODERATOR = "forumUserIsModerator";
    
    /**
     */
    public static final String FORUM_REQUEST_USER_IS_FOLLOW = "forumRequestUserIsFollow";
    

    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getForumMediaId() {
        return getString(FORUM_MEDIA_ID);
    }
    
    public void setForumMediaId(String forumMediaId) {
        put(FORUM_MEDIA_ID, forumMediaId);
    }

    public String getForumMediaFilePath() {
        return getString(FORUM_MEDIA_FILE_PATH);
    }
    
    public void setForumMediaFilePath(String forumMediaFilePath) {
        put(FORUM_MEDIA_FILE_PATH, forumMediaFilePath);
    }

    public String getForumMediaType() {
        return getString(FORUM_MEDIA_TYPE);
    }
    
    public void setForumMediaType(String forumMediaType) {
        put(FORUM_MEDIA_TYPE, forumMediaType);
    }

    public String getForumBackgroundMediaId() {
        return getString(FORUM_BACKGROUND_MEDIA_ID);
    }
    
    public void setForumBackgroundMediaId(String forumBackgroundMediaId) {
        put(FORUM_BACKGROUND_MEDIA_ID, forumBackgroundMediaId);
    }
    
    public String getForumBackgroundMediaFilePath() {
        return getString(FORUM_BACKGROUND_MEDIA_FILE_PATH);
    }
    
    public void setForumBackgroundMediaFilePath(String forumBackgroundMediaFilePath) {
        put(FORUM_BACKGROUND_MEDIA_FILE_PATH, forumBackgroundMediaFilePath);
    }

    public String getForumBackgroundMediaType() {
        return getString(FORUM_BACKGROUND_MEDIA_TYPE);
    }
    
    public void setForumBackgroundMediaType(String forumBackgroundMediaType) {
        put(FORUM_BACKGROUND_MEDIA_TYPE, forumBackgroundMediaType);
    }

    public String getForumName() {
        return getString(FORUM_NAME);
    }
    
    public void setForumName(String forumName) {
        put(FORUM_NAME, forumName);
    }

    public String getForumDescription() {
        return getString(FORUM_DESCRIPTION);
    }
    
    public void setForumDescription(String forumDescription) {
        put(FORUM_DESCRIPTION, forumDescription);
    }
    
    public String getForumModeratorUserId() {
    	return getString(FORUM_MODERATOR_USER_ID);
	}

	public void setForumModeratorUserId(String forumModeratorUserId) {
		put(FORUM_MODERATOR_USER_ID, forumModeratorUserId);
	}

    public String getForumModeratorMemberId() {
        return getString(FORUM_MODERATOR_MEMBER_ID);
    }
    
    public void setForumModeratorMemberId(String forumModeratorMemberId) {
        put(FORUM_MODERATOR_MEMBER_ID, forumModeratorMemberId);
    }
    
    public String getRequestMemberId() {
        return getString(REQUEST_MEMBER_ID);
    }
    
    public void setRequestMemberId(String requestMemberId) {
        put(REQUEST_MEMBER_ID, requestMemberId);
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

	public String getForumLocation() {
        return getString(FORUM_LOCATION);
    }
    
    public void setForumLocation(String forumLocation) {
        put(FORUM_LOCATION, forumLocation);
    }

    public Integer getForumSort() {
        return getInteger(FORUM_SORT);
    }
    
    public void setForumSort(Integer forumSort) {
        put(FORUM_SORT, forumSort);
    }

    public Boolean getForumIsTop() {
        return getBoolean(FORUM_IS_TOP);
    }
    
    public void setForumIsTop(Boolean forumIsTop) {
        put(FORUM_IS_TOP, forumIsTop);
    }

    public Integer getForumTopLevel() {
        return getInteger(FORUM_TOP_LEVEL);
    }
    
    public void setForumTopLevel(Integer forumTopLevel) {
        put(FORUM_TOP_LEVEL, forumTopLevel);
    }

    public Date getForumTopEndTime() {
        return getDate(FORUM_TOP_END_TIME);
    }
    
    public void setForumTopEndTime(Date forumTopEndTime) {
        put(FORUM_TOP_END_TIME, forumTopEndTime);
    }

    public Boolean getForumIsActive() {
        return getBoolean(FORUM_IS_ACTIVE);
    }
    
    public void setForumIsActive(Boolean forumIsActive) {
        put(FORUM_IS_ACTIVE, forumIsActive);
    }

    public String getForumAuditContent() {
        return getString(FORUM_AUDIT_CONTENT);
    }
    
    public void setForumAuditContent(String forumAuditContent) {
        put(FORUM_AUDIT_CONTENT, forumAuditContent);
    }

    public String getForumAuditStatus() {
        return getString(FORUM_AUDIT_STATUS);
    }
    
    public void setForumAuditStatus(String forumAuditStatus) {
        put(FORUM_AUDIT_STATUS, forumAuditStatus);
    }

    public Boolean getForumIsRecommend() {
        return getBoolean(FORUM_IS_RECOMAND);
    }
    
    public void setForumIsRecommend(Boolean forumIsRecommend) {
        put(FORUM_IS_RECOMAND, forumIsRecommend);
    }


}