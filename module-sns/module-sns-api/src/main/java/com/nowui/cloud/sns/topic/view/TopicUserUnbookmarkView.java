package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题取消收藏视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_user_unbookmark_info")
public class TopicUserUnbookmarkView extends BaseView {

    /**
     * 话题取消收藏编号
     */
    @KeyId
    @Field
    @NotNull(message = "话题取消收藏编号不能为空")
    private String topicUserUnbookmarkId;
    public static final String TOPIC_USER_UNBOOKMARK_ID = "topicUserUnbookmarkId";

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
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";


    public String getTopicUserUnbookmarkId() {
        return getString(TOPIC_USER_UNBOOKMARK_ID);
    }

    public void setTopicUserUnbookmarkId(String topicUserUnbookmarkId) {
        put(TOPIC_USER_UNBOOKMARK_ID, topicUserUnbookmarkId);
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

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }


}