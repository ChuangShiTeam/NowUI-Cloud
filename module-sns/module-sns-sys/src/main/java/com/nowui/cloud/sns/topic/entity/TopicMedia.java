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
 * 话题多媒体
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_media_info")
public class TopicMedia extends BaseEntity {

    /**
     * 话题多媒体编号
     */
	@Id
    @TableId
    @NotNull(message = "话题多媒体编号不能为空")
    @Length(max = 32, message = "话题多媒体编号长度超出限制")
    private String topicMediaId;
    public static final String TOPIC_MEDIA_ID = "topicMediaId";

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
     * 多媒体图片文件id
     */
    @TableField
    @NotNull(message = "多媒体图片文件id不能为空")
    @Length(max = 32, message = "多媒体图片文件id长度超出限制")
    private String topicMediaFileId;
    public static final String TOPIC_MEDIA_FILE_ID = "topicMediaFileId";
    
    /**
     * 多媒体图片文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "多媒体图片文件路径不能为空")
    private String topicMediaFilePath;
    public static final String TOPIC_MEDIA_FILE_PATH = "topicMediaFilePath";

    /**
     * 多媒体类型
     */
    @TableField
    @NotNull(message = "多媒体类型不能为空")
    @Length(max = 25, message = "多媒体类型长度超出限制")
    private String topicMediaType;
    public static final String TOPIC_MEDIA_TYPE = "topicMediaType";

    /**
     * 排序
     */
    @TableField
    @NotNull(message = "排序不能为空")
    @Length(max = 11, message = "排序长度超出限制")
    private Integer topicMediaSort;
    public static final String TOPIC_MEDIA_SORT = "topicMediaSort";

    public String getTopicMediaId() {
        return getString(TOPIC_MEDIA_ID);
    }
    
    public void setTopicMediaId(String topicMediaId) {
        put(TOPIC_MEDIA_ID, topicMediaId);
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

    public String getTopicMediaFileId() {
        return getString(TOPIC_MEDIA_FILE_ID);
    }
    
    public void setTopicMediaFileId(String topicMediaFileId) {
        put(TOPIC_MEDIA_FILE_ID, topicMediaFileId);
    }
    
    public String getTopicMediaFilePath() {
        return getString(TOPIC_MEDIA_FILE_PATH);
    }
    
    public void setTopicMediaFilePath(String topicMediaFilePath) {
        put(TOPIC_MEDIA_FILE_PATH, topicMediaFilePath);
    }

    public String getTopicMediaType() {
        return getString(TOPIC_MEDIA_TYPE);
    }
    
    public void setTopicMediaType(String topicMediaType) {
        put(TOPIC_MEDIA_TYPE, topicMediaType);
    }

    public Integer getTopicMediaSort() {
        return getInteger(TOPIC_MEDIA_SORT);
    }
    
    public void setTopicMediaSort(Integer topicMediaSort) {
        put(TOPIC_MEDIA_SORT, topicMediaSort);
    }
}