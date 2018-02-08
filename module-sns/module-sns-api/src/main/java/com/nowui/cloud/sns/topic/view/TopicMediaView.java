package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题多媒体视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_media_info")
public class TopicMediaView extends BaseView {

    /**
     * 话题多媒体编号
     */
    @KeyId
    @Field
    @NotNull(message = "话题多媒体编号不能为空")
    private String topicMediaId;
    public static final String TOPIC_MEDIA_ID = "topicMediaId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题编号
     */
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 多媒体
     */
    @Field
    @NotNull(message = "多媒体不能为空")
    private String topicMedia;
    public static final String TOPIC_MEDIA = "topicMedia";

    /**
     * 多媒体类型
     */
    @Field
    @NotNull(message = "多媒体类型不能为空")
    private String topicMediaType;
    public static final String TOPIC_MEDIA_TYPE = "topicMediaType";

    /**
     * 排序
     */
    @Field
    @NotNull(message = "排序不能为空")
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

    public String getTopicMedia() {
        return getString(TOPIC_MEDIA);
    }

    public void setTopicMedia(String topicMedia) {
        put(TOPIC_MEDIA, topicMedia);
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