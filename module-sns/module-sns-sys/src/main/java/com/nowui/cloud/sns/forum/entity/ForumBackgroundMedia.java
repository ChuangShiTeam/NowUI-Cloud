package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛背景
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
@Component

@TableName(value = "forum_background_media_info")
public class ForumBackgroundMedia extends BaseEntity {

    /**
     * 论坛背景多媒体编号
     */
    @Id
    @TableId
    @NotNull(message = "论坛背景多媒体编号不能为空")
    @Length(max = 32, message = "论坛背景多媒体编号长度超出限制")
    private String forumBackgroundMediaId;
    public static final String FORUM_BACKGROUND_MEDIA_ID = "forumBackgroundMediaId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛编号
     */
    @TableField
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 论坛背景多媒体文件Id
     */
    @TableField
    @NotNull(message = "论坛背景多媒体文件Id不能为空")
    @Length(max = 32, message = "论坛背景多媒体文件Id长度超出限制")
    private String forumBackgroundMediaFileId;
    public static final String FORUM_BACKGROUND_MEDIA_FILE_ID = "forumBackgroundMediaFileId";

    /**
     * 论坛背景多媒体文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "论坛背景多媒体文件路径不能为空")
    @Length(max = 32, message = "论坛背景多媒体文件路径长度超出限制")
    private String forumBackgroundMediaFilePath;
    public static final String FORUM_BACKGROUND_MEDIA_FILE_PATH = "forumBackgroundMediaFilePath";
    
    /**
     * 论坛背景多媒体类型
     */
    @TableField
    @NotNull(message = "论坛背景多媒体类型不能为空")
    @Length(max = 32, message = "论坛背景多媒体类型长度超出限制")
    private String forumBackgroundMediaType;
    public static final String FORUM_BACKGROUND_MEDIA_TYPE = "forumBackgroundMediaType";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    @Length(max = 5, message = "排序长度超出限制")
    private Integer forumBackgroundMediaSort;
    public static final String FORUM_BACKGROUND_MEDIA_SORT = "forumBackgroundMediaSort";


    public String getForumBackgroundMediaId() {
        return getString(FORUM_BACKGROUND_MEDIA_ID);
    }
    
    public void setForumBackgroundMediaId(String forumBackgroundMediaId) {
        put(FORUM_BACKGROUND_MEDIA_ID, forumBackgroundMediaId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public String getForumBackgroundMediaFileId() {
        return getString(FORUM_BACKGROUND_MEDIA_FILE_ID);
    }
    
    public void setForumBackgroundMediaFileId(String forumBackgroundMediaFileId) {
        put(FORUM_BACKGROUND_MEDIA_FILE_ID, forumBackgroundMediaFileId);
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

    public Integer getForumBackgroundMediaSort() {
        return getInteger(FORUM_BACKGROUND_MEDIA_SORT);
    }
    
    public void setForumBackgroundMediaSort(Integer forumBackgroundMediaSort) {
        put(FORUM_BACKGROUND_MEDIA_SORT, forumBackgroundMediaSort);
    }


}