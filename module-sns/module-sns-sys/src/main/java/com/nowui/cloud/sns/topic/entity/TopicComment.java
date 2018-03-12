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
 * 话题评论
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_comment_info")
public class TopicComment extends BaseEntity {

    /**
     * 话题评论id
     */
	@Id
    @TableId
    @NotNull(message = "话题评论id不能为空")
    @Length(max = 32, message = "话题评论id长度超出限制")
    private String topicCommentId;
    public static final String TOPIC_COMMENT_ID = "topicCommentId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";
    
    /**
     * 发评论的用户昵称
     */
    @TableField(exist = false)
    @NotNull(message = "发评论的用户昵称不能为空")
    @Length(max = 32, message = "发评论的用户昵称长度超出限制")
    private String userNickName;
    public static final String USER_NICK_NAME = "userNickName";
    
    /**
     * 发评论的用户头像
     */
    @TableField(exist = false)
    @NotNull(message = "发评论的用户头像不能为空")
    @Length(max = 200, message = "发评论的用户头像长度超出限制")
    private String userAvatarFilePath;
    public static final String USER_AVATAR_FILE_PATH = "userAvatarFilePath";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";
    
    /**
     * 话题评论内容
     */
    @TableField
    @NotNull(message = "话题评论内容不能为空")
    @Length(max = 255, message = "话题评论内容长度超出限制")
    private String topicCommentContent;
    public static final String TOPIC_COMMENT_CONTENT = "topicCommentContent";
    
    /**
     * 被回复评论id
     */
    @TableField
    @NotNull(message = "被回复评论id不能为空")
    @Length(max = 32, message = "被回复评论id长度超出限制")
    private String topicReplyCommentId;
    public static final String TOPIC_REPLY_COMMENT_ID = "topicReplyCommentId";
    
    /**
     * 被回复人的会员编号
     */
    @TableField
    @NotNull(message = "被回复人的会员编号不能为空")
    @Length(max = 32, message = "被回复人的会员编号长度超出限制")
    private String topicReplyMemberId;
    public static final String TOPIC_REPLY_MEMBER_ID = "topicReplyMemberId";

    /**
     * 被回复的用户昵称
     */
    @TableField(exist = false)
    @NotNull(message = "被回复的用户昵称不能为空")
    @Length(max = 32, message = "被回复的用户昵称长度超出限制")
    private String topicReplyUserNickName;
    public static final String TOPIC_REPLY_USER_NICKNAME = "topicReplyUserNickName";

    /**
     * 评论是否自己发的
     */
    public static final String TOPIC_COMMENT_IS_SELF = "topicCommentIsSelf";
    
    /**
     * 评论是否被用户点赞
     */
    public static final String TOPIC_COMMENT_IS_LIKE = "topicCommentIsLike";
    
    /**
     * 评论被点赞数
     */
    public static final String TOPIC_COMMENT_LIKE_COUNT = "topicCommentLikeCount";
    
    /**
     * 被排除的评论id (用于滚动加载)
     */
    public static final String EXCLUDE_COMMENT_ID_LIST = "excludeCommentIdList";
    
    

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
    
    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

    public String getTopicCommentContent() {
        return getString(TOPIC_COMMENT_CONTENT);
    }
    
    public void setTopicCommentContent(String topicCommentContent) {
        put(TOPIC_COMMENT_CONTENT, topicCommentContent);
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
    
    
}