package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题用户取消收藏关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@TableName(value = "topic_user_unbookmark_map")
public class TopicUserUnbookmark extends BaseEntity {

    /**
     * 用户话题取消收藏id
     */
    @TableId
    @NotNull(message = "用户话题取消收藏id不能为空")
    @Length(max = 32, message = "用户话题取消收藏id长度超出限制")
    private String userUnBookMarked;
    public static final String USER_UN_BOOK_MARKED = "userUnBookMarked";

    /**
     * 应用Id
     */
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题Id
     */
    @TableField
    @NotNull(message = "话题Id不能为空")
    @Length(max = 32, message = "话题Id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 用户ID
     */
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 11, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getUserUnBookMarked() {
        return getString(USER_UN_BOOK_MARKED);
    }
    
    public void setUserUnBookMarked(String userUnBookMarked) {
        put(USER_UN_BOOK_MARKED, userUnBookMarked);
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