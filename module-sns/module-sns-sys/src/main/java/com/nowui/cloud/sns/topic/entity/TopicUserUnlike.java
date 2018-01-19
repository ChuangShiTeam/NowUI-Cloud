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
 * 话题用户取消点赞关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "topic_user_unlike_map")
@TableName(value = "topic_user_unlike_map")
public class TopicUserUnlike extends BaseEntity {

    /**
     * 用户话题取消点赞id
     */
	@Id
    @TableId
    @NotNull(message = "用户话题取消点赞id不能为空")
    @Length(max = 32, message = "用户话题取消点赞id长度超出限制")
    private String userUnLikeId;
    public static final String USER_UN_LIKE_ID = "userUnLikeId";

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
     * 话题Id
     */
    @Field
    @TableField
    @NotNull(message = "话题Id不能为空")
    @Length(max = 32, message = "话题Id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";


    public String getUserUnLikeId() {
        return getString(USER_UN_LIKE_ID);
    }
    
    public void setUserUnLikeId(String userUnLikeId) {
        put(USER_UN_LIKE_ID, userUnLikeId);
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