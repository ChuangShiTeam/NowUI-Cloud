package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
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
@Document(indexName = "nowui", type = "forum_info")
@TableName(value = "forum_info")
public class Forum extends BaseEntity {

    /**
     * 论坛id
     */
	@Id
    @TableId
    @NotNull(message = "论坛id不能为空")
    @Length(max = 32, message = "论坛id长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 应用Id
     */
    @Field
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛多媒体id
     */
    @Field
    @TableField
    @NotNull(message = "论坛多媒体id不能为空")
    @Length(max = 32, message = "论坛多媒体id长度超出限制")
    private String forumMediaId;
    public static final String FORUM_MEDIA_ID = "forumMediaId";

    /**
     * 论坛多媒体类型
     */
    @Field
    @TableField
    @NotNull(message = "论坛多媒体类型不能为空")
    @Length(max = 25, message = "论坛多媒体类型长度超出限制")
    private String forumMediaType;
    public static final String FORUM_MEDIA_TYPE = "forumMediaType";

    /**
     * 论坛多媒体背景id
     */
    @Field
    @TableField
    @NotNull(message = "论坛多媒体背景id不能为空")
    @Length(max = 32, message = "论坛多媒体背景id长度超出限制")
    private String forumBackgroundMediaId;
    public static final String FORUM_BACKGROUND_MEDIA_ID = "forumBackgroundMediaId";

    /**
     * 论坛多媒体背景类型
     */
    @Field
    @TableField
    @NotNull(message = "论坛多媒体背景类型不能为空")
    @Length(max = 32, message = "论坛多媒体背景类型长度超出限制")
    private String forumBackgroundMediaType;
    public static final String FORUM_BACKGROUND_MEDIA_TYPE = "forumBackgroundMediaType";

    /**
     * 论坛名称
     */
    @Field
    @TableField
    @NotNull(message = "论坛名称不能为空")
    @Length(max = 25, message = "论坛名称长度超出限制")
    private String forumName;
    public static final String FORUM_NAME = "forumName";

    /**
     * 论坛简介
     */
    @Field
    @TableField
    @NotNull(message = "论坛简介不能为空")
    @Length(max = 255, message = "论坛简介长度超出限制")
    private String forumDescription;
    public static final String FORUM_DESCRIPTION = "forumDescription";

    /**
     * 版主(用户id)
     */
    @Field
    @TableField
    @NotNull(message = "版主(用户id)不能为空")
    @Length(max = 32, message = "版主(用户id)长度超出限制")
    private String forumModerator;
    public static final String FORUM_MODERATOR = "forumModerator";

    /**
     * 位置
     */
    @Field
    @TableField
    @NotNull(message = "位置不能为空")
    @Length(max = 200, message = "位置长度超出限制")
    private String forumTopicLocation;
    public static final String FORUM_TOPIC_LOCATION = "forumTopicLocation";

    /**
     * 论坛排序
     */
    @Field
    @TableField
    @NotNull(message = "论坛排序不能为空")
    @Length(max = 11, message = "论坛排序长度超出限制")
    private Integer forumSort;
    public static final String FORUM_SORT = "forumSort";

    /**
     * 论坛是否置顶
     */
    @Field
    @TableField
    @NotNull(message = "论坛是否置顶不能为空")
    @Length(max = 1, message = "论坛是否置顶长度超出限制")
    private Boolean forumTop;
    public static final String FORUM_TOP = "forumTop";

    /**
     * 论坛置顶级别
     */
    @Field
    @TableField
    @NotNull(message = "论坛置顶级别不能为空")
    @Length(max = 11, message = "论坛置顶级别长度超出限制")
    private Integer forumTopLevel;
    public static final String FORUM_TOP_LEVEL = "forumTopLevel";

    /**
     * 论坛置顶结束时间
     */
    @Field
    @TableField
    @NotNull(message = "论坛置顶结束时间不能为空")
    @Length(max = 0, message = "论坛置顶结束时间长度超出限制")
    private Date forumTopEndTime;
    public static final String FORUM_TOP_END_TIME = "forumTopEndTime";

    /**
     * 论坛是否有效
     */
    @Field
    @TableField
    @NotNull(message = "论坛是否有效不能为空")
    @Length(max = 1, message = "论坛是否有效长度超出限制")
    private Boolean forumIsActive;
    public static final String FORUM_IS_ACTIVE = "forumIsActive";

    /**
     * 是否关注
     */
    @Field
    @TableField
    @NotNull(message = "是否关注不能为空")
    @Length(max = 1, message = "是否关注长度超出限制")
    private Boolean forumIsFollow;
    public static final String FORUM_IS_FOLLOW = "forumIsFollow";

    /**
     * 是否推荐
     */
    @Field
    @TableField
    @NotNull(message = "是否推荐不能为空")
    @Length(max = 1, message = "是否推荐长度超出限制")
    private Boolean forumIsRecomand;
    public static final String FORUM_IS_RECOMAND = "forumIsRecomand";

    public static final String FORUM_TODAY_TOPIC_COUNT = "forumTodayTopicCount";
    
    public static final String FORUM_ID_LIST = "forumIdList";

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

    public String getForumModerator() {
        return getString(FORUM_MODERATOR);
    }
    
    public void setForumModerator(String forumModerator) {
        put(FORUM_MODERATOR, forumModerator);
    }

    public String getForumTopicLocation() {
        return getString(FORUM_TOPIC_LOCATION);
    }
    
    public void setForumTopicLocation(String forumTopicLocation) {
        put(FORUM_TOPIC_LOCATION, forumTopicLocation);
    }

    public Integer getForumSort() {
        return getInteger(FORUM_SORT);
    }
    
    public void setForumSort(Integer forumSort) {
        put(FORUM_SORT, forumSort);
    }

    public Boolean getForumTop() {
        return getBoolean(FORUM_TOP);
    }
    
    public void setForumTop(Boolean forumTop) {
        put(FORUM_TOP, forumTop);
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

    public Boolean getForumIsFollow() {
        return getBoolean(FORUM_IS_FOLLOW);
    }
    
    public void setForumIsFollow(Boolean forumIsFollow) {
        put(FORUM_IS_FOLLOW, forumIsFollow);
    }

    public Boolean getForumIsRecomand() {
        return getBoolean(FORUM_IS_RECOMAND);
    }
    
    public void setForumIsRecomand(Boolean forumIsRecomand) {
        put(FORUM_IS_RECOMAND, forumIsRecomand);
    }


}