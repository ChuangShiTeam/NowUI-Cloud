package com.nowui.cloud.sns.forum.view;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 论坛视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "forum_info")
public class ForumView extends BaseView {

    /**
     * 论坛编号
     */
    @KeyId
    @Field
    @NotNull(message = "论坛编号不能为空")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛名称
     */
    @Field
    @NotNull(message = "论坛名称不能为空")
    private String forumName;
    public static final String FORUM_NAME = "forumName";

    /**
     * 论坛多媒体文件路径
     */
    @Field
    @NotNull(message = "论坛多媒体文件路径不能为空")
    private String forumMediaFilePath;
    public static final String FORUM_MEDIA_FILE_PATH = "forumMediaFilePath";
    
    /**
     * 论坛多媒体类型
     */
    @Field
    @NotNull(message = "论坛多媒体类型不能为空")
    private String forumMediaType;
    public static final String FORUM_MEDIA_TYPE = "forumMediaType";

    /**
     * 论坛多媒体背景文件路径
     */
//    @Field
//    @NotNull(message = "论坛多媒体背景文件路径不能为空")
//    private String forumBackgroundMediaFilePath;
//    public static final String FORUM_BACKGROUND_MEDIA_FILE_PATH = "forumBackgroundMediaFilePath";

    /**
     * 论坛多媒体背景类型
     */
//    @Field
//    @NotNull(message = "论坛多媒体背景类型不能为空")
//    private String forumBackgroundMediaType;
//    public static final String FORUM_BACKGROUND_MEDIA_TYPE = "forumBackgroundMediaType";

    /**
     * 论坛多媒体背景文件List(fileId,filePath,fileType,sort)
     */
    @Field
    @NotNull(message = "论坛多媒体背景文件List不能为空")
    private JSONArray forumBackgroundMediaList;
    public static final String FORUM_BACKGROUND_MEDIA_LIST = "forumBackgroundMediaList";

    /**
     * 版主(会员编号)
     */
    @Field
    @NotNull(message = "版主(会员编号)不能为空")
    private String forumModeratorMemberId;
    public static final String FORUM_MODERATOR_MEMBER_ID = "forumModeratorMemberId";

    /**
     * 版主头像文件路径
     */
    @Field
    @NotNull(message = "版主头像文件路径")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";
    
    /**
     * 版主昵称
     */
    @Field
    @NotNull(message = "版主昵称")
    private String userNickName;
    public static final String USER_NICKNAME = "userNickName";
    
    /**
     * 版主会员签名
     */
    @Field
    @NotNull(message = "版主的会员签名")
    private String memberSignature;
    public static final String MEMBER_SIGNATURE = "memberSignature";
    

    /**
     * 位置
     */
    @Field
    @NotNull(message = "位置不能为空")
    private String forumLocation;
    public static final String FORUM_LOCATION = "forumLocation";

    /**
     * 论坛是否置顶
     */
    @Field
    @NotNull(message = "论坛是否置顶不能为空")
    private Boolean forumIsTop;
    public static final String FORUM_IS_TOP = "forumIsTop";

    /**
     * 论坛置顶级别
     */
    @Field
    @NotNull(message = "论坛置顶级别不能为空")
    private Integer forumTopLevel;
    public static final String FORUM_TOP_LEVEL = "forumTopLevel";

    /**
     * 论坛置顶结束时间
     */
    @Field
    @NotNull(message = "论坛置顶结束时间不能为空")
    private Date forumTopEndTime;
    public static final String FORUM_TOP_END_TIME = "forumTopEndTime";

    /**
     * 论坛是否有效
     */
    @Field
    @NotNull(message = "论坛是否有效不能为空")
    private Boolean forumIsActive;
    public static final String FORUM_IS_ACTIVE = "forumIsActive";

    /**
     * 是否推荐
     */
    @Field
    @NotNull(message = "是否推荐不能为空")
    private Boolean forumIsRecommend;
    public static final String FORUM_IS_RECOMMEND = "forumIsRecommend";

    /**
     * 审核状态
     */
    @Field
    @NotNull(message = "审核状态不能为空")
    private String forumAuditStatus;
    public static final String FORUM_AUDIT_STATUS = "forumAuditStatus";

