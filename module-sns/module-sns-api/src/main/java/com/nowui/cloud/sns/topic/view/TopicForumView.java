package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题论坛视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_forum_map")
public class TopicForumView extends BaseView {

    /**
     * 话题论坛编号
     */
    @Field
    @NotNull(message = "话题论坛编号不能为空")
    private String topicForumId;
    public static final String TOPIC_FORUM_ID = "topicForumId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 论坛编号
     */
    @Field
    @NotNull(message = "论坛编号不能为空")
    private String forumId;
    public static final String FORUM_ID = "forumId";

    /**
     * 话题编号
     */
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";


    public String getTopicForumId() {
        return getString(TOPIC_FORUM_ID);
    }

    public void setTopicForumId(String topicForumId) {
        put(TOPIC_FORUM_ID, topicForumId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getForumId() {
        return getString(FORUM_ID);
    }

    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }

    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }


}