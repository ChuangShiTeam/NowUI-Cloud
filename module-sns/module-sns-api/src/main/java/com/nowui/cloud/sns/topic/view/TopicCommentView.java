package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题评论视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_comment_info")
public class TopicCommentView extends BaseView {

    /**
     * 话题评论id
     */
    @Field
    @NotNull(message = "话题评论id不能为空")
    private String topicCommentId;
    public static final String TOPIC_COMMENT_ID = "topicCommentId";

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
     * 用户id
     */
    @Field
    @NotNull(message = "用户id不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 被回复的评论的id
     */
    @Field
    @NotNull(message = "被回复的评论的id不能为空")
    private String topicReplyCommentId;
    public static final String TOPIC_REPLY_COMMENT_ID = "topicReplyCommentId";

    /**
     * 回复人
     */
    @Field
    @NotNull(message = "回复人不能为空")
    private String topicReplayUserId;
    public static final String TOPIC_REPLAY_USER_ID = "topicReplayUserId";

    /**
     * 话题评论内容
     */
    @Field
    @NotNull(message = "话题评论内容不能为空")
    private String topicCommentContent;
    public static final String TOPIC_COMMENT_CONTENT = "topicCommentContent";


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

    public String getTopicReplyCommentId() {
        return getString(TOPIC_REPLY_COMMENT_ID);
    }

    public void setTopicReplyCommentId(String topicReplyCommentId) {
        put(TOPIC_REPLY_COMMENT_ID, topicReplyCommentId);
    }

    public String getTopicReplayUserId() {
        return getString(TOPIC_REPLAY_USER_ID);
    }

    public void setTopicReplayUserId(String topicReplayUserId) {
        put(TOPIC_REPLAY_USER_ID, topicReplayUserId);
    }

    public String getTopicCommentContent() {
        return getString(TOPIC_COMMENT_CONTENT);
    }

    public void setTopicCommentContent(String topicCommentContent) {
        put(TOPIC_COMMENT_CONTENT, topicCommentContent);
    }


}