    /**
     * 审核内容
     */
    @Field
    @NotNull(message = "审核内容不能为空")
    private String forumAuditContent;
    public static final String FORUM_AUDIT_CONTENT = "forumAuditContent";

    /**
     * 论坛简介
     */
    @Field
    @NotNull(message = "论坛简介不能为空")
    private String forumDescription;
    public static final String FORUM_DESCRIPTION = "forumDescription";

    /**
     * 论坛排序
     */
    @Field
    @NotNull(message = "论坛排序不能为空")
    private Integer forumSort;
    public static final String FORUM_SORT = "forumSort";

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

    public String getForumName() {
        return getString(FORUM_NAME);
    }

    public void setForumName(String forumName) {
        put(FORUM_NAME, forumName);
    }

    public String getForumMediaFilePath() {
        return getString(FORUM_MEDIA_FILE_PATH);
    }

    public void setForumMediaFilePath(String forumMediaFilePath) {
        put(FORUM_MEDIA_FILE_PATH, forumMediaFilePath);
    }

    public String getForumModeratorMemberId() {
        return getString(FORUM_MODERATOR_MEMBER_ID);
    }

    public void setForumModeratorMemberId(String forumModeratorMemberId) {
        put(FORUM_MODERATOR_MEMBER_ID, forumModeratorMemberId);
    }
    // 回来要删掉
//    public JSONObject getForumModeratorInfo() {
//        return getJSONObject(FORUM_MODERATOR_INFO);
//	}
//
//	public void setForumModeratorInfo(JSONObject forumModeratorInfo) {
//        put(FORUM_MODERATOR_INFO, forumModeratorInfo);
//	}
	

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

	public String getForumMediaType() {
        return getString(FORUM_MEDIA_TYPE);
    }

    public void setForumMediaType(String forumMediaType) {
        put(FORUM_MEDIA_TYPE, forumMediaType);
    }

//    public String getForumBackgroundMediaFilePath() {
//        return getString(FORUM_BACKGROUND_MEDIA_FILE_PATH);
//    }
//
//    public void setForumBackgroundMediaFilePath(String forumBackgroundMediaFilePath) {
//        put(FORUM_BACKGROUND_MEDIA_FILE_PATH, forumBackgroundMediaFilePath);
//    }
//
//    public String getForumBackgroundMediaType() {
//        return getString(FORUM_BACKGROUND_MEDIA_TYPE);
//    }
//
//    public void setForumBackgroundMediaType(String forumBackgroundMediaType) {
//        put(FORUM_BACKGROUND_MEDIA_TYPE, forumBackgroundMediaType);
//    }
    
	public JSONArray getForumBackgorundMediaList() {
        return getJSONArray(FORUM_BACKGROUND_MEDIA_LIST);
	}

	public void setForumBackgorundMediaList(JSONArray topicTipUserList) {
        put(FORUM_BACKGROUND_MEDIA_LIST, topicTipUserList);
	}

    public String getForumLocation() {
        return getString(FORUM_LOCATION);
    }

    public void setForumLocation(String forumLocation) {
        put(FORUM_LOCATION, forumLocation);
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

    public Boolean getForumIsRecommend() {
        return getBoolean(FORUM_IS_RECOMMEND);
    }

    public void setForumIsRecommend(Boolean forumIsRecommend) {
        put(FORUM_IS_RECOMMEND, forumIsRecommend);
    }

    public String getForumAuditStatus() {
        return getString(FORUM_AUDIT_STATUS);
    }

    public void setForumAuditStatus(String forumAuditStatus) {
        put(FORUM_AUDIT_STATUS, forumAuditStatus);
    }

    public String getForumAuditContent() {
        return getString(FORUM_AUDIT_CONTENT);
    }

    public void setForumAuditContent(String forumAuditContent) {
        put(FORUM_AUDIT_CONTENT, forumAuditContent);
    }

    public String getForumDescription() {
        return getString(FORUM_DESCRIPTION);
    }

    public void setForumDescription(String forumDescription) {
        put(FORUM_DESCRIPTION, forumDescription);
    }

    public Integer getForumSort() {
        return getInteger(FORUM_SORT);
    }

    public void setForumSort(Integer forumSort) {
        put(FORUM_SORT, forumSort);
    }

}