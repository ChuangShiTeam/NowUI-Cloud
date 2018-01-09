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
 * 话题提醒
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "topic_tip_info")
@TableName(value = "topic_tip_info")
public class TopicTip extends BaseEntity {

    /**
     * 话题提示id
     */
	@Id
    @TableId
    @NotNull(message = "话题提示id不能为空")
    @Length(max = 32, message = "话题提示id长度超出限制")
    private String topicTipId;
    public static final String TOPIC_TIP_ID = "topicTipId";

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
     * 话题Id
     */
    @Field
    @TableField
    @NotNull(message = "话题Id不能为空")
    @Length(max = 32, message = "话题Id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 发送用户
     */
    @Field
    @TableField
    @NotNull(message = "发送用户不能为空")
    @Length(max = 32, message = "发送用户长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getTopicTipId() {
        return getString(TOPIC_TIP_ID);
    }
    
    public void setTopicTipId(String topicTipId) {
        put(TOPIC_TIP_ID, topicTipId);
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