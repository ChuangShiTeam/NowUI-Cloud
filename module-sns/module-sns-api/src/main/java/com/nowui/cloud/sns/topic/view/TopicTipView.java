package com.nowui.cloud.sns.topic.view;

import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题提醒视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_tip_info")
public class TopicTipView extends BaseView {

    /**
     * 话题提醒编号
     */
    @Field
    @NotNull(message = "话题提醒编号不能为空")
    private String topicTipId;
    public static final String TOPIC_TIP_ID = "topicTipId";

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
     * 发送用户编号
     */
    @Field
    @NotNull(message = "发送用户编号不能为空")
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