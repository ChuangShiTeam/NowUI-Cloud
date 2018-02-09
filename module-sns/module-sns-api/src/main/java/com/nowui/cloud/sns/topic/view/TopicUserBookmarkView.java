package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题收藏视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_user_bookmark_info")
public class TopicUserBookmarkView extends BaseView {

    /**
     * 用户收藏id
     */
    @KeyId
    @Field
    @NotNull(message = "用户收藏id不能为空")
    private String topicUserBookmarkId;
    public static final String TOPIC_USER_BOOKMARK_ID = "topicUserBookmarkId";

    /**
     * 应用Id
     */
    @Field
    @NotNull(message = "应用Id不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题Id
     */
    @Field
    @NotNull(message = "话题Id不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 用户ID
     */
    @Field
    @NotNull(message = "用户ID不能为空")
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