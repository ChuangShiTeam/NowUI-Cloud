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
    @KeyId
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
     * 会员id
     */
    @Field
    @NotNull(message = "会员id不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 发评论的用户昵称
     */
    @Field
    @NotNull(message = "发评论的用户昵称不能为空")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 发评论的用户头像
     */
    @Field
    @NotNull(message = "发评论的用户头像不能为空")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";

    /**
     * 被回复的评论的id
     */
    @Field
    @NotNull(message = "被回复的评论的id不能为空")
    private String topicReplyCommentId;
    public static final String TOPIC_REPLY_COMMENT_ID = "topicReplyCommentId";

    /**
     * 被回复人的会员编号
     */
    @Field
    @NotNull(message = "被回复人不能为空")
    private String topicReplyMemberId;
    public static final String TOPIC_REPLY_MEMBER_ID = "topicReplyMemberId";
    
    /**
     * 被回复人的用户昵称
     */
    @Field
    @NotNull(message = "被回复人的用户昵称不能为空")
    private String topicReplyUserNickName;
    public static final String TOPIC_REPLY_USER_NICKNAME = "topicReplyUserNickName";

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

    public String getMemberId() {
        return getString(MEMBER_ID);
    }

    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }
    
    public String getUserNickName() {
        return getString(USER_NICK_NAME);
	}

	public void setUserNickName(String userNickName) {
        put(USER_NICK_NAME, userNickName);
	}

	public String getUserAvatarFilePath() {
        return getString(USER_AVATAR_FILE_PATH);
	}

	public void setUserAvatarFilePath(String userAvatarFilePath) {
        put(USER_AVATAR_FILE_PATH, userAvatarFilePath);
	}

    public String getTopicReplyCommentId() {
        return getString(TOPIC_REPLY_COMMENT_ID);
    }

    public void setTopicReplyCommentId(String topicReplyCommentId) {
        put(TOPIC_REPLY_COMMENT_ID, topicReplyCommentId);
    }

    public String getTopicReplyMemberId() {
        return getString(TOPIC_REPLY_MEMBER_ID);
    }

    public void setTopicReplyMemberId(String topicReplyMemberId) {
        put(TOPIC_REPLY_MEMBER_ID, topicReplyMemberId);
    }
    
    public String getTopicReplyUserNickName() {
        return getString(TOPIC_REPLY_USER_NICKNAME);
    }

    public void setTopicReplyUserNickName(String topicReplyUserNickName) {
        put(TOPIC_REPLY_USER_NICKNAME, topicReplyUserNickName);
    }

    public String getTopicCommentContent() {
        return getString(TOPIC_COMMENT_CONTENT);
    }

    public void setTopicCommentContent(String topicCommentContent) {
        put(TOPIC_COMMENT_CONTENT, topicCommentContent);
    }


}