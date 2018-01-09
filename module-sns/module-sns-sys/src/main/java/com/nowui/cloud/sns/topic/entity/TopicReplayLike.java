package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题回复
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "topic_replay_like_map")
@TableName(value = "topic_replay_like_map")
public class TopicReplayLike extends BaseEntity {

    /**
     * 话题回复id
     */
	@Id
    @TableId
    @NotNull(message = "话题回复id不能为空")
    @Length(max = 32, message = "话题回复id长度超出限制")
    private String topicReplyId;
    public static final String TOPIC_REPLY_ID = "topicReplyId";

    /**
     * 话题评论id
     */
    @Field
    @TableField
    @NotNull(message = "话题评论id不能为空")
    @Length(max = 32, message = "话题评论id长度超出限制")
    private String topicCommentId;
    public static final String TOPIC_COMMENT_ID = "topicCommentId";

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
     * 用户id
     */
    @Field
    @TableField
    @NotNull(message = "用户id不能为空")
    @Length(max = 32, message = "用户id长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getTopicReplyId() {
        return getString(TOPIC_REPLY_ID);
    }
    
    public void setTopicReplyId(String topicReplyId) {
        put(TOPIC_REPLY_ID, topicReplyId);
    }

    public String getTopicCommentId() {
        return getString(TOPIC_COMMENT_ID);
    }
    
    public void setTopicCommentId(String topicCommentId) {
        put(TOPIC_COMMENT_ID, topicCommentId);
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


}