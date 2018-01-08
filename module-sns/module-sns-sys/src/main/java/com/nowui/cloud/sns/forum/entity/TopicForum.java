package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题论坛关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@TableName(value = "topic_forum_map")
public class TopicForum extends BaseEntity {

    /**
     * 话题论坛关联id
     */
    @TableId
    @NotNull(message = "话题论坛关联id不能为空")
    @Length(max = 32, message = "话题论坛关联id长度超出限制")
    private String topicForumMapId;
    public static final String TOPIC_FORUM_MAP_ID = "topicForumMapId";

    /**
     * 应用Id
     */
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛Id
     */
    @TableField
    @NotNull(message = "论坛Id不能为空")
    @Length(max = 32, message = "论坛Id长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 话题Id
     */
    @TableField
    @NotNull(message = "话题Id不能为空")
    @Length(max = 32, message = "话题Id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";


    public String getTopicForumMapId() {
        return getString(TOPIC_FORUM_MAP_ID);
    }
    
    public void setTopicForumMapId(String topicForumMapId) {
        put(TOPIC_FORUM_MAP_ID, topicForumMapId);
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

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }


}