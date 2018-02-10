package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题取消点赞视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_user_unlike_info")
public class TopicUserUnlikeView extends BaseView {

    /**
     * 话题取消点赞编号
     */
    @KeyId
    @Field
    @NotNull(message = "话题取消点赞编号不能为空")
    private String topicUserUnlikeId;
    public static final String TOPIC_USER_UNLIKE_ID = "topicUserUnlikeId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 取消点赞会员编号
     */
    @Field
    @NotNull(message = "取消点赞会员编号不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 话题编号
     */
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";


    public String getTopicUserUnlikeId() {
        return getString(TOPIC_USER_UNLIKE_ID);
    }

    public void setTopicUserUnlikeId(String topicUserUnlikeId) {
        put(TOPIC_USER_UNLIKE_ID, topicUserUnlikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }

    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }


}