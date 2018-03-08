package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题收藏
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component

@TableName(value = "topic_user_bookmark_info")
public class TopicUserBookmark extends BaseEntity {

    /**
     * 用户收藏id
     */
	@Id
    @TableId
    @NotNull(message = "用户收藏id不能为空")
    @Length(max = 32, message = "用户收藏id长度超出限制")
    private String topicUserBookmarkId;
    public static final String TOPIC_USER_BOOKMARK_ID = "topicUserBookmarkId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题编号
     */
    @TableField
    @NotNull(message = "话题编号不能为空")
    @Length(max = 32, message = "话题编号长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 会员编号
     */
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 发布动态的人的会员编号
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的人的会员编号不能为空")
    @Length(max = 32, message = "发布动态的人的会员编号长度超出限制")
    private String topicMemberId;
    public static final String TOPIC_MEMBER_ID = "topicMemberId";
    
    /**
     * 发布动态的人的头像路径
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的人的头像路径不能为空")
    private String topicUserAvatarFilePath;
    public static final String TOPIC_USER_AVATAR_FILE_PATH = "topicUserAvatarFilePath";
    
    /**
     * 发布动态的人的昵称
     */
    @TableField(exist = false)
    @NotNull(message = "发布动态的人的昵称不能为空")
    private String topicUserNickName;
    public static final String TOPIC_USER_NICK_NAME = "topicUserNickName";
    
    /**
     * 动态的内容
     */
    @TableField(exist = false)
    @NotNull(message = "动态的内容不能为空")
    private String topicSummary;
    public static final String TOPIC_SUMMARY = "topicSummary";
    
    /**
     * 动态的第一张图片文件路径
     */
    @TableField(exist = false)
    @NotNull(message = "动态的第一张图片文件路径不能为空")
    private String topicFirstMediaFilePath;
    public static final String TOPIC_FIRST_MEDIA_FILE_PATH = "topicFirstMediaFilePath";

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

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }
    
    public String getTopicMemberId() {
		return getString(TOPIC_MEMBER_ID);
	}

	public void setTopicMemberId(String topicMemberId) {
		put(TOPIC_MEMBER_ID, topicMemberId);
	}

	public String getTopicUserAvatarFilePath() {
		return getString(TOPIC_USER_AVATAR_FILE_PATH);
	}

	public void setTopicUserAvatarFilePath(String topicUserAvatarFilePath) {
		put(TOPIC_USER_AVATAR_FILE_PATH, topicUserAvatarFilePath);
	}

	public String getTopicUserNickName() {
		return getString(TOPIC_USER_NICK_NAME);
	}

	public void setTopicUserNickName(String topicUserNickName) {
		put(TOPIC_USER_NICK_NAME, topicUserNickName);
	}

	public String getTopicSummary() {
		return getString(TOPIC_SUMMARY);
	}

	public void setTopicSummary(String topicSummary) {
		put(TOPIC_SUMMARY, topicSummary);
	}

	public String getTopicFirstMediaFilePath() {
		return getString(TOPIC_FIRST_MEDIA_FILE_PATH);
	}

	public void setTopicFirstMediaFilePath(String topicFirstMedia) {
		put(TOPIC_FIRST_MEDIA_FILE_PATH, topicFirstMedia);
	}


}