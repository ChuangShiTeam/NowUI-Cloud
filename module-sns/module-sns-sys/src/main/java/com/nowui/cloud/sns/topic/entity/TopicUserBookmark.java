package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题收藏
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_bookmark_info")
public class TopicUserBookmark extends BaseEntity {

    /**
     * 用户收藏id
     */
	@Id
    @TableId
    @NotNull(message = "用户收藏id不能为空")
    @Length(max = 32, message = "用户收藏id长度超出限制")
    private String topicUserBookmarkId;
    public static final String TOPIC_USER_BOOKMARK_ID = "topicUserBookmarkId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getTopicUserBookmarkId() {
        return getString(TOPIC_USER_BOOKMARK_ID);
    }
    
    public void setTopicUserBookmarkId(String topicUserBookmarkId) {
        put(TOPIC_USER_BOOKMARK_ID, topicUserBookmarkId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}