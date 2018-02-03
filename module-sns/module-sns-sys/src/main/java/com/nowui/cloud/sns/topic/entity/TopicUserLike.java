package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 点赞话题关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_like_info")
public class TopicUserLike extends BaseEntity {

    /**
     * 用户话题点赞id
     */
	@Id
    @TableId
    @NotNull(message = "用户话题点赞id不能为空")
    @Length(max = 32, message = "用户话题点赞id长度超出限制")
    private String topicUserLikeId;
    public static final String TOPIC_USER_LIKE_ID = "topicUserLikeId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 关注人
     */
    @Field
    @TableField
    @NotNull(message = "关注人不能为空")
    @Length(max = 32, message = "关注人长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 话题编号
     */
    @Field
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    public static final String TOPIC_USER_LIKE_IS_SELF = "topicUserLikeIsSelf";

    public String getTopicUserLikeId() {
        return getString(TOPIC_USER_LIKE_ID);
    }
    
    public void setTopicUserLikeId(String topicUserLikeId) {
        put(TOPIC_USER_LIKE_ID, topicUserLikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }


}