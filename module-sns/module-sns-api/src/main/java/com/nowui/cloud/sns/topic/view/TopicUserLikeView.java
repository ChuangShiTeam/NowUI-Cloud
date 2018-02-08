package com.nowui.cloud.sns.topic.view;

import com.baomidou.mybatisplus.annotations.TableField;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 话题点赞视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "topic_user_like_info")
public class TopicUserLikeView extends BaseView {

    /**
     * 话题点赞编号
     */
    @KeyId
    @Field
    @NotNull(message = "话题点赞编号不能为空")
    private String topicUserLikeId;
    public static final String TOPIC_USER_LIKE_ID = "topicUserLikeId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";
    
    /**
     * 点赞人昵称
     */
    @Field
    @NotNull(message = "昵称不能为空")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 点赞人头像
     */
    @Field
    @NotNull(message = "头像不能为空")
    private String userAvatar;
    public static final String USER_AVATAR = "userAvatar";

    /**
     * 话题编号
     */
    @Field
    @NotNull(message = "话题编号不能为空")
    private String topicId;
    public static final String TOPIC_ID = "topicId";


    public String getTopicUserLikeId() {
        return getString(TOPIC_USER_LIKE_ID);
    }

    public void setTopicUserLikeId(String topicUserLikeId) {
        put(TOPIC_USER_LIKE_ID, topicUserLikeId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }
    
    public String getUserNickName() {
        return getString(USER_NICK_NAME);
	}

	public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
	}

	public String getUserAvatar() {
        return getString(USER_AVATAR);
	}

	public void setUserAvatar(String userAvatar) {
        put(USER_AVATAR, userAvatar);
	}

    public String getTopicId() {
        return getString(TOPIC_ID);
    }

    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }


}