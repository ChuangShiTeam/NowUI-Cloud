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
 * 话题多媒体
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "topic_media_map")
@TableName(value = "topic_media_map")
public class TopicMedia extends BaseEntity {

    /**
     * 话题多媒体关联id
     */
	@Id
    @TableId
    @NotNull(message = "话题多媒体关联id不能为空")
    @Length(max = 32, message = "话题多媒体关联id长度超出限制")
    private String topicMediaMapId;
    public static final String TOPIC_MEDIA_MAP_ID = "topicMediaMapId";

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
     * 话题id
     */
    @Field
    @TableField
    @NotNull(message = "话题id不能为空")
    @Length(max = 32, message = "话题id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 多媒体Id
     */
    @Field
    @TableField
    @NotNull(message = "多媒体Id不能为空")
    @Length(max = 25, message = "多媒体Id长度超出限制")
    private String topicMediaId;
    public static final String TOPIC_MEDIA_ID = "topicMediaId";

    /**
     * 多媒体类型
     */
    @Field
    @TableField
    @NotNull(message = "多媒体类型不能为空")
    @Length(max = 25, message = "多媒体类型长度超出限制")
    private String topicMediaType;
    public static final String TOPIC_MEDIA_TYPE = "topicMediaType";


    public String getTopicMediaMapId() {
        return getString(TOPIC_MEDIA_MAP_ID);
    }
    
    public void setTopicMediaMapId(String topicMediaMapId) {
        put(TOPIC_MEDIA_MAP_ID, topicMediaMapId);
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

    public String getTopicMediaId() {
        return getString(TOPIC_MEDIA_ID);
    }
    
    public void setTopicMediaId(String topicMediaId) {
        put(TOPIC_MEDIA_ID, topicMediaId);
    }

    public String getTopicMediaType() {
        return getString(TOPIC_MEDIA_TYPE);
    }
    
    public void setTopicMediaType(String topicMediaType) {
        put(TOPIC_MEDIA_TYPE, topicMediaType);
    }


